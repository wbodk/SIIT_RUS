def complement_and_write(input_file, output_header_file, output_complement_file):
    with open(input_file, 'rb') as f:

        header = f.read(14)
        size_of_file = int.from_bytes(header[2:7], byteorder='little')
        size_of_header = int.from_bytes(header[10:], byteorder='little')

        complemented_int8_dump = [255 - int(byte) for byte in f.read()]
        dump_to_string = [str(byte) for byte in bytearray(complemented_int8_dump)]

    with open(output_header_file, 'w') as f_header:
        f_header.write(str(size_of_file) + " " + str(size_of_header))

    with open(output_complement_file, 'w') as f_complement:
        f_complement.write(str(" ".join(dump_to_string)))


if __name__ == '__main__':
    complement_and_write("data/in_zadatak5.bmp",
                         "data/header_output.txt",
                         "data/complement_output.txt")
