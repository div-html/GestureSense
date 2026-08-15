import matplotlib.pyplot as plt
import numpy as np
categories=np.array(["Freshman", "Sophomores", "Juniors", "Seniors"])
values= np.array([300, 250, 275, 190])
colors=["Red", "Yellow", "Blue", "Green"]
plt.pie(values, labels=categories,
                autopct="%1.1f%%",
                colors=colors,
                explode=[0,0,0,0.1],
                shadow=True,
                startangle=80)
plt.title("Chad Kewlegge")
plt.show()