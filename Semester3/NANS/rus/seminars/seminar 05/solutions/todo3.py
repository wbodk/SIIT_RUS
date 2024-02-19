import pandas as pd
import statsmodels.api as sm
from sklearn.model_selection import train_test_split

from todo1 import are_assumptions_satisfied
from todo2 import fit_and_get_rsquared_adj_test, get_rsquared_adj

if __name__ == '__main__':
    df = pd.read_csv('data/skincancer.csv', sep=',')
    x = df.drop(columns=['Mort', 'State'])
    y = df['Mort']
    x_train, x_val, y_train, y_val = train_test_split(x, y, train_size=0.8, shuffle=True, random_state=42)
    
    model, adj_r2 = fit_and_get_rsquared_adj_test(x_train, x_val, y_train, y_val)
    print(model.summary(), adj_r2) # adj_r2 = 0.39
    if not are_assumptions_satisfied(model, x_train, y_train): print('pretpostavke nisu zadovoljene')

    # Na osnovu ispisa metode summary() gledamo kolonu `P>|t|` i tražimo najveću
    # vrednost (koja je veća od praga). Atribut sa najvećom vrednosti verovatno 
    # možemo izbaciti i dobiti bolji model (pretpostavljamo da ta kolona nema uticaj u modelu).
    # Izbcujemo kolonu Long i dobijamo najbolji model koji zadovoljava pretpostavke.
    cols = ['Long']
    x_train_new, x_val_new = x_train.drop(columns=cols), x_val.drop(columns=cols)
    model, adj_r2 = fit_and_get_rsquared_adj_test(x_train_new, x_val_new, y_train, y_val)
    print(model.summary(), adj_r2, sep='\n') # 0.50
    if not are_assumptions_satisfied(model, x_train_new, y_train): print('pretpostavke nisu zadovoljene')

    # Učitamo test skup nad kojim testiramo fitovan model
    df_test = pd.read_csv('data/skincancer_europe.csv')
    x_test = df_test.drop(columns=['Mort', 'State', 'Long'])
    y_test = df_test['Mort']
    adj_r2 = get_rsquared_adj(model, x_test, y_test)
    print(f'r^2 mera na test skupu: {adj_r2}') # 0.46

    # NOTE: zaključak je da je najbolji model onaj koji koristi samo kolonu Lat. 
    # Takav model nad podacima iz test skupa ostvaruje 0.46 meru za prilagođeni r^2.