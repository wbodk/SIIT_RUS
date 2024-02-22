#include <thread>
#include <iostream>

using namespace std;

void telo_prve_niti() {
    for (int i = 0; i < 10; i++)
        cout << i;
}

void telo_druge_niti() {
    cout << "Druga";
}

int main() {
	thread niti[5];

	for (int i = 0; i < 5; i++)
        niti[i] = thread(telo_prve_niti);

    thread druga_nit(telo_druge_niti);

	for (int i = 0; i < 5; i++)
        niti[i].join();

    druga_nit.join();
}