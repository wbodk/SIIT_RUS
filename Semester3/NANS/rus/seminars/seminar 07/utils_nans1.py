import matplotlib
import matplotlib.pyplot as plt
import seaborn as sb
import pandas as pd
import numpy as np
import statsmodels.api as sm
from statsmodels.regression.linear_model import RegressionResultsWrapper
from sklearn.linear_model import LinearRegression    


matplotlib.rcParams['figure.figsize'] = (8, 4)
sb.set(font_scale=1.)

def calculate_residuals(model, features, labels):
    '''Вычисляет остатки между реальным значением `labels` и предсказанным значением.'''
    y_pred = model.predict(features)
    df_results = pd.DataFrame({'Actual': labels, 'Predicted': y_pred})
    df_results['Residuals'] = abs(df_results['Actual']) - abs(df_results['Predicted'])
    return df_results


def linear_assumption(model: LinearRegression| RegressionResultsWrapper, features: np.ndarray|pd.DataFrame, labels: pd.Series, p_value_thresh=0.05, plot=True):
    '''
    Линейное предположение: предполагает линейную связь между независимыми и зависимыми переменными.
    Тестирование линейности с использованием F-теста.

    Интерпретация `p-value`:
    - `p-value >= p_value_thresh` указывает на линейность.
    - `p-value < p_value_thresh` не указывает на линейность.

    Возвращает (только если модель из `statsmodels`, а не из `scikit-learn`):
    - is_linearity_found: логическое значение, указывающее, поддерживается ли предположение о линейности данными.
    - p_value: p-значение, полученное из теста на линейность.
    '''
    df_results = calculate_residuals(model, features, labels)
    y_pred = df_results['Predicted']

    if plot:
        plt.figure(figsize=(6,6))
        plt.scatter(labels, y_pred, alpha=.5)
        # x = y line
        line_coords = np.linspace(np.concatenate([labels, y_pred]).min(), np.concatenate([labels, y_pred]).max())
        plt.plot(line_coords, line_coords, color='darkorange', linestyle='--')
        plt.title('Линейное предположение')
        plt.xlabel('Фактическое')
        plt.ylabel('Предсказанное')
        plt.show()

    if type(model) == RegressionResultsWrapper:
        p_value = model.f_pvalue
        is_linearity_found = True if p_value < p_value_thresh else False
        return is_linearity_found, p_value
    else:
        pass


def independence_of_errors_assumption(model, features, labels, plot=True):
    '''
    Независимость ошибок: предполагает отсутствие автокорреляции в остатках.
    Предполагает, что в остатках нет автокорреляции.
    Тестирование автокорреляции с использованием теста Дарбина-Уотсона.
    
    Интерпретация значения `d`:
    - 1.5 <= d <= 2: Нет автокорреляции (независимые остатки).
    - d < 1.5: Положительная автокорреляция.
    - d > 2: Отрицательная автокорреляция.

    Возвращает:
    - autocorrelation: Тип автокорреляции ('positive', 'negative' или None).
    - dw_value: Значение статистики Дарбина-Уотсона.
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
    Нормальность ошибок: предполагает нормально распределенные остатки вокруг нуля.
    Тестирование с использованием теста Андерсона-Дарлинга на нормальное распределение.
    Интерпретация `p-value`:
    - `p-value >= p_value_thresh` указывает на нормальное распределение.
    - `p-value < p_value_thresh` указывает на ненормальное распределение.

    Возвращает:
    - dist_type: Строка, указывающая тип распределения ('normal' или 'non-normal').
    - p_value: P-значение из теста Андерсона-Дарлинга.
    '''
    df_results = calculate_residuals(model, features, label)
    
    if plot:
        plt.title('Распределение остатков')
        sb.histplot(df_results['Residuals'], kde=True, kde_kws={'cut':3})
        plt.show()

    from statsmodels.stats.diagnostic import normal_ad
    p_value = normal_ad(df_results['Residuals'])[1]
    dist_type = 'normal' if p_value >= p_value_thresh else 'non-normal'
    return dist_type, p_value


def equal_variance_assumption(model, features, labels, p_value_thresh=0.05, plot=True):
    '''
    Равномерность дисперсии: предполагает, что остатки имеют одинаковую дисперсию по всей линии регрессии.
    Тестирование равенства дисперсии с использованием теста Гольдфельда-Квандта.
    
    Интерпретация `p-value`:
    - `p-value >= p_value_thresh` указывает на равенство дисперсии.
    - `p-value < p_value_thresh` указывает на неравенство дисперсии.

    Возвращает:
    - dist_type: Строка, указывающая тип распределения ('eqal' или 'non-eqal').
    - p_value: P-значение из теста Гольдфельда-Квандта.
    '''
    df_results = calculate_residuals(model, features, labels)

    if plot:
        sb.scatterplot(x='Predicted', y='Residuals', data=df_results)
        plt.axhline(y=0, color='darkorange', linestyle='--')
        plt.show()

    if type(model) == LinearRegression:
        features = sm.add_constant(features)
    p_value =  sm.stats.het_goldfeldquandt(df_results['Residuals'], features)[1]
    dist_type = 'equal' if p_value >= p_value_thresh else 'non-equal'
    return dist_type, p_value


def perfect_collinearity_assumption(features: pd.DataFrame, plot=True):
    '''
    Идеальная коллинеарность: предполагает отсутствие идеальной корреляции между двумя или более признаками.
    Тестирование идеальной коллинеарности между ровно двумя признаками с использованием матрицы корреляции.

    Возвращает:
    - `has_perfect_collinearity`: Логическое значение, указывающее, найдена ли идеальная коллинеарность.
    '''
    correlation_matrix = features.corr() # рассчитываем матрицу корреляции

    if plot:
        sb.heatmap(correlation_matrix, annot=True, cmap='coolwarm', fmt='.2f', linewidths=0.1)
        plt.title('Матрица корреляции')
        plt.show()
    
    np.fill_diagonal(correlation_matrix.values, np.nan)
    pos_perfect_collinearity = (correlation_matrix > 0.999).any().any()
    neg_perfect_collinearity = (correlation_matrix < -0.999).any().any()
    has_perfect_collinearity = pos_perfect_collinearity or neg_perfect_collinearity
    return has_perfect_collinearity


def are_assumptions_satisfied(model, features, labels, p_value_thresh=0.05):
    '''Проверяет, выполнены ли все предположения в множественной линейной регрессии.'''
    x_with_const = sm.add_constant(features)
    is_linearity_found, p_value = linear_assumption(model, x_with_const, labels, p_value_thresh, plot=False)
    autocorrelation, dw_value = independence_of_errors_assumption(model, x_with_const, labels, plot=False)
    n_dist_type, p_value = normality_of_errors_assumption(model, x_with_const, labels, p_value_thresh, plot=False)
    e_dist_type, p_value = equal_variance_assumption(model, x_with_const, labels, p_value_thresh, plot=False)
    has_perfect_collinearity = perfect_collinearity_assumption(features, plot=False)

    if is_linearity_found and autocorrelation is None and n_dist_type == 'normal' and e_dist_type == 'equal' and not has_perfect_collinearity:
        return True
    else:
        return False



def get_fitted_model(features, labels):
    '''Подгоняет модель с использованием пакета `statsmodels`.'''
    x_with_const = sm.add_constant(features, has_constant='add')
    model = sm.OLS(labels, x_with_const).fit()
    return model


def check_for_missing_values(df): 
    '''Находит пропущенные значения и возвращает количество ненулевых пропущенных значений.'''
    missing_values = df.isna().sum()
    non_zero_missing = missing_values[missing_values != 0]
    non_zero_missing_percentage = (non_zero_missing / len(df)) * 100
    return pd.DataFrame({
        'N missing': non_zero_missing,
        '% missing': non_zero_missing_percentage
    })


def fillna_mean(df, col_name):
    '''Заполняет пропущенные значения средним значением.'''
    df_copy = df.copy()
    col_mean = df_copy[col_name].mean()
    df_copy[col_name] = df[col_name].fillna(col_mean)
    return df_copy


def visualize_column(df, col_name,  df_fixed=None):
    '''Строит график значений одного столбца в указанном столбце.'''
    x = df.index
    plt.plot(x, df[col_name], 'bo', alpha=.2, label='исходное')
    if df_fixed is not None: plt.plot(x, df_fixed[col_name], 'r-', label='исправленное')
    plt.legend()
    plt.show()


def get_sse(model, features, labels):
    '''Возвращает оценку SSE.'''
    y_pred = model.predict(sm.add_constant(features, has_constant='add'))
    sse = np.sum(((labels - y_pred) ** 2))
    return sse


def get_rmse(model, features, labels):
    '''Возвращает оценку RMSE.'''
    y_pred = model.predict(sm.add_constant(features, has_constant='add'))
    rmse = np.sqrt(np.mean(((labels - y_pred) ** 2)))
    return rmse


def get_rsquared(model, features, labels):
    '''Возвращает значение коэффициента детерминации (r^2).'''
    y_pred = model.predict(sm.add_constant(features, has_constant='add'))

    from sklearn.metrics import r2_score
    r_squared = r2_score(labels, y_pred)
    return r_squared

def get_rsquared_adj(model, features, labels):
    '''Возвращает скорректированное значение коэффициента детерминации (скорректированный r^2).'''
    num_attributes = features.shape[1]
    y_pred = model.predict(sm.add_constant(features, has_constant='add'))

    from sklearn.metrics import r2_score
    r_squared = r2_score(labels, y_pred)
    n = len(y_pred)
    p = num_attributes
    adjusted_r_squared = 1 - (1 - r_squared) * (n - 1) / (n - p - 1)
    return adjusted_r_squared


def get_conf_interval(model: RegressionResultsWrapper, feature_name:str, alpha=0.05):
    '''
    Рассчитывает доверительный интервал для коэффициента конкретной фичи в модели.

    Параметры:
    model (RegressionResultsWrapper): Обученная модель линейной регрессии.
    feature_name (str): Название фичи, для которой нужно рассчитать доверительный интервал.
    alpha (float, optional): Уровень значимости (по умолчанию 0.05 для доверительного интервала 95%).

    Возвращает:
    Кортеж (min_value, max_value): Нижняя и верхняя границы доверительного интервала для коэффициента указанной фичи.
    '''
    min_value, max_value = model.conf_int(alpha).loc[feature_name]
    return min_value, max_value

def get_pred_interval(model, features: int | float | np.ndarray | pd.Series | pd.DataFrame, p_value_trash=0.05):
    '''
    Рассчитывает предсказательный интервал для заданных фич.
    '''
    if type(features) == int or type(features) == float:
        pred_intervals = model.get_prediction(sm.add_constant([features, 0])).summary_frame(alpha=p_value_trash)
        low = pred_intervals['obs_ci_lower'].values[0]
        high = pred_intervals['obs_ci_upper'].values[0]
        return low, high
    
    if type(features) == list or type(features) == np.ndarray:
        const = np.array([1])
        datapoint = np.concatenate([features, const])
        pred_intervals = model.get_prediction(datapoint).summary_frame(alpha=p_value_trash)
        low = pred_intervals['obs_ci_lower'].values[0]
        high = pred_intervals['obs_ci_upper'].values[0]
        return low, high

    else:
        pred_intervals = model.get_prediction(sm.add_constant(features)).summary_frame(alpha=p_value_trash)
        low = pred_intervals['obs_ci_lower'].values
        high = pred_intervals['obs_ci_upper'].values
        return low, high