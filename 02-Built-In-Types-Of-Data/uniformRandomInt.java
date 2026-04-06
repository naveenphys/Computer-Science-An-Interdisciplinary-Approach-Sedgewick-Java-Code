/* Program: uniformRandomInt
 * Description: Demonstrates the generation of random int between 0 and N-1,
 *              using the Math.random() method.
 *              Math.random() -> double in the range [0,1)
 */
public class uniformRandomInt {
    public static void main(String[] args) {
        if (args.length != 1)
        {
            System.out.println("Command line argument N is missing");
            System.out.println("N: Generate random integers [0,N)");
            return;
        }
        int N = Integer.parseInt(args[0]);
        int randint = (int) (Math.random() * N);
        System.out.println("Random integer: " + randint);
    }
}
 