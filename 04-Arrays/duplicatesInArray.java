/* Program: duplicatesInArray
 * Description: Given a random array, the program checks if there is a 
 *              duplicate entry. The program exits when such an entry is
 *              found.
 */
public class duplicatesInArray {
    public static void main(String[] args) {
        int N = 100;

        // Array that is checked for uniqueness of all elements.
        int[] test = new int[N];

        // Random numbers.
        for (int i = 0; i < N; i++) {
            test[i] = (int) (Math.random() * N);
        }
        printVector(test, 10);

        boolean hasDuplicate = false;
        // Loop over each element.
        outer: for (int i = 0; i < test.length - 1; i++) {
            int etest = test[0];
            // Loop over remaining elements to the right.
            for (int j = i + 1; j < test.length; j++) {
                if (etest == test[j]) {
                    System.out.println("value at " + i + " == " + j);
                    hasDuplicate = true;
                    break outer;
                }
            }
        }
        if (!hasDuplicate)
            System.out.println("No duplicates");
    }

    /*
     * Print a vector such that each row has length N
     */
    public static void printVector(int[] vec, int N) {
        for (int i = 0; i < vec.length; i++) {
            if (i != 0 && i % N == 0) {
                System.out.println("");
                System.out.printf("%3d ", vec[i]);
            } else {
                System.out.printf("%3d ", vec[i]);
            }
        }
        System.out.println("");
    }
}
