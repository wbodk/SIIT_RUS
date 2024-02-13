from typing import Dict
from binary_file import BinaryFile
from record import Record

class HashFile(BinaryFile):
    def __init__(self, filename: str, record: Record, blocking_factor: int, number_of_blocks: int, empty_record: Dict, empty_key: int = -1):
        super().__init__(filename, record, blocking_factor, empty_record, empty_key)
        self.number_of_blocks = number_of_blocks

    def init_file(self) -> None:
        block = self.blocking_factor * [self.empty_record]
        with open(self.filename, 'wb') as f:
            for i in range(self.number_of_blocks):
                self.write_block(f, block)
    
    def print_file(self) -> None:
        with open(self.filename, 'rb') as f:
            i = 0
            while i < self.number_of_blocks:
                block = self.read_block(f)
                print(f"Block {i}")
                for rec in block:
                    print(rec, '\n')
                i += 1
            
            i=0
            while True:
                block = self.read_block(f)
                if block == []:
                    break
                print(f'Overflow block {i}')
                for rec in block:
                    print(rec, '\n')
                i +=1
    
    def hash(self, id: int) -> int:
        return id%self.number_of_blocks
    
    def __find_by_id(self, id: int) -> tuple:
        block_index = self.hash(id)
        with open(self.filename, 'rb') as f:
            f.seek(block_index * self.block_size, 0)
            block = self.read_block(f)
            for i in range(self.blocking_factor):
                if block[i]['id'] == id and block[i]['status'] == 1:
                    return block_index, i
        return self.__find_in_overflow(id)
    
    def __find_in_overflow(self, id: int) -> tuple:
        block_index = self.number_of_blocks
        with open(self.filename, 'rb') as f:
            f.seek(self.number_of_blocks * self.block_size, 0)
            while True:
                block = self.read_block(f)
                if block == []:
                    return None
                for i in range(self.blocking_factor):
                    if block[i]['id'] == id and block[i]['status'] == 1:
                        return block_index, i
                block_index += 1

    def __insert_in_overflow(self, record: Dict) -> bool:
        with open(self.filename, 'rb+') as f:
            f.seek((self.number_of_blocks) * self.block_size, 0)
            while True:
                block = self.read_block(f)
                if block == []:
                    block = self.blocking_factor * [self.empty_record]
                    block[0] = record
                    self.write_block(f, block)
                    return True
                for i in range(self.blocking_factor):
                    if block[i]['status'] == 0:
                        block[i] = record
                        f.seek(-self.block_size, 1)
                        self.write_block(f, block)
                        return True

    def insert_record(self, record: Dict) -> bool:
        id = record['id']
        if self.__find_by_id(id) is not None:
            return False
        
        block_index = self.hash(id)
        with open(self.filename, 'rb+') as f:
            f.seek(self.block_size * block_index, 0)
            block = self.read_block(f)
            for i in range(self.blocking_factor):
                if block[i]['status'] == 0:
                    block[i] = record
                    f.seek(-self.block_size, 1)
                    self.write_block(f, block)
                    return True
        
        return self.__insert_in_overflow(record)
    
    def phys_delete_by_id(self, id: int) -> bool:
        res = self.__find_by_id(id)
        if res is None:
            return False
        
        block_index, rec_index = res
        with open(self.filename, 'rb+') as f:
            if block_index < self.number_of_blocks:
                f.seek(self.block_size * block_index, 0)
                block = self.read_block(f)
                if rec_index == self.blocking_factor-1:
                    block[rec_index] = self.empty_record
                    return True
                i = rec_index
                while i < self.blocking_factor - 1:
                    block[i] = block[i+1]
                    i += 1
                block[self.blocking_factor - 1] = self.empty_record
                f.seek(-self.block_size, 1)
                self.write_block(f, block)
                return True
            
            f.seek(self.block_size * block_index, 0)
            while True:
                block = self.read_block(f)
                
                i = rec_index
                while i < self.blocking_factor - 1:
                    block[i] = block[i + 1]
                    i +=1
                next_block = self.read_block(f)
                if next_block == []:
                    block[self.blocking_factor - 1] = self.empty_record
                    f.seek(-self.block_size, 1)
                    self.write_block(f, block)
                    return True
                block[self.blocking_factor - 1] = next_block[0]
                f.seek(-2 * self.block_size, 1)
                self.write_block(f, block)
                    
            
            

