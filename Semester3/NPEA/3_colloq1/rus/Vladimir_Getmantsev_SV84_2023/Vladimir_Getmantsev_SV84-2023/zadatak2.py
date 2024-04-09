import numpy as np

def func(x):
    return np.array(2 * x[0] * x[0] + 2 * x[1] * x[1] - 4)

def dfunc1(x):
    return 4 * x[0]

def dfunc2(x):
    return 4 * x[1]

def gradf(x):
    return np.array([dfunc1(x), dfunc2(x)])

def stepest_descent(x, gamma, eps, max_count): 
    x = np.array(x, dtype='float64')
    i = 0
    for i in range(max_count):
        i += 1
        g = gradf(x)
        x = x - gamma * g
        if (np.linalg.norm(g) < eps):
            break
    return x, i

def main():
    gamma = 0.1
    max_count = 100
    eps = 1e-5
    x = [-2,2]

    xopt, iter = stepest_descent(x, gamma, eps, max_count)
    fopt = func(xopt)

    print(f"xopt :{xopt[0]:.5f}, {xopt[1]:.5f}".format())
    print(f"fopt :{fopt:.5f}".format(fopt))
    print(f"iter_count :{iter}".format(iter))


if __name__ == "__main__":
    main()
