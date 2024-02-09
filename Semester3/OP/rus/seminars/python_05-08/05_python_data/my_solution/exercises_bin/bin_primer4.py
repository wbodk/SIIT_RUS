#!/usr/bin/python

import pickle

# Define a dictionary representing information about dogs
dogs_dict = {'Ozzy': 3, 'Filou': 8, 'Luna': 5,
             'Skippy': 10, 'Barco': 12, 'Balou': 9, 'Laika': 16}

# Specify the filename for storing the dictionary in binary format
filename = 'data/dogs_out.bin'

# Serialize and write the dictionary to a binary file
with open(filename, 'wb') as outfile:
    pickle.dump(dogs_dict, outfile)

# Deserialize and read the dictionary from the binary file
with open(filename, 'rb') as infile:
    new_dict = pickle.load(infile)

# Print the deserialized dictionary, compare it with the original, and display its type
print(new_dict)
print(new_dict == dogs_dict)
print(type(new_dict))

# The script can be run as a standalone program
if __name__ == '__main__':
    pass
