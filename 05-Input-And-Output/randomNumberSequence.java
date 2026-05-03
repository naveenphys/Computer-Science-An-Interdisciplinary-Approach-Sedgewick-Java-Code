
import java.util.Random;

/* Program: randomNumberSequence.
 * Description: Generate a sequence of random number demostrating input using
 *              command line and output using standard output.
 * 
 * Format in Java:
 * https://docs.oracle.com/javase/8/docs/api/java/util/Formatter.html
 * 
 * Note: In case of formatted output, the argument and specifier must match.
 * Otherwise, the IllegalFormatConversionException will be thrown.
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
        
        // Array to store the numbers.
        double [] randf = new double[N];

        // Generate and print the numbers.
        for (int i = 0; i < N; i++) {
            randf[i] = randomDoubleBracketed(min, max);
        }

        // Print using the StdOut class.
        for (int i = 0; i < N; i++) {
            System.out.printf("%3d %21.15e\n", i, randf[i]);
        }

    }

    /* Generates one random number at a time.
     */
    public static double randomDoubleBracketed(double min, double max) {
        return rnd.nextDouble(min, max);
    }


}
