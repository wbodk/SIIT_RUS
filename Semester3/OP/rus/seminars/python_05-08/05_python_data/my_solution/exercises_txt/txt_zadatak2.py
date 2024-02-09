class Auto:
    def __init__(self, string: str) -> None:
        string = string.split()
        self.brand: str = string[0]
        self.engine_vol: float = float(string[1])
        self.year: int = int(string[2])

    def __str__(self) -> str:
        return f"Brand: {self.brand}" + f"\nEngineVol: {self.engine_vol}" + f"\nYear: {self.year}"


def readfile(filename: str) -> list:
    with open(filename) as f:
        return f.readlines()


def main() -> int:
    autos = [Auto(line) for line in readfile("data/in_zadatak2.txt")]

    try:
        max_volume: float = float(input("Enter engine max volume: "))
    except ValueError:
        print("Wrong input!")
        return -1

    best_auto = autos[0]
    for auto in autos[1::]:
        if auto.engine_vol <= max_volume:
            best_auto = auto if best_auto.year < auto.year else best_auto

    if max_volume < best_auto.engine_vol:
        print("We don't have such auto(")
    else:
        print("Your newest auto is:", )
        print(best_auto)
    return 0


if __name__ == "__main__":
    main()
