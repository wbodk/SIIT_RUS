#include <stack>

using namespace std;

template<class MESSAGE>             
class Message_box {
	private:
		stack<MESSAGE> messages;
		int capacity;
	public:
		Message_box(int c): capacity(c) {} 
		void put(const MESSAGE* message);  
		MESSAGE get();
};


template<class MESSAGE>
void Message_box<MESSAGE>::put(const MESSAGE* message) {
	if (messages.size() < capacity) 
		messages.push(*message);
	else 
		throw new exception;
}

template<class MESSAGE> 
MESSAGE Message_box<MESSAGE>::get() {
	if (messages.empty())
		throw new exception;
	MESSAGE retVal = messages.top();
	messages.pop();
	return retVal;
}