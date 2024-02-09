import pandas as pd
from sklearn.decomposition import PCA
from sklearn.preprocessing import StandardScaler
import warnings
warnings.filterwarnings("ignore")

from utils import *

if __name__ == '__main__':
    # 1. Izbacuje sve redove kojima nedostaje vrednost.
    df = pd.read_csv('data/responses.csv') # učitamo podatke
    df = df.dropna() # izbacimo redove sa nedostajućim vrednostima
    df = df.iloc[:, 134:] # indeksiramo tražene kolone
    df = df.drop(columns=['Weight']) # izbacimo kolonu za težinu

    # pretprocesiranje
    # ordinal encoding
    df['Education'] = df['Education'].map({
        'currently a primary school pupil': 0,
        'primary school': 1,
        'secondary school': 2,
        'college/bachelor degree': 3,
        'masters degree': 4,
        'doctorate degree': 5
    })
    # one-hot encoding
    cols = df.select_dtypes(exclude=['int', 'float']).columns
    df = pd.get_dummies(df, columns=cols, drop_first=True).astype('int')

    # skaliramo podatke
    target_label = 'Height'
    x, y = df.drop(columns=[target_label]), df[target_label]
    scaler = StandardScaler()
    x_scaled = scaler.fit_transform(x)
    df = pd.DataFrame(x_scaled, columns=x.columns)
    df[target_label] = y.values
    # izdvajamo skalirane podatke će biti ulaz u PCA
    x, y = df.drop(columns=[target_label]), df[target_label]

    # primenimo PCA
    pca_model = PCA(n_components=2, random_state=42)
    principal_components = pca_model.fit_transform(x)
    
    # otkomentariši sledeću liniju da odrediš broj komponenti. Idealan broj bi bio 2 u ovom slučaju.
    # plot_explained_variance(pca_model)

    model = get_fitted_model(principal_components, y)
    print(model.summary())

    # PC1 se odnosi na markiranu odeću i izgled. Više osobe troše više para na izgled i preferiraju markiranu odeću.
    plot_pc_loading(pca_model, 0, x.columns)

    # PC2 se odnosi na broj godina. Niže osobe su mlađe, a starije osobe su više (jer je pozitivan koeficijent uz PC1).
    plot_pc_loading(pca_model, 1, x.columns)