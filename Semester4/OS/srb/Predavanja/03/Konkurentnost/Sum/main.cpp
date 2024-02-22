#include <iostream>
#include <thread>

using namespace std;

int a = 0;

void sum() {
	for (int i = 0; i < 100000;  i++) {
		a += 2;
	}
}


int main() {
	thread t1(sum);
	thread t2(sum);

	t1.join();
	t2.join();

	cout << "a = " << a << endl;
}

