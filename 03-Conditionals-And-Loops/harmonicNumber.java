/* harmonicNumber
 * Description: In mathematics, the n-th harmonic number is the sum of the 
 *              reciprocals of the first n natural numbers:
 *              H_n=1+ 1/2 + 1/3 + ... + 1/n
 */
public class harmonicNumber {
    public static void main(String[] args) {
        long N = 4;
        if (args.length == 1) {
            N = Long.parseLong(args[0]);
            // We get an overflow at N = 43
            N = Math.min(N, (long) 43);
        }

        // Numerator and denomenator of the number.
        long num = 1;
        long den = 1;
        double sum = 1.0;

        for (long i = 2; i <= N; i++) {
            num = num * i + den;
            den *= i;
            long factor = gcd(num, den);
            num /= factor;
            den /= factor;
            sum += (1.0/i);
            System.out.println("i = " + i + "\t" + num + "/" + den +
                "\t" + (1.0*num)/den + "\t" + sum);
        }

        System.out.println("Direct sum: H_" + N + " = " + sum);
        System.out.println("Precise sum: H_" + N + " = " + (1.0*num)/den);
        System.out.println("Difference = " + (sum - (1.0*num)/den));
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
    
}
