import numpy as np

from SD_func_routines import gradf


def adagrad(gradf, x0, gamma, eta, epsilon, max_iterations):
    x0 = np.array(x0)
    v = np.zeros(len(x0))
    G = np.zeros(len(x0))

    for i in range(max_iterations):
        g = gradf(x0)
        G += np.multiply(g, g)
        v = gamma * np.ones_like(G) / np.sqrt(G + eta) * g
        x0 = x0 - v
        if (np.linalg.norm(g) < epsilon):
            break

    return x0, i + 1


if __name__ == '__main__':
    x0 = [3, 3]
    epsilon = 1e-5
    max_iterations = 10000
    
    gamma = 1
    eta = 1e-5  ## Порядка epsilon

    [x1, x2], n = adagrad(gradf, x0, gamma, eta, epsilon, max_iterations)

    print('adagrad')
    print(f"x1 = {x1:.4f}".format(x1))
    print(f"x2 = {x2:.4f}".format(x2))
    print("n =", n)
