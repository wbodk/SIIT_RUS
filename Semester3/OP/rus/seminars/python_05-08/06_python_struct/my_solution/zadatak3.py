import struct


def main(block_number: int = 1, rec_per_block: int = 1) -> int:
    FILENAME = "data/username.bin"
    REC_FORMAT = "12si10s10s"
    RECSIZE = struct.calcsize(REC_FORMAT)
    BLOCK_SIZE = rec_per_block * RECSIZE
    ENCODING = "ascii"

    with open(file=FILENAME, mode='rb') as f:
        f.seek((block_number - 1) * BLOCK_SIZE)
        packed_block: bytes = f.read(BLOCK_SIZE)

        for i in range(len(packed_block) // RECSIZE):
            packed_rec = packed_block[i * RECSIZE:(i + 1) * RECSIZE:]
            rec = [v.decode(ENCODING).strip('\x00') if isinstance(v, bytes) else v
                   for v in struct.unpack(REC_FORMAT, packed_rec)]
            print(rec)

    return 0


if __name__ == '__main__':
    block_number = 2
    rec_per_block = 3
    main(block_number, rec_per_block)
