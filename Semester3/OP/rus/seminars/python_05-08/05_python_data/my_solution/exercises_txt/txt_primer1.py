def read_by_chars(file_name: str) -> None:
    with open(file_name, mode='r') as file:
        while True:
            char = file.read(1)
            if not char:
                break
            print(char, end='', sep='')
    return


def read_by_lines(file_name: str) -> None:
    with open(file_name, mode='r') as file:
        for line in file:
            line = file.readline()
            if not line:
                break
            print(line, end='', sep='')
    return


def read_file(file_name: str) -> str:
    with open(file_name, mode='r') as file:
        text = file.read()
        print(text)
        return text


def reverse_file_lines(filename_in: str, filename_out: str) -> None:
    with open(filename_in, mode='r') as file_in:
        lines_arr = file_in.readlines()
        lines_arr[-1] = lines_arr[-1] + "\n"

    with open(filename_out, mode='w') as file_out:
        for line in lines_arr[::-1]:
            file_out.write(line)
    return


def reverse_file(filename_in: str, filename_out: str) -> None:
    with open(filename_in) as file_in:
        text = file_in.read()

    text = text[::-1]

    with open(filename_out, mode='w') as file_out:
        file_out.write(text)
    return


if __name__ == '__main__':
    print("orig:")
    read_file('data/in_primer1.txt')
    print()

    print("reversed file:")
    reverse_file('data/in_primer1.txt', 'data/out1_primer1.txt')
    read_file('data/out1_primer1.txt')
    print()

    print("reversed lines:")
    reverse_file_lines('data/in_primer1.txt', 'data/out2_primer1.txt')
    read_file('data/out2_primer1.txt')
    print()
