#include "Semaphore.hpp"

Semaphore::Semaphore(int initCounter): counter(initCounter) {}

void Semaphore::semWait() {
	unique_lock<mutex> l(m);
	counter --;
	if (counter < 0) 
		cv.wait(l);
}

void Semaphore::semSignal() {
	unique_lock<mutex> l(m);
	counter++;
	if (counter <= 0)
		cv.notify_one();
}