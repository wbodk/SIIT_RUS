#!/usr/bin/python

S = "Some string"

with open("p3_out.bin", "wb") as f:
    # When converting a string to a byte array,
    # it's necessary to provide the character encoding
    f.write(S.encode("ASCII"))

with open("p3_out.bin", "rb") as f1, open("p3_out2.bin", "wb") as f2:
    # Read the content and decode it from ASCII, then add some Unicode characters
    p = f1.read()
    p = p.decode("ascii") + " Ж Ђ Ś"

    # Another way to convert a string to bytes
    f2.write(bytes(p, "utf8"))

with open("p3_out2.bin", "rb") as f:
    print("Initial position " + str(f.tell()))
    f.seek(-8, 2)
    print("Position after position change " + str(f.tell()))
    p = f.read()
    p = p.decode("utf8")
    print(p)

if __name__ == '__main__':
    pass
