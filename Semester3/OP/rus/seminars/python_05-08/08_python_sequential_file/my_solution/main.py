from app.sequential_file import SequentialFile

if __name__ == "__main__":
    bin_f = "./data/input.bin"
    csv_f = "./data/data.csv"
    block_size = 4
    encoding = 'ascii'
    fmt: str = "i7s12s5si"
    file = SequentialFile(bin_f, block_size, encoding, fmt)
    file.load_from_csv(csv_f)
    file.find_record_by_key(13)

