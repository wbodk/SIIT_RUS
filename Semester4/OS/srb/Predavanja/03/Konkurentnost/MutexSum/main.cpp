#include <iostream>
#include <thread>
#include <mutex>

using namespace std;

int a = 0;
mutex m;

void sum() {
	for (int i = 0; i < 100000;  i++) {
		m.lock();
		a += 2;
		m.unlock();
	}
}


int main() {
	thread t1(sum);
	thread t2(sum);

	t1.join();
	t2.join();

	cout << "a = " << a << endl;
}