#include <thread>
#include <iostream>
#include <mutex>
#include <condition_variable>

#include "message_box.cpp"

#define BUFFER_SIZE 5
#define NO_OF_THREADS 10

using namespace std;

Message_box<int> buffer(BUFFER_SIZE);

mutex m;
condition_variable read;
condition_variable write;

int no_of_messages = 0; //trenutni broj poruka u baferu

void producer(int value) {
    unique_lock<mutex> l(m);
    while (no_of_messages == BUFFER_SIZE)
        write.wait(l);
    buffer.put(&value);
    cout << "Producer put " << value << endl;
    no_of_messages++;
    read.notify_one();
}

void consumer() {
    unique_lock<mutex> l(m);
    while (no_of_messages == 0)
        read.wait(l);
    cout << "Consumer read: " << buffer.get() << endl;
    no_of_messages--;
    write.notify_one();
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
