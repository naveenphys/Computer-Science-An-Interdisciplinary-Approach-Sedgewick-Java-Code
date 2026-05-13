
import java.util.Random;

/* Program: bubbles
 * Description: Draw n filled circles inside a rectange of size 1. The 
 *              position and radius, and color (b/w) of each circle is chosen
 *              at random. The radius has a range [RMIN, RMAX), and color is
 *              chosen as black with a probability PROB.
 */
public class bubbles {
    public static void main(String[] args) {
        // Initialize a random number generator.
        Random rand = new Random();

        final double LMIN = -0.5;
        final double LMAX = 0.5;
        double RMIN = 0.1;
        double RMAX = 0.5;
        double PROB = 0.5;
        double NMAX = 100;

        if (args.length > 0) {
            if (args[0].equals("--help")) {
                System.out.println("""
                        Enter
                        NMAX(int)
                        PROB (double <= 1.0)
                        RMIN (double)
                        RMAX(double)
                        """);
            }
            NMAX = Integer.parseInt(args[0]);
            PROB = Double.parseDouble(args[1]);
            RMIN = Double.parseDouble(args[2]);
            RMAX = Double.parseDouble(args[3]);
        }

        // Set screen
        StdDraw.setCanvasSize(800, 800);
        StdDraw.setXscale(1.1 * LMIN, 1.1 * LMAX);
        StdDraw.setYscale(1.1 * LMIN, 1.1 * LMAX);
        // Draw a containing box.
        StdDraw.line(LMIN, LMIN, LMIN, NMAX);
        StdDraw.line(LMIN, LMAX, LMAX, LMAX);
        StdDraw.line(LMAX, LMAX, LMAX, LMIN);
        StdDraw.line(LMAX, LMIN, LMIN, LMIN);

        // Seed the random number generator.
        for (int i = 0; i < NMAX; i++) {
            double r = rand.nextDouble(RMIN, RMAX + 1e-12);
            double x = rand.nextDouble(LMIN + r, LMAX - r);
            double y = rand.nextDouble(LMIN + r, LMAX - r);
            double p = rand.nextDouble(0.0, 1.0);
            if (p <= PROB) {
                StdDraw.setPenColor(StdDraw.BLACK);
            } else {
                StdDraw.setPenColor(StdDraw.WHITE);

            }
            StdDraw.filledCircle(x, y, r);
        }
    }
}
