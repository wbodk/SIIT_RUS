import numpy as np
import pandas as pd
import statsmodels.api as sm
from sklearn.model_selection import train_test_split
from sklearn.base import clone
import matplotlib.pyplot as plt
import seaborn as sb

from todo1 import are_assumptions_satisfied
from todo2 import fit_and_get_rsquared_adj_test, get_rsquared_adj

if __name__ == '__main__':
        ## consts
    alpha = 0.01
    train_part = 0.8
    df_europe = pd.read_csv("data/skincancer_europe.csv")
    df_amerika = pd.read_csv("data/skincancer.csv")
    random_state_amer = 51 ## 51
    random_state_eur = 43 ## 43

        ## get test data
    x_amer = df_amerika.drop(columns=["Mort", "State"])
    y_amer = df_amerika["Mort"]
    x_train_amer, x_val_amer, y_train_amer, y_val_amer = train_test_split(x_amer, y_amer, 
                                                      train_size=train_part,
                                                      shuffle=True,
                                                      random_state=random_state_amer)
    
    x_train_amer_c = sm.add_constant(x_train_amer)
    model = sm.OLS(y_train_amer, x_train_amer_c).fit()

    column_names = x_train_amer_c.columns.tolist()
    dropped_columns = []
    i = 0

    while i < len(column_names):
        qurr_col = column_names[i]
        if alpha < model.pvalues[qurr_col]:
            x_train_amer_c = x_train_amer_c.drop(columns=qurr_col)
            dropped_columns.append(qurr_col)
            column_names.remove(qurr_col)
            model = sm.OLS(y_train_amer, x_train_amer_c).fit()
            i = 0
            continue
        i += 1

    x_val_amer = x_val_amer.drop(columns=dropped_columns)

    print(model.summary())
    print(are_assumptions_satisfied(model, x_train_amer_c, y_train_amer, alpha), '\n')

    x_eur = df_europe.drop(columns=["Mort", 'State'] + dropped_columns)
    y_eur = df_europe['Mort']

    print(get_rsquared_adj(model, x_eur, y_eur))

    x_train_eur, x_val_eur, y_train_eur, y_val_eur = train_test_split(x_eur, y_eur, 
                                                    train_size=train_part,
                                                    shuffle=True,
                                                    random_state=random_state_eur)
                                                    
    x_train_eur_c = sm.add_constant(x_train_eur)
    x_global_train_c = pd.concat([x_train_amer_c, x_train_eur_c], ignore_index=True)
    y_global_train = pd.concat([y_train_amer, y_train_eur], ignore_index=True)

    model_new = sm.OLS(y_global_train, x_global_train_c).fit()

    print(model_new.summary())
    print(are_assumptions_satisfied(model_new, x_global_train_c, y_global_train, alpha))
    print("old amer: ", get_rsquared_adj(model, x_val_amer, y_val_amer))
    print("old eur: ", get_rsquared_adj(model, x_val_eur, y_val_eur))
    print("new amer ", get_rsquared_adj(model_new, x_val_amer, y_val_amer))
    print("new eur ", get_rsquared_adj(model_new, x_val_eur, y_val_eur))
