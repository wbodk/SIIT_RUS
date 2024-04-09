import numpy as np 
import math

def zadatak4(x,y):
    if len(x) != len(y):
        raise ValueError('Vektori nisu istih duzina')
    p = []
    red  = 0
    greska = math.inf   
    while greska>0.1:
        red += 1
        p = np.polyfit(x,y,red)
        yp = np.polyval(p,x)
        greska = max(abs((y-yp)/y)*100)  
    return p

x = input('Unesite x:')
y = input('Unesite y:')
x = [int(broj.strip()) for broj in x.split(',')]
y = [int(broj.strip()) for broj in y.split(',')]
p = zadatak4(x, y)
print(p)