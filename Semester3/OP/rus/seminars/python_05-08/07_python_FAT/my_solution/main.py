from my_app.bin_files.serial_block_file import SerialBlockFile

if __name__ == '__main__':
    header_fmt = "i28s"
    rec_fmt = "ii12s11s"
    dbf_filenames = ["data/data.myDBF", 'data/data2.myDBF']
    csv_filenames = ["data/data.csv", "data/data2.csv"]
    dbfiles = [SerialBlockFile(filename, header_fmt=header_fmt, block_factor=4, rec_fmt=rec_fmt) for filename in
               dbf_filenames]

    for i in range(len(dbfiles)):
        dbfiles[i].load_from_csv(csv_filenames[i])


    dbfiles[0].print_file()
    print(dbfiles[0].find_by_id(7))
    print(dbfiles[0].delete_record_by_id(7))
    print(dbfiles[0].find_by_id(7))
    print(dbfiles[0].add_new_record(tuple([2222, 123, "2023-12-15", '55FR7'])))
    dbfiles[0].print_file()
    print(dbfiles[0].update_record(tuple([2222, 8, "2023-12-15", '55RFC'])))
    dbfiles[0].print_file()

    dbfiles[1].print_file()
