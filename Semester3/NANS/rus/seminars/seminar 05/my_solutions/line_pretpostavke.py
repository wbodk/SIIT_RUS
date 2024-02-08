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
    '''Рассчитывает остатки между реальным значением `labels` и предсказанным значением.'''
    y_pred = model.predict(features)
    df_results = pd.DataFrame({'Actual': labels, 'Predicted': y_pred})
    df_results['Residuals'] = abs(df_results['Actual']) - abs(df_results['Predicted'])
    return df_results


def linear_assumption(model: LinearRegression| RegressionResultsWrapper, features: np.ndarray|pd.DataFrame, labels: pd.Series, p_value_thresh=0.05, plot=True):
    '''
    Линейное предположение: предполагает линейную связь между независимыми и зависимыми переменными.
    Тестирование линейности с использованием F-теста.

    Интерпретация значения `p-value`:
    - `p-value >= p_value_thresh` указывает на линейность.
    - `p-value < p_value_thresh` не указывает на линейность.

    Возвращает (только если модель из `statsmodels`, а не из `scikit-learn`):
    - is_linearity_found: Логическое значение, указывающее, поддерживают ли данные предположение о линейности.
    - p_value: Значение p-value, полученное из теста линейности.
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
        plt.xlabel('Фактическое значение')
        plt.ylabel('Предсказанное значение')
        plt.show()

    if type(model) == RegressionResultsWrapper:
        p_value = model.f_pvalue
        is_linearity_found = True if p_value < p_value_thresh else False
        return is_linearity_found, p_value
    else:
        pass


def independence_of_errors_assumption(model, features, labels, plot=True):
    '''
    Предположение об независимости ошибок: предполагает независимость ошибок.
    Предполагает отсутствие автокорреляции в остатках.
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
    Предположение о нормальности ошибок: предполагает нормально распределенные остатки вокруг нуля.
    Тестирование с использованием теста Андерсона-Дарлинга на нормальное распределение остатков.
    Интерпретация значения `p-value`:
    - `p-value >= p_value_thresh` указывает на нормальное распределение.
    - `p-value < p_value_thresh` указывает на ненормальное распределение.

    Возвращает:
    - dist_type: Строка, указывающая тип распределения ('normal' или 'non-normal').
    - p_value: Значение p-value из теста Андерсона-Дарлинга.
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
    Предположение о равной дисперсии: предполагает, что остатки имеют равную дисперсию по всей линии регрессии.
    Тестирование равенства дисперсии с использованием теста Гольдфельда-Квандта.
    
    Интерпретация значения `p-value`:
    - `p-value >= p_value_thresh` указывает на равенство дисперсии.
    - `p-value < p_value_thresh` указывает на неравенство дисперсии.

    Возвращает:
    - dist_type: С

трока, указывающая тип распределения ('равная' или 'неравная').
    - p_value: Значение p-value из теста Гольдфельда-Квандта.
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
    Предположение о совершенной коллинеарности: предполагает отсутствие совершенной корреляции между двумя или более признаками.
    Тестирование совершенной коллинеарности между ровно двумя признаками с использованием матрицы корреляции.

    Возвращает:
    - `has_perfect_collinearity`: Булевое значение, указывающее, найдена ли совершенная коллинеарность.
    '''
    correlation_matrix = features.corr() # вычисляем матрицу корреляции

    if plot:
        sb.heatmap(correlation_matrix, annot=True, cmap='coolwarm', fmt='.2f', linewidths=0.1)
        plt.title('Матрица корреляции')
        plt.show()
    
    np.fill_diagonal(correlation_matrix.values, np.nan)
    pos_perfect_collinearity = (correlation_matrix > 0.999).any().any()
    neg_perfect_collinearity = (correlation_matrix < -0.999).any().any()
    has_perfect_collinearity = pos_perfect_collinearity or neg_perfect_collinearity
    return has_perfect_collinearity