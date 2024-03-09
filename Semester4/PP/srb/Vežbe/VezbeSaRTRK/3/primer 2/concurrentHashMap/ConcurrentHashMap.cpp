#include "tbb/concurrent_hash_map.h"
#include "tbb/blocked_range.h"
#include "tbb/parallel_for.h"
#include <iostream>

using namespace std;
using namespace tbb;

// TODO: Create a simple MyHashCompare structure
struct MyHashCompare {

};

// A concurrent hash table that maps ints to ints
typedef concurrent_hash_map<int, int, MyHashCompare> IntTable;

// TODO: Create a simple Histogram class which will count the number of occurrences
struct Histogram {
	
};

const size_t N = 1000000;
int Data[N];

// Count occurrences
void main() {
	// Construct empty table
	IntTable table;
	
	// Create some data to work with
	for (int i = 0; i < N; i++) {
		Data[i] = rand() % 10;
	}

	// TODO: Put occurrences into the table using parallel_for

	// Display the occurrences
}
