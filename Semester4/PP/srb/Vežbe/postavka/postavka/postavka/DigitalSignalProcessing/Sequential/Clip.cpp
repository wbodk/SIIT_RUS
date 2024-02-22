#include "Clip.h"

extern vector<int> filter2ClipVector;
extern vector<int> clip2CounterVector;


// Funkcija za odsecanje viska vrednosti
RetVal Clip(int lowerValue, int upperValue)
{
     int data;

	// odsecanje svake vrednosti
	const int size_v = filter2ClipVector.size();
	int i;

	for (i = 0; i < size_v; i++) {
		data = filter2ClipVector.at(i);
		if (data<lowerValue)
		{
			data = lowerValue;
		}
		else if (data>upperValue)
		{
			data = upperValue;
		}
		clip2CounterVector.push_back(data);
	}
    return RET_OK;
}
