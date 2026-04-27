

/* Program: permulationsAndCombinations
 * Description: Generates the permutations and combinations from a set.
 *              Assume that the elements in the set are unique.
 */
public class permulationsAndCombinations {

    public static void main(String[] args) {
        int N = 4;
        int R = 3;

        int[] set = new int [N];
        for (int i = 0; i < N; i++) {
            set[i] = i + 1;
        }

        int [][] p = nPr(set, R);

        int [][] c = nCr(set, R);

    }

    public static int [][] nPr(int [] set, int r) {
        int n = set.length;
        int s = n;
        for (int i = 1; i < r; i++) {
            s*= (n-i);
        }
        System.err.println("Number of permutations: " + s);
        int [][] perm = new int [s][r];

        return perm;
    }

    public static int [][] nCr(int [] set, int r) {
        int n = set.length;
        int s = n;
        for (int i = 1; i < r; i++) {
            s*= (n-i);
        }

        for (int i = 1; i <= r; i++) {
            s /= i;
        }

        System.err.println("Number of combinations: " + s);
        int [][] perm = new int [s][r];

        return perm;
    }


    /*
     * Print the matrix.
     */
    public static void printMatrix(int[][] matrix) {
        System.out.println("");
        // Matrix1 is being used to refer to a row.
        for (int[] matrix1 : matrix) {
            for (int j = 0; j < matrix1.length; j++) {
                System.out.printf("%2d ", matrix1[j]);
            }
            System.out.println("");
        }
    }
}
