from typing import Dict, BinaryIO
from record import Record
from binary_file import BinaryFile
import os

class SeriallFile(BinaryFile):
    def __init__(self, filename: str, record: Record, blocking_factor: int, empty_record: Dict, empty_key: int = -1):
        super().__init__(filename, record, blocking_factor, empty_record, empty_key)


    def init_file(self) -> None:
        with open(self.filename, 'wb') as f:
            block = self.blocking_factor * [self.empty_record]
            self.write_block(f, block)
        return


    def __find_by_id(self, id: int) -> tuple:
        with open(self.filename, 'rb') as f:
            block_idx = 0
            while True:
                block = self.read_block(f)
                if block == []:
                    return None
                for i in range(self.blocking_factor):
                    if block[i]['id'] == id and block[i]['status'] == 1:
                        return block_idx, i
                block_idx += 1
                

    def write_record(self, rec: Dict) -> bool:
        if self.__find_by_id(rec['id']) is not None:
            return False
        with open(self.filename, 'rb+') as f:
            while True:
                block = self.read_block(f)

                if block == []:
                    block = self.blocking_factor * [self.empty_record]
                    block[0] = rec
                    self.write_block(f, block)
                    return True
                
                for i in range(self.blocking_factor):
                    if block[i]['status'] == 0:
                        block[i] = rec
                        f.seek(-self.block_size, 1)
                        self.write_block(f, block)
                        return True


    def print_file(self) -> None:
        with open(self.filename, 'rb') as f:
            i = 0
            while True:
                block = self.read_block(f)
                if  block == []:
                    break
                i += 1
                print(f"Block {i}")
                for rec in block:
                    print(rec, '\n')


    def logical_delete_by_id(self, id) -> bool:
        found = self.__find_by_id(id)
        if found is None:
            return False
        
        block_index = found[0]
        rec_index = found[1]

        with open(self.filename, 'rb+') as f:
            f.seek(block_index * self.block_size, 0)
            block = self.read_block(f)
            block[rec_index]['status'] = 0
            f.seek(-self.block_size, 1)
            self.write_block(f, block)


    def phys_delete_by_id(self, id: int):
        found = self.__find_by_id(id)
        if found is None:
            return False

        block_index, rec_index = found

        with open(self.filename, 'rb+') as f:
            while True:
                f.seek(block_index * self.block_size, 0)
                block = self.read_block(f)
                i = rec_index
                while i < self.blocking_factor - 1:
                    block[i] = block[i + 1]
                    i += 1
                
                block_index += 1
                rec_index = 0
                next_block = self.read_block(f)
                if next_block == []:
                    block[self.blocking_factor - 1] = self.empty_record
                    f.seek(-1 * self.block_size, 1)
                    self.write_block(f, block)
                    break
                    
                block[self.blocking_factor - 1] = next_block[0]
                f.seek(-2*self.block_size, os.SEEK_CUR)
                self.write_block(f, block)
            
            f.seek(-self.block_size, 1)
            block = self.read_block(f)
            if block[0] == self.empty_record:
                f.seek(-self.block_size, 1)
                f.truncate()
