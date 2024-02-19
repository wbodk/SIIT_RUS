import os  # os.SEEK_SET os.SEEK_CUR os.SEEK_END
from typing import BinaryIO

from app.data_builders import BlockBuilder, RecordBuilder


class BinaryFile:
    def __init__(self, file: BinaryIO, encoding: str = 'ascii', fmt: str = '64s', block_factor: int = 1):
        self.__file: BinaryIO = file
        rbuilder = RecordBuilder().with_fmt(fmt).with_encoding(encoding)
        self._bbuilder: BlockBuilder = BlockBuilder().with_block_factor(block_factor).with_rec_builder(rbuilder)

    def _write_block(self, block) -> None:
        self.__file.write(self._bbuilder.to_bytes(block))

    def _read_block(self) -> list[tuple]:
        encoded = self.__file.read(self._bbuilder.get_block_size())
        return self._bbuilder.from_bytes(encoded)

    def _init_file(self) -> None:
        self.__file.write(self._bbuilder.to_bytes(self._bbuilder.get_empty_block()))

    def _next_block(self, backwards=False) -> int:
        self.__file.seek((-2 * int(backwards) + 1) * self._bbuilder.get_block_size(), os.SEEK_CUR)
        return self.__file.tell()

    def _is_block_empty(self, block: list[tuple]) -> bool:
        return self._bbuilder.get_empty_block() == block

    def _restore(self):
        self.__file.seek(0, os.SEEK_SET)
        return self.__file.tell()

    def _strip(self):
        self.__file.seek(0, os.SEEK_END)
        self._next_block(backwards=True)
        block = self._read_block()
        if self._is_block_empty(block):
            self._next_block(backwards=True)
            if self.__file.tell():
                self.__file.truncate()

    def _is_eof(self) -> bool:
        curr_pos = self.__file.tell()
        self.__file.seek(0, os.SEEK_END)
        end_pos = self.__file.tell()
        self.__file.seek(curr_pos, os.SEEK_SET)
        return end_pos <= curr_pos

    def _set_to_index(self, index: int) -> None:
        self.__file.seek(index * self._bbuilder.get_block_size(), os.SEEK_SET)
