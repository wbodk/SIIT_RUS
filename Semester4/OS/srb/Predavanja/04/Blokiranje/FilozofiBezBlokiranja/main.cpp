#include <iostream>
#include <thread>
#include <mutex>
#include <condition_variable>

#define MAX_THINKING_PERIOD 10
#define MAX_EATING_PERIOD 10
#define MEAN_TIME 10

using namespace std;
using namespace chrono;

int rand_sync() { 
    static mutex mx;
    unique_lock<mutex> l(mx);
    return rand();
}

int mod5(int a) {
	return (a > 4 ? 0 : a);
}

enum Philosopher_state {
		THINKING = 'T', 
		WAITING_LEFT_FORK = 'L',
		HOLDING_ONE_FORK = 'O',
		WAITING_RIGHT_FORK = 'R',
		EATING = 'E'
};

class Philosopher {	
	int philosopher_id;
	Philosopher_state philosopher_state;
 public:
	Philosopher(): philosopher_state(THINKING) {} 
	void setIdentifier(int id) {philosopher_id = id;}
	int getIdentifier() const {return philosopher_id;}
	void setPhilosopherState(Philosopher_state state) {philosopher_state = state;}
	char displayState() const {return philosopher_state;}
};

Philosopher philosophers[5];

void show() {
	cout << "=======================" << endl;
	for (int i = 0;  i < 5; i++) {
		cout << "Philosopher " << i << ":" << philosophers[i].displayState() << endl;
	}
	cout << "=======================" << endl;

}

class Dining_table {
	enum Fork_state {FREE, BUSY};
	mutex m;
	Fork_state fork_state[5];
	condition_variable fork_available[5];
 public:
	 Dining_table();
	 void take_fork(int fork);
	 void release_fork(int fork);
};

Dining_table::Dining_table() {
	for (int i = 0; i < 5; i++) {
		fork_state[i] = FREE; //all forks are free initially
	}
}

void Dining_table::take_fork(int fork_number) {
	unique_lock<mutex> l(m);
	show();
	while (fork_state[fork_number] == BUSY) 
		fork_available[fork_number].wait(l);

	fork_state[fork_number] = BUSY;
}

void Dining_table::release_fork(int fork_number) {
	unique_lock<mutex> l(m);
	fork_state[fork_number] = FREE;
	fork_available[fork_number].notify_one();
	show();
}

void thread_philosopher(Philosopher& philosopher, Dining_table& dining_table) {
	int id = philosopher.getIdentifier();
	int firstFork, secondFork;
	Philosopher_state firstState, secondState;
	if (id % 2 == 0) { // even philosophers take left fork firstly
		firstFork = id;
		secondFork = mod5(id + 1);
		firstState = WAITING_LEFT_FORK;
		secondState = WAITING_RIGHT_FORK;
	} else { // odd philosophers take right fork firstly
		firstFork = mod5(id + 1);
		secondFork = id;
		firstState = WAITING_RIGHT_FORK;
		secondState = WAITING_LEFT_FORK;
	}

	this_thread::sleep_for(milliseconds(rand_sync() % MAX_THINKING_PERIOD)); //thinking for some random time
	philosopher.setPhilosopherState(firstState);
	dining_table.take_fork(firstFork);
	philosopher.setPhilosopherState(HOLDING_ONE_FORK);	
	this_thread::sleep_for(milliseconds(rand_sync() % MEAN_TIME)); //wait for some random time
	philosopher.setPhilosopherState(secondState);
	dining_table.take_fork(secondFork);
	philosopher.setPhilosopherState(EATING);
	this_thread::sleep_for(milliseconds(rand_sync() % MAX_EATING_PERIOD)); //thinking for some random time
		
	dining_table.release_fork(firstFork);
	philosopher.setPhilosopherState(HOLDING_ONE_FORK);	
	this_thread::sleep_for(milliseconds(rand_sync() % MEAN_TIME)); //thinking for some random time
	dining_table.release_fork(secondFork);
	philosopher.setPhilosopherState(THINKING);	
}



int main() {
	for (int i = 0; i < 5; i++)
		philosophers[i].setIdentifier(i);

	Dining_table dining_table;
	thread philosopher_threads[5];
	for (int i = 0; i < 5; i++) {
		philosopher_threads[i] = thread(thread_philosopher, ref(philosophers[i]), ref(dining_table));
	}

	for (int i = 0; i < 5; i++) {
		philosopher_threads[i].join();
	}
	return 0;
}



