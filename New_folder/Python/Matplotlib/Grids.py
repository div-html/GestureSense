import matplotlib.pyplot as plt
import numpy as np
x = [1,2,3,4,5]
y = [5,10,15,20,25]
plt.grid(axis = "x", #only vertical lines
         linewidth=2,
         color="lightgray",
         linestyle="solid") 
plt.grid(axis = "y",
         linewidth=2,
         color="black")
plt.plot(x,y,marker = ".",
             markersize=10, #ms
             markerfacecolor="black", #mfc
             markeredgecolor="black")
plt.title("Gay Plot", 
          fontsize=20,
          family="Arial")
plt.xlabel("Just Gay",
           fontsize=15,
           fontweight=20)
plt.ylabel("More gay",
           fontsize=20,
           fontweight=40)
plt.xticks(x)
plt.yticks(y)

plt.show()