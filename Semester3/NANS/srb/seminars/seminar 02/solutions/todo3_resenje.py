import pandas as pd
import matplotlib.pyplot as plt
import linreg_simple_resenje as linreg

# Ucitaj podatke
data = pd.read_csv("data/skincancer.csv")
x = data['Lat']
y = data['Mort'] 

# izracunaj nagib i presek za liniju koja se najbolja uklapa (best fit)
slope, intercept = linreg.fit(x, y)

# prediktuj y za svako x
y_pred = linreg.make_predictions(x, slope, intercept)

# plotuj podatke i liniju koja se nabolje uklapa (best fit)
plt.plot(x, y, '.')
plt.plot(x, y_pred, 'b')
plt.title(f'slope: {slope:.2f}, intercept: {intercept:.2f}')
plt.show()