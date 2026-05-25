/* Program: fourierSpikes
 * Description: Demonstrates that any function can be represented using 
 *              a sum of sinusoids.
 * 
 * Outout the results to a file and plot using gnuplot.
 */
import java.security.InvalidParameterException;

public class fourierSpikes {
    public static void main(String[] args) {
        final int nPts = 2000;
        if (args.length != 1) {
            System.out.println("Enter an integer on command line");
            return;
        }

        int nMax = Integer.parseInt(args[0]);
        double xMin = -10.0;
        double xMax = 10.0;

        double dx = (xMax - xMin)/(nPts -1);

        for (int i = 0; i < nPts; i++) {
            double x = xMin + i * dx;
            double y = cosSum(nMax, x);
            System.out.printf("%.15f %.15f\n", x, y);
        }
        
    }

    public static double cosSum(int n, double x) {
        if (n <= 0) {
            throw new InvalidParameterException("n <= 0!");
        }
        
        double sum = 0.0;
        
        for (int i = 1; i <= n; i++) {
            sum += (Math.cos(i*x)/n);
        }

        return sum;
    }
}
