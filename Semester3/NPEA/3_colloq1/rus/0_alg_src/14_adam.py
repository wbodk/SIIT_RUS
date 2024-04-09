import numpy as np

from SD_func_routines import gradf


def adam(gradf, x0, gamma, omega1, omega2, eta, max_iterations, epsilon):
    x0 = np.array(x0)
    m = np.ones_like(x0)
    v = np.ones_like(x0)
    for i in range(max_iterations):
        g = gradf(x0)

        v = v * omega2 + (1 - omega2) * np.multiply(g, g)
        adj_v = v / (1 - omega2)
        gamma_adj = gamma * np.ones_like(x0) / (np.sqrt(adj_v + eta))

        m = m * omega1 + (1 - omega1) * g
        adj_m = m / (1 - omega1)

        x0 = x0 - np.multiply(gamma_adj, adj_m)

        if np.linalg.norm(g) < epsilon:
            break
    return x0, i + 1


if __name__ == '__main__':
    x0 = [3, 3]
    epsilon = 1e-5
    max_iterations = 10000
    
    gamma = 1  ## скорость обучения
    omega1, omega2 = 0.69, 0.99  ## omega1 наиболее близкая к 1, разница между omega1 omega2 влияет на скорость обучения
    eta = 1e-5  ## порядка epsilon

    [x1, x2], n = adam(gradf, x0, gamma, omega1, omega2, eta, max_iterations, epsilon)

    print('adam')
    print(f"x1 = {x1:.4f}".format(x1))
    print(f"x2 = {x2:.4f}".format(x2))
    print("n =", n)
