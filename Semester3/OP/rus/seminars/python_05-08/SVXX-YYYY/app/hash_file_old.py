#!/usr/bin/python

from binary_file import BinaryFile
from record import Record


class HashFile(BinaryFile):
    def __init__(self, filename: str, record: Record, blocking_factor: int, number_of_blocks: int, empty_key=-1):
        BinaryFile.__init__(self, filename, record, blocking_factor, empty_key)
        self.number_of_blocks = number_of_blocks

    def init_file(self):
        rec = self.get_empty_rec()
        block = self.blocking_factor * [rec]
        with open(self.filename, 'wb') as f:
            i = 0
            while i < self.number_of_blocks:
                self.write_block(f, block)
                i += 1

    def print_file(self):
        with open(self.filename, 'rb') as f:
            i = 0
            while i < self.number_of_blocks:
                block = self.read_block(f)
                print('Block {}'.format(i))
                for r in block:
                    print(r)
                i += 1
            print('Overflow:')
            while True:
                rec = self.read_record(f)
                if rec is None: return
                print(rec)

    def hash(self, id: int):
        return id % self.number_of_blocks

    def __insert_overflow(self, f, rec: dict):
        f.seek(self.number_of_blocks * self.block_size)
        while True:
            r = self.read_record(f)
            if r is None:
                break
            if r.get('id') == rec.get('id'):
                if r.get('status') == 1:
                    print('Already exists in Overflow with ID {}'.format(r.get('id')))
                    return
                else:
                    f.seek(-self.record_size, 1)
                    self.write_record(f, rec)
                    return
        self.write_record(f, rec)
        return

    def insert_record(self, rec: dict):
        with open(self.filename, 'rb+') as f:
            id = rec.get('id')
            block_index = self.hash(id)
            f.seek(self.block_size * block_index)
            block = self.read_block(f)
            i = 0
            insert_index = -1
            while i < self.blocking_factor:
                if block[i].get('id') == rec.get('id'):
                    if block[i].get('status') == 1:
                        print('Already exists in Block {} with ID {}'.format(block_index, rec.get('id')))
                        return
                    else:
                        insert_index = i
                        break
                if block[i].get('status') == 0 and insert_index == -1:
                    insert_index = i
                i += 1

            if i >= self.blocking_factor and insert_index == -1:
                self.__insert_overflow(f, rec)
                return

            block[insert_index] = rec
            f.seek(-self.block_size, 1)
            self.write_block(f, block)
            return

    def __find_in_overflow(self, f, id: int):
        f.seek(self.number_of_blocks * self.block_size)
        i = 0
        while True:
            rec = self.read_record(f)
            if rec is None:
                break
            if rec.get('id') == id and rec.get('status') == 1:
                return self.number_of_blocks, i
            i += 1
        print('Record with ID {} not found'.format(id))
        return

    def find_by_id(self, id: int):
        with open(self.filename, 'rb') as f:
            block_index = self.hash(id)
            f.seek(block_index * self.block_size)
            block = self.read_block(f)
            i = 0
            while i < self.blocking_factor and block[i].get('id') != -1:
                if block[i].get('id') == id:
                    if block[i].get('status') == 1:
                        return block_index, i
                    else:
                        break
                i += 1

            if i >= self.blocking_factor:
                return self.__find_in_overflow(f, id)
        print('Record not found!')

    def delete_by_id(self, id: int):
        if self.find_by_id(id) is None:
            return None
        block_index, i = self.find_by_id(id)
        with open(self.filename, 'rb+') as f:
            if block_index < self.number_of_blocks:
                f.seek(self.block_size * block_index)
                block = self.read_block(f)
                i = 0
                while i < self.blocking_factor:
                    if block[i].get('id') == id and block[i].get('status') == 1:
                        block[i]['status'] = 0
                        f.seek(-self.block_size, 1)
                        self.write_block(f, block)
                        return
                    i += 1
            else:
                f.seek(self.number_of_blocks * self.block_size)
                while True:
                    rec = self.read_record(f)
                    if rec is None:
                        break
                    if rec.get('id') == id:
                        if rec.get('status') == 1:
                            rec['status'] = 0
                            f.seek(-self.record_size, 1)
                            self.write_record(f, rec)
                            return
                        else:
                            break
                print('Record not found!')
