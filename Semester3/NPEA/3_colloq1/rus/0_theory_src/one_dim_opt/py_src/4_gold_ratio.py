from math import sqrt

from matplotlib import pyplot as plt

from func_routines import count_x_y
from func_routines import func


def golden_ratio(l, r, eps):
    phi = (1 + sqrt(5)) / 2
    resphi = 2 - phi
    iter_count = 0
    x1 = l + resphi * (r - l)
    x2 = r - resphi * (r - l)
    while abs(r - l) > eps:
        iter_count += 1
        if func(x1) < func(x2):
            r = x2
            x2 = x1
            x1 = l + resphi * (r - l)
        else:
            l = x1
            x1 = x2
            x2 = r - resphi * (r - l)

    return x1, x2, iter_count


def main_gr(a, b, eps):
    left, right, n = golden_ratio(a, b, eps)
    fleft = func(left)
    fright = func(right)

    x, y = count_x_y()

    p1 = plt.plot(x, y)
    plt.scatter(left, fleft)
    plt.text(left, fleft, "gr_l")
    plt.scatter(right, fright)
    plt.text(right, fright, "gr_r")
    plt.show()

    print(n)
    print(left, fleft)
    print(right, fright)


if __name__ == '__main__':
    eps = 0.001
    a = 0
    b = 1
    main_gr(a, b, eps)
