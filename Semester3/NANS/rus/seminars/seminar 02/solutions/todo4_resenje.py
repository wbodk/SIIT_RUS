import pandas as pd
import matplotlib.pyplot as plt

from sklearn.linear_model import LinearRegression

# Ucitaj podatke
data = pd.read_csv("data/skincancer.csv")
x = data['Lat'].values.reshape(-1,1)
y = data['Mort'] 

# izracunaj nagib i presek za liniju koja se najbolja uklapa (best fit)
model = LinearRegression()
model.fit(x, y)

# prediktuj y za svako x
y_pred = model.predict(x)

# plotuj podatke i liniju koja se nabolje uklapa (best fit)
plt.plot(x, y, '.')
plt.plot(x, y_pred, 'b')
plt.title(f'slope: {model.coef_.item():.2f}, intercept: {model.intercept_.item():.2f}')
plt.show()