import matplotlib
import matplotlib.pyplot as plt
import seaborn as sb
import pandas as pd
import statsmodels.api as sm
from statsmodels.graphics.tsaplots import plot_acf, plot_pacf
from statsmodels.tsa.seasonal import STL
import numpy as np

matplotlib.rcParams['figure.figsize'] = (8, 4)
sb.set(font_scale=1.)

if __name__ == '__main__':
    df = pd.read_csv('data/airline-passengers.csv')
    df['Month'] = pd.to_datetime(df['Month'])
    df = df.set_index('Month')
    df = df['1954':'1960']

    # 1. nacrtaj grafik vremenske seirje
    df.plot()
    plt.show()

    # 2. Utvrdi da li se podaci mogu modelovati kao vremenska serija
    # moze jer postoji autokorelacija koja polako opada
    plot_acf(df, lags=20)
    plt.show()

    # 3. Utvrdi koji prethodni meseci imaju direktan uticaj na trenutni
    # Direktan uticaj na trenutni imaju znacajne vrednosti za k=1, 2, 9, 11 i 13.
    plot_pacf(df, lags=20)
    plt.show()

    # 4. Razdvoji vremensku seriju na komponente
    # razdvajamo pomocu STL metode. 
    # STL zahteva aditivni model (dobijamo logaritmovanjem).
    # logaritmovane podatke saljemo stl metodi i crtamo da vizualizujemo komponente.
    df['log_Passengers'] = np.log10(df['Passengers'])
    stl = STL(df['log_Passengers']).fit()
    trend, seasonal, resid = stl.trend, stl.seasonal, stl.resid
    stl.plot()
    plt.show()

    # 5. Rekonstruiši originalnu vremensku seriju koristeći podatke o trendu, sezonalnosti i šumu
    # Da dobijemo aditivni model iz multiplikativnog transformisali smo: log(𝑌_𝑡 )=log(𝑇_𝑡 )+log(𝑆_𝑡 )+log(𝐶_𝑡 )+log(𝑅_𝑡)
    # Da dobijmo multiplikativni model iz aditivnog koristimo inverznu operaciju od logaritma -> eksponent.
    # S obzirom da smo koristili log10 (umesto log), eksponenciramo sa osnovom 10 (umesto sa osnovom e).
    # Finalno dobijamo da je 𝑌_𝑡 = 10 ^ {log(𝑇_𝑡 )+log(𝑆_𝑡 )+log(𝐶_𝑡 )+log(𝑅_𝑡)}
    reconstructed_original_data = np.power(10, trend + seasonal + resid)
    # ekvivalentno sa:
    # reconstructed_original_data = np.power(10, trend) * np.power(10, seasonal) * np.power(10, resid)

    # sa grafika vidimo da smo uspesno rekonstruisali podatke.
    plt.plot(reconstructed_original_data, '.', label='rekonstruisani podaci')
    plt.plot(df['Passengers'], '--', label='stvarni podaci')
    plt.legend()
    plt.show()

    is_success = np.allclose(df['Passengers'], reconstructed_original_data) 
    print(is_success) # rucna provera

    from utils import is_data_stationary

    # 6. Postigni stacionarnost bez upotrebe diferenciranja
    stationary_data = df['log_Passengers'].copy() # duboka kopija kako ne bi menjali kolonu
    is_stationary, p_value = is_data_stationary(stationary_data, p_value_tresh=0.01)
    print(f'{p_value=:.3f}')
    if is_stationary: print('postoji stacionarnost')
    else: print('ne postoji stacionarnost')

    # oduzimamo trend
    stationary_data -= trend
    is_stationary, p_value = is_data_stationary(stationary_data, p_value_tresh=0.01)
    print(f'{p_value=:.3f}')
    if is_stationary: print('postoji stacionarnost')
    else: print('ne postoji stacionarnost')

    stationary_data -= seasonal
    is_stationary, p_value = is_data_stationary(stationary_data, p_value_tresh=0.01)
    print(f'{p_value=:.3f}')
    if is_stationary: print('postoji stacionarnost')
    else: print('ne postoji stacionarnost')

    plt.plot(stationary_data)
    plt.show()