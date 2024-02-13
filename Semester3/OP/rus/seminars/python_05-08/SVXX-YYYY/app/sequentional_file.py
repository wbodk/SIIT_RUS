from typing import Dict
from record import Record
from binary_file import BinaryFile

class SequentionalFile(BinaryFile):
    def __init__(self, filename: str, record: Record, blocking_factor: int, empty_record: Dict, empty_key: int = -1):
        super().__init__(filename, record, blocking_factor, empty_record, empty_key)
    
    def init_file(self) -> None:
        with open(self.filename, 'wb') as f:
            block = self.blocking_factor * [self.empty_record]
            self.write_block(f, block)
    
    def print_file(self) -> None:
        with open(self.filename, 'rb') as f:
            i = 0
            while True:
                block = self.read_block(f)
                if block == []:
                    return None
                print('Block {i}'.format(i = i + 1))
                for rec in block:
                    print(rec, '\n')
                i += 1

    def __find_by_id(self, id: int) -> tuple:
        with open(self.filename, 'rb') as f:
            block_index = 0
            while True:
                block = self.read_block(f)
                if block == []:
                    return None
                
                for i in range(self.blocking_factor):
                    if block[i]['id'] == id and block[i]['status'] == 1:
                        return block_index, i
                block_index += 1
            
    def insert_record(self, record: Dict) -> bool:
        found = self.__find_by_id(record['id'])

        if found is not None:
            return False
        
        with open(self.filename, 'rb+') as f:
            block = [record]
            while True:
                block = sorted([rec for rec in self.read_block(f) + block if rec['id'] >= 0], key= lambda k: k['id'])
                f.seek(-self.block_size, 1)
                if len(block) < self.blocking_factor:
                    block += (self.blocking_factor - len(block)) * [self.empty_record]
                self.write_block(f, block[:self.blocking_factor])
                block = block[self.blocking_factor:]
                if block == []:
                    return True
                if self.read_block(f) == [] and block != []:
                    block += (self.blocking_factor - len(block)) * [self.empty_record]
                    self.write_block(f, block)
                    return True
                f.seek(-self.block_size, 1)
    
    def phys_delete_by_id(self, id: int) -> bool:
        found = self.__find_by_id(id)

        if found is None:
            return False

        block_index, rec_index = found

        with open(self.filename, 'rb+') as f:
            while True:
                f.seek(self.block_size * block_index, 0)
                block = self.read_block(f)

                i = rec_index
                while i < self.blocking_factor - 1:
                    block[i] = block[i + 1]
                    i += 1

                block_index += 1
                rec_index = 0
                
                next_block = self.read_block(f)
                f.seek(-self.block_size, 1)
                if next_block == []:
                    block[self.blocking_factor - 1] = self.empty_record
                    self.write_block(f, block)
                    break

                block[self.blocking_factor - 1] = next_block[0]
                f.seek(-self.block_size, 1)
                self.write_block(f, block)
            
            f.seek(-self.block_size, 1)
            if self.read_block(f)[0] == self.empty_record:
                f.seek(-self.block_size, 1)
                f.truncate()
        