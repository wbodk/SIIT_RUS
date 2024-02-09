def check_vars(s: str, s_bin: bytes, is_ascii: bool,
               write=False, bytes_written=0) -> None:
    if is_ascii:
        print("String:", s)
        print(f"Encoded string is ASCII:", end=" ")
        for byte in s_bin:
            print(f"{byte:x}", end=" ")
        print()
    else:
        print("String:", s)
        print(f"Encoded string is UTF-8:", end=" ")
        for byte in s_bin:
            print(f"{byte:x}", end=" ")
        print()
    print("Length:", len(s))
    print("Length in binary:", len(s_bin))
    if write:
        print("Bytes written:", bytes_written)
    print()
    pass


def main(file1: str, file2: str, ascii_str: str, not_ascii_str: str) -> None:
    with open(file1, 'wb') as f:
        s: str = ascii_str
        s_bin: bytes = ascii_str.encode(encoding="ascii")
        bytes_written: int = f.write(s_bin)

    check_vars(s, s_bin, is_ascii=True, write=True, bytes_written=bytes_written)

    with open(file1, 'rb') as f:
        s_bin: bytes = f.read()
        s: str = s_bin.decode(encoding='ascii')

    check_vars(s, s_bin, is_ascii=True)

    s_prev = s
    s = " ".join([s, not_ascii_str])
    print(f"Previous string: {s_prev}")
    print(f"New string: {s}\n")

    with open(file2, 'wb') as f:
        s_bin: bytes = s.encode(encoding="utf-8")
        bytes_written: int = f.write(s_bin)

    check_vars(s, s_bin, is_ascii=False, write=True, bytes_written=bytes_written)

    with open(file2, 'rb') as f:
        s_bin: bytes = f.read()
        s: str = s_bin.decode(encoding="utf-8")

    check_vars(s, s_bin, is_ascii=False)


if __name__ == '__main__':
    filename1: str = "data/bin1_primer3_out.bin"
    filename2: str = "data/bin2_primer3_out.bin"
    string_ascii: str = "The quick brown fox jumps over the ASCII."
    string_not_ascii: str = "Быстрая коричневая лиса прыгнула через АСКДОИ."
    main(filename1, filename2, string_ascii, string_not_ascii)
