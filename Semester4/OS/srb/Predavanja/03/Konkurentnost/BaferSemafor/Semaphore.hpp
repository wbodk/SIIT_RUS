#ifndef __SEMAPHORE_H_ 
#define __SEMAPHORE_H_ 

#include <condition_variable>

using namespace std;

class Semaphore {
	private:
		int counter;
		mutex m;
		condition_variable cv;
	public:
		Semaphore(int initCounter = 1);
		void semWait();
		void semSignal();
};

#endif