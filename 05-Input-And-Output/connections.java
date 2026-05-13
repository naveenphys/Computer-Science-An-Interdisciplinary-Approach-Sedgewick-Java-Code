/* Program: connections
 * Description: For a number n, and a floating point value p in [0,1), plot
 *              n equally spaced points on a circle, and with a probaility
 *              p for each pair of points, draw a gray line connecting them.
 */
public class connections {
    public static void main(String[] args) {
        // Number of points.
        int N = 2;
        if (args.length > 0)
            N = Integer.parseInt(args[0]);

        double p = 0.5;
        // Probability
        if (args.length > 1)
            p = Double.parseDouble(args[1]);

        // Divide the angle between 0 to 360 degrees in n arcs of equal size,
        // and remove the last point.
        double dTheta = (2.0 * Math.PI) / (double) (N);

        double[] theta = new double[N];
        double[] x = new double[N];
        double[] y = new double[N];

        for (int i = 0; i < N; i++) {
            theta[i] = i * dTheta;
            x[i] = Math.cos(theta[i]);
            y[i] = Math.sin(theta[i]);
            System.out.printf("%2d\t%+22.15e\t%+22.15e\n", i, x[i], y[i]);
        }

        // Set the canvas properties.
        StdDraw.setPenRadius(0.01);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setCanvasSize(800, 800);
        StdDraw.setXscale(-1.2, 1.2);
        StdDraw.setYscale(-1.2, 1.2);

        StdDraw.enableDoubleBuffering();

        // Draw the points.
        for (int i = 0; i < N; i++) {
            StdDraw.point(x[i], y[i]);
        }

        StdDraw.setPenRadius(0.001);
        // Draw the connecting lines.
        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
                // Get the odds favoring this pair.
                double prob = Math.random();
                if (prob <= p) {
                    StdDraw.line(x[i], y[i], x[j], y[j]);
                }
            }
        }

        StdDraw.show();

    }
}
