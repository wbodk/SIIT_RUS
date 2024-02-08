import pandas as pd
import statsmodels.api as sm
import matplotlib.pyplot as plt
import seaborn as sb

import line_pretpostavke as line

import warnings
warnings.filterwarnings("ignore")

if __name__ == '__main__':
    df = pd.read_csv('data/skincancer.csv')
    alpha = 0.05

    ## 1 Найти минимальный и максимальный ожидаемый уровень смертности 
    # от рака кожи в Новом Саду. Нарисовать точки на графике. 
    # *Координаты Нового Сада: 45.2396° N, 19.8227° E.*
    ## 2. Обосновать, являются ли найденные значения валидными.

        ## 1.1 create model x=latitude
    x = df['Lat']
    y = df['Mort']

    x_c = sm.add_constant(x)
    model = sm.OLS(y, x_c).fit()
    y_pred = model.predict(x_c)

        ## create pred interval
    pred_intervals = model.get_prediction(x_c).summary_frame(alpha)
    print(pred_intervals)
    plt.fill_between(df['Lat'],
                     pred_intervals['obs_ci_lower'],
                     pred_intervals['obs_ci_upper'],
                     color='b',
                     alpha=.1)
    
        ## according to latitude we have normal results
    ns_lat = 45.2396
    ns_lat_c = sm.add_constant([0, ns_lat])
    ns_lat_c_pred = model.get_prediction(ns_lat_c).summary_frame(alpha)

    mean_ns_lat = ns_lat_c_pred["mean"][1]
    lower_ns_lat = ns_lat_c_pred["obs_ci_lower"][1]
    upper_ns_lat =ns_lat_c_pred["obs_ci_upper"][1]

    sb.scatterplot(data=df, x='Lat', y='Mort')
    plt.plot(x, y_pred)

    plt.plot(ns_lat, mean_ns_lat, ".", color='y')
    plt.plot(ns_lat, upper_ns_lat, ".", color='r')
    plt.plot(ns_lat, lower_ns_lat, ".", color='g')

    plt.show()

        ## 2.1 check for latitude
    print(line.linear_assumption(model, x_c, y, plot=False))
    print(line.independence_of_errors_assumption(model, x_c, y, plot=False))
    print(line.normality_of_errors_assumption(model, x_c, y, plot=False))
    print(line.equal_variance_assumption(model, x_c, y, plot=False))
    print('\n')
        ## results is valid


    ###############################################################################
        ## 1.2 create model x=longitude
    x = df['Long']
    y = df['Mort']

    x_c = sm.add_constant(x)
    model = sm.OLS(y, x_c).fit()
    y_pred = model.predict(x_c)

            ## create pred interval
    pred_intervals = model.get_prediction(x_c).summary_frame(alpha)
    plt.fill_between(df['Lat'],
                     pred_intervals['obs_ci_lower'],
                     pred_intervals['obs_ci_upper'],
                     color='b',
                     alpha=.1)

        ## according to longitude we have abnormal results
    ns_long = 19.8227
    ns_long_c = sm.add_constant([0, ns_long])
    ns_long_c_pred = model.get_prediction(ns_long_c).summary_frame(alpha)

    mean_ns_long = ns_long_c_pred["mean"][1]
    lower_ns_long = ns_long_c_pred["obs_ci_lower"][1]
    upper_ns_long =ns_long_c_pred["obs_ci_upper"][1]

    sb.scatterplot(data=df, x='Long', y='Mort')
    plt.plot(x, y_pred)

    plt.plot(ns_long, mean_ns_long, ".", color='y')
    plt.plot(ns_long, upper_ns_long, ".", color='r')
    plt.plot(ns_long, lower_ns_long, ".", color='g')

    plt.show()

        ## 2.2 check for longitude
    print(line.linear_assumption(model, x_c, y, plot=False))
    print(line.independence_of_errors_assumption(model, x_c, y, plot=False))
    print(line.normality_of_errors_assumption(model, x_c, y, plot=False))
    print(line.equal_variance_assumption(model, x_c, y, plot=False))
    print('\n')
        ## non valid because our selection too small and 
        # we have not enough data in neighbourhood close to longitude 19.8227 degr
        # no linear dependence mort(long)

    ###############################################################################
    ## 3. Согласно регрессионной модели, существует ли уровень смертности 
    # от рака кожи на Северном полюсе? Обосновать ответ. 
    # *Географическая широта Северного полюса: 90° N.*

        ## 3.1 create model x=latitude
    x = df['Lat']
    y = df['Mort']

    x_c = sm.add_constant(x)
    model = sm.OLS(y, x_c).fit()
    y_pred = model.predict(x_c)

            ## create pred interval
    pred_intervals = model.get_prediction(x_c).summary_frame(alpha)
    plt.fill_between(df['Lat'],
                     pred_intervals['obs_ci_lower'],
                     pred_intervals['obs_ci_upper'],
                     color='b',
                     alpha=.1)

        ## 3.2 according to latitude we have abnormal results
    north_pole_lat = 90.
    north_pole_lat_c = sm.add_constant([0, north_pole_lat])
    north_pole_lat_c_pred = model.get_prediction(north_pole_lat_c).summary_frame(alpha)

    mean_north_pole_lat = north_pole_lat_c_pred["mean"][1]
    lower_north_pole_lat = north_pole_lat_c_pred["obs_ci_lower"][1]
    upper_north_pole_lat = north_pole_lat_c_pred["obs_ci_upper"][1]

    sb.scatterplot(data=df, x='Lat', y='Mort')
    plt.plot(x, y_pred)

    plt.plot(north_pole_lat, mean_north_pole_lat, ".", color='y')
    plt.plot(north_pole_lat, lower_north_pole_lat, ".", color='r')
    plt.plot(north_pole_lat, upper_north_pole_lat, ".", color='g')

    plt.show()

        ## 3.3 check for latitude
    print(line.linear_assumption(model, x_c, y, plot=False))
    print(line.independence_of_errors_assumption(model, x_c, y, plot=False))
    print(line.normality_of_errors_assumption(model, x_c, y, plot=False))
    print(line.equal_variance_assumption(model, x_c, y, plot=False))
    print('\n')

        ## 3. fin result
        # non valid because our selection too small and 
        # we have not enough data in neighbourhood close to latitude 90 degr