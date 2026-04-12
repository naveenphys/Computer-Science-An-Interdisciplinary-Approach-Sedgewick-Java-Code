#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <time.h>
#include <float.h>

double sqrt_newton(double x);

/* Program: squareRoot
 * Description: Calculate the square root of a number using Newton-Raphson 
 * method.
 * 
 * Consider a real number s, for which y = sqrt(s)
 * We are solving the equation f := y - sqrt(s) = 0 or g:= y^2 - s = 0.
 * Here the independent variable is y. As we vary y, we arrive at a value where
 * the function g = 0.
 * 
 * We assume that we are close to the solution, and curve between the guess and
 * solution can be approximated by a straight line.
 * 
 * f(y) = y^2 - s
 * If yGuess1 is the initial guess. The slope of the tangent is 
 * dg/dy = 2*yGuess1. 
 * Equation of the straight line between (yGuess2, 0) and 
 * (yGuess1, f(yGuess1)) is
 * 
 * (f(yGuess2) - f(yGuess1))/(yGuess2 - yGuess1) = 2*yGuess1
 * => yGuess2 = yGuess1 - (yGuess1 * yGuess1 - x) / (2 * yGuess1);
 * 
 * Dont use the following as a guess !!:
 * Math.pow(10.0, Math.log10(x)/2.0)
 * as it is simply equal to sqrt(x).
 * 
 * gcc -o squareroot squareRoot.c -lm
 */

int main(int argc, char *argv[]) {
    printf("Max double: %e\n", DBL_MAX);
    
    // Default value of x
    double x0 = 1.0;
    if (argc == 2) {
        x0 = atof(argv[1]);
    }
    printf("Finding square root of %f\n", x0);

    // Finding square root using library.
    clock_t tbeg1 = clock();
    double sol1 = sqrt(x0);
    clock_t tend1 = clock();

    clock_t tbeg2 = clock();
    double sol2 = sqrt_newton(x0);
    clock_t tend2 = clock();

    printf("%-22s\t%-22s\t%-22s\t%-22s\n",
            "x", "sqrt", "sqrt_newton", "abs(sqrt-sqrt_newton)");
    printf("%.16e\t%.16e\t%.16e\t%.16e\n",
            x0, sol1, sol2, fabs(sol1 - sol2));
    printf("sqrt       elapsed time: %ld clocks\n", (long)(tend1 - tbeg1));
    printf("sqrt_newton elapsed time: %ld clocks\n", (long)(tend2 - tbeg2));

    printf("sqrt       elapsed time: %f sec\n", ((double)(tend1 - tbeg1))/CLOCKS_PER_SEC);
    printf("sqrt_newton elapsed time: %f sec\n", ((double)(tend2 - tbeg2))/CLOCKS_PER_SEC);
    
    return 0;
}

double sqrt_newton(double x) {
    // Finding square root using Newton-Raphson method.

    const int MAXITER = 100;
    const double TOL = 1e-16;
    
    int exp;
    frexp(x, &exp);
    exp = exp - 1;  // frexp returns exponent such that x = mantissa * 2^exp, with mantissa in [0.5, 1)
    
    if (exp % 2 != 0) {
        x = x / pow(2.0, exp - 1);
    } else {
        x = x / pow(2.0, exp);
    }

    // Initial guess.
    double yGuess1;
    yGuess1 = 0.5 * x;
    // double yGuess1 = x / 2.0;
    int count = 0;
    while (count < MAXITER) {
        count += 1;
        double yGuess2 = yGuess1 - (yGuess1 * yGuess1 - x) / (2 * yGuess1);
        double error = fabs(yGuess2 - yGuess1);
        yGuess1 = yGuess2;
        if (error < TOL) {
            break;
        }
    }

    if (exp % 2 != 0) {
        yGuess1 *= pow(2.0, (exp - 1) / 2);
    } else {
        yGuess1 *= pow(2.0, exp / 2);
    }
    return yGuess1;
}