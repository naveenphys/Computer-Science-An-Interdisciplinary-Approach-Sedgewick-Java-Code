/* Program: primeFactorizationUsingShor
 * Description: If N is a positive integer, then the set ZN is defined as the
 * set of all integers z calcuated as z Modulo N.
 * For example: If N = 5
 * Z5 = {0, 1, 2, 3, 4}
 * 
 * ZNstar is the set of all z in ZN, that have GCD(z, N) = 1.
 * For example, if N = 15
 * Z15 = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14}
 * Z15star = {1, 2, 4, 7, 8, 11, 13, 14}
 */

public class primeFactorizationUsingShor {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Missing value of N on the command line");
            return;
        }

        int N = Integer.parseInt(args[0]);
        long[] ZN = new long[N];
        long[] ZNstar = new long[N];
        long[] order = new long[N];
        for (int i = 0; i < N; i++) {
            ZN[i] = i;
        }

        // Print elements of ZNstar.
        int count = 0;
        for (int i = 2; i < N; i++) {
            if (gcd(ZN[i], (long)N) == (long)1) {
                ZNstar[count] = ZN[i];
                long beg = System.nanoTime();
                order[count] = period(ZN[i], (long) N);
                long end = System.nanoTime();
                System.out.println(ZNstar[count] + "\torder = " +
                        order[count] + "\tElapsed time: " + (end - beg));
                if (order[count] % 2 == 0) {
                    long a1 = ipow(ZNstar[count], order[count] / 2) - 1;
                    long a2 = ipow(ZNstar[count], order[count] / 2) + 1;
                    long f1 = gcd(a1, N);
                    long f2 = gcd(a2, N);
                    if ((f1 > 1) && (f2 > 1)) {
                        System.out.println("\t" + f1 + "\t" + f2);
                        break;
                    }
                }
                count += 1;
            }
        }

        System.out.println("Number of elements in Z" + N + "star = " + count);

    }

    private static long ipow(long a, long b) {
        // Calculate an integer raised to power b.
        if (a == 0 || b == 0) {
            return 0;
        }
        long r = 1;
        for (long i = 0; i < b; i++) {
            r *= a;
        }
        return r;
    }

    private static long gcd(long a, long b) {
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

    private static long period(long a, long N) {
        // Find the period of a in ZNstar.
        // Period is the smallest value of r (an integer)
        // such that a^r Modulo N = 1.
        long r = 1;
        long b = a;
        while (b % N != 1) {
            // Multiply by a.
            b *= a;
            r += 1;
        }
        return r;
    }
}