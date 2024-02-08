import pandas as pd
import statsmodels.api as sm
import matplotlib.pyplot as plt
import seaborn as sb

import line_pretpostavke as line

import warnings
warnings.filterwarnings("ignore")

if __name__ == '__main__':
    df = pd.read_csv('data/skincancer.csv', sep=',')
    x = df['Lat']
    y = df['Mort']

    x_with_const = sm.add_constant(x)
    model = sm.OLS(y, x_with_const).fit()
    y_pred = model.predict(x_with_const)

    # plotuj podatke i liniju koja se nabolje uklapa (best fit)
    sb.scatterplot(data=df, x='Lat', y='Mort')
    plt.plot(x, y_pred, 'b')

    # plotuj interval predikcije
    pred_intervals = model.get_prediction(x_with_const).summary_frame()
    plt.fill_between(df['Lat'],
                pred_intervals['obs_ci_lower'],
                pred_intervals['obs_ci_upper'],
                color='b',
                alpha=.1)


    # 1. Novi Sad
    # ----------------------------------
    ns_lat = 45.2396
    ns_with_cons = sm.add_constant([0, ns_lat])

    pred_intervals = model.get_prediction(ns_with_cons).summary_frame()
    low_y_pred = pred_intervals['obs_ci_lower'][1] # minimalna ocekivana stopa mortaliteta
    plt.scatter(ns_lat, low_y_pred)
    high_y_pred = pred_intervals['obs_ci_upper'][1] # maksimalna ocekivana stopa mortaliteta
    plt.scatter(ns_lat, high_y_pred)
    plt.show()


    # 2. da li su validne vrednosti?
    # ----------------------------------
    autocorrelation, dw_value = line.independence_of_errors_assumption(model, x_with_const, y, plot=False)
    if autocorrelation is None: print('intervali su validni. Nije narusena pretpostavka.')
    else: print('intervali nisu validni. Narusena je pretpostavka.')
    
    # NOTE: kada je narusena pretpostavka o nezavinosti gresaka, onda intervali nisu validni/pouzdani.
    # Postoji autokorelacija i narusena je pretpostvka => pa intervali nisu validni/pouzdani.
    
    
    # 3. Severni pol
    # ----------------------------------
    north_pole_lat = 90
    north_pole_with_const = sm.add_constant([0, north_pole_lat])
    pred_intervals = model.get_prediction(north_pole_with_const).summary_frame()
    low_y_pred = pred_intervals['obs_ci_lower'][1] # minimalna ocekivana
    high_y_pred = pred_intervals['obs_ci_upper'][1] # maksimalna ocekivana
    if low_y_pred <= 0 and high_y_pred <= 0: print('ne postoji stopa mortaliteta na Severnom polu.')
    else: print('postoji stopa mortaliteta na Severnom polu.')

    # NOTE: prema nasem regresionom modelu su i minimalna i maksimalna ocekivana vrednost ispod 0, 
    # sto znaci da ne postoji stopa mortaliteta na servernom polu.