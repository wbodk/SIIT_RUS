import pandas as pd
from sklearn.linear_model import LinearRegression    
import warnings
warnings.filterwarnings("ignore")

import line_pretpostavke as line


if __name__ == '__main__':
    df = pd.read_csv('data/skincancer.csv', sep=',')
    x = df['Lat'].values.reshape(-1,1)
    y = df['Mort']
    
    model = LinearRegression()
    model.fit(x, y)
    y_pred = model.predict(x)

    # L.I.N.E. pretpostavke
    # L - pretpostavka vazi
    line.linear_assumption(model, x, y)

    # I - pretpostavka ne vazi jer postoji negativna zavisnost
    autocorrelation, dw_value = line.independence_of_errors_assumption(model, x, y)
    if autocorrelation is None: print('Nema zavisnosti (ili vrlo malo).')
    if autocorrelation == 'negative': print('Negativna zavisnost.')
    if autocorrelation == 'positive': print('Pozitivna zavisnost.')
    print('Durbin-Watson value:', dw_value)

    # N - pretpostavka vazi
    dist_type, p_value = line.normality_of_errors_assumption(model, x, y)
    if dist_type == 'normal': print('Reziduali su normalno distribuirani.')
    if dist_type == 'non-normal': print('Reziduali nisu normalno distribuirani.')
    print(p_value)

    # E - pretpostavka vazi
    dist_type, p_value = line.equal_variance_assumption(model, x, y)
    if dist_type == 'equal': print('Jednaka varijansa.')
    if dist_type == 'non-equal': print('Nije jednaka varijansa.')
    print(p_value)

    # NOTE: metod najmanjih kvadrata nije prikladan u ovom zadatku