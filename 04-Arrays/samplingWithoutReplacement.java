
/* Program: samplingWithoutReplacement
 * Description: Draw a random sample from a set such that each member of the 
 *              set appears at most once in the sample. As an example, this is
 *              done in dealing from a deck of cards. The function takes two
 *              command line arguments m and n and creates a permutation of 
 *              length n, whose first m elements form a random sample. 
 *              Resulting permutation has no repeating elements.
 * The method can be used to draw random samples from an array, without 
 * rearranging it. For example, if we want to select a random permutation of
 * names that are stored in alphabetical order.
 */
public class samplingWithoutReplacement {
    public static void main(String[] args) {
        int N, M;

        if (args.length != 2) {
            System.out.println("Provide N and M as command line args");
            return;
        }

        N = Integer.parseInt(args[0]);
        M = Integer.parseInt(args[1]);

        assert (N > M);

        int[] set = new int[N];
        // Initialize the set.
        for (int i = 0; i < N; i++) {
            set[i] = i;
        }

        if (N <= 10) printSet(set, N);
        for (int i = 0; i < M; i++) {
            int r = i + (int) (Math.random() * (N - i));
            if (r != i) {
                int tm = set[i];
                set[i] = set[r];
                set[r] = tm;
            }
            if (N <= 10) printSet(set, N);
        }

        if (N <= 10) System.out.println();
        printSet(set, M);
    }

    public static void printSet(int[] deck, int m) {
        for (int i = 0; i < m; i++) {
            System.out.print(deck[i]);
            if ((i + 1) % 15 == 0 || i == m - 1) {
                System.out.println();
            } else {
                System.out.print("\t");
            }
        }
    }
}