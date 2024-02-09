import csv
import pickle


def main():
    arr = []
    with open('data/in_zadatak4.csv') as f:
        for line in csv.reader(f): arr.append(tuple(line))

    with open('data/out_zadatak4.bin', 'wb') as f:
        pickle.dump(arr, f)

    with open('data/out_zadatak4.bin', 'rb') as f:
        print(pickle.load(f))


if __name__ == '__main__':
    main()
