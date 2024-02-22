#include <queue>
#include <mutex>
#include <condition_variable>

using namespace std;

template<class MESSAGE>
class Message_box {
	private:
		queue<MESSAGE> messages;
		int capacity;
		mutex m;
		condition_variable write;
		condition_variable read;
	public:
		Message_box(int c): capacity(c) {}
		void put(const MESSAGE* message);
		MESSAGE get();
};


template<class MESSAGE>
void Message_box<MESSAGE>::put(const MESSAGE* message) {
	unique_lock<mutex> l(m);
	while (messages.size() == capacity)
		write.wait(l);
	messages.push(*message);
	read.notify_one();
}

template<class MESSAGE>
MESSAGE Message_box<MESSAGE>::get() {
	unique_lock<mutex> l(m);
	while (messages.size() == 0)
		read.wait(l);
	MESSAGE retVal = messages.front();
	messages.pop();
	write.notify_one();
	return retVal;
}
