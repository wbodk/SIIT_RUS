import csv
import struct
from typing import BinaryIO

from app.my_binary import BinaryFile


class SequentialFile(BinaryFile):
    def __init__(self, filename: str, blocking_factor: int, encoding: str, fmt: str):
        self.__filename = filename
        self.__blocking_factor = blocking_factor
        self.__is_initialized = False
        file: BinaryIO = None

        try:
            struct.calcsize(fmt)
        except Exception as e:
            raise ValueError("Invalid format") from e

        try:
            file = open(filename, mode='rb+')
            self.__is_initialized = True
        except IOError as e:
            file = open(filename, mode='wb+')
        except Exception as e:
            raise IOError(f"Could not open or create file: {e.args}")
        finally:
            super().__init__(file, encoding=encoding, fmt=fmt, block_factor=blocking_factor)
            if not self.__is_initialized:
                self._init_file()
                self.__is_initialized = True
            self._restore()

    def load_from_csv(self, csv_file: str):
        with open(csv_file, 'r') as f:
            reader = csv.reader(f)
            next(reader)
            for row in reader:
                print(row)
                if not row: continue
                record = tuple([
                    int(row[0]), row[1], row[2],
                    row[3], int(row[4])
                ])
                self.add_record(record)

    def find_record_by_key(self, key: int) -> tuple:
        res = self._find_record_by_key(key, equal=True)
        return res[0] if res else None

    def add_record(self, record):

        self._strip()
        pass

    def delete_record_by_key(self, key):
        not_exists = self._find_record_by_key(key, equal=False, more=False)
        if not_exists:
            return False

        index = not_exists[1]
        self._next_block(backwards=True)
        block = self._read_block()
        block.pop(index)
        while not self._is_eof():
            block += self._read_block()
            self._next_block(backwards=True)
            self._next_block(backwards=True)
            self._write_block(block[:3])
            block = block[3:]
            self._next_block()

        self._next_block(backwards=True)
        block.append(self._bbuilder.get_empty_record())
        self._write_block(block)
        self._strip()
        return True

    def update_record(self, new_rec, key: int) -> bool:
        not_exists = self._find_record_by_key(key, equal=True, more=False)
        if not_exists:
            return False
        index = not_exists[1]

        self._next_block(backwards=True)
        block: list[tuple] = self._read_block()
        self._next_block(backwards=True)
        block[index] = new_rec
        self._write_block(block)
        return True

    def _find_record_by_key(self, key: int, equal=True, more=False) -> tuple:
        self._restore()
        while not self._is_eof():
            block: list[tuple] = self._read_block()
            for i in range(len(block)):
                rec = block[i]
                if rec[0] == key and equal or \
                        rec[0] < key and not more or \
                        rec[0] > key and more:
                    return tuple([rec, i])
        return tuple()

    def __del__(self):
        if self.file is not None and not self.file.closed:
            self.file.close()
