import pandas as pd
import statsmodels.api as sm
from sklearn.model_selection import train_test_split

from todo1 import are_assumptions_satisfied

def get_rsquared_adj(model, x, y):
    num_attributes = x.shape[1]
    y_pred = model.predict(sm.add_constant(x, has_constant='add'))

    from sklearn.metrics import r2_score
    r_squared = r2_score(y, y_pred)
    n = len(y_pred)
    p = num_attributes
    adjusted_r_squared = 1 - (1 - r_squared) * (n - 1) / (n - p - 1)
    return adjusted_r_squared

def get_fitted_model(x, y):
    x_with_const = sm.add_constant(x, has_constant='add')
    model = sm.OLS(y, x_with_const).fit()
    return model

def fit_and_get_rsquared_adj_test(x_train, x_test, y_train, y_test):
    '''pomoćna funkcija koja vraca fitovan model i prilagodjeni r^2 nad test skupom.'''
    model = get_fitted_model(x_train, y_train)
    adj_r2 = get_rsquared_adj(model, x_test, y_test)
    return model, adj_r2


if __name__ == '__main__':
    df = pd.read_csv('../data/housing.csv', sep=',')
    x = df.drop(columns=['price'])
    y = df['price']
    x_train, x_val, y_train, y_val = train_test_split(x, y, train_size=0.9, shuffle=True, random_state=42)

    model, adj_r2 = fit_and_get_rsquared_adj_test(x_train, x_val, y_train, y_val)
    print(model.summary(), adj_r2, sep='\n') # adj_r2 = 0.24
    if not are_assumptions_satisfied(model, x_train, y_train): print('pretpostavke nisu zadovoljene')

    # Na osnovu ispisa metode summary() gledamo kolonu `P>|t|` i tražimo najveću
    # vrednost (koja je veća od praga). Atribut sa najvećom vrednosti verovatno 
    # možemo izbaciti i dobiti bolji model (pretpostavljamo da ta kolona nema uticaj u modelu).
    # Može se programski ispitati p-vrednost for petljom, i na taj način 
    # dobiti najbolji model. Pošto imamo malo kolona, možemo i 'ručno' 
    # ispitati perfromanse. Svaki put kada izbacimo neku kolonu, proveravamo 
    # da li su se performanse poboljšale i koji sledeći atribut možemo 
    # izbaciti (ponovo gledamo kolonu `P>|t|`).

    cols = ['bedrooms']
    x_train_new, x_val_new = x_train.drop(columns=cols), x_val.drop(columns=cols)
    model, adj_r2 = fit_and_get_rsquared_adj_test(x_train_new, x_val_new, y_train, y_val)
    print(model.summary(), adj_r2, sep='\n') # 0.29
    if not are_assumptions_satisfied(model, x_train_new, y_train): print('pretpostavke nisu zadovoljene')

    cols = ['bedrooms', 'gashw']
    x_train_new, x_val_new = x_train.drop(columns=cols), x_val.drop(columns=cols)
    model, adj_r2 = fit_and_get_rsquared_adj_test(x_train_new, x_val_new, y_train, y_val)
    print(model.summary(), adj_r2, sep='\n') # 0.33
    if not are_assumptions_satisfied(model, x_train_new, y_train): print('pretpostavke nisu zadovoljene')

    cols = ['bedrooms', 'gashw', 'driveway']
    x_train_new, x_val_new = x_train.drop(columns=cols), x_val.drop(columns=cols)
    model, adj_r2 = fit_and_get_rsquared_adj_test(x_train_new, x_val_new, y_train, y_val)
    print(model.summary(), adj_r2, sep='\n') # 0.36
    if not are_assumptions_satisfied(model, x_train_new, y_train): print('pretpostavke nisu zadovoljene')

    # ovaj model postigao je najbolje perfromanse
    cols = ['bedrooms', 'gashw', 'driveway', 'garagepl']
    x_train_new, x_val_new = x_train.drop(columns=cols), x_val.drop(columns=cols)
    model, adj_r2 = fit_and_get_rsquared_adj_test(x_train_new, x_val_new, y_train, y_val)
    print(model.summary(), adj_r2, sep='\n') # 0.38
    if not are_assumptions_satisfied(model, x_train_new, y_train): print('pretpostavke nisu zadovoljene')

    # Ako probamo dodatno da uklonimo kolonu `fullbase`dobićemo malo bolje perfromanse, 
    # mada u tom slučaju pretpostavke linearne regresije nisu zadovoljene, pa ovaj model odbacujemo.
    # NOTE: Zakljucujemo da je najbolji model onaj bez kolona: 'bedrooms', 'gashw', 'driveway', 'garagepl'