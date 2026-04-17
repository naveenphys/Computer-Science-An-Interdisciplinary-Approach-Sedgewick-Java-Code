/* Program: twoDimensionalArrays
 * Description: Demonstrating declaration, definition and initialization of 
 *              2 dimensional arrays.
 */
public class twoDimensionalArrays {
    public static void main(String[] args) {
        int M = 2;
        int N = 2;
        if (args.length == 2) {
            M = Integer.parseInt(args[0]);
            N = Integer.parseInt(args[1]);
        }
        // Declare 2D array.
        int [][] matrixA;

        // Define 2D array.
        matrixA = new int[M][N];

        // Initialization.
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                matrixA[i][j] = i + j;
            }
        }

        printMatrix(matrixA);

        // Setting values at compile time. Combining declaration, defintion 
        // and initialization.
        int [][] matrixB = {{1, 0},{0, 1}};

        printMatrix(matrixB);


        // Creating a ragged array.
        int [][] raggedA = new int[M][];
        for (int i = 0; i < M; i++) {
            raggedA[i] = new int [N + i];
        }

        // Initialize 
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < raggedA[i].length; j++) {
                raggedA[i][j] = i + j;
            }
        }

        printMatrix(raggedA);

        // Ragged arrays setting values at compile time. Combining 
        // declaration, defintion and initialization.
        int [][] raggedB = {{1,0},{1,0,1},{1,0},{1,0,1,0,1}};
        printMatrix(raggedB);

    }

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
