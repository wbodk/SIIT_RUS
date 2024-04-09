import numpy as np

from SD_func_routines import gradf


def steepest_descent(gradf, x0, gamma, epsilon, N):
    x = np.array(x0)
    for i in range(N):
        g = gradf(x)
        x = x - gamma * g
        if np.linalg.norm(g) < epsilon:
            break
    return x, i + 1


if __name__ == '__main__':
    x0 = [3, 3]
    epsilon = 1e-5
    max_iterations = 10000
    
    gamma = 1
    [x1, x2], n = steepest_descent(gradf, x0, gamma, epsilon, max_iterations)

    print('steepest_descent')
    print(f"x1 = {x1:.4f}".format(x1))
    print(f"x2 = {x2:.4f}".format(x2))
    print("n =", n)
