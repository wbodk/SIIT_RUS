from matplotlib import pyplot as plt

from fib_opt_search import fib_opt_search
from func_routines import count_x_y, func
from gold_ratio import golden_ratio
from newton_rhapson import newton_raphson
from secant import secant


def main():
    eps = 0.001
    x_beg = 0
    x_end = 1

    x, y = count_x_y(x_beg, x_end)
    p1 = plt.plot(x, y)

    xopt, fopt, iter_count = newton_raphson(1, eps)
    plt.scatter(xopt, fopt)
    print("NR count:", iter_count, "\n")

    xopt, fopt, iter_count = secant(0, 1, eps)
    plt.scatter(xopt, fopt)
    print("Secant count:", iter_count, "\n")

    xleft_opt, xright_opt, iter_count = fib_opt_search(0, 1, eps)
    xopt = (xleft_opt+ xright_opt) / 2
    yopt = func(xopt)
    plt.scatter(xopt, yopt)
    print("Fib count:", iter_count, "\n")

    xleft_optft, yright_opt, iter_count = golden_ratio(0, 1, eps)
    xopt = (xleft_opt+ xright_opt) / 2
    yopt = func(xopt)
    plt.scatter(xopt, yopt)
    print("Golden Ratio count:", iter_count, "\n")

    plt.show()


if __name__ == '__main__':
    main()
