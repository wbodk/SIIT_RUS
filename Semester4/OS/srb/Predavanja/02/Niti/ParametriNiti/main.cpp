#include <thread>
#include <iostream>
#include <string>

using namespace std;

void teloNiti(string naziv) {
	cout << "Ispis iz niti " << naziv << endl;
}

int main() {
	cout << "Pocetak programa." << endl;

	thread t1(teloNiti, "prva"); //kreiranje prve niti
	thread* t2 = new thread(teloNiti, "druga"); //kreiranje druge niti (preko pokazivaca)

	t1.join();
	t2->join();

	cout << "Kraj programa." << endl;
}




