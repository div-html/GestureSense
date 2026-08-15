import matplotlib.pyplot as plt
import numpy as np
# Histogram = A visual representation of the distribution
# of qualitative data.
# They group values into bins (intervals)
# and counts how many fall in each range
scores = np.random.normal(loc=80, scale=10, size=100) # loc is the median score and 
                                        #   scale is the how much it is going to deviate from the center
plt.hist(scores, bins=10,
                 color="lightgreen",
                 edgecolor="black")
plt.title("Exam Scores")
plt.xlabel("Score")
plt.ylabel("# of students")
plt.show()