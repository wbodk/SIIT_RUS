import matplotlib
import matplotlib.pyplot as plt
import seaborn as sb
import pandas as pd
import numpy as np
import statsmodels.api as sm
from statsmodels.graphics.tsaplots import plot_acf, plot_pacf
from statsmodels.tsa.arima.model import ARIMA
from sklearn.metrics import mean_squared_error
from sklearn.model_selection import train_test_split

import warnings
warnings.filterwarnings("ignore")

matplotlib.rcParams['figure.figsize'] = (8, 4)
sb.set(font_scale=1.)

from utils import is_data_stationary


if __name__ == '__main__':
    # Učitamo skup podataka
    df = pd.read_csv('data/airline-passengers.csv')
    df['Month'] = pd.to_datetime(df['Month'])
    df = df.set_index('Month')
    df.head()

    # 1. Podeli vremensku seriju na trening i validacioni skup u odnosu 70:30.
    dataset_split = int(len(df['Passengers']) * 0.7)
    train_df = df[:dataset_split].copy()
    val_df  =  df[dataset_split:].copy()

    # 2. Proveri da li su podaci sortirani.
    # Veoma je bitno da su podaci vremenske serije sortirani, u suprotnom, predikcije neće biti tačne
    df = df.sort_index()


    # 3. Napravi najbolji MA model (model pokretnog proseka) i ispiši koliku RMSE meru model ostvaruje 
    # koristeći walk-forward evaluaciju.
    train_df['log10(Passengers)'] = np.log10(train_df['Passengers'])
    train_df['stationary_data'] = train_df['log10(Passengers)'].diff().diff()

    is_stationary = is_data_stationary(train_df['stationary_data'].dropna())
    if is_stationary: print('postoji stacionarnost')
    else: print('ne postoji stacionarnost')

    # preko ACF grafika utvrđujemo red MA
    # značajne vrednosti su q=1, q=4 i q=12. Solidni rezultati se dobiju za q=4, a najbolji za q=12.
    # plot_acf(train_df['stationary_data'].dropna(), lags=15)
    # plt.show()

    # treniramo model
    val_df['log10(Passengers)'] = np.log10(val_df['Passengers'])
    p, d, q = 0, 2, 12
    from utils import walk_forward_loop
    wf_pred = walk_forward_loop(train_df, val_df, column_name='log10(Passengers)',  order=(p, d, q))
    wf_pred = np.power(10, wf_pred)

    # prijavljujemo RMSE meru
    actual = val_df['Passengers']
    rmse = np.sqrt(mean_squared_error(actual, wf_pred))
    print(f'{rmse=:.2f}')

    # 4. Nacrtaj grafik predikcija.
    plt.plot(train_df['Passengers'], color='b', linewidth=4, alpha=0.3, label='train')
    plt.plot(val_df['Passengers'], color='mediumblue', linewidth=4, alpha=0.3, label='val')
    plt.plot(wf_pred, color='darkorange', label='Walk-forward model prediction')
    plt.title('predikcije za Passengers')
    plt.legend()
    plt.show()

    # 5. Napravi MA model koristeći sve podatke i proceni koliko putnika se očekuje u prvom kvartalu 1961. godine.

    # S ozbirom da koristimo sve podatke, moramo ponovo da tražimo stacionarnost, ovaj put nad celim skupom podataka
    df['log10(Passengers)'] = np.log10(df['Passengers'])
    df['stationary_data'] = df['log10(Passengers)'].diff().diff()

    is_stationary, _ = is_data_stationary(df['stationary_data'].dropna())
    if is_stationary: print('postoji stacionarnost')
    else: print('ne postoji stacionarnost')

    # Zatim proveravamo vrednosti ACF grafika da utvrdimo red modela.
    # Očekujemo da će red modela biti ista značajna vrednost kao i kod MA modela napravljenog pomoću walk-forward evaluacije.
    # plot_acf(df['stationary_data'].dropna(), lags=15)
    # plt.show()

    # treniramo MA model koristeći sve podatke
    df['log10(Passengers)'] = np.log10(df['Passengers'])
    p, d, q = 0, 2, 12
    ma_model = ARIMA(df['log10(Passengers)'], order=(p, d, q)).fit()
    # forecast metoda predviđa narednih `steps` koraka od poslenjeg datuma nad kojim je trenirana
    # poslednji datum je decembar 1960, što znači da su naredna tri meseca: januar, februar i mart 1961 - tj. prvi kvartal 1961 godine.
    ma_y_pred = ma_model.forecast(steps=3)
    ma_y_pred = np.power(10, ma_y_pred)
    ma_n_passengers = int(ma_y_pred.sum()) # ocekivani broj putnika MA modela
    print(f'ocekivani broj putnika u prvom kvartalu je: {ma_n_passengers}')

    # 6. Napravi ARIMA model (koristeći sve podatke) tako da su mu predikcije za prvi kvartal 1961. godine najbliže MA modelu.

    # crtamo PACF i ACF grafika da odredimo red modela
    # plot_pacf(df['stationary_data'].dropna(), lags=15, method='ols') # da nađemo red AR modela
    # plt.show()
    # plot_acf(df['stationary_data'].dropna(), lags=15) # da nađemo red MA modela
    # plt.show()

    # treniramo ARIMA model tako da je razlika između broja putnika najmanja
    p, d, q = 14, 2, 1
    arima_model = ARIMA(df['log10(Passengers)'], order=(p, d, q)).fit()
    y_pred_arima = arima_model.forecast(steps=3)
    y_pred_arima = np.power(10, y_pred_arima)

    arima_n_passengers = int(y_pred_arima.sum()) # ocekivani broj putnika ARIMA modela
    diff = np.abs(ma_n_passengers - arima_n_passengers)
    print(f'procena MA modela i ARIMA modela se razlikuje za: {diff} putnika.')