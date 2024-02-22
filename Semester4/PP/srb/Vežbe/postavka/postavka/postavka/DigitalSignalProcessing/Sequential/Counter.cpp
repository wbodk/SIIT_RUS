#include "Counter.h"
#include <iostream>

extern vector<int> clip2CounterVector;

//Funkcija za prebrojavanje parnih i neparnih brojeva
RetVal Counter()
{
	const int size_v = clip2CounterVector.size();
	int evenNumbers = 0;
	int oddNumbers = 0;

	for (int i = 0; i < size_v; i++) {
		if ((int)clip2CounterVector[i] % 2 == 0) {
			evenNumbers++;
		} else {
			oddNumbers++;
		}
	}

	cout << "There are " << size_v << " elements." << endl;
	cout << evenNumbers << " of them are even, and " << oddNumbers << " are odd." << endl;

	if ((evenNumbers + oddNumbers) == size_v) {
		cout << "Looks all right." << endl;
	}
	else {
		cout << "Hold up - somethin' ain't right here..." << endl;
	}

	return RET_OK;
}
