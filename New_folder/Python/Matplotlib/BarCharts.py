import matplotlib.pyplot as plt
import numpy as np
categories = np.array(["Grains", "Fruit", "Vegetables", "Protein", "Dairy", "Sweets"])
values = np.array([4,3,2,5,3,6])
plt.bar(categories, values, 
        color="Red",
        )
# for horizontal bar chart then use barh
plt.title("Daily consumption")
plt.xlabel("Categories")
plt.ylabel("Consumption Rate")
plt.show()