#include <iostream>

#include "tbb/tick_count.h"
#include "tbb/task_group.h"

#define N 10

using namespace tbb;
using namespace std;

static long CutOff = 16;

long SerialFib( long n );
long ParallelFib( long n );

int main(int argc, char* argv[])
{
    long n = N;
    long fibs_serial = 0;
    long fibs_parallel = 0;

    cout << endl << "Serijska verzija racunanja Fibonacijevog elementa..." << endl;

    tick_count startTime = tick_count::now();

    fibs_serial = SerialFib(n);

    tick_count endTime = tick_count::now();

    cout << endl << n << ". fibonacijev broj je " << fibs_serial << "." << endl;
    cout << endl << "Racunanje fibonacijevog broja je trajalo: " << (endTime-startTime).seconds()*1000 << "ms." << endl << endl;

    cout << endl << "Paralelna verzija racunanja Fibonacijevog elementa..." << endl;

    startTime = tick_count::now();

    fibs_parallel = ParallelFib(n);

    endTime = tick_count::now();

    cout << endl << n << ". fibonacijev broj je " << fibs_parallel << "." << endl;
    cout << endl << "Racunanje fibonacijevog broja je trajalo: " << (endTime - startTime).seconds() * 1000 << "ms." << endl << endl;

    return 0;
}

long SerialFib( long n ) { 
    if( n<2 ) { 
        return n; 
    } else {
        return SerialFib(n-1)+SerialFib(n-2); 
    }
}

long ParallelFib( long n ) { 
    long sum = 0;
    task_group g;
    if (n < 2) {
        return n;
    }
    if (n < CutOff) {
        sum = SerialFib(n);
    } else {
        long x, y;
        g.run([&] {x = ParallelFib(n - 1); });
        g.run([&] {y = ParallelFib(n - 2); });
        g.wait();
        sum = x + y;
    }
    
    return sum;
}
