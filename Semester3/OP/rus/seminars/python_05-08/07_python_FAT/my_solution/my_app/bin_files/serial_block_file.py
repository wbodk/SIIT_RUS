import csv

from my_app.bin_files.binary_file import BinaryFile


class SerialBlockFile(BinaryFile):
    def __init__(self, filename, block_factor=3, header_fmt='i28s', rec_fmt: str = ""):
        super().__init__(filename, header_fmt=header_fmt, block_factor=block_factor, rec_fmt=rec_fmt)
        self.block_factor = block_factor

    # returns decoded record if exists else empty tuple
    def find_by_id(self, id: int) -> tuple:
        res = self._find_by_id(id)
        return res[0] if res else res

    # returns amount of written records
    def add_new_record(self, rec: tuple) -> int:
        if self._find_by_id(rec[0]):
            return -1
        self._restore_file()
        while not self._is_eof():
            if self._insert_record(rec):
                return 1
            self._next_block()
        self._write_block([rec])
        return 1

    # returns amount of records printed
    def print_file(self, sep='\n') -> int:
        self._restore_file()
        recs_counter = 0
        while not self._is_eof():
            block = self._read_block()
            for rec in block:
                print(rec, end=sep)
            recs_counter += len(block)
            self._next_block()
        return recs_counter

    # returns amount of updated records
    def update_record(self, rec: tuple) -> int:
        res = self._find_by_id(rec[0])
        if not res:
            return -1
        self._insert_record(rec, res[1])
        return 1

    # returns amount of updated records
    def delete_record_by_id(self, idx: int) -> int:
        res = self._find_by_id(idx)
        if not res:
            return -1
        self._delete_by_index(res[1])
        return 1

    def mark_as_deleted_by_id(self, idx: int) -> int:
        res = self._find_by_id(idx)
        if not res:
            return -1
        self._mark_as_deleted(res[1])
        return 1

    # returns amount of loaded records
    def load_from_csv(self, filename: str) -> int:
        records = []
        with open(filename, 'r') as file:
            csv_reader = csv.reader(file)

            next(csv_reader, None)
            for row in csv_reader:
                if len(row):
                    records.append(tuple([int(row[0]), int(row[3]), row[1], row[2]]))
        counter = 0
        for rec in records:
            counter += self.add_new_record(rec)
        return counter

    def _find_by_id(self, idx: int) -> tuple:
        self._restore_file()
        while not self._is_eof():
            recs = self._read_block()
            i = 0
            for rec in recs:
                if rec[0] == idx:
                    return tuple([rec, i])
                i += 1
            self._next_block()
        return tuple()
