import struct


def main():
    IN_FILE = "data/username.csv"
    OUT_FILE = "data/username.bin"
    DELIMITER = ";"
    FORMAT = "12si10s10s"
    ENCODING = "ascii"

    with (open(IN_FILE, 'r') as fin, open(OUT_FILE, 'wb') as fout):
        fin.readline()

        line: str = fin.readline()
        while line:
            fields: list[str] = line.strip().split(DELIMITER)
            record: tuple[str, int, str, str] = (fields[0], int(fields[1]), fields[2], fields[3])

            # noinspection PyTypeChecker
            encoded_record: tuple[bytes, int, bytes, bytes] = \
                tuple([v.encode(ENCODING) if isinstance(v, str) else v for v in record])

            print(f"Record: {record}")
            print(f"Encoded record: {encoded_record}")

            packed_record: bytes = struct.pack(FORMAT, *encoded_record)
            print(f"Wrote {fout.write(packed_record)} bytes\n")

            line = fin.readline()


if __name__ == '__main__':
    main()
