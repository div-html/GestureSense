"""
GestureSense - Smart Volume Controller (Windows)
Uses the MediaPipe Tasks API (HandLandmarker), since mp.solutions was removed.

  - Pinch thumb + index finger  -> volume goes DOWN
  - Spread thumb + index finger -> volume goes UP
  - Keep pinky DOWN             -> volume follows your fingers (active)
  - Raise pinky UP              -> volume is LOCKED
  - Press 'q'                   -> quit

Install:
    pip install opencv-python mediapipe numpy pycaw comtypes
"""

import math
import os
import time
import urllib.request
from ctypes import POINTER, cast

import cv2
import mediapipe as mp
import numpy as np
from comtypes import CLSCTX_ALL
from mediapipe.tasks import python as mp_python
from mediapipe.tasks.python import vision
from pycaw.pycaw import AudioUtilities, IAudioEndpointVolume

# ----------------------------- Configuration ------------------------------
CAM_INDEX = 0
FRAME_W, FRAME_H = 1280, 720

MODEL_URL = (
    "https://storage.googleapis.com/mediapipe-models/hand_landmarker/"
    "hand_landmarker/float16/1/hand_landmarker.task"
)
MODEL_PATH = os.path.join(os.path.dirname(os.path.abspath(__file__)), "hand_landmarker.task")

DETECT_CONF = 0.7
PRESENCE_CONF = 0.7
TRACK_CONF = 0.7

# Pinch distance / palm size -> these ratios map to 0% and 100% volume
RATIO_MIN = 0.25   # fingers touching
RATIO_MAX = 1.40   # fingers fully spread

SMOOTHING = 0.25   # 0..1, lower = smoother but slower
# --------------------------------------------------------------------------

# Hand skeleton connections (21 landmarks)
HAND_CONNECTIONS = [
    (0, 1), (1, 2), (2, 3), (3, 4),          # thumb
    (0, 5), (5, 6), (6, 7), (7, 8),          # index
    (5, 9), (9, 10), (10, 11), (11, 12),     # middle
    (9, 13), (13, 14), (14, 15), (15, 16),   # ring
    (13, 17), (17, 18), (18, 19), (19, 20),  # pinky
    (0, 17),                                  # palm edge
]


def ensure_model():
    if not os.path.exists(MODEL_PATH):
        print("Downloading hand landmarker model (one-time, ~8 MB)...")
        urllib.request.urlretrieve(MODEL_URL, MODEL_PATH)
        print("Model saved to", MODEL_PATH)


def get_volume_interface():
    """Return IAudioEndpointVolume (works with old and new pycaw)."""
    speakers = AudioUtilities.GetSpeakers()
    if hasattr(speakers, "EndpointVolume"):  # newer pycaw
        return speakers.EndpointVolume
    interface = speakers.Activate(IAudioEndpointVolume._iid_, CLSCTX_ALL, None)
    return cast(interface, POINTER(IAudioEndpointVolume))


def dist(p1, p2):
    return math.hypot(p2[0] - p1[0], p2[1] - p1[1])


def draw_volume_bar(img, vol_percent, locked):
    x1, y1, x2, y2 = 50, 150, 85, 400
    bar_y = int(np.interp(vol_percent, [0, 100], [y2, y1]))
    color = (0, 165, 255) if locked else (0, 255, 0)
    cv2.rectangle(img, (x1, y1), (x2, y2), (255, 255, 255), 3)
    cv2.rectangle(img, (x1, bar_y), (x2, y2), color, cv2.FILLED)
    cv2.putText(img, f"{int(vol_percent)}%", (x1 - 10, y2 + 40),
                cv2.FONT_HERSHEY_SIMPLEX, 1, color, 2)


def draw_hand(img, lm):
    for a, b in HAND_CONNECTIONS:
        cv2.line(img, lm[a], lm[b], (255, 255, 255), 2)
    for p in lm:
        cv2.circle(img, p, 4, (0, 200, 255), cv2.FILLED)


def main():
    ensure_model()
    volume = get_volume_interface()

    options = vision.HandLandmarkerOptions(
        base_options=mp_python.BaseOptions(model_asset_path=MODEL_PATH),
        running_mode=vision.RunningMode.VIDEO,
        num_hands=1,
        min_hand_detection_confidence=DETECT_CONF,
        min_hand_presence_confidence=PRESENCE_CONF,
        min_tracking_confidence=TRACK_CONF,
    )
    landmarker = vision.HandLandmarker.create_from_options(options)

    cap = cv2.VideoCapture(CAM_INDEX)
    cap.set(cv2.CAP_PROP_FRAME_WIDTH, FRAME_W)
    cap.set(cv2.CAP_PROP_FRAME_HEIGHT, FRAME_H)
    if not cap.isOpened():
        raise RuntimeError("Could not open webcam. Check CAM_INDEX.")

    smooth_vol = volume.GetMasterVolumeLevelScalar() * 100
    locked = True
    prev_time = 0
    start = time.monotonic()

    while True:
        ok, img = cap.read()
        if not ok:
            break

        img = cv2.flip(img, 1)
        h, w, _ = img.shape
        rgb = cv2.cvtColor(img, cv2.COLOR_BGR2RGB)

        mp_image = mp.Image(image_format=mp.ImageFormat.SRGB, data=rgb)
        timestamp_ms = int((time.monotonic() - start) * 1000)
        result = landmarker.detect_for_video(mp_image, timestamp_ms)

        if result.hand_landmarks:
            lm = [(int(p.x * w), int(p.y * h)) for p in result.hand_landmarks[0]]
            draw_hand(img, lm)

            thumb_tip, index_tip = lm[4], lm[8]
            wrist, middle_mcp = lm[0], lm[9]
            pinky_tip, pinky_pip = lm[20], lm[18]

            palm_size = max(dist(wrist, middle_mcp), 1)
            ratio = dist(thumb_tip, index_tip) / palm_size

            locked = pinky_tip[1] < pinky_pip[1]  # pinky up => locked

            cx, cy = (thumb_tip[0] + index_tip[0]) // 2, (thumb_tip[1] + index_tip[1]) // 2
            cv2.circle(img, thumb_tip, 10, (255, 0, 255), cv2.FILLED)
            cv2.circle(img, index_tip, 10, (255, 0, 255), cv2.FILLED)
            cv2.line(img, thumb_tip, index_tip, (255, 0, 255), 3)
            cv2.circle(img, (cx, cy), 8,
                       (0, 0, 255) if ratio <= RATIO_MIN else (0, 255, 0), cv2.FILLED)

            if not locked:
                target = float(np.interp(ratio, [RATIO_MIN, RATIO_MAX], [0, 100]))
                smooth_vol += (target - smooth_vol) * SMOOTHING
                smooth_vol = float(np.clip(smooth_vol, 0, 100))
                volume.SetMasterVolumeLevelScalar(smooth_vol / 100, None)

            status = "LOCKED (pinky up)" if locked else "ADJUSTING (pinky down)"
            cv2.putText(img, status, (50, 100), cv2.FONT_HERSHEY_SIMPLEX, 0.9,
                        (0, 165, 255) if locked else (0, 255, 0), 2)
        else:
            cv2.putText(img, "No hand detected", (50, 100),
                        cv2.FONT_HERSHEY_SIMPLEX, 0.9, (0, 0, 255), 2)

        draw_volume_bar(img, smooth_vol, locked)

        now = time.time()
        fps = 1 / (now - prev_time) if prev_time else 0
        prev_time = now
        cv2.putText(img, f"FPS: {int(fps)}", (w - 150, 40),
                    cv2.FONT_HERSHEY_SIMPLEX, 0.9, (255, 255, 0), 2)

        cv2.imshow("GestureSense - Volume Controller", img)
        if cv2.waitKey(1) & 0xFF == ord("q"):
            break

    cap.release()
    landmarker.close()
    cv2.destroyAllWindows()


if __name__ == "__main__":
    main()