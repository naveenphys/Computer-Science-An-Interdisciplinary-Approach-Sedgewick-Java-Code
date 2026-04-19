/* Program: transposeSquareMatrixInPlace
 * Description: Transposing a square matrix in place, meaning not using any
 *              additional arrays.
 */
public class transposeSquareMatrixInPlace {
    public static void main(String[] args) {
        int N = 4;
        if (args.length == 1)
            N = Integer.parseInt(args[0]);

        // Define a matrix (a 2D array).
        int [][] matrix = new int[N][N];

        // Initialize.
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (j >= i) matrix[i][j] = 1;
            }
        }

        // Print the matrix.
        printMatrix(matrix, "Matrix:");

        // Transpose in place. Note the limit of the j loop.
        for (int i = 0; i < N; i++) {
            for (int j = i; j < N; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        // Print the transposed matrix.
        printMatrix(matrix, "Transposed matrix:");

    }
    /*  Print the matrix.
     */
    public static void printMatrix(int[][] matrix, String name) {
        // Matrix1 is being used to refer to a row.
        System.out.println(name);
        for (int[] matrix1 : matrix) {
            for (int j = 0; j < matrix1.length; j++) {
                System.out.printf("%2d ", matrix1[j]);
            }
            System.out.println("");
        }
        System.out.println("");
    }
}
