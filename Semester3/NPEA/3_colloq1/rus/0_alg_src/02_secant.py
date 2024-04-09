from math import inf

from matplotlib import pyplot as plt

from ODM_func_routines import func, dfunc, count_x_y


def secant(x0, x1, eps):
    xk = inf
    xk1, xk2 = x0, x1
    iter_count = 0
    while abs(xk - xk1) > eps:
        xk, xk1= xk1, xk2
        xk2 = xk1 - dfunc(xk1) * (xk1 - xk) / (dfunc(xk1) - dfunc(xk))
        iter_count += 1

    return xk2, func(xk2), iter_count


def main_sec(x0, x1, eps):
    xopt, fopt, n = secant(x0, x1, eps)

    x, y = count_x_y()

    plt.plot(x, y)
    plt.scatter(xopt, fopt)
    plt.text(xopt, fopt, "sec")
    plt.show()

    print('secant')
    print('x_opt:', f'{xopt:.4f}'.format(xopt))
    print('x_opt:', f'{fopt:.4f}'.format(fopt))
    print('iterations:', n)


if __name__ == '__main__':
    x0 = 0
    x1 = 1
    eps = 1e-4
    main_sec(x0, x1, eps)
