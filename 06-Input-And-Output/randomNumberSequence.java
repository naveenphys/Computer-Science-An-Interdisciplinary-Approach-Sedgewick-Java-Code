
import java.util.Random;

/* Program: randomNumberSequence.
 * Description: Generate a sequence of random number demostrating input using
 *              command line and output using standard output.
 */
public class randomNumberSequence {
    // class Random
    // https://docs.oracle.com/javase/8/docs/api/java/util/Random.html
    static Random rnd = new Random();
    public static void main(String[] args) {
        int N = 100; // Number of samples.
        double min = 0.0; // Minimum value.
        double max = 1.0; // Maximum value.

        // Process the command line arguments.
        if (args.length >= 1) {
            N = Integer.parseInt(args[0]);
        }

        if (args.length == 3) {
            min = Double.parseDouble(args[1]);
            max = Double.parseDouble(args[2]);
        }

        // Seed the random number generator.
        rnd.setSeed(Long.MAX_VALUE);
        // Generate the numbers.
        for (int i = 0; i < N; i++) {
            System.out.printf("%3d %21.15e\n", i, randomDoubleBracketed(min, max));
        }
    }

    /* Generates one random number at a time.
     */
    public static double randomDoubleBracketed(double min, double max) {
        return rnd.nextDouble(min, max);
    }

}
