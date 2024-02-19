import pandas as pd
import statsmodels.api as sm
import numpy as np
from sklearn.model_selection import train_test_split
from utils import *

def fit_and_get_rsquared_adj_test(x_train, x_test, y_train, y_test):
    '''pomoćna funkcija koja vraca fitovan model i prilagodjeni r^2 nad test skupom.'''
    model = get_fitted_model(x_train, y_train)
    adj_r2 = get_rsquared_adj(model, x_test, y_test)
    return model, adj_r2


if __name__ == '__main__':
    # 1. Удаляет все строки с отсутствующими значениями.
    df = pd.read_csv('data/missing_data_housing.csv', sep=',') 
    df = df.dropna()
    
    x, y = df.drop(columns=['price']), df['price']
    x_train, x_val, y_train, y_val = train_test_split(x, y, train_size=0.7, shuffle=True, random_state=42)
    model, adj_r2 = fit_and_get_rsquared_adj_test(x_train, x_val, y_train, y_val)
    print(adj_r2) # 0.29

    # 2. Удаляет атрибут `stories`, а атрибут `lotsize(m^2)` заполняет средним значением.
    df = pd.read_csv('data/missing_data_housing.csv', sep=',') 
    df = df.drop(columns='stories')
    df = fillna_mean(df, col_name='lotsize(m^2)')
    
    x, y = df.drop(columns=['price']), df['price']
    x_train, x_val, y_train, y_val = train_test_split(x, y, train_size=0.7, shuffle=True, random_state=42)
    model, adj_r2 = fit_and_get_rsquared_adj_test(x_train, x_val, y_train, y_val)
    print(adj_r2) # 0.38

    # 3. Заполняет отсутствующие значения кубическим сплайном.
    df = pd.read_csv('data/missing_data_housing.csv', sep=',') 
    
    df['lotsize(m^2)'] = df['lotsize(m^2)'].interpolate(method='spline', order=3, limit_direction='both')
    df['stories'] = df['stories'].interpolate(method='spline', order=3, limit_direction='both')

    x, y = df.drop(columns=['price']), df['price']
    x_train, x_val, y_train, y_val = train_test_split(x, y, train_size=0.7, shuffle=True, random_state=42)
    model, adj_r2 = fit_and_get_rsquared_adj_test(x_train, x_val, y_train, y_val)
    print(adj_r2) # 0.65

    # 4. Заполняет отсутствующие значения атрибута `stories` линейным сплайном. Остальные отсутствующие значения заполняет кубическим сплайном.
    df = pd.read_csv('data/missing_data_housing.csv', sep=',') 
    
    df['stories'] = df['stories'].interpolate(method='linear', limit_direction='both')
    df['lotsize(m^2)'] = df['lotsize(m^2)'].interpolate(method='spline', order=2, limit_direction='both')

    x, y = df.drop(columns=['price']), df['price']
    x_train, x_val, y_train, y_val = train_test_split(x, y, train_size=0.7, shuffle=True, random_state=42)
    model, adj_r2 = fit_and_get_rsquared_adj_test(x_train, x_val, y_train, y_val)
    print(adj_r2) # 0.66

    # NOTE: заключаем, что лучшая модель - последняя, где используется линейный сплайн для `stories`
    # и кубический сплайн для `lotsize(m^2)`
