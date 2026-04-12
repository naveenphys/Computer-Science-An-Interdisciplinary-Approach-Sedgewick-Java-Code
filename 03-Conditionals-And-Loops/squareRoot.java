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
 */
public class squareRoot {
    public static void main(String[] args) {


        System.out.println("Max double: " + Double.MAX_VALUE);
        // Default value of x
        double x0 = 1.0;
        if (args.length == 1) {
            x0 = Double.parseDouble(args[0]);
        }
        System.out.println("Finding square root of " + x0);

        // Finding square root using library.
        long tbeg1 = System.nanoTime();
        double sol1 = Math.sqrt(x0);
        long tend1 = System.nanoTime();

        long tbeg2 = System.nanoTime();
        double sol2 = sqrt(x0);
        long tend2 = System.nanoTime();

        System.out.printf("%-22s\t%-22s\t%-22s\t%-22s\n",
                "x", "Math.sqrt", "sqrt", "abs(Math.sqrt-sqrt)");
        System.out.printf("%.16e\t%.16e\t%.16e\t%.16e\n",
                x0, sol1, sol2, Math.abs(sol1 - sol2));
        System.out.println("Math.sqrt elapsed time: " + (tend1 - tbeg1));
        System.out.println("sqrt      elapsed time: " + (tend2 - tbeg2));

    }

    public static double sqrt(double x) {
        // Finding square root using Newton-Raphson method.

        // Exposing the bits of a double:
        // long bits = Double.doubleToLongBits(x);
        // Bit 63 : Sign bit.
        // long sign = bits & 0x8000000000000000L;
        // Bit 62-52: Exponent
        // long exponent = bits & 0x7ff0000000000000L;
        // Bit 51-0 : Mantissa.
        // long mantissa = bits & 0x000fffffffffffffL;

        final int MAXITER = 100;
        final double TOL = 1e-16;
        
        int exp = Math.getExponent(x);
        if (exp % 2 != 0) {
            x = x / Math.pow(2.0, exp - 1);
        } else {
            x = x / Math.pow(2.0, exp);
        }

        // Initial guess.
        double yGuess1;
        yGuess1 = 0.5 * x;
        // double yGuess1 = x / 2.0;
        int count = 0;
        while (count < MAXITER) {
            count += 1;
            double yGuess2 = yGuess1 - (yGuess1 * yGuess1 - x) / (2 * yGuess1);
            double error = Math.abs(yGuess2 - yGuess1);
            yGuess1 = yGuess2;
            if (error < TOL) {
                break;
            }
        }

        if (exp % 2 != 0) {
            yGuess1 *= Math.pow(2.0, (exp - 1) / 2);
        } else {
            yGuess1 *= Math.pow(2.0, exp / 2);
        }
        return yGuess1;
    }
}
