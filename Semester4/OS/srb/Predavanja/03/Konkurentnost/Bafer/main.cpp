#include <thread>
#include <iostream>
#include "message_box.cpp"

using namespace std;

Message_box<int> buffer(1);

void producer() {
	for (int i = 0; i < 3; i++) {
		int c = i;
		buffer.put(&c);
	}
}

void consumer() {
	for (int i = 0; i < 3; i++) {
		cout << buffer.get() << endl;
	}
}

int main() {
    thread t1(consumer);
	thread t2(producer);

    t1.join();
    t2.join();
}
