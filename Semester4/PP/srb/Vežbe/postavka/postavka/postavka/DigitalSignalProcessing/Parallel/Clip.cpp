#include "Clip.h"
#include <iostream>

extern vector<int> clip2CounterVector;
extern vector<int> filter2ClipVector;


// Funkcija za odsecanje viska vrednosti
RetVal Clip(int lowerValue, int upperValue)
{
	// odsecanje svake vrednosti
	const int size_v = filter2ClipVector.size();
	//clip2CounterVector.resize(size_v);
	int i;
	
	for (i = 0; i < size_v; i++) {
		int data = filter2ClipVector.at(i);
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
