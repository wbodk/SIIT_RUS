import numpy as np
import statistics as stat

def zadatak2(x,y):
    p3 = np.polyfit(x,y,3)
    p4 = np.polyfit(x,y,4)
    
    yp3 = np.polyval(p3,x)
    yp4 = np.polyval(p4,x)
    
    aps_gr3 = stat.mean(abs(y-yp3))
    aps_gr4 = stat.mean(abs(y-yp4))
    
    if aps_gr3 < aps_gr4:
        p = p3
    else:
        p = p4
    return p

x = input('Unesite x:')
y = input('Unesite y:')
x = [int(broj.strip()) for broj in x.split(',')]
y = [int(broj.strip()) for broj in y.split(',')]
p = zadatak2(x,y)
print(p)