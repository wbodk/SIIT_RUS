from math import sqrt

from matplotlib import pyplot as plt

from ODM_func_routines import count_x_y, func


def golden_ratio(l, r, eps):
    resphi = 2 - (1 + sqrt(5)) / 2
    x1, x2 = l + resphi * (r - l), r - resphi * (r - l)

    iter_count = 0
    while abs(r - l) > eps:
        iter_count += 1
        if func(x1) < func(x2):
            r, x2 = x2, x1
            x1 = l + resphi * (r - l)
        else:
            l, x1 = x1, x2
            x2 = r - resphi * (r - l)

    return (l+r)/2, func((l+r)/2), iter_count


def main_gr(a, b, eps):
    xopt, fopt, n = golden_ratio(a, b, eps)

    x, y = count_x_y()

    plt.plot(x, y)
    plt.scatter(xopt, fopt)
    plt.text(xopt, fopt, "fib")
    plt.show()

    print("golden_ratio")
    print('x_opt:', f'{xopt:.4f}'.format(xopt))
    print('x_opt:', f'{fopt:.4f}'.format(fopt))
    print('iterations:', n)


if __name__ == '__main__':
    eps = 1e-4
    a = 0
    b = 1
    main_gr(a, b, eps)
