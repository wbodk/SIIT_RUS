from math import cos, log, sin, inf
from numpy import exp


# function
def func(x):
    return cos(2*x) + x * log(x)

# function first derivate
def dfunc(x):
    return -2 * sin(2 * x) + log(x) + 1

# function second derivate
def ddfunc(x):
    return -4 * cos(2 * x) + 1 / x

# Newton-Rhapson method: x0 - start pointl, eps - epsilon
def newton_rhapson(x0, eps):
    xp = inf
    x = x0
    i = 0

    while ( abs(xp - x) > eps ):
        xp = x
        x = xp - dfunc(x)/ ddfunc(x)
        i += 1
    
    return x, i
    

def main():
    eps = 1e-2
    x0 = 2 

    xopt, iter_count = newton_rhapson(x0, eps)
    fopt = func(xopt)
    
    print(f"xopt :{xopt:.2f}".format(xopt))
    print(f"fopt :{fopt:.2f}".format(fopt))
    print(f"iter_count :{iter_count}".format(iter_count))


if __name__ == "__main__":
    main()