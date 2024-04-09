import numpy as np
import matplotlib.pyplot as plt
from math import sqrt

def func(x):
    return 1/2*(x[0]**2 + x[1]**2)

def dfunc1(x):
    return x[0]

def dfunc2(x):
    return x[1]

def gradf(x):
    x = [dfunc1(x), dfunc2(x)]
    x = np.array(x)
    return x 
