#!/usr/bin/python

IN_FILE = "in.txt"

print("Character by character:")
with open(IN_FILE) as f:
    while True:
        c = f.read(1)
        if not c:
            break
        print(c)

print("\nLine by line:")
with open(IN_FILE) as f:
    while True:
        l = f.readline()
        if not l:
            break
        print(l)

print("\nAll at once:")
with open(IN_FILE) as f:
    content = f.read()
    print(content)

# Outputting lines in reverse order to a new file
with open(IN_FILE) as fin, open("outr.txt", "w") as fout:
    lines = fin.readlines()
    for line in lines[::-1]:
        if line.endswith("\n"):
            fout.write(line)
        else:
            fout.write(line + "\n")

# Outputting characters in reverse order to a new file
with open(IN_FILE) as fin, open("outc.txt", "w") as fout:
    content = fin.read()
    fout.write(content[::-1])

if __name__ == '__main__':
    pass
