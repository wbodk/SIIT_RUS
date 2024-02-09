def main():
    try:
        symbol: str = input("Enter a character: ")
        n: int = int(input("Enter a number: "))
        if n <= 0:
            print("Value Error!")
            return -1
    except:
        print("Value Error!")
        return -1

    with open("data/out_zadatak3.txt", "w") as f:
        line = ''

        for i in range(0, n):
            line += (2 * i + 1) * symbol + '\n'
        line += (2 * (i + 1) + 1) * symbol + line[::-1]

        f.write(line)

    return 0


if __name__ == "__main__":
    main()
