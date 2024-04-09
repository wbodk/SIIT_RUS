import numpy as np
import matplotlib.pyplot as plt

#%% Primer 1.a
F = np.array([1,2,3,4,5])
delta_x = np.array([3,6,9,12,15])

p = np.polyfit(F, delta_x, 1)
y_aproks = np.polyval(p, F)
plt.plot(F,delta_x)
plt.show()

sila = np.polyval(p, 7)
print(sila)