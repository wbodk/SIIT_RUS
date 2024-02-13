#!/usr/bin/python
from constants import *
from record import Record
from serial_file import SeriallFile
from sequentional_file import SequentionalFile
from hash_file_my import HashFile

def main():
    filename = '/home/roman/FTN/SIIT_RUS/Semester3/OP/rus/seminars/python_05-08/SVXX-YYYY/data/test3.bin'
    record = Record(ATTRIBUTES, FMT, CODING)
    sf = HashFile(filename, record, F, B, EMPTY_REC)

    sf.init_file()

    sf.insert_record({'id': 1, 'name': '', 'q': 0.0, 'status': 1})
    sf.insert_record({'id': 6, 'name': '', 'q': 0.0, 'status': 1})
    sf.insert_record({'id': 11, 'name': '', 'q': 0.0, 'status': 1})
    sf.insert_record({'id': 16, 'name': '', 'q': 0.0, 'status': 1})
    sf.insert_record({'id': 21, 'name': '', 'q': 0.0, 'status': 1})
    sf.insert_record({'id': 26, 'name': '', 'q': 0.0, 'status': 1})
    sf.insert_record({'id': 31, 'name': '', 'q': 0.0, 'status': 1})

    sf.phys_delete_by_id(31)
    sf.phys_delete_by_id(1)


    print('\n\n')
    sf.print_file()

    # sf.insert_record({'id': 2, 'name': '', 'q': 0.0, 'status': 1})

    # sf.phys_delete_by_id(7)
    # sf.phys_delete_by_id(1)
    # sf.phys_delete_by_id(2)
    # sf.phys_delete_by_id(3)

    

if __name__ == "__main__":
    main()
