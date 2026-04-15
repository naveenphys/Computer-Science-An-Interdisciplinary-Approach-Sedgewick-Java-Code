/* Program: sieveOfEratosthenes.
 * Description: Algorithm to find prime numbers. We start with the first
*               prime number 2, and mark all its multiples as non-primes.
 */
public class sieveOfEratosthenes {
    public static void main(String[] args) {
        int Nmax = 1024;
        final boolean printIt = false;
        if (args.length == 1) {
            Nmax = Integer.parseInt(args[0]);
        }

        // Default value is false.
        boolean[] isPrime = new boolean[Nmax + 1];

        long tbeg = System.nanoTime();
       
        isPrime[0] = false;
        isPrime[1] = false;

        // Assume that all the numbers are prime.
        for (int i = 2; i <= Nmax; i += 1) {
            isPrime[i] = true;
        }

        int nPrimes = 0;
        for (int i = 2; i <= Nmax; i++) {
            if (isPrime[i])
            {
                nPrimes++ ;
                // 2 => 2*2, 2*3, 2*4, 2*5, ...     
                // 3 => 3*3, 3*4, 3*5, 3*6, ...
                for (int j = i; j <= Nmax/i; j++)
                {
                    isPrime[j*i] = false;
                }
            }

        }

        long tend = System.nanoTime();
        System.out.println("Elapsed time: " + (tend - tbeg));
        
        System.out.println("Nmax = " + Nmax);
        System.out.println("Number of primes from [2, " + Nmax + "] = " + nPrimes);
        System.out.println("Fraction = " + (nPrimes/(double)Nmax));

        // Print numbers.
        if (printIt) {
            int count = 0;
            for (int i = 2; i <= Nmax; i++) {
                if (isPrime[i]) {
                    count++;
                    if (count < nPrimes) {
                        System.out.printf("%8d, ", i);
                    } else {
                        System.out.printf("%8d", i);
                    }
                    if (count % 10 == 0)
                        System.out.println();
                }
            }
            System.out.println();
        }
    }
}
