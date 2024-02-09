from sklearn.model_selection import train_test_split

from utils_nans1 import *


def print_sums(model, x_train, y_train, x_val, y_val, df_train, plot=False):
    print(model.summary())
    print("Tests:")
    print(linear_assumption(model, sm.add_constant(x_train), y_train, plot=plot))
    print(independence_of_errors_assumption(model, sm.add_constant(x_train), y_train, plot=plot))
    print(normality_of_errors_assumption(model, sm.add_constant(x_train), y_train, plot=plot))
    print(equal_variance_assumption(model, sm.add_constant(x_train), y_train, plot=plot))
    print(perfect_collinearity_assumption(sm.add_constant(x_train), plot=plot))
    print("Metrics(val data):", get_rmse(model, x_val, y_val))
    pass


alpha = 0.05
rand_state = 42
train_part = 0.8

df_train = pd.read_csv("data/train.csv")
df_test = pd.read_csv("data/test.csv")

if __name__ == '__main__':
    ############### №1 and №3
    # check nans
    print(df_train.isna().sum())

    # fixing train NANs
    df_train = df_train.interpolate(method='spline', order=3, limit_direction='both')
    df_train = df_train.round(0)

    # build model
    x_train = df_train.drop(columns=['plata'])
    y_train = df_train['plata']

    x_train, x_val, y_train, y_val = train_test_split(x_train, y_train,
                                                      train_size=train_part, shuffle=True, random_state=rand_state)

    model = get_fitted_model(x_train, y_train)
    print(model.summary())
    print_sums(model, x_train, y_train, x_val, y_val, df_train, plot=False)

    # drop 'pol Zenski' because of collinearity with 'pol Muski'
    # drop 'godina_iskustva' because p_value > p_treshhold
    x_train = x_train.drop(columns=['godina_iskustva', 'pol Zenski'])
    x_val = x_val.drop(columns=['godina_iskustva', 'pol Zenski'])

    # then recreate and check
    model = get_fitted_model(x_train, y_train)
    print_sums(model, x_train, y_train, x_val, y_val, df_train, plot=False)
    # check on test data
    x_test = df_test.drop(columns=['plata', 'pol Zenski', 'godina_iskustva'])
    y_test = df_test['plata']
    print("Test data metrics:", get_rmse(model, x_test, y_test))

    # drop 'pol Muski' because p_value > p_treshhold
    x_train = x_train.drop(columns=['pol Muski'])
    x_val = x_val.drop(columns=['pol Muski'])
    # then recreate and check
    model = get_fitted_model(x_train, y_train)
    print_sums(model, x_train, y_train, x_val, y_val, df_train, plot=True)
    # check on test data
    x_test = df_test.drop(columns=['plata', 'pol Zenski', 'godina_iskustva', 'pol Muski'])
    y_test = df_test['plata']
    print("Test data metrics:", get_rmse(model, x_test, y_test))

    ############### №2
    min_raise, max_raise = get_conf_interval(model, 'zvanje', alpha)
    print(f"\nMin: {min_raise:.2f}")
    print(f"Max: {max_raise:.2f}")
    if normality_of_errors_assumption(model, sm.add_constant(x_train), y_train, plot=False)[0] == 'non-normal':
        print("Results are non-valid")
    else:
        print("Results are valid")
    print()

    ############### №4
    print(model.summary())
    ## due to the "Prob (F-statistic): 3.39e-27" < 0.05
    ## then 95% that there is liner dependence between dependent variable
    ## and any other independent variable
