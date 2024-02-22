#include "WriteOutFile.h"
#include "Counter.h" 

//extern vector<unsigned char> clip2CounterVector;
extern vector<int> clip2CounterVector;

RetVal WriteOutFile(string fileName)
{
	ofstream outputFile(fileName.c_str());

	if (outputFile.is_open() == false)
	{
		cout << "WriteOutFile: Output file " << fileName << " could not be opened." << endl;
		return RET_ERROR;
	}

	unsigned int cnt = 0;
	int i;

	for (i = 0; i < clip2CounterVector.size(); ++i)
	{
		outputFile << i << ":\t" << clip2CounterVector[i] << "\t" << endl;

	}
	outputFile.close();

	return RET_OK;
}
