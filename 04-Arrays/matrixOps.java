
/* Program: matrixOps
 * Description: Implementing basic operations on matrices of integers.
 * Add
 * Multiply
 * Transpose
 */
public class matrixOps {
    public static void main(String[] args) {
        int N = 3;
        if (args.length == 1) {
            N = Integer.parseInt(args[0]);
        }
        int[][] matrixA = new int[N][N];
        int[][] matrixB = new int[N][N];

        // Initialize.
        for (int[] row : matrixA) {
            for (int j = 0; j < row.length; j++) {
                row[j] = j;
            }
        }

        printMatrix(matrixA);

        // Initialize.
        for (int[] row : matrixB) {
            for (int j = 0; j < row.length; j++) {
                row[j] = j;
            }
        }

        printMatrix(matrixB);

        // Add the matrices.
        System.out.println("\nAddition");
        printMatrix(Add(matrixA, matrixB));

        // Multiply.
        System.out.println("\nMultiply");
        printMatrix(multiply(matrixA, matrixB));

        // Multiply.
        System.out.println("\nMultiply with identity");
        printMatrix(multiply(matrixA, identity(N)));

        // Transpose.
        System.out.println("\nTranspose");
        printMatrix(transpose(multiply(matrixA, identity(N))));
    }

    /*  Returns an idenity matrix of size N x N.
     */
    public static int[][] identity(int N) {
        int[][] matrix = new int[N][N];
        for (int i = 0; i < N; i++) {
            matrix[i][i] = 1;
        }
        return matrix;
    }

    /*  Addition of matrices.
     */
    public static int[][] Add(int[][] a, int[][] b) {
        int[][] c = new int[a.length][a[0].length];

        if (haveIdenticalShape(a, b)) {
            for (int i = 0; i < a.length; i++) {
                for (int j = 0; j < a[i].length; j++) {
                    c[i][j] = a[i][j] + b[i][j];
                }
            }
        } else {
            System.err.println("Error: matrices have different shapes");
            System.exit(1);
        }
        return c;
    }

    /*  Matrix multiplication.
     */
    public static int[][] multiply(int[][] a, int[][] b) {
        int[] s1 = shape(a);
        int[] s2 = shape(b);
        int[][] c = new int[s1[0]][s2[1]];

        // Arrays must have the same number of rows.
        if (canBeMultiplied(a, b)) {
            for (int i = 0; i < c.length; i++) {
                for (int j = 0; j < c[i].length; j++) {
                    for (int k = 0; k < c[i].length; k++) {
                        c[i][j] += (a[i][k] * b[k][j]);
                    }
                }
            }
        } else {
            System.err.println("Error: ncols in a not equal to nrows in b");
            System.exit(1);
        }
        return c;
    }

    /*  Checks if two matrices have the same number of rows and cols.
     */
    public static boolean haveIdenticalShape(int[][] matrixA, int[][] matrixB) {
        int[] s1 = shape(matrixA);
        int[] s2 = shape(matrixB);
        return (s1[0] == s2[0]) && (s1[1] == s2[1]);
    }

    /*  Checks if two matrices can be multiplied.
     *  Number of cols in A must be equal to number of rows in B.
     */
    public static boolean canBeMultiplied(int[][] matrixA, int[][] matrixB) {
        int[] s1 = shape(matrixA);
        int[] s2 = shape(matrixB);
        // Number of columns in A is equal to number of rows in B.
        return (s1[1] == s2[0]);
    }

    /*  Shape of the matrix.
     */
    public static int[] shape(int[][] matrix) {
        int[] s = new int[2];
        s[0] = matrix.length;
        s[1] = matrix[0].length;
        return s;
    }

    /*  Print the matrix.
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

    /*  Transpose of a matrix.
     */
    public static int[][] transpose(int[][] matrix) {
        int[] s = shape(matrix);
        int[][] c = new int[s[1]][s[0]];

        // Copy rows to columns.
        for (int i = 0; i < s[1]; i++) {
            for (int j = 0; j < s[0]; j++) {
                c[i][j] = matrix[j][i];
            }
        }
        return c;
    }
}
