import os


class FileManager:
    @staticmethod
    def get_dbfs_from_directory(directory: str) -> list[str]:
        # get list of *.myDBF files
        dbf_files = [file for file in os.listdir(directory) if file.endswith(".myDBF")]
        return dbf_files

    @staticmethod
    def get_csvs_from_directory(directory: str) -> list[str]:
        # get list *.csv files with header "ID,Timestamp_of_arriving,Room_ID,Imprisonment_in_months"
        csv_files = []
        for file in os.listdir(directory):
            if file.endswith(".csv"):
                file_path = os.path.join(directory, file)
                with open(file_path, 'r') as txt_file:
                    header = txt_file.readline().strip()
                    if header == "ID,Timestamp_of_arriving,Room_ID,Imprisonment_in_months":
                        csv_files.append(file)
        return csv_files
