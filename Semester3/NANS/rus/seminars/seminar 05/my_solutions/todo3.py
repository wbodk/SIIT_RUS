import pandas as pd
import statsmodels.api as sm
from sklearn.model_selection import train_test_split

from todo1 import are_assumptions_satisfied
from todo2 import fit_and_get_rsquared_adj_test, get_rsquared_adj

if __name__ == '__main__':
        ## consts
    alpha = 0.01
    train_part = 0.8
    rand_stat = 42
        ## import data frame
    df_europe = pd.read_csv("data/skincancer_europe.csv")
    df_amerika = pd.read_csv("data/skincancer.csv")

        ## get train data
    x_amer = df_amerika.drop(columns=["Mort", "State"])
    y_amer = df_amerika["Mort"]
    x_train_amer, x_val_amer, y_train_amer, y_val_amer = train_test_split(x_amer, y_amer, 
                                                      train_size=train_part,
                                                      shuffle=True,
                                                      random_state=rand_stat)
    
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
        pass

    print(model.summary())
    print(are_assumptions_satisfied(model, x_train_amer_c, y_train_amer, alpha))

    x_val_amer = x_val_amer.drop(columns=dropped_columns)
    print(get_rsquared_adj(model, x_val_amer, y_val_amer), '\n')

    x_eur = df_europe.drop(columns=["Mort", 'State'] + dropped_columns)
    x_eur_c = sm.add_constant(x_eur)
    y_eur = df_europe['Mort']

    print(get_rsquared_adj(model, x_eur, y_eur))
