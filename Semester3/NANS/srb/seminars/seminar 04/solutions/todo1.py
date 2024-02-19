import pandas as pd
import statsmodels.api as sm
import matplotlib.pyplot as plt
import seaborn as sb

import line_pretpostavke as line

import warnings
warnings.filterwarnings("ignore")

if __name__ == '__main__':
    df = pd.read_csv('data/housing.csv', sep=',')
    x = df['lotsize(m^2)']
    y = df['price']

    x_with_const = sm.add_constant(x)
    model = sm.OLS(y, x_with_const).fit()
    y_pred = model.predict(x_with_const)

    # 1. Linearity - pretpostavka vazi
    # ----------------------------------
    print('za pouzdanost od 95%:', end='')
    is_linearity_found, p_value = line.linear_assumption(model, x_with_const, y, plot=True)
    if is_linearity_found: print('veza je linearna')
    else: print('veza je mozda nelinearna, a mozda linearna')

    
    # 2. Linearity (pouzdanost od 99%) - pretpostavka vazi
    # ----------------------------------
    print()
    print('za pouzdanost od 99%:', end='')
    is_linearity_found, p_value = line.linear_assumption(model, x_with_const, y, p_value_thresh=0.01, plot=False)
    if is_linearity_found: print('veza je linearna')
    else: print('veza je mozda nelinearna, a mozda linearna')

    
    # 3. najgori i najbolji moguc slucaj
    # ----------------------------------    
    # plotuj podatke i liniju koja se nabolje uklapa (best fit)
    sb.scatterplot(data=df, x='lotsize(m^2)', y='price')
    plt.plot(x, y_pred, 'b')

    alpha = 0.01 # postavimo nivo pouzdanosti od 99%
    low_intercept, high_intercept = model.conf_int(alpha).iloc[0] # interval poverenja za presek
    low_slope, high_slope = model.conf_int().iloc[1] # interval poverenja za nagib
    worst_case_y_pred = high_slope * x + high_intercept
    best_case_y_pred = low_slope * x + low_intercept

    # plotuj najgori i najbolji moguc slucaj
    plt.plot(x, worst_case_y_pred, 'r', label='najgori moguc slucaj')
    plt.plot(x, best_case_y_pred, 'g', label='najbolji moguc slucaj')


    # 4. povrsina placa = 120m^2, min cena = ?
    # ----------------------------------
    lotsize = 120
    lotsize_with_const = sm.add_constant([0, lotsize])
    alpha = 0.01 # postavimo nivo pouzdanosti od 99%
    pred_intervals = model.get_prediction(lotsize_with_const).summary_frame(alpha)
    low_y_pred = pred_intervals['obs_ci_lower'][1] # minimalna cena
    plt.scatter(lotsize, low_y_pred, label=f'min cena za {lotsize}m^2: {low_y_pred:.2f}$.')
    
    plt.legend()
    plt.show()