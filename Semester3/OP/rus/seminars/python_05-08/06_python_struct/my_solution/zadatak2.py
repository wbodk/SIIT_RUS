import struct

import numpy as np


def main():
    IN_FILE = 'data/out_zadatak1.bin'
    HEADER_FORMAT = 'ii'
    HEADER_SIZE = struct.calcsize(HEADER_FORMAT)

    with open(IN_FILE, 'rb') as f:
        packed_data: bytes = f.readline()
        [num, dim] = struct.unpack(HEADER_FORMAT, packed_data[:HEADER_SIZE])

        POINT_FORMAT = 'd' * dim
        RECSIZE = struct.calcsize(POINT_FORMAT)

        centroid = np.zeros(shape=(1, dim))
        for i in range(num):
            offset = HEADER_SIZE + RECSIZE * i
            packed_point = packed_data[offset:offset + RECSIZE:]
            point = struct.unpack(POINT_FORMAT, packed_point)
            centroid += np.array(point)

        centroid /= num
        print(centroid.round(2))


if __name__ == '__main__':
    main()
