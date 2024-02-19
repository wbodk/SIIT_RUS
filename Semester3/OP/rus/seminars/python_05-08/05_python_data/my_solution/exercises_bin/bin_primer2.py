import sys


def check(filename, num, num_size, num_bytes,
          write=True, bytes_written=0, print_in_bytes=False) -> None:
    if write:
        operation = "Write"
    else:
        operation = "Read"

    print(f"Operation '{operation}'")
    print(f"file: '{filename}'")
    print("number: ", num)
    print("size: ", num_size)
    if print_in_bytes:
        print("bytes: ")
        for byte in num_bytes:
            print(bin(byte), end=' ')
    else:
        print(f"bytes: {num_bytes}")
    if write:
        print("Bytes written: ", bytes_written)
    print()


# noinspection PyTypeChecker
def main(filename1: str, filename2: str, num: int, byte_order: str, signed: bool = True) -> int:
    with open(filename1, mode="wb") as f:
        num_size: int = sys.getsizeof(int())
        num_bytes: bytes = int.to_bytes(num, num_size, byteorder=byte_order, signed=signed)
        bytes_written = f.write(num_bytes)

    check(filename1, num, num_size, num_bytes, write=True, bytes_written=bytes_written)

    with open(filename1, mode="rb") as f:
        num_bytes: bytes = f.read(sys.getsizeof(int()))
        num: int = int.from_bytes(num_bytes, byteorder=byte_order, signed=signed)

    check(filename1, num, num_size, num_bytes, write=False)

    prev_num = num
    # num = num >> 1
    num *= 2
    print(f"number was changed: {prev_num} -> {num}\n")

    with open(filename2, mode="wb") as f:
        num_bytes: bytes = int.to_bytes(num, num_size, byteorder=byte_order, signed=signed)
        bytes_written: int = f.write(num_bytes)

    check(filename2, num, num_size, num_bytes, write=True, bytes_written=bytes_written)

    with open(filename2, mode="rb") as f:
        num_bytes: bytes = f.read(sys.getsizeof(int()))
        num: int = int.from_bytes(num_bytes, byteorder=byte_order, signed=signed)

    check(filename2, num, num_size, num_bytes, write=False)
    return 0


if __name__ == "__main__":
    n: int = (pow(2, 28 * 8) - 1) // 2
    byteorder: str = "big"
    signed: bool = False
    file1: str = "data/bin1_primer2_out.bin"
    file2: str = "data/bin2_primer2_out.bin"
    rc = main(file1, file2, n, byteorder, signed=signed)
    print("Program finished with return code", rc)
