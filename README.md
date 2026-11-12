# GestureSense 
### Smart Volume Controller Using Hand Gestures

Control your Windows system volume without touching your keyboard or mouse. GestureSense uses real-time hand tracking to translate simple finger movements into volume adjustments.

Built using Python, OpenCV, and Google's MediaPipe Tasks API.

---

##  What is GestureSense?

GestureSense is a computer vision project that lets you control your laptop's master volume using hand gestures captured through your webcam.

Instead of reaching for the volume keys, simply move your thumb and index finger to adjust the sound. Raise your pinky to lock the current volume and lower it to resume control.

The idea is simple: **your hand becomes your volume controller.**

##  Features

- Real-time hand tracking using MediaPipe.
- Detects 21 hand landmarks.
- Controls actual Windows master volume.
- Pinch and spread gestures for volume adjustment.
- Pinky gesture to lock and unlock volume.
- Palm-size normalization for more consistent gesture recognition.
- Smooth volume transitions using exponential smoothing.
- Live volume percentage and visual volume bar.
- FPS counter for performance monitoring.
- Automatic download of the hand landmark model.

##  Gesture Controls

| Gesture | Action |
|---|---|
| 🤏 Thumb + index close | Decrease volume |
| 🖐️ Thumb + index spread | Increase volume |
| ☝️ Pinky raised | Lock volume |
| 👇 Pinky lowered | Activate volume control |
| Q | Exit application |

**How the lock works:** Raising the pinky freezes volume adjustments. Lowering it allows the thumb-index distance to control volume again.

##  Tech Stack

| Technology | Purpose |
|---|---|
| Python | Core application logic |
| OpenCV | Webcam access and image processing |
| MediaPipe Tasks | Hand landmark detection |
| NumPy | Volume interpolation and smoothing |
| Pycaw | Windows master volume control |
| COM / comtypes | Windows audio interface communication |

##  How It Works

1. OpenCV captures live frames from the laptop webcam.
2. Frames are converted into RGB format for MediaPipe.
3. MediaPipe HandLandmarker detects 21 hand landmarks.
4. The application extracts the thumb, index finger, wrist, middle finger base, and pinky landmarks.
5. Euclidean distance calculates the separation between the thumb and index finger.
6. The distance is normalized using palm size to make tracking more consistent.
7. The pinky position determines whether volume control is locked.
8. NumPy interpolation maps the normalized distance to a volume percentage.
9. Exponential smoothing reduces sudden volume changes.
10. Pycaw updates the Windows master volume.

##  Project Structure

```text
GestureSense/
│
├── main.py
├── requirements.txt
├── README.md
└── hand_landmarker.task
```

`hand_landmarker.task` is downloaded automatically the first time the application runs.

## Installation and Setup

### 1. Clone the repository

```bash
git clone https://github.com/YOUR-USERNAME/GestureSense.git
cd GestureSense
```

### 2. Create a virtual environment

```bash
python -m venv .venv
```

Activate it on Windows:

```powershell
.\.venv\Scripts\Activate.ps1
```

### 3. Install dependencies

```bash
pip install -r requirements.txt
```

### 4. Run the application

```bash
python main.py
```

Allow webcam access if Windows requests permission.

### 5. Start controlling your volume

- Show your hand to the webcam.
- Lower your pinky to activate volume control.
- Move your thumb and index finger closer or farther apart.
- Raise your pinky whenever you want to freeze the volume.
- Press `Q` to close the application.

##  Dependencies

```text
opencv-python
mediapipe
numpy
pycaw
comtypes
```

## Requirements and Limitations

- Windows operating system (Pycaw is used for Windows audio control).
- Working webcam.
- Python environment compatible with the installed MediaPipe release.
- Adequate lighting for reliable hand detection.
- Pinky lock detection currently uses vertical landmark positions, so hand orientation can affect accuracy.

##  Future Improvements

Some ideas I'd like to explore:

- [ ] Add mute and unmute gestures.
- [ ] Add media playback controls.
- [ ] Add brightness control.
- [ ] Support two-hand gestures.
- [ ] Build a graphical user interface.
- [ ] Add user-specific gesture calibration.
- [ ] Improve gesture recognition across different hand orientations.
- [ ] Add individual application volume control.

##  Learning Outcomes

This project explores practical applications of:

- Computer vision and image processing.
- Real-time hand landmark detection.
- Coordinate geometry and Euclidean distance.
- Data normalization and interpolation.
- Exponential smoothing.
- Hardware interaction through software APIs.

---

**GestureSense** — Making everyday computer interactions a little more natural.

*Built with Python and a little curiosity.*
