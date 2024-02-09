import random
import matplotlib.pyplot as plt


def fit(x, y):
    # TODO 1: implementirati linearnu regresiju, y = ax + b, y = slope * x + intercept
    assert len(x) == len(y)
    # broj podataka
    n = len(x) 
    # nadji srednju vrednost za x i y
    x_mean = sum(x) / n
    y_mean = sum(y) / n

    # racunamo brojilac i imenilac nagiba (slope ili 'a' u formuli)
    numerator = denominator = 0 
    for i in range(n):
        numerator += (x[i] - x_mean) * (y[i] - y_mean)
        denominator += (x[i] - x_mean) ** 2

    # izracunaj nagib i presek
    slope = numerator / denominator
    intercept = y_mean - slope * x_mean
    return slope, intercept

def predict(x, slope, intercept):
    # TODO 2: prediktuj y na osnovu x koristeci nagib i presek -> y = ax + b
    y_pred = slope * x + intercept
    return y_pred

def make_predictions(x, slope, intercept):
    y_pred = [predict(xi, slope, intercept) for xi in x]
    return y_pred


if __name__ == '__main__':
    # da rezultati mogu da se reprodukuju
    random.seed(1337)  
    # random generisi podatke sa nasumicnim sumom
    x = [xi for xi in range(50)]
    # y = x (+- nasumicni sum)
    y = [(xi + random.randint(-5, 5)) for xi in x]  

    # izracunaj nagib i presek za liniju koja se najbolja uklapa (best fit)
    slope, intercept = fit(x, y)

    # prediktuj y za svako x
    y_pred = make_predictions(x, slope, intercept)

    # plotuj podatke i liniju koja se nabolje uklapa (best fit)
    plt.plot(x, y, '.')
    plt.plot(x, y_pred, 'b')
    plt.title(f'slope: {slope:.2f}, intercept: {intercept:.2f}')
    plt.show()
