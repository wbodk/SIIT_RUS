import numpy as np

def func(x):
    return x * x - np.sin(2 * x)


def dfunc(x):
    return 2 * x - 2 * np.cos(2 * x)


def ddfunc(x):
    return 2 + 4 * np.sin(2 * x)


def count_x_y(x_start=-1, x_end=1, steps=10000):
    x = np.linspace(x_start, x_end, steps)
    y = np.linspace(0, 0, len(x))
    for i in range(0, len(x), 1):
        y[i] = func(x[i])
    return x, y
