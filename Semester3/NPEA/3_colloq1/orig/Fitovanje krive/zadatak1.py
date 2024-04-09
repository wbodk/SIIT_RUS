import numpy as np

#%% Zadatak 1.
def zadatak1(x,y):
    if len(x) != len(y):
        raise ValueError('Vektori nisu istih duzina')
    p = np.polyfit(x,y,1)
    return p

x = input('Unesite x:')
y = input('Unesite y:')
x = [int(broj.strip()) for broj in x.split(',')]
y = [int(broj.strip()) for broj in y.split(',')]
p = zadatak1(x,y)
print(p)