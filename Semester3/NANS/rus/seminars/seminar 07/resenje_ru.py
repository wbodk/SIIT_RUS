import pandas as pd
from sklearn.model_selection import train_test_split
from utils_nans1 import *

# **Задача 1.**
# загружаем данные
df = pd.read_csv('data/train.csv', sep=',')
df.head()

# Перед обучением модели необходимо решить пропущенные значения.
# Самый простой способ - удалить все строки.
df = df.dropna()

# обучаем модель (ищем параметры)
x = df.drop(columns=['plata'])
y = df['plata']
model = get_fitted_model(x, y)

# сообщаем метрику на тестовом наборе данных
df_test = pd.read_csv('data/test.csv', sep=',')
x_test = df_test.drop(columns=['plata'])
y_test = df_test['plata']
test_rmse = get_rmse(model, x_test, y_test)
print(test_rmse)


# **Задача 2.**
# находим минимальное и максимальное значения
min_expected_raise, max_expected_raise = get_conf_interval(model, 'zvanje', alpha=0.05)
print(f'{min_expected_raise:.2f}')
print(f'{max_expected_raise:.2f}')

# проверка, являются ли минимальное и максимальное значения допустимыми
autocorrelation, _ = independence_of_errors_assumption(model, sm.add_constant(x), y, plot=False)
if autocorrelation is None:
    print('значения допустимы, так как выполнено предположение о независимости ошибок')
else:
    print('значения не допустимы')


# **Задача 3.**
# загружаем данные
df = pd.read_csv('data/train.csv', sep=',')
# интерполяция вместо удаления значений удовлетворяет всем предположениям
df['zvanje'] = df['zvanje'].interpolate(method='spline', order=3, limit_direction='both')
df['godina_doktor'] = df['godina_doktor'].interpolate(method='linear', limit_direction='both')

# удаляем столбец пола Мужчины (или пола Женщины), чтобы избежать полной коллинеарности
df = df.drop(columns=['pol Muski', 'pol Zenski'])

# делим данные в отношении 80-20
x = df.drop(columns=['plata'])
y = df['plata']
x_train, x_val, y_train, y_val = train_test_split(x, y, train_size=0.8, shuffle=True, random_state=42)

# обучаем модель
model = get_fitted_model(x_train, y_train)

# проверяем, выполнены ли предположения (см. объяснение)
print(are_assumptions_satisfied(model, x_train, y_train))

# смотрим меру на валидационном наборе данных, чтобы найти лучшую модель
val_rmse = get_rmse(model, x_val, y_val)
print(f'валидационное rmse: {val_rmse:.2f}')

# сообщаем меру на тестовом наборе данных
df_test = pd.read_csv('data/test.csv', sep=',')
x_test = df_test.drop(columns=['plata', 'pol Muski', 'pol Zenski'])
y_test = df_test['plata']
test_rmse = get_rmse(model, x_test, y_test)
print(f'тестовое rmse: {test_rmse:.2f}')