from matplotlib import pyplot as plt
from math import sqrt
from func_routines import count_x_y
from func_routines import func


def bine(n):
    return int((((1 + sqrt(5)) ** n - (1 - sqrt(5)) ** n) / (2 ** n * sqrt(5))))

def fib(a):
    fibs = [1, 1]
    while fibs[len(fibs) - 1] < a:
        fibs.append(fibs[len(fibs) - 1] + fibs[len(fibs) - 2])

    n = len(fibs) - 1
    return n, fibs


def fib_opt_search(l, r, eps):
    crit = (r - l) / eps
    iter_counter, fibs = fib(crit)

    for i in range(iter_counter, 1, -1):
        x1 = l + fibs[i - 2] / fibs[i] * (r - l)
        x2 = l + r - x1
        if func(x1) > func(x2):
            l = x1
        else:
            r = x2

    return l, r, iter_counter


def main_fib(a, b, eps):
    left, right, n = fib_opt_search(a, b, eps)
    fleft = func(left)
    fright = func(right)

    x, y = count_x_y()

    p1 = plt.plot(x, y)
    plt.scatter(left, fleft)
    plt.text(left, fleft, "fib_l")
    plt.scatter(right, fright)
    plt.text(right, fright, "fib_r")
    plt.show()

    print(n)
    print(left, fleft)
    print(right, fright)


if __name__ == '__main__':
    eps = 0.001
    a = 0
    b = 1
    main_fib(a, b, eps)
