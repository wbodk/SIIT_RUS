from math import sqrt

from matplotlib import pyplot as plt

from ODM_func_routines import count_x_y, func


def fib(a):
    fp, f = 1, 1
    i = 2
    while a > f:
        fp, f = f, f + fp
        i += 1
    return f, f-fp, i


def fib_opt_search(l, r, eps):
    crit = (r - l) / eps
    fn, fn_2, n = fib(crit)

    while (r - l) > eps:
        x1 = l + fn_2 / fn * (r - l)
        x2 = l + r - x1
        if func(x1) > func(x2):
            l = x1
        else:
            r = x2

    return (l+r)/2, func((l+r)/2), n


def main_fib(a, b, eps):
    xopt, fopt, n = fib_opt_search(a, b, eps)

    x, y = count_x_y()

    plt.plot(x, y)
    plt.scatter(xopt, fopt)
    plt.text(xopt, fopt, "fib")
    plt.show()

    print('fib_opt_search')
    print('x_opt:', f'{xopt:.4f}'.format(xopt))
    print('fopt:', f'{fopt:.4f}'.format(fopt))
    print('iterations:', n)


if __name__ == '__main__':
    eps = 1e-4
    a = -1
    b = 2
    main_fib(a, b, eps)
