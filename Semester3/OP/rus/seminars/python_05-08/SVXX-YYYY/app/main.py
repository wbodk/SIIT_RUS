#!/usr/bin/python
from constants import *
from record import Record
from serial_file import SeriallFile
from sequentional_file import SequentionalFile

def main():
    filename = '/home/roman/FTN/SIIT_RUS/Semester3/OP/rus/seminars/python_05-08/SVXX-YYYY/data/test2.bin'
    record = Record(ATTRIBUTES, FMT, CODING)
    sf = SequentionalFile(filename, record, F, EMPTY_REC)

    sf.init_file()

    sf.print_file()

    sf.insert_record({'id': 1, 'name': '', 'q': 0.0, 'status': 1})
    sf.insert_record({'id': 3, 'name': '', 'q': 0.0, 'status': 1})
    sf.insert_record({'id': 4, 'name': '', 'q': 0.0, 'status': 1})

    sf.insert_record({'id': 5, 'name': '', 'q': 0.0, 'status': 1})
    sf.insert_record({'id': 6, 'name': '', 'q': 0.0, 'status': 1})
    sf.insert_record({'id': 7, 'name': '', 'q': 0.0, 'status': 1})

    sf.insert_record({'id': 2, 'name': '', 'q': 0.0, 'status': 1})

    sf.phys_delete_by_id(7)
    sf.phys_delete_by_id(1)
    sf.phys_delete_by_id(2)
    sf.phys_delete_by_id(3)




    sf.print_file()
    

if __name__ == "__main__":
    main()
