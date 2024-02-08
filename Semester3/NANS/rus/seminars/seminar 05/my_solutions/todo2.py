import pandas as pd
import statsmodels.api as sm
from sklearn.model_selection import train_test_split
from sklearn.metrics import r2_score

from todo1 import are_assumptions_satisfied

def get_rsquared_adj(model, x, y):
    num_attributes = x.shape[1]
    y_pred = model.predict(sm.add_constant(x, has_constant='add'))

    from sklearn.metrics import r2_score
    r_squared = r2_score(y, y_pred)
    n = len(y_pred)
    p = num_attributes
    adjusted_r_squared = 1 - (1 - r_squared) * (n - 1) / (n - p - 1)
    return adjusted_r_squared

def get_fitted_model(x, y):
    x_with_const = sm.add_constant(x, has_constant='add')
    model = sm.OLS(y, x_with_const).fit()
    return model

def fit_and_get_rsquared_adj_test(x_train, x_test, y_train, y_test):
    '''pomoćna funkcija koja vraca fitovan model i prilagodjeni r^2 nad test skupom.'''
    model = get_fitted_model(x_train, y_train)
    adj_r2 = get_rsquared_adj(model, x_test, y_test)
    return model, adj_r2

if __name__ == '__main__':
    alpha = 0.01
    random_st = 42
    train_part = 0.9
    df = pd.read_csv("data/housing.csv", sep=',')
    
    x = df.drop(columns="price")
    y = df["price"]

    x_train, x_val, y_train, y_val = train_test_split(x, y, train_size=train_part,
                                                      shuffle=True,
                                                      random_state=random_st)

    x_train_c = sm.add_constant(x_train)
    model = sm.OLS(y_train, x_train_c).fit()
    
    print(model.summary())

    columns_list = x_train_c.columns.tolist()
    dropped_columns = []
    i = 0
    while ( i < len(columns_list) ):
        elem = columns_list[i]
        if alpha <= model.pvalues[elem]:
            x_train_c = x_train_c.drop(columns=elem)
            model = sm.OLS(y_train, x_train_c).fit()
            columns_list = x_train_c.columns.tolist()
            i = 0
            dropped_columns.append(elem)
            continue
        i += 1

    print(model.summary())
    print(are_assumptions_satisfied(model, x_train_c, y_train, alpha))

    x_val = x_val.drop(columns=dropped_columns)

    print(get_rsquared_adj(model, x_val, y_val))