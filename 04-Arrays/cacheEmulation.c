/* Program: cacheEmulation.c
 * Description: Caching the values of a function for reuse.
 * Compilation:
 * gcc -o cacheEmulation cacheEmulation.c -lrt
 * 
 * The -lrt flag is needed for the clock_gettime function on some systems.
 */

#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <time.h>

#define CACHE_SIZE 50

long cache[CACHE_SIZE];
bool cached[CACHE_SIZE];

void updateCache(int i, long val) {
    if (!cached[i]) {
        cache[i] = val;
        cached[i] = true;
    }
}

long fibonacciCC(int n) {
    if (cached[n - 1]) {
        return cache[n - 1];
    }
    
    if (n == 1) {
        updateCache(0, 1);
        return 1;
    }
    
    if (n == 2) {
        updateCache(1, 1);
        return 1;
    }
    
    long f = fibonacciCC(n - 1) + fibonacciCC(n - 2);
    updateCache(n - 1, f);
    return f;
}

long fibonacci(int n) {
    if (n == 1 || n == 2) {
        return 1;
    }
    return fibonacci(n - 1) + fibonacci(n - 2);
}

long nanoTime(void) {
    struct timespec ts;
    clock_gettime(CLOCK_MONOTONIC, &ts);
    return ts.tv_sec * 1000000000L + ts.tv_nsec;
}

int main(int argc, char *argv[]) {
    int NMAX = 10;
    
    if (argc == 2) {
        NMAX = atoi(argv[1]);
    }
    
    // Initialize cache arrays
    for (int i = 0; i < CACHE_SIZE; i++) {
        cached[i] = false;
    }
    
    long fib0, fib1;
    for (int i = 1; i <= NMAX; i++) {
        long e0 = nanoTime();
        fib0 = fibonacci(i);
        long e1 = nanoTime();
        fib1 = fibonacciCC(i);
        long e2 = nanoTime();
        
        if (fib0 == fib1) {
            printf("fib(%-3d) = %-10ld\teps.Recurse = %-10ld\teps.Cached = %-10ld\n",
                   i, fib0, (e1 - e0), (e2 - e1));
        }
    }
    
    return 0;
}