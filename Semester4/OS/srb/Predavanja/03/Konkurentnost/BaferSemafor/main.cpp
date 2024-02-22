#include <thread>
#include <iostream>
#include "message_box.cpp"
#include "Semaphore.hpp"

#define BUFFER_SIZE 5
#define NO_OF_THREADS 10

using namespace std;

Message_box<int> buffer(BUFFER_SIZE);

Semaphore write(BUFFER_SIZE);
Semaphore read(0);
Semaphore mutex_lock(1);

void producer(int value) {
    write.semWait();
    mutex_lock.semWait();
    buffer.put(&value);
    cout << "Producer put: " << value << endl;
    mutex_lock.semSignal();
    read.semSignal();
}

void consumer() {
    read.semWait();
    mutex_lock.semWait();
    cout << "Consumer read: " << buffer.get() << endl;
    mutex_lock.semSignal();
    write.semSignal();
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
