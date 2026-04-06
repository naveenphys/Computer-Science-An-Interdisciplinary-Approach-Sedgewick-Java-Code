#include <stdio.h>
#include <stdlib.h>
#include <math.h>

/* harmonicNumber
 * Description: In mathematics, the n-th harmonic number is the sum of the 
 *              reciprocals of the first n natural numbers:
 *              H_n=1+ 1/2 + 1/3 + ... + 1/n
 */

// Function to find GCD using Euclidean algorithm
long long gcd(long long a, long long b) {
    if (a == 0 || b == 0) {
        return 0;
    }
    
    a = llabs(a);
    b = llabs(b);
    
    while (b != 0) {
        long long t = b;
        b = a % b;
        a = t;
    }
    return a;
}

int main(int argc, char *argv[]) {
    long long N = 4;
    
    if (argc == 2) {
        N = atoll(argv[1]);
        // We get an overflow at N = 43
        if (N > 43) N = 43;
    }
    
    // Numerator and denominator of the number
    long long num = 1;
    long long den = 1;
    double sum = 1.0;
    
    for (long long i = 2; i <= N; i++) {
        num = num * i + den;
        den *= i;
        long long factor = gcd(num, den);
        num /= factor;
        den /= factor;
        sum += (1.0 / i);
        printf("i = %lld\t%lld/%lld\t%f\t%f\n", i, num, den, 
               (double)num / den, sum);
    }
    
    printf("Direct sum: H_%lld = %f\n", N, sum);
    printf("Precise sum: H_%lld = %f\n", N, (double)num / den);
    printf("Difference = %f\n", sum - (double)num / den);
    
    return 0;
}
