from math import inf

from matplotlib import pyplot as plt

from ODM_func_routines import func, dfunc, ddfunc, count_x_y


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
    xopt, fopt, n = newton_raphson(x0, eps)

    x, y = count_x_y()

    plt.plot(x, y)
    plt.scatter(xopt, fopt)
    plt.text(xopt, fopt, "N-R")
    plt.show()

    print('newton_raphson')
    print('x_opt:', f'{xopt:.4f}'.format(xopt))
    print('x_opt:', f'{fopt:.4f}'.format(fopt))
    print('iterations:', n)


if __name__ == '__main__':
    x0 = 1
    eps = 1e-4
    main_nr(x0, eps)
