def readfile(filename: str) -> str:
    with open(filename, mode='r', encoding='utf') as f:
        return f.read().replace('\n', '')


def count_chars(string: str) -> int:
    return len(string)


def count_numbers(string: str) -> int:
    letter_count = 0
    for char in string:
        if char.isdigit():
            letter_count += 1
    return letter_count


def count_letters(string: str) -> int:
    letter_count = 0
    for char in string:
        if char.isalpha():
            letter_count += 1
    return letter_count


def count_words(string: str) -> int:
    return len(string.split())


def count_sentences(string: str) -> int:
    sentence_endings = {'.', '!', '?'}
    sentence_count = 0
    for char in string:
        if char in sentence_endings:
            sentence_count += 1
    return sentence_count


if __name__ == '__main__':
    text = readfile('data/in_primer1.txt')

    print("text:", text)
    print("chars:", count_chars(text))
    print("letters:", count_letters(text))
    print("numbers:", count_numbers(text))

    print("words:", count_words(text))
    print("sentences:", count_sentences(text))
