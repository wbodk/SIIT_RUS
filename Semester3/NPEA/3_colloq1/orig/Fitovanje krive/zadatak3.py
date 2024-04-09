import numpy as np

def zadatak3(x,y):
    if len(x) != len(y):
        raise ValueError('Vektori nisu istih duzina')
    p5 = np.polyfit(x,y,5)
    p6 = np.polyfit(x,y,6)
    return p5, p6

x = input('Unesite x:')
y = input('Unesite y:')
x = [int(broj.strip()) for broj in x.split(',')]
y = [int(broj.strip()) for broj in y.split(',')]
[p5, p6] = zadatak3(x,y)
print(p5,p6)