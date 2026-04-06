
/* Program: booleanVariables
 * Description: Demonstration of boolean operations.
 *              Produces a table showing for pair of booleans a and b
 *              !a !b a && b  a||b
 */
public class booleanVariables {
    public static void main(String[] args) {
        boolean[] A = new boolean[2];
        boolean[] B = new boolean[2];
        A[0] = false; A[1] = true;
        B[0] = false; B[1] = true;
        boolean notA, notB, aAndB, aOrB;
        System.out.println("A   \tB    \t!A   \t!B   \tA&&B \tA||B");
        for (boolean a : A) {
            for (boolean b : B) {
                notA = !a;
                notB = !b;
                aAndB = a && b;
                aOrB  = a || b;
                System.out.println(a + "\t" + b + "\t" + notA + "\t" + notB +
                 "\t" + aAndB + "\t" + aOrB);
            }
        }
    }
}
