import struct


class RecordBuilder:

    def __init__(self, fmt: str = None, encoding: str = 'ascii'):
        self.__fmt = fmt
        self.__fmt_size = struct.calcsize(self.__fmt)
        self.__empty_record: tuple = self.from_bytes(bytes(bytearray([0] * self.get_fmt_size())))
        self.__encoding = encoding

    def with_fmt(self, fmt: str) -> 'RecordBuilder':
        self.__fmt = fmt
        self.__fmt_size = struct.calcsize(self.__fmt)
        self.__empty_record: tuple = self.from_bytes(bytes(bytearray([0] * self.get_fmt_size())))
        return self

    def with_encoding(self, encoding: str) -> 'RecordBuilder':
        self.__encoding = encoding
        return self

    def get_fmt_size(self) -> int:
        return self.__fmt_size

    def get_empty_record(self) -> tuple:
        return self.__empty_record

    def to_bytes(self, rec: tuple) -> bytes:
        rec = tuple([v.encode(encoding=self.__encoding) if isinstance(v, str) else v for v in rec])
        return struct.pack(self.__fmt, *rec)

    def from_bytes(self, buff: bytes) -> tuple:
        rec = struct.unpack(self.__fmt, buff)
        return tuple([v.decode(encoding=self.__encoding) if isinstance(v, bytes) else v for v in rec])


class BlockBuilder:

    def __init__(self, block_factor: int = 1, rec_builder: RecordBuilder = RecordBuilder()) -> None:
        if not isinstance(rec_builder, RecordBuilder):
            raise TypeError("Argument rec_builder must be a RecordBuilder")
        if block_factor < 1:
            raise ValueError("Block factor must be more then one")

        self.__rec_builder = rec_builder
        self.__block_factor = block_factor
        self.__block_size = self.__rec_builder.get_fmt_size() * self.__block_factor
        self.__empty_block: list[tuple] = self.from_bytes(bytes(bytearray([0] * self.get_block_size())))

    def with_block_factor(self, block_factor: int) -> 'BlockBuilder':
        if block_factor < 1:
            raise ValueError("Block factor must be more then one")
        self.__block_factor = block_factor
        self.__block_size = self.__rec_builder.get_fmt_size() * self.__block_factor
        self.__empty_block: list[tuple] = self.from_bytes(bytes(bytearray([0] * self.get_block_size())))
        return self

    def with_rec_builder(self, rec_builder: RecordBuilder) -> 'BlockBuilder':
        if not isinstance(rec_builder, RecordBuilder):
            raise TypeError("Argument rec_builder must be a RecordBuilder")
        self.__rec_builder = rec_builder
        self.with_block_factor(self.__block_factor)
        return self

    def get_block_size(self) -> int:
        return self.__block_size

    def get_empty_block(self) -> list[tuple]:
        return self.__empty_block

    def get_empty_record(self) -> tuple:
        return self.__rec_builder.get_empty_record()

    def from_bytes(self, data: bytes) -> list[tuple]:
        enc_data = bytearray(data)

        dec_data: list[tuple] = []
        rec_size = self.__rec_builder.get_fmt_size()

        for i in range(self.__block_factor):
            enc_rec = enc_data[i * rec_size:(i + 1) * rec_size]
            dec_rec = self.__rec_builder.from_bytes(enc_rec)
            dec_data.append(dec_rec)
        return dec_data

    def to_bytes(self, block: list[tuple]) -> bytes:
        enc_data = bytearray()
        for rec in block:
            enc_data += self.__rec_builder.to_bytes(rec)
        return bytes(enc_data)
