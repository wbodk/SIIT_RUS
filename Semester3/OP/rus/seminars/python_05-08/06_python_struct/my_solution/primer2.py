import struct


def main():
    IN_FILE = "data/username.bin"
    FORMAT = "12si10s10s"
    ENCODING = "ascii"
    RECORD_SIZE = struct.calcsize(FORMAT)

    with open(IN_FILE, 'rb') as fin:
        recs_to_print = [2, 5]

        for i in recs_to_print:
            fin.seek((i - 1) * RECORD_SIZE)
            record_bytes: bytes = fin.read(RECORD_SIZE)

            for byte in record_bytes[:12:]: print(f"{hex(byte)}", end=" ")
            print()
            for byte in record_bytes[12:16:]: print(f"{hex(byte)}", end=" ")
            print()
            for byte in record_bytes[16:26:]: print(f"{hex(byte)}", end=" ")
            print()
            for byte in record_bytes[26:36:]: print(f"{hex(byte)}", end=" ")

            rec: tuple[bytes, int, bytes, bytes] = struct.unpack(FORMAT, record_bytes)
            record: tuple[str, int, str, str] = \
                [v.decode(ENCODING).strip('\x00') if isinstance(v, bytes) else v for v in rec]
            print('\n', record)

if __name__ == '__main__':
    main()
