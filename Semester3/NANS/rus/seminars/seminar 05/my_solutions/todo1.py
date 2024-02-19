import pandas as pd
import statsmodels.api as sm
from sklearn.model_selection import train_test_split

import line_pretpostavke as line

def are_assumptions_satisfied(model, x, y, p_value_thresh=0.01, 
                              plot_=False, ret_checks=False, ret_nums=False):
    results = []
    num_res = []

    tmp1, tmp2 = line.linear_assumption(model, x, y,
                                  p_value_thresh=p_value_thresh, plot=plot_)
    results.append(tmp1)
    num_res.append(tmp2)

    tmp1, tmp2 = line.normality_of_errors_assumption(model, x, y,
                                               p_value_thresh=p_value_thresh, plot=plot_)
    results.append(tmp1)
    num_res.append(tmp2)

    tmp1, tmp2 = line.independence_of_errors_assumption(model, x, y, plot=plot_)
    results.append(tmp1)
    num_res.append(tmp2)

    tmp1, tmp2 = line.equal_variance_assumption(model, x, y,
                                          p_value_thresh=p_value_thresh, plot=plot_)
    results.append(tmp1)
    num_res.append(tmp2)

    tmp1 = line.perfect_collinearity_assumption(x, plot=plot_)
    results.append(tmp1)

    ret_val = []
    if results == [True, 'normal', None, 'equal', False]:
        ret_val.append(True)
    else:
        ret_val.append(False)

    if ret_checks:
        ret_val.append(results)

    if ret_nums:
        ret_val.append(num_res)

    return ret_val


if __name__ == '__main__':
    df = pd.read_csv("data/housing.csv", sep=',')
    
    x = df.drop(columns="price")
    y = df["price"]

    x_train, x_val, y_train, y_val = train_test_split(x, y, train_size=0.8,
                                                      shuffle=True,
                                                      random_state=51)

    x_train_c = sm.add_constant(x_train)
    model = sm.OLS(y_train,x_train_c).fit()
    y_pred_train = model.predict(x_train_c)

    print(are_assumptions_satisfied(model, x_train_c, y_train,
                              p_value_thresh=0.01))

    pass