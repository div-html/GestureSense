# Basics
import matplotlib.pyplot as plt
import numpy as np
x = np.array([2023, 2024, 2025, 2026])
y = np.array([15, 24, 30, 20])
y2 = np.array([17,23,34,2])
y3 = np.array([13,15,16,17])
# Customizing the plot function
line_style = dict(marker = ".",
                  markersize=10, #ms
                  markerfacecolor="black", #mfc
                  markeredgecolor="black",
                  linestyle="solid",
                  linewidth=2)
plt.title("Class Size", 
           fontsize=25,
           family="Arial",
           fontweight="bold",
           color="#2d4cfc")
plt.xlabel("Year",
           fontsize=18,
           family="Arial",
           fontweight="bold",
           color="#2dbefc")
plt.ylabel("Students",
           fontsize=18,
           family="Arial",
           fontweight="bold",
           color="#2dbefc")
plt.xticks(x)
plt.tick_params(axis="both",
                colors="#2dbefc")
plt.plot(x,y,color="red" ,**line_style) #unpacking the dictionary
plt.plot(x,y2, color="green",**line_style) 
plt.plot(x,y3, color="blue",**line_style)
plt.show()
