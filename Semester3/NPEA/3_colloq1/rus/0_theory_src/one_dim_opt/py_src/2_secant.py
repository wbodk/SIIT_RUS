from math import inf

from matplotlib import pyplot as plt

from func_routines import func, dfunc, count_x_y


def secant(x0, x1, eps):
    xk = inf
    xk1 = x0
    xk2 = x1
    iter_count = 0
    while abs(xk - xk1) > eps:
        xk = xk1
        xk1 = xk2
        xk2 = xk1 - dfunc(xk1) * (xk1 - xk) / (dfunc(xk1) - dfunc(xk))
        iter_count += 1

    xopt = xk1
    fopt = func(xopt)

    return xopt, fopt, iter_count


def main_sec(x0, x1, eps):
    xopt, fopt, iter_count = secant(x0, x1, eps)

    x, y = count_x_y()

    p1 = plt.plot(x, y)
    plt.scatter(xopt, fopt)
    plt.text(xopt, fopt, "sec")
    plt.show()

    print(xopt, fopt, iter_count)


if __name__ == '__main__':
    x0 = 0
    x1 = 1
    eps = 0.001
    main_sec(x0, x1, eps)
