#include "ReadInFile.h"

//extern vector<unsigned char> in2FilterVector;
extern vector<int> in2FilterVector;

RetVal ReadInFile(string fileName)
{

	int sum = 0;
	int x;
	ifstream inFile;

	inFile.open(_D_IN_FILE_NAME);
	if (!inFile) {
		cout << "Unable to open file";
		exit(1); // terminate with error
	}

	while (inFile >> x) {
		in2FilterVector.push_back(x);
	}

	int vel = in2FilterVector.size();
	cout << "SIZE = " << vel << endl;
	inFile.close();
	


    //unsigned char data;
	/*int data;
    FILE* inputFile = fopen(fileName.c_str(), "rb");

	if (inputFile == NULL)
    {
        cout << "ReadInFile: Input file " << fileName << " could not be opened." << endl;
		return RET_ERROR;
	}
	
    while(fread(&data, sizeof(int), 1, inputFile))
    {
		in2FilterVector.push_back(data);
    }

	int size_prvi = in2FilterVector.size();

    fclose(inputFile);*/

    return RET_OK;
}
