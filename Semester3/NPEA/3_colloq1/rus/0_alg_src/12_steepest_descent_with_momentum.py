import numpy as np

from SD_func_routines import gradf


def steepest_descent_with_momentum(gradf, x0, gamma, epsilon, omega, max_iterations):
    x = np.array(x0)
    v = 0

    for i in range(max_iterations):
        g = gradf(x)
        v = omega * v + gamma * g
        x = x - v
        if np.linalg.norm(g) < epsilon:
            break
    return x, i + 1


if __name__ == "__main__":
    x0 = [3, 3]
    epsilon = 1e-5
    max_iterations = 10000
    
    gamma = 0.9 * 0.8  ## разбиваем множитель для gamma и omega (который в сумме дает 1) на доли для gamma и omega
    omega = 0.1 * 0.2

    [x1, x2], n = steepest_descent_with_momentum(gradf, x0, gamma, epsilon, omega, max_iterations)

    print('steepest_descent_with_momentum')
    print(f"x1 = {x1:.4f}".format(x1))
    print(f"x2 = {x2:.4f}".format(x2))
    print("n =", n)

