import seaborn as sb
import pandas as pd
from sklearn.linear_model import LinearRegression    
sb.set(font_scale=1.)
import warnings
warnings.filterwarnings("ignore")

import line_pretpostavke as line


if __name__ == '__main__':
    df = pd.read_csv('data/housing.csv', sep=',')
    x = df['lotsize(m^2)'].values.reshape(-1,1)
    y = df['price']
    
    model = LinearRegression()
    model.fit(x, y)
    y_pred = model.predict(x)

    # L.I.N.E. pretpostavke
    # L - pretpostavka vazi
    line.linear_assumption(model, x, y)

    # I - pretpostavka vazi
    autocorrelation, dw_value = line.independence_of_errors_assumption(model, x, y)
    if autocorrelation is None: print('Nema zavisnosti (ili vrlo malo).')
    if autocorrelation == 'negative': print('Negativna zavisnost.')
    if autocorrelation == 'positive': print('Pozitivna zavisnost.')
    print('Durbin-Watson value:', dw_value)

    # N - iako se dobije da nisu reziduali nisu normalno distribuirani (navodi da pretpostavka ne vazi), 
    # ovu pretpostavku mozemo da zanemarimo jer imamo puno podataka.
    dist_type, p_value = line.normality_of_errors_assumption(model, x, y)
    if dist_type == 'normal': print('Reziduali su normalno distribuirani.')
    if dist_type == 'non-normal': print('Reziduali nisu normalno distribuirani.')
    print(p_value)

    # E - pretpostavka vazi
    dist_type, p_value = line.equal_variance_assumption(model, x, y)
    if dist_type == 'equal': print('Jednaka varijansa.')
    if dist_type == 'non-equal': print('Nije jednaka varijansa.')
    print(p_value)

    # NOTE: metod najmanjih kvadrata jeste prikladan u ovom zadatku