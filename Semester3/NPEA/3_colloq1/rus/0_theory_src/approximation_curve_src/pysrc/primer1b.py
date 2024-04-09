import numpy as np
import matplotlib.pyplot as plt
import math

F = np.array([1,2,3,4,5])
delta_x = np.array([3,6.5,9,11,15])

p = np.polyfit(F, delta_x, 1)
y_aproks = np.polyval(p, F)
plt.plot(F,delta_x)
plt.show()