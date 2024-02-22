#include <thread>
#include <iostream>

using namespace std;

void teloNiti() {
	cout << "Ispis iz niti " << endl;
}

int main() {
	cout << "Pocetak programa." << endl;

	thread t1(teloNiti); //kreiranje prve niti
	thread* t2 = new thread(teloNiti); //kreiranje druge niti (preko pokazivaca)

	t1.join();	
	t2->join();

	cout << "Kraj programa." << endl;
}




