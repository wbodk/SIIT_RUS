import pandas as pd
import statsmodels.api as sm
import matplotlib.pyplot as plt
import seaborn as sb

import line_pretpostavke as line

import warnings
warnings.filterwarnings("ignore")

if __name__ == '__main__':

    ## построить модель
    df = pd.read_csv("data/housing.csv", sep=',')
    x = df["lotsize(m^2)"]
    y = df["price"]

    x_c = sm.add_constant(x)
    model = sm.OLS(y, x_c).fit()
    y_pred = model.predict(x_c)

    sb.scatterplot(data=df, x='lotsize(m^2)', y='price')
    plt.plot(x, y_pred)
    plt.show()

    ## 1. Статистическим тестом проверить, удовлетворяет ли модель линейности.
    print(line.linear_assumption(model, x_c, y))    

    ## 2. Проверить, удовлетворяет ли модель линейности с уровнем доверия 99%.
    print(line.linear_assumption(model, x_c, y, 0.01))
    
    ## 3. Нарисовать наихудший и наилучший случаи (верхнюю и нижнюю границу 
    ## регрессионной линии) с уровнем доверия 99%.
    low_slope, high_slope = model.conf_int(0.01).iloc[1]
    low_intercept, high_intercept = model.conf_int(0.01).iloc[0]

    y_pred_low = low_slope * x + low_intercept
    y_pred_high = high_slope * x + high_intercept

    sb.scatterplot(data=df, x='lotsize(m^2)', y='price')
    plt.plot(x, y_pred)
    plt.plot(x, y_pred_low, 'g--')
    plt.plot(x, y_pred_high, 'r--')

    plt.show()

    ## 4. Если покупатель хочет купить дом с участком площадью 120 м², 
    # рассчитать минимальную сумму, которую ему придется потратить на покупку дома. 
    # Нарисовать такой дом на графике. Рассчитать интервал доверия с уровнем доверия 99%.
        
    alpha = 0.01
        ## regression line and data points
    sb.scatterplot(data=df, x='lotsize(m^2)', y='price')
    plt.plot(x, y_pred)

        ## conf interval 
        # (в каком диапазоне лежит изменение y с вероятностью alpha)
    low_slope, high_slope = model.conf_int(alpha).iloc[1]
    low_intercept, high_intercept = model.conf_int(alpha).iloc[0]

    y_pred_low = low_slope * x + low_intercept
    y_pred_high = high_slope * x + high_intercept

    sb.scatterplot(data=df, x='lotsize(m^2)', y='price')
    plt.plot(x, y_pred)
    plt.plot(x, y_pred_low, 'g--')
    plt.plot(x, y_pred_high, 'r--')

        ## pred interval
        # (интервал доверия для каждого предсказания) 
        # (для кажлого xi определяем между какими значениями лежит yi)
    pred_intervals = model.get_prediction(x_c).summary_frame(alpha)
    print(pred_intervals)
    plt.fill_between(df['lotsize(m^2)'],
                pred_intervals['obs_ci_lower'],
                pred_intervals['obs_ci_upper'],
                color='b',
                alpha=.1)

        ## prediction of house with 120m^2
    lotsize = 120
    lotsize_c = sm.add_constant([0, lotsize])
    lotsize_c_pred_interval = model.get_prediction(lotsize_c).summary_frame(alpha)

    mean_lotsize_pred = lotsize_c_pred_interval['mean'][1]
    low_lotsize_pred = lotsize_c_pred_interval['obs_ci_lower'][1]
    high_lotsize_pred = lotsize_c_pred_interval['obs_ci_upper'][1]

    plt.plot(lotsize, mean_lotsize_pred, '.', color='b')
    plt.plot(lotsize, low_lotsize_pred, '.', color='r')
    plt.plot(lotsize, high_lotsize_pred, '.', color='g')

    plt.show()

    pass