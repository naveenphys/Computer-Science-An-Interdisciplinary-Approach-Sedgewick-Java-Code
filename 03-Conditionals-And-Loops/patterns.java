
/* Program: patterns
 * Description: Prints the following patterns
 * Assuming I, J are in the interval [1,2,3,..,n]
 * 
 *      j >= i ? "*" : "."
 *      * * * * * *
 *      . * * * * *
 *      . . * * * *
 *      . . . * * *
 *      . . . . * *
 *      . . . . . *
 * 
 *      j == i || j == N - (i-1) ? "*" : "."
 *      * . . . . . *
 *      . * . . . * .
 *      . . * . * . .
 *      . . . * . . .
 *      . . * . * . .
 *      . * . . . * .
 *      * . . . . . *
 * 
 *      *. . . . . .* 
 *      * * . . . * * 
 *      * * * . * * * 
 *      * * * * * * * 
 *      * * * . * * * 
 *      * * . . . * * 
 *      * . . . . . *
 * 
 *      . . . . * . . . . 
 *      . . . * * * . . . 
 *      . . * * * * * . . 
 *      . * * * * * * * . 
 *      * * * * * * * * * 
 *      . * * * * * * * . 
 *      . . * * * * * . . 
 *      . . . * * * . . . 
 *      . . . . * . . . . 
 */
public class patterns {
    public static void main(String[] args) {
        int n = 9;
        if (args.length == 1) {
            n = Integer.parseInt(args[0]);
            // Use odd number only.
            if (n % 2 == 0)
                n = 9;
        }

        triangle(n);
        xsign(n);
        bowtie(n);
        diamond(n);
        triangle_(n);
        xsign_(n);
        bowtie_(n);
        diamond_(n);

    }

    public static void triangle(int n) {
        // i, j run from 1 to n.
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (j >= i) {
                    System.out.print("* ");
                } else {
                    System.out.print(". ");
                }
            }
            System.err.println();
        }
        System.err.println();
    }

    public static void xsign(int n) {
        // i, j run from 1 to n.
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (j == i || j == n - (i - 1)) {
                    System.out.print("* ");
                } else {
                    System.out.print(". ");
                }
            }
            System.err.println();
        }
        System.err.println();
    }

    public static void bowtie(int n) {
        // i, j run from 1 to n.
        for (int i = 1; i <= n; i++) {
            if (i <= (n + 1) / 2) {
                for (int j = 1; j <= n; j++) {
                    if (j <= i || j >= n - (i - 1)) {
                        System.out.print("* ");
                    } else {
                        System.out.print(". ");
                    }
                }
            } else {
                for (int j = 1; j <= n; j++) {
                    if (j >= i || j <= n - (i - 1)) {
                        System.out.print("* ");
                    } else {
                        System.out.print(". ");
                    }
                }
            }
            System.err.println();
        }
        System.err.println();
    }

    public static void diamond(int n) {
        // i, j run from 1 to n.
        int m = (n + 1) / 2;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (j >= m - (i - 1) && j <= m + (i - 1)) {
                    System.out.print("* ");
                } else {
                    System.out.print(". ");
                }
            }
            System.err.println();
        }
        for (int i = m + 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (j >= (i % m) + 1 && j <= n - (i % m)) {
                    System.out.print("* ");
                } else {
                    System.out.print(". ");
                }
            }
            System.err.println();
        }
        System.err.println();
    }

    public static void triangle_(int n) {
        // i, j run from -(n+1)/2 to (n+1)/2.
        int m = (n + 1) / 2;
        int ib = m - n;
        int ie = n - m;

        for (int i = ib; i <= ie; i++) {
            for (int j = ib; j <= ie; j++) {
                if (j >= i) {
                    System.out.print("* ");
                } else {
                    System.out.print(". ");
                }
            }
            System.err.println();
        }
        System.err.println();
    }

    public static void xsign_(int n) {
        // i, j run from -(n+1)/2 to (n+1)/2.
        int m = (n + 1) / 2;
        int ib = m - n;
        int ie = n - m;
        for (int i = ib; i <= ie; i++) {
            for (int j = ib; j <= ie; j++) {
                if (Math.abs(j) == Math.abs(i)) {
                    System.out.print("* ");
                } else {
                    System.out.print(". ");
                }
            }
            System.err.println();
        }
        System.err.println();
    }

    public static void bowtie_(int n) {
        // i, j run from -(n+1)/2 to (n+1)/2.
        int m = (n + 1) / 2;
        int ib = m - n;
        int ie = n - m;
        for (int i = ib; i <= ie; i++) {
            for (int j = ib; j <= ie; j++) {
                if (j <= -Math.abs(i) || j >= Math.abs(i)) {
                    System.out.print("* ");
                } else {
                    System.out.print(". ");
                }
            }
            System.err.println();
        }
        System.err.println();
    }

    public static void diamond_(int n) {
        // i, j run from -(n+1)/2 to (n+1)/2.
        int m = (n + 1) / 2;
        int ib = m - n;
        int ie = n - m;
        for (int i = ib; i <= ie; i++) {
            for (int j = ib; j <= ie; j++) {
                if (j >= -(ie - Math.abs(i)) && j <= (ie - Math.abs(i))) {
                    System.out.print("* ");
                } else {
                    System.out.print(". ");
                }
            }
            System.err.println();
        }
        System.err.println();
    }
}
