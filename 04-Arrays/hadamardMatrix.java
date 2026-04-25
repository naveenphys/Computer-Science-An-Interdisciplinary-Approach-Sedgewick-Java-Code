/* program: hadamardMatrix
 * Description: The n-by-n Hadamard H(n) matrix is a boolean matrix with the 
 *              remarkable property that any two rows differ in exactly n/2 
 *              bits. (This property makes it useful for designing 
 *              error-correcting codes.) H(1) is a 1-by-1 matrix with the 
 *              single entry true, and for n > 1, H(2n) is obtained by 
 *              aligning four copies of H(n) in a large square, and then 
 *              inverting all of the entries in the lower right n-by-n copy, 
 * 
 *              https://en.wikipedia.org/wiki/Hadamard_matrix
 * 
 *              A square matrix whose entries are either +1 or −1 and whose 
 *              rows are mutually orthogonal. In geometric terms, this means 
 *              that each pair of rows in a Hadamard matrix represents two 
 *              perpendicular vectors, while in combinatorial terms, it means 
 *              that each pair of rows has matching entries in exactly half of
 *              their columns and mismatched entries in the remaining columns.
 *              It is a consequence of this definition that the corresponding 
 *              properties hold for columns as well as rows. It implies that
 *              the matrix size is an even number or unity;
 * 
 *              Hadamard matrices are used in error correction codes.
 *              https://en.wikipedia.org/wiki/Error_correction_code
 *              https://errorcorrectionzoo.org/
 *              https://www.eccpage.com/
 * 
 *              Hadamard code:
 *              https://en.wikipedia.org/wiki/Hadamard_code
 */
public class hadamardMatrix {
    public static void main(String[] args) {
        int N = 2;
        if (args.length == 1) {
            N = Integer.parseInt(args[0]);
            // Assertions are enabled by using -ea
            assert (isPowerOf(N, 2)) : "N must a power of two";
        }

        System.err.println("Generating Hadamard Matrix of order: " + N);

        boolean[][] Hn = generateHn(1);
        System.out.printf("H(%d):", 1);
        printMatrix(Hn);

        for (int i = 1; i <= N; i++) {
            Hn = generateHn(2 * i);
            System.out.printf("H(%d):", 2 * i);
            printMatrix(Hn);
        }

    }

    public static boolean[][] generateHn(int N) {
        boolean Hn[][];
        if (N == 1) {
            Hn = new boolean[1][1];
            Hn[0][0] = true;
        } else {
            int M = N / 2;
            boolean[][] H1 = generateHn(N / 2);
            boolean[][] H2 = not(H1);
            Hn = new boolean[N][N];
            for (int i = 0; i < 2; i++) {
                // To shift the placement of elements.
                int sx = i * M;
                for (int j = 0; j < 2; j++) {
                    int sy = j * M;
                    if (i == 1 && j == 1) {
                        // Copy the transpose.
                        for (int r = 0; r < M; r++) {
                            for (int c = 0; c < M; c++) {
                                Hn[sx + r][sy + c] = H2[r][c];
                            }
                        }

                    } else {
                        // Copy the matrix.
                        for (int r = 0; r < M; r++) {
                            for (int c = 0; c < M; c++) {
                                Hn[sx + r][sy + c] = H1[r][c];
                            }
                        }
                    }
                }
            }
        }
        return Hn;
    }

    /*
     * Checks if n is a power of n.
     */
    public static boolean isPowerOf(int n, int m) {

        boolean isPow = false;
        // Check for the power
        int pow = m;
        while (pow <= n) {
            if (n % pow == 0) {
                isPow = true;
                break;
            }
            pow *= m;
        }
        return isPow;
    }

    /*
     * Print the matrix.
     */
    public static void printMatrix(boolean[][] matrix) {
        System.out.println("");
        // Matrix1 is being used to refer to a row.
        for (boolean[] matrix1 : matrix) {
            for (int j = 0; j < matrix1.length; j++) {
                System.out.printf("%s ", (matrix1[j] == true ? "T" : "F"));
            }
            System.out.println("");
        }
    }

    /*
     * Shape of the matrix.
     */
    public static int[] shape(boolean[][] matrix) {
        int[] s = new int[2];
        s[0] = matrix.length;
        s[1] = matrix[0].length;
        return s;
    }

    /*
     * Transpose of a matrix.
     */
    public static boolean[][] not(boolean[][] matrix) {
        int[] s = shape(matrix);
        boolean[][] c = new boolean[s[1]][s[0]];

        // Copy rows to columns.
        for (int i = 0; i < s[1]; i++) {
            for (int j = 0; j < s[0]; j++) {
                c[i][j] = !matrix[j][i];
            }
        }
        return c;
    }
}
