/*
 * Program: eulerTotientFunction
 * Description: Euler's totient function is defined as the number of 
 *              positive integers less than or equal to n that are relatively 
 *              prime with n.
 */

public class eulerTotientFunction {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("Provide a positive integer on command line");
            return;
        }
        long n = Long.parseLong(args[0]);
        totientFunction tf = new totientFunction(n);
        tf.evaluate();
        tf.show();
    }
}

class totientFunction {
    private long n;
    private int phi;
    totientFunction(long n_) {
        n = n_;
    }

    // Reset the value of n.
    public void reset(long n_) {
        n = n_;
    }

    // Evaluate the function.
    public void evaluate() {
        phi = 0;
        for (long i = 1; i <= n; i++) {
            if (gcd(n, i) == 1) phi++;
        }
    }

    // Show the value.
    public void show() {
        System.out.println("phi(" + n + ") = " + phi);
    }

    // Calculate the gcd of two numbers.
    private long gcd(long a, long b) {
        // Find GCD using Euclidean algorithm.
        if (a == 0 || b == 0) {
            return 0;
        }

        a = Math.abs(a);
        b = Math.abs(b);

        while (b != 0) {
            long t = b;
            b = a % b;
            a = t;
        }
        return a;
    }
}
