/* Program: binomialDistribution
 * Description: Assume that we have an event that has only two possible 
 *              outcomes (0 and 1) with probabilites p and q respectively.
 *              In an experiment, we repeat the event N times, and want to 
 *              find the probability that the outcome yields the first outcome
 *              r number of times and second outcome (N-r) times. The order in
 *              which the otcomes occur is not of significance. For example, 
 *              N = 1
 *              0 1
 *              N = 2
 *              00 01 10 11
 *              N = 3
 *              000 001 010 011 100 101 110 111
 *              N = 4
 *              0000 0001 0010 0011 0100 0101 0110 0111 
 *              1000 1001 1010 1011 1100 1101 1110 1111
 *              
 *              Assuming the order is not important
  *             N = 1
 *              1(0) 1(1)
 *              N = 2
 *              1(00)  2(01) 1(11)
 *              N = 3
 *              1(000) 3(001) 3(011) 1(111)
 *              N = 4
 *              1(0000) 4(0001) 6(0011) 4(0111) 1(1111)
 * This program prints the Pascal triangle and the probabilities.
 */
public class binomialDistribution {
    public static void main(String[] args) {
        int N = 8;

        // Array to store the binomial expansion.
        int[][] binomial = new int[N][];

        binomial[0] = new int[1];
        binomial[0][0] = 1;

        // Loop over the number of powers.
        for (int iter = 1; iter < N; iter++) {
            binomial[iter] = new int[iter + 1];
            binomial[iter][0] = binomial[iter - 1][0];

            int m = 1;
            while (m < iter) {
                binomial[iter][m] = binomial[iter - 1][m - 1] + binomial[iter - 1][m];
                m+= 1;
            }

            binomial[iter][iter] = binomial[iter - 1][iter - 1];
        }

        // Print the Pascal triangle.
        for (int iter = 0; iter < N; iter++) {
            for (int val : binomial[iter]) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
        System.out.println();

        // Print the probabilities of the occurances.
        for (int iter = 0; iter < N; iter++) {
            System.out.print("N = " + iter + ": | ");
            for (int i = 0; i <= iter; i++) {
                System.out.print("p^" + (iter - i) + ".q^" + i + " | ");
            }
            System.out.println();
        }
    }
}
