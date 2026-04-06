/* sumAlternatingHarmonicNumbers
 * Description: The following is known as the alternating harmonic series
 *              1- 1/2 + 1/3 - ... + 1/n.
 * The sum can also be written using:
 * For example, n = 4
 * S_4 = (1 + 1/3) - (1/2 + 1/4)
 * S_4 = (1 + 1/3) - 1/2*H_2
 * S_4 = (1 + 1/3) - 1/2*H_2 - 1/2*H_2 + 1/2*H_2
 * S_4 = (1 + 1/3) + 1/2*H_2 - 1/2*H_2 - 1/2*H_2
 * S_4 = H_4 - H_2
 * 
 * S_5 = (1 + 1/3 + 1/5) - (1/2 + 1/4)
 * S_5 = (1 + 1/3 + 1/5) - 1/2*H_2
 * S_5 = (1 + 1/3 + 1/5) - 1/2*H_2 - 1/2*H_2 + 1/2*H_2
 * S_5 = (1 + 1/3 + 1/5) + 1/2*H_2 - 1/2*H_2 - 1/2*H_2
 * S_5 = H_5 - H_2
 * 
 * For even n, S_{2n} = H_{2n} - H_{n}
 * For odd  n, S_{2n+1} = H_{2n+1} - H_{n}
 * 
 * https://en.wikipedia.org/wiki/Harmonic_series_(mathematics)
 */
public class sumAlternatingHarmonicNumbers {
    public static void main(String[] args) {
        long N = 4;
        if (args.length == 1) {
            N = Long.parseLong(args[0]);
            // We get an overflow at N = 42
            N = Math.min(N, (long) 42);
        }

        // Numerator and denomenator of the number.
        long num = 1;
        long den = 1;
        double sum = 1.0;

        long sign = 1;
        for (long i = 2; i <= N; i++) {
            sign *= -1;
            num = num * i + sign * den;
            den *= i;
            long factor = gcd(num, den);
            num /= factor;
            den /= factor;
            sum += (sign * 1.0/i);
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
