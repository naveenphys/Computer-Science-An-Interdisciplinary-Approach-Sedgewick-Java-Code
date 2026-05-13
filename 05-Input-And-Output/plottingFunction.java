
import java.util.Arrays;

/* Program: plottingFunction
 * Description: Demostrate the creation of a simple plot.
 * 
 * Double buffering: All the drawing takes place on on offscreen canvas, 
 *                   which exists only in computer memory. With a specific 
 *                   instruction (like show in matplotlib) the offscreen 
 *                   canvas gets copied to the onscreen canvas.
 */
public class plottingFunction {
    public static void main(String[] args) {

        if (args.length != 3) {
            System.out.println("Provide the following value on command line");
            System.out.println("xmin: Start of the plot");
            System.out.println("xmax: End of the plot");
            System.out.println("nsamples: Number of samples on [xmin,xmax]");
            return;
        }

        int N = Integer.parseInt(args[0]);
        assert (N > 1);

        double a = Double.parseDouble(args[1]);
        double b = Double.parseDouble(args[2]);
        assert (b > a);

        double[] x = new double[N];
        double[] y = new double[N];
        double[] z = new double[N];

        double dx = (b - a) / (double) N;

        int nCoefficients = 4;
        double[] coeff = new double[nCoefficients];

        for (int i = 0; i < nCoefficients; i++) {
            coeff[i] = Math.random();
        }

        for (int i = 0; i < N; i++) {
            x[i] = a + i * dx;
            y[i] = Math.cos(Math.PI * x[i]);
            z[i] = generate(x[i], coeff);
        }

        // Minimum and maximum of arrays.
        // https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/util/Arrays.html
        double yMin = Math.min(Arrays.stream(y).min().getAsDouble(),
                Arrays.stream(z).min().getAsDouble());
        double yMax = Math.max(Arrays.stream(y).max().getAsDouble(),
                Arrays.stream(z).max().getAsDouble());

        // Set the canvas properties.
        StdDraw.setPenRadius(0.001);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setCanvasSize(600, 600);
        StdDraw.setXscale(a - 2.0, b + 2.0);
        StdDraw.setYscale(yMin - 2.0, yMax + 2.0);

        // Enable doubleBuffering.
        StdDraw.enableDoubleBuffering();
        for (int i = 0; i < N - 1; i++) {
            StdDraw.line(x[i], y[i], x[i + 1], y[i + 1]);
        }

        StdDraw.setPenColor(StdDraw.RED);
        for (int i = 0; i < N - 1; i++) {
            StdDraw.line(x[i], z[i], x[i + 1], z[i + 1]);
        }
        StdDraw.show();

    }

    /*
     * Function to generate a sum using n coefficients given by
     * coeff.
     * c[i] * sin(2*Pi*n*x) + c[i] * cos(2*Pi*n*x)
     */
    public static double generate(double x, double[] coeff) {
        int n = coeff.length;
        double y = 0.0;
        for (int i = 0; i < n; i++) {
            y += (coeff[i] * Math.sin(2.0 * i * Math.PI * x));
            y += (coeff[i] * Math.cos(2.0 * i * Math.PI * x));
        }
        return y;
    }
}
