/* Program: nPetals
 * Description: Takes a command-line argument n and plots a rose with n petals
 *              if n is odd, and 2n petals when n is even. The radius of the
 *              bubble is given by sin(2*n*t).
 */
public class nPetals {
    public static void main(String[] args) {
        int N = 16;
        if (args.length > 0) {
            N = Integer.parseInt(args[0]);
        }

        int nSamples = 100 * N; // Number of samples.
        if (args.length > 1) {
            nSamples = Integer.parseInt(args[1]);
        }

        System.out.println("nPetals: " + N);
        System.out.println("nSamples: " + nSamples);

        double dTheta = (2.0 * Math.PI) / nSamples;

        double[] x = new double[nSamples + 1];
        double[] y = new double[nSamples + 1];

        // Radius can be negative value.
        for (int i = 0; i < nSamples + 1; i++) {
            double r = Math.abs(Math.sin(N * i * dTheta));
            x[i] = r * Math.cos(i * dTheta);
            y[i] = r * Math.sin(i * dTheta);
        }

        // Plot the points.
        // Set the canvas properties.
        StdDraw.setCanvasSize(800, 800);
        StdDraw.setXscale(-1.2, 1.2);
        StdDraw.setYscale(-1.2, 1.2);

        StdDraw.enableDoubleBuffering();

        StdDraw.setPenRadius(0.04);
        StdDraw.setPenColor(StdDraw.GRAY);
        // Draw the connecting lines.
        for (int i = 0; i < nSamples; i++) {
            StdDraw.line(x[i], y[i], x[i + 1], y[i + 1]);
        }

        // Radius is a positive value.
        for (int i = 0; i < nSamples + 1; i++) {
            double r = Math.sin(N * i * dTheta);
            x[i] = r * Math.cos(i * dTheta);
            y[i] = r * Math.sin(i * dTheta);
        }

        StdDraw.setPenRadius(0.002);
        StdDraw.setPenColor(StdDraw.RED);
        // Draw the connecting lines.
        for (int i = 0; i < nSamples; i++) {
            StdDraw.line(x[i], y[i], x[i + 1], y[i + 1]);
        }

        StdDraw.show();
    }
}
