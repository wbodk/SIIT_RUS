#include <thread>
#include <iostream>

#include "message_box.cpp"

#define BUFFER_SIZE 5
#define NO_OF_THREADS 10

using namespace std;

Message_box<int> buffer(BUFFER_SIZE);

mutex console_lock;

void producer(int value) {
	buffer.put(&value);
	unique_lock<mutex> l(console_lock);
	cout << "Producer put: " << value << endl;
}

void consumer() {
    unique_lock<mutex> l(console_lock);
    cout << "Consumer read: " << buffer.get() << endl;
}

int main() {
    thread consumers[NO_OF_THREADS];
    thread producers[NO_OF_THREADS];

    for (int i = 0; i < NO_OF_THREADS; i++) {
        producers[i] = thread(producer, i);
        consumers[i] = thread(consumer);
    }

    for (int i = 0; i < NO_OF_THREADS; i++) {
        producers[i].join();
        consumers[i].join();
    }
}
