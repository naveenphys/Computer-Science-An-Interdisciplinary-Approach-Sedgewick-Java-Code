/* Program: boltzmannDistribution
 * Description: Program to generate samples from a Boltzmann distribution.
 * Key differences from the Java version:
 * Memory management: C requires explicit memory allocation with malloc() and deallocation with free()
 * No built-in array copying: Manual loops are used instead of System.arraycopy()
 * Different random number generation: Uses rand() instead of Math.random(), seeded with srand(time(NULL))
 * Array parameters: Need to pass array sizes explicitly since C doesn't track array lengths
 * No foreach loops: Traditional for loops are used instead
 * Main function: Returns int instead of being void
 * Header files: Includes necessary headers (stdio.h, stdlib.h, time.h)
 * Function prototypes: Required for functions defined after main()
 */
#include <stdio.h>
#include <stdlib.h>
#include <time.h>

/* Function prototypes */
int** generateSamples(int nSamples, int sampleSize, int min, int max, int sum);
int* generateOneSample(int n, int min, int max, int sum);
int* randVec(int n, int min, int max);
int vecSum(int* vec, int n);
void printVector(int* vec, int N, int length);

int main() {
    int nSamples = 100;
    int sampleSize = 100;
    int min = 1;
    int max = 200;
    int sum = 6000;

    int** samples;
    
    /* Seed the random number generator */
    srand(time(NULL));
    
    samples = generateSamples(nSamples, sampleSize, min, max, sum);
    
    int* hist = (int*)calloc(max - min + 1, sizeof(int));
    for (int i = 0; i < nSamples; i++) {
        for (int j = 0; j < sampleSize; j++) {
            hist[samples[i][j]]++;
        }
    }
    
    for (int h = 0; h < (max - min + 1); h++) {
        printf("%d\n", hist[h]);
    }
    
    /* Free allocated memory */
    for (int i = 0; i < nSamples; i++) {
        free(samples[i]);
    }
    free(samples);
    free(hist);
    
    return 0;
}

/*
 * Generate k sets of n random number between min and max whose sum
 * matches sum.
 */
int** generateSamples(int nSamples, int sampleSize, int min, int max, int sum) {
    int** samples = (int**)malloc(nSamples * sizeof(int*));
    
    int count = 0;
    while (count < nSamples) {
        int* sample = generateOneSample(sampleSize, min, max, sum);
        samples[count] = (int*)malloc(sampleSize * sizeof(int));
        for (int i = 0; i < sampleSize; i++) {
            samples[count][i] = sample[i];
        }
        // printf("%d\n", count);
        // printVector(sample, 10, sampleSize);
        free(sample);
        count++;
    }
    return samples;
}

/*
 * Generate a set of n random number between min and max whose sum matches
 * s.
 */
int* generateOneSample(int n, int min, int max, int sum) {
    int* sample = randVec(n, min, max);
    while (vecSum(sample, n) != sum) {
        free(sample);
        sample = randVec(n, min, max);
    }
    return sample;
}

/*
 * Generate n random numbers in the interval [min, max).
 */
int* randVec(int n, int min, int max) {
    int* rnd = (int*)malloc(n * sizeof(int));
    int range = max - min;
    for (int i = 0; i < n; i++) {
        rnd[i] = min + (rand() % range);
    }
    return rnd;
}

/*
 * Calculate the sum of all elements of an array.
 */
int vecSum(int* vec, int n) {
    int s = 0;
    for (int i = 0; i < n; i++)
        s += vec[i];
    return s;
}

/*
 * Print a vector such that each row has length N
 */
void printVector(int* vec, int N, int length) {
    for (int i = 0; i < length; i++) {
        if (i != 0 && i % N == 0) {
            printf("\n");
            printf("%3d ", vec[i]);
        } else {
            printf("%3d ", vec[i]);
        }
    }
    printf("\n");
}