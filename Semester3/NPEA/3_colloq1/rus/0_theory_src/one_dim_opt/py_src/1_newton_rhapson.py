from math import inf

from matplotlib import pyplot as plt

from func_routines import func, dfunc, ddfunc, count_x_y


def newton_raphson(x0, eps):
    xk = inf
    xk1 = x0
    iter_count = 0
    while abs(xk - xk1) > eps:
        xk = xk1
        xk1 = xk - dfunc(xk) / ddfunc(xk)
        iter_count += 1

    xopt = xk1
    fopt = func(xopt)

    return xopt, fopt, iter_count


def main_nr(x0, eps):
    xopt, fopt, iter_count = newton_raphson(x0, eps)

    x, y = count_x_y()

    p1 = plt.plot(x, y)
    plt.scatter(xopt, fopt)
    plt.text(xopt, fopt, "N-R")
    plt.show()

    print(xopt, fopt, iter_count)


if __name__ == '__main__':
    x0 = 1
    eps = 0.0001
    main_nr(x0, eps)
