import pandas as pd
import statsmodels.api as sm
import numpy as np
from sklearn.model_selection import train_test_split
from utils import *


if __name__ == '__main__':
    alpha = 0.05
    rand_state = 29
    train_part = 0.7
        ## read original data
    df = pd.read_csv('data/missing_data_housing.csv', sep=',')

    # 1. Izbacuje sve redove kojima nedostaje vrednost.
        ## fix NANs
    df_1 = df.dropna()
        ## create vars
    x_1 = df_1.drop(columns=['price'])
    y_1 = df_1['price']
        ## create train and validat sets
    x_1_train, x_1_val, y_1_train, y_1_val = train_test_split(x_1, y_1,
                                                              train_size=train_part,
                                                              shuffle=True,
                                                              random_state=rand_state)

    model_1 = get_fitted_model(x_1_train, y_1_train)
    print("1: ")
    print("Tests: ", 
          my_are_assumptions_satisfied(model_1, x_1_train, y_1_train, ## random_state=29 : True
                                                 p_value_thresh=alpha, ret_checks=True),
          are_assumptions_satisfied(model_1, x_1_train, y_1_train, p_value_thresh=alpha))
    print("Adj. r^2: ",get_rsquared_adj(model_1, x_1_val, y_1_val))

    # 2. Izbacuje atribut `stories`, a atribut `lotsize(m^2)` popunjava srednjom vrednošću. 
        ## fix NANs
    df_2 = df.copy()
    df_2 = df_2.drop(columns=['stories'])
    lotsize_mean = df_2['lotsize(m^2)'].mean()
    df_2['lotsize(m^2)'] = df_2['lotsize(m^2)'].fillna(lotsize_mean)
        ## create vars
    x_2 = df_2.drop(columns=['price'])
    y_2 = df_2['price']
        ## create train and validat sets
    x_2_train, x_2_val, y_2_train, y_2_val = train_test_split(x_2, y_2,
                                                              train_size=train_part,
                                                              shuffle=True,
                                                              random_state=rand_state)

    model_2 = get_fitted_model(x_2_train, y_2_train)
    print("2: ")
    print("Tests: ", 
          my_are_assumptions_satisfied(model_2, x_2_train, y_2_train, ## random_state=29 : True
                                                 p_value_thresh=alpha, ret_checks=True),
          are_assumptions_satisfied(model_2, x_2_train, y_2_train, p_value_thresh=alpha)) 
    print("Adj. r^2: ", get_rsquared_adj(model_2, x_2_val, y_2_val))

    # 3. Nedostajuće vrednosti popunjava kubnim splajnom.
    
        ## fix NANs
    df_3 = df.copy()
    df_3 = df_3.interpolate(method='spline', order=3, limit_direction='both')
        ## create vars
    x_3 = df_3.drop(columns=['price'])
    y_3 = df_3['price']
        ## create train and validat sets
    x_3_train, x_3_val, y_3_train, y_3_val = train_test_split(x_3, y_3,
                                                              train_size=train_part,
                                                              shuffle=True,
                                                              random_state=rand_state)

    model_3 = get_fitted_model(x_3_train, y_3_train)
    print("3: ")
    print("Tests: ", 
          my_are_assumptions_satisfied(model_3, x_3_train, y_3_train, ## random_state=29 : True
                                                 p_value_thresh=alpha, ret_checks=True),
          are_assumptions_satisfied(model_3, x_3_train, y_3_train, p_value_thresh=alpha))
    print("Adj. r^2: ", get_rsquared_adj(model_3, x_3_val, y_3_val))

    # 4. Nedostajuce vrednosti atributa `stories` popunjava linearnim splajnom. 
    # Ostale nedostajuce vrednosti popunjava kubnim splajnom.
    
        ## fix NANs
    df_4 = df.copy()
    df_4['stories'] = df_4['stories'].interpolate(method='spline', order=1, limit_direction='both')
    df_4 = df_4.interpolate(method='spline', order=2, limit_direction='both')
        ## create vars
    x_4 = df_4.drop(columns=['price'])
    y_4 = df_4['price']
        ## create train and validat sets
    x_4_train, x_4_val, y_4_train, y_4_val = train_test_split(x_4, y_4,
                                                              train_size=train_part,
                                                              shuffle=True,
                                                              random_state=rand_state)

    model_4 = get_fitted_model(x_4_train, y_4_train)
    print("4: ")
    print("Tests: ", 
          my_are_assumptions_satisfied(model_4, x_4_train, y_4_train, ## random_state=29 : True
                                              p_value_thresh=alpha, ret_checks=True), 
          are_assumptions_satisfied(model_4, x_4_train, y_4_train, p_value_thresh=alpha))
    print("Adj. r^2: ", get_rsquared_adj(model_4, x_4_val, y_4_val))
    print(model_4.summary())