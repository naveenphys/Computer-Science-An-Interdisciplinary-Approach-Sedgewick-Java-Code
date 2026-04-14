/* Program: cacheEmulation.
 * Description: Caching the values of a function for reuse.
 */
public class cacheEmulation {
    final public static int CACHE_SIZE = 120;
    public static long[] cache = new long[CACHE_SIZE];
    public static boolean[] cached = new boolean[CACHE_SIZE];

    public static void main(String[] args) {
        int NMAX = 10;
        if (args.length == 1) {
            NMAX = Integer.parseInt(args[0]);
        }

        long fib0, fib1;
        for (int i = 1; i <= NMAX; i++) {
            long e0 = System.nanoTime();
            fib0 = fibonacci(i);
            long e1 = System.nanoTime();
            fib1 = fibonacciCC(i);
            long e2 = System.nanoTime();
            if (fib0 == fib1)
            System.out.printf(
            "fib(%-3d) = %-10d\teps.Recurse = %-10d\teps.Cached = %-10d\n", 
            i, fib0, (e1 - e0), (e2 - e1));
        }
    }

    public static void updateCache(int i, long val) {
        if (cached[i] == false) {
            cache[i] = val;
            cached[i] = true;
        }
    }

    public static long fibonacciCC(int n) {

        if (cached[n - 1]) {
            return cache[n - 1];
        }

        if (n == 1) {
            updateCache(0, 1);
            return 1;
        }

        if (n == 2) {
            updateCache(1, 1);
            return 1;
        }

        long f = fibonacciCC(n - 1) + fibonacciCC(n - 2);
        updateCache(n - 1, f);
        return f;
    }

    public static long fibonacci(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 1;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

}
