import matplotlib
import matplotlib.pyplot as plt
import seaborn as sb
import pandas as pd
import numpy as np
import statsmodels.api as sm
matplotlib.rcParams['figure.figsize'] = (8, 4)
sb.set(font_scale=1.)

def calculate_residuals(model, features, labels):
    '''Calculates residuals between true value `labels` and predicted value.'''
    y_pred = model.predict(features)
    df_results = pd.DataFrame({'Actual': labels, 'Predicted': y_pred})
    df_results['Residuals'] = abs(df_results['Actual']) - abs(df_results['Predicted'])
    return df_results


def linear_assumption(model, features, labels, plot=True):
    '''Linear assumption: assumees linear relation between the independent and dependent variables to be linear.'''
    df_results = calculate_residuals(model, features, labels)
    y_pred = df_results['Predicted']

    if plot == True:
        plt.figure(figsize=(6,6))
        plt.scatter(labels, y_pred, alpha=.5)
        # x = y line
        line_coords = np.linspace(np.concatenate([labels, y_pred]).min(), np.concatenate([labels, y_pred]).max())
        plt.plot(line_coords, line_coords, color='darkorange', linestyle='--')
        plt.title('Linear assumption')
        plt.xlabel('Actual')
        plt.ylabel('Predicted')
        plt.show()


def independence_of_errors_assumption(model, features, labels, plot=True):
    '''
    Independence of errors: assumes independent errors. 
    Assumes that there is no autocorrelation in the residuals. 
    Testing autocorrelation using Durbin-Watson Test.
    
    Interpretation of `d` value:
    - 1.5 <= d <= 2: No autocorrelation (independent residuals).
    - d < 1.5: Positive autocorrelation.
    - d > 2: Negative autocorrelation.

    Returns:
    - autocorrelation: The type of autocorrelation ('positive', 'negative', or None).
    - dw_value: The Durbin-Watson statistic value.
    '''
    df_results = calculate_residuals(model, features, labels)

    if plot:
        sb.scatterplot(x='Predicted', y='Residuals', data=df_results)
        plt.axhline(y=0, color='darkorange', linestyle='--')
        plt.show()

    from statsmodels.stats.stattools import durbin_watson
    dw_value = durbin_watson(df_results['Residuals'])

    autocorrelation = None
    if dw_value < 1.5: autocorrelation = 'positive'
    elif dw_value > 2: autocorrelation = 'negative'
    else: autocorrelation = None
    return autocorrelation, dw_value


def normality_of_errors_assumption(model, features, label, p_value_thresh=0.05, plot=True):
    '''
    Normality of errors: assumes normally distributed residuals around zero.
    Testing using the Anderson-Darling test for normal distribution on residuals.
    Interpretation of `p-value`:
    - `p-value >= p_value_thresh` indicates normal distribution.
    - `p-value < p_value_thresh` indicates non-normal distribution.

    Returns:
    - dist_type: A string indicating the distribution type ('normal' or 'non-normal').
    - p_value: The p-value from the Anderson-Darling test.
    '''
    df_results = calculate_residuals(model, features, label)
    
    if plot:
        plt.title('Distribution of residuals')
        sb.histplot(df_results['Residuals'], kde=True, kde_kws={'cut':3})
        plt.show()

    from statsmodels.stats.diagnostic import normal_ad
    p_value = normal_ad(df_results['Residuals'])[1]
    dist_type = 'normal' if p_value >= p_value_thresh else 'non-normal'
    return dist_type, p_value


def equal_variance_assumption(model, features, labels, p_value_thresh=0.05, plot=True):
    '''
    Equal variance: assumes that residuals have equal variance across the regression line.
    Testing equal variance using Goldfeld-Quandt test.
    
    Interpretation of `p-value`:
    - `p-value >= p_value_thresh` indicates equal variance.
    - `p-value < p_value_thresh` indicates non-equal variance.

    Returns:
    - dist_type: A string indicating the distribution type ('eqal' or 'non-eqal').
    - p_value: The p-value from the Goldfeld-Quandt test.
    '''
    df_results = calculate_residuals(model, features, labels)

    if plot:
        sb.scatterplot(x='Predicted', y='Residuals', data=df_results)
        plt.axhline(y=0, color='darkorange', linestyle='--')
        plt.show()

    features = sm.add_constant(features)
    p_value =  sm.stats.het_goldfeldquandt(df_results['Residuals'], features)[1]
    dist_type = 'equal' if p_value >= p_value_thresh else 'non-equal'
    return dist_type, p_value
