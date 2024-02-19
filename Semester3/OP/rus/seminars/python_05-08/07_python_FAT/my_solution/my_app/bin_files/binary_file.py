import os
import struct


class BinaryFile:
    def __init__(self, filename: str, header_fmt: str, block_factor: int = 1, rec_fmt: str = "") -> None:
        self._file = None
        self._block_size = block_factor
        self._header_fmt = header_fmt

        if block_factor < 1:
            raise ValueError("Размер блока не может быть меньше одного")

        try:
            self._header_size = struct.calcsize(self._header_fmt)
            self._file = open(filename, mode='rb+')
        except FileNotFoundError:
            if not len(rec_fmt):
                raise ValueError("Ошибка при создании файла")
            self._file = open(filename, mode='wb+')
            self._rec_fmt = rec_fmt
            self._rec_size = struct.calcsize(self._rec_fmt)
            self._len_of_file = 0
            self._write_header()
        except Exception as e:
            raise IOError(f"Ошибка ввода-вывода при открытии файла: {e.args}")

        try:
            self._file.seek(0, os.SEEK_SET)
            [self._len_of_file, self._rec_fmt] = self._read_header()
            self._rec_fmt = self._rec_fmt.decode(encoding="ascii").strip('\0x00')
            self._rec_size = struct.calcsize(self._rec_fmt)
        except IOError:
            raise IOError("Ошибка ввода-вывода при открытии файла")

        self.__restore_file()

    def _next_block(self, backwards: bool = False, step_size: int = 1):
        self.__next_block(backwards=backwards, step_size=step_size)

    def _read_block(self) -> list[tuple]:
        recs = []
        decoded_recs = []
        for i in range(self._block_size):
            if not self._is_rec_empty():
                recs.append(self._read_rec())
            else:
                self._next_rec()
        self.__next_block(backwards=True)
        for rec in recs:
            decoded_recs.append(
                tuple([field.decode('ascii').strip('\0x00')
                       if isinstance(field, bytes) else field
                       for field in rec]))
        return decoded_recs

    def _write_block(self, block: list[tuple]):
        for rec in block:
            self._write_rec(rec)

        self._file.write(bytes([0] * ((self._block_size - len(block)) * (self._rec_size + 1))))
        self._set_eof_pos()
        self._count_records()
        self.__next_block(backwards=True)

    def _is_eof(self) -> bool:
        self._set_eof_pos()
        return self._file.tell() >= self._end_pos

    def _if_block_has_empty_rec(self) -> int:
        for i in range(self._block_size):
            if self._is_rec_empty():
                self._restore_block()
                return i
            self._next_rec()
        self.__next_block(backwards=True)
        return -1

    def _insert_record(self, rec: tuple, rec_index: int = None) -> bool:
        self._restore_block()
        if rec_index is None:
            rec_index = self._if_block_has_empty_rec()
            if rec_index < 0:
                self._restore_block()
                return False
        for i in range(rec_index):
            self._next_rec()
        self._write_rec(rec)
        self._restore_block()
        return True

    def _restore_file(self):
        self.__restore_file()

    def _is_rec_empty(self) -> bool:
        res = self._file.read(1)
        self._restore_rec()
        return res == b'\0'

    def _mark_as_deleted(self, rec_index: int):
        self._restore_block()
        for i in range(rec_index):
            self._next_rec()
        self._file.write(b'\0')
        self._restore_block()

    def _delete_by_index(self, rec_index: int):
        self._restore_block()
        for i in range(rec_index):
            self._next_rec()
        self._write_rec(tuple([0, 0, "".encode('ascii'), "".encode("ascii")]), save=False)
        self._restore_block()

    def _is_block_empty(self) -> bool:
        counter = self._block_size
        for i in range(self._block_size):
            counter -= 1 if not self._is_rec_empty() else 0
            self._next_rec()
        self._restore_block()
        if counter == 0: self.__next_block(backwards=True)
        return not counter

    def _set_eof_pos(self) -> None:
        curr_pos = self._file.tell()
        self._file.seek(0, os.SEEK_END)
        end_file = self._file.tell()
        self._file.seek(curr_pos, os.SEEK_SET)
        self._end_pos = end_file

    def _next_rec(self, backwards=False, step_size: int = 1) -> None:
        self._file.seek((self._rec_size + 1) * (-1 if backwards else 1) * step_size, os.SEEK_CUR)

    def __next_block(self, backwards=False, step_size: int = 1) -> None:
        self._file.seek((self._rec_size + 1) * self._block_size * (-1 if backwards else 1) * step_size, os.SEEK_CUR)

    def _restore_rec(self) -> None:
        self._file.seek(-1 * ((self._file.tell() - self._header_size) % (self._rec_size + 1)), os.SEEK_CUR)

    def _restore_block(self) -> None:
        self._file.seek(-1 * ((self._file.tell() - self._header_size) % (self._block_size * (self._rec_size + 1))),
                        os.SEEK_CUR)

    def __restore_file(self) -> None:
        self._file.seek(self._header_size, os.SEEK_SET)

    def _read_rec(self) -> tuple:
        return struct.unpack(self._rec_fmt, bytes(bytearray(self._file.read(self._rec_size + 1)[1:])))

    def _write_rec(self, enc_rec: tuple, save=True):
        enc_rec = tuple([elem.encode() if isinstance(elem, str) else elem for elem in enc_rec])
        dump_rec = bytes([save]) + struct.pack(self._rec_fmt, *enc_rec)
        self._file.write(dump_rec)

    def _read_header(self) -> tuple:
        curr_pos = self._file.tell()
        self._file.seek(0, os.SEEK_SET)
        res = struct.unpack(self._header_fmt, self._file.read(self._header_size))
        self._file.seek(curr_pos, os.SEEK_SET)
        return res

    def _write_header(self) -> None:
        curr = self._file.tell()
        self._file.seek(0, os.SEEK_SET)
        self._file.write(struct.pack(self._header_fmt,
                                     *tuple([
                                         self._len_of_file, self._rec_fmt.encode(encoding='ascii')
                                     ])))
        self._file.seek(curr, os.SEEK_SET)

    def _count_records(self):
        self._len_of_file = (os.fstat(self._file.fileno()).st_size - self._header_size) // self._rec_size

    def __del__(self):
        if self._file is not None and not self._file.closed:
            self._write_header()
            self._file.close()
