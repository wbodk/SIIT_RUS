import struct


def main():
    IN_FILE = 'data/in_zadatak1.txt'
    OUT_FILE = 'data/out_zadatak1.bin'
    HEADER_FORMAT = 'ii'
    HEADER_SIZE = struct.calcsize(HEADER_FORMAT)

    with open(IN_FILE, 'r') as fin, open(OUT_FILE, 'wb') as fout:
        line = fin.readline()
        COORD_NUM = len(line.strip().split(" "))
        POINT_FORMAT = 'd' * COORD_NUM
        RECSIZE = struct.calcsize(POINT_FORMAT)

        packed_points: bytes = bytearray()
        points_counter: int = 0

        line = fin.readline()
        while line:
            points_counter += 1
            point = (float(component) for component in line.strip().split(" "))
            packed_points += struct.pack(POINT_FORMAT, *point)
            line = fin.readline()

        header_packed: bytes = struct.pack(HEADER_FORMAT, *tuple([points_counter, COORD_NUM]))
        packed_data: bytes = header_packed + packed_points

        print("Header:\n\tBinary:\t", packed_data[:HEADER_SIZE],
              "\n\tData\t", struct.unpack(HEADER_FORMAT, packed_data[:HEADER_SIZE]))
        for i in range(points_counter):
            print(f"Point № {i}:\n\tBinary:\t", end='')

            pack: bytes = packed_data[HEADER_SIZE + i * RECSIZE: HEADER_SIZE + (i + 1) * RECSIZE]
            for byte in pack:
                print(byte, end=' ')

            point = struct.unpack(POINT_FORMAT, pack)
            print("\n\tData:\t", point)

        fout.write(header_packed + packed_points)


if __name__ == '__main__':
    main()
