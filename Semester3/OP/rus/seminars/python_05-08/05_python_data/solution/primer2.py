#!/usr/bin/python

import sys

# Define an integer N with the value 12345
N = 12345

# Write the integer N to a binary file named "p2_out.bin" in little-endian byte order
with open("p2_out.bin", "wb") as f:
    # Determine the number of bytes N occupies
    n_size = sys.getsizeof(N)
    # Convert the integer to a byte array
    n_bytes = N.to_bytes(n_size, "little")
    print(n_bytes)
    f.write(n_bytes)

# Read the value of N from "p2_out.bin", multiply it by 2,
# and write the result to a new binary file named "p2_out1.bin" in little-endian byte order
with open("p2_out.bin", "rb") as f1, open("p2_out1.bin", "wb") as f2:
    # Read the bytes representing N from the file
    n_bytes = f1.read(sys.getsizeof(N))
    # Convert the byte array to an integer
    m = int.from_bytes(n_bytes, "little")
    print(m)
    # Multiply the integer by 2
    m *= 2
    # Write the result to a new binary file
    f2.write(m.to_bytes(sys.getsizeof(m), "little"))

# The script can be run as a standalone program
if __name__ == '__main__':
    pass
