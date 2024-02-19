import pandas as pd
import statsmodels.api as sm
from sklearn.model_selection import train_test_split

import line_pretpostavke as line

def are_assumptions_satisfied(model, x, y, p_value_thresh=0.01):
    '''provera pretpostavki.'''
    x_with_const = sm.add_constant(x)
    # Linearity
    is_linearity_found, p_value = line.linear_assumption(model, x_with_const, y, p_value_thresh, plot=False)
    # Independence of errors
    autocorrelation, dw_value = line.independence_of_errors_assumption(model, x_with_const, y, plot=False)
    # Normality of errors
    n_dist_type, p_value = line.normality_of_errors_assumption(model, x_with_const, y, p_value_thresh, plot=False)
    # Equal variance
    e_dist_type, p_value = line.equal_variance_assumption(model, x_with_const, y, p_value_thresh, plot=False)
    # Perfect collinearity
    has_perfect_collinearity = line.perfect_collinearity_assumption(x, plot=False)

    if is_linearity_found and autocorrelation is None and n_dist_type == 'normal' and e_dist_type == 'equal' and not has_perfect_collinearity:
        return True
    else:
        return False


if __name__ == '__main__':
    df = pd.read_csv('../data/housing.csv', sep=',')
    x = df.drop(columns=['price'])
    y = df['price']
    x_train, x_val, y_train, y_val = train_test_split(x, y, train_size=0.9, shuffle=True, random_state=42)

    x_with_const = sm.add_constant(x_train)
    model = sm.OLS(y_train, x_with_const).fit()

    if are_assumptions_satisfied(model, x_train, y_train, p_value_thresh=0.01):
        print('sve pretpostavke su zadovoljene')
    else:
        print('bar jedna pretpostavka nije zadovoljena')