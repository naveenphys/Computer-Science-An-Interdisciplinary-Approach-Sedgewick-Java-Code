/* Program: diceSimulation
 * Description: In a throw of a pair of independent dice. Following 
 *              microstates are possible.
 * (1,1) (1,2) (1,3) (1,4) (1,5) (1,6) 
 * (2,1) (2,2) (2,3) (2,4) (2,5) (2,6) 
 * (3,1) (3,2) (3,3) (3,4) (3,5) (3,6) 
 * (4,1) (4,2) (4,3) (4,4) (4,5) (4,6) 
 * (5,1) (5,2) (5,3) (5,4) (5,5) (5,6) 
 * (6,1) (6,2) (6,3) (6,4) (6,5) (6,6)
 * 
 * which correspond to the following values of the sum
 * (2) (3) (4) ( 5) ( 6) ( 7) 
 * (3) (4) (5) ( 6) ( 7) ( 8) 
 * (4) (5) (6) ( 7) ( 8) ( 9) 
 * (5) (6) (7) ( 8) ( 9) (10) 
 * (6) (7) (8) ( 9) (10) (11) 
 * (7) (8) (9) (10) (11) (12) 
 * 
 * Therefore, we find that the 
 * 2 and 12 appear  1 times. 
 * 3 and 11 appear  2 times.
 * 4 and 10 appear  3 times.
 * 5 and  9 appear  4 times.
 * 6 and  8 appear  5 times.
 * 7        appears 6 times.
 * 
 * The program evaluates these probabilities for N Dice simulation.
 * Compilation:
 * gcc diceSimulation.c -o diceSimulation -lm
 */

#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <string.h>

#define SHOW_STATE 0

int main(int argc, char *argv[]) {
    int N = 10;
    if (argc == 2) N = atoi(argv[1]);

    // With N arrays we have 6^N states.
    int mstates_size = (int)pow(6, N);
    int *state = (int *)malloc(N * sizeof(int));
    int *freq = (int *)calloc(6 * N + 1, sizeof(int));
    
    if (state == NULL || freq == NULL) {
        printf("Memory allocation failed!\n");
        return 1;
    }

    for (int m = 1; m <= mstates_size; m++) {
        int ms = m;
        for (int d = 1; d <= N; d++) {
            state[d-1] = (ms - 1) / (int)pow(6, N - d) + 1;
            ms -= (int)pow(6, N - d) * (state[d-1] - 1);
        }
        
        int sum = 0;
        for (int i = 0; i < N - 1; i++) {
            if (SHOW_STATE) printf("%d,", state[i]);
            sum += state[i];
        }
        if (SHOW_STATE) printf("%d\n", state[N-1]);
        sum += state[N-1];
        freq[sum]++;
    }

    // Print the histogram.
    for (int i = N; i < 6 * N + 1; i++) {
        printf("%d\n", freq[i]);
    }

    // Free allocated memory
    free(state);
    free(freq);
    
    return 0;
}