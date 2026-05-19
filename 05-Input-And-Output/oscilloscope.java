/* Program: oscilloscope
 * Description: Simulate the output of an oscilloscope and produce Lissajous 
 *              patterns. These patterns are named after the French physicist,
 *              Jules A. Lissajous, who studied the patterns that arise when 
 *              two mutually perpendicular periodic disturbances occur 
 *              simultaneously. Assume that the inputs are sinusoidal, so that 
 *              the following parametric equations describe the curve
 *
 * Run: java --class-path ./stdlib_dir oscilloscope.java 1 1 0 1 3 1.5707963267948966              
 */
public class oscilloscope {
    public static void main(String[] args) {
        if (args.length != 6) {
            System.out.println("""
                    Enter the following quantities (in this order):
                    aX: Double
                    omegaX/2*PI: Double
                    phaseX: Double
                    aY: Double
                    omegaY/2*PI: Double
                    phaseY: Double""");
        }
        final double twoPI = 2.0* Math.PI;
        final double tol = 1e-8;
        double aX = Double.parseDouble(args[0]);
        double nuX = Double.parseDouble(args[1]);
        double omegaX = twoPI* nuX;
        double phaseX = Double.parseDouble(args[2]);
        double aY = Double.parseDouble(args[3]);
        double nuY = Double.parseDouble(args[4]);
        double omegaY = twoPI * nuY;
        double phaseY = Double.parseDouble(args[5]);

        // Plotting setup.
        // Set the canvas properties.
        StdDraw.setPenRadius(0.005);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setCanvasSize(1200, 1200);
        StdDraw.setXscale(-1.2 * aX, 1.2 * aX);
        StdDraw.setYscale(-1.2 * aY, 1.2 * aY);

        // Draw the axis.
        StdDraw.line(0.0, -1.4 * aY, 0.0, 1.4 * aY);
        StdDraw.line(-1.4 * aX, 0.0, 1.4 * aX, 0.0);

        System.out.printf("Freq of the x-oscillator  = %f Hz\n", nuX);
        System.out.printf("Freq of the y-oscillator  = %f Hz\n", nuY);

        // Time period. (omega * T = 2*Pi)
        double Tx = 1.0/nuX;
        double Ty = 1.0/nuY;
        double tMin = Math.min(Tx, Ty);

        System.out.printf("Tx = %f\n", Tx);
        System.out.printf("Ty = %f\n", Ty);
        System.out.printf("tMin = %f\n", tMin);

        // Find out the number of full cycles needed such that both 
        // oscillators reach their initial phase space position.
        int nCycles = 0;
        double x0, y0, u0, v0;
        double xt, yt, ut, vt;

        // Initial position and velocity
        x0 = aX * Math.sin(phaseX);
        y0 = aY * Math.sin(phaseY);
        u0 = aX * Math.cos(phaseX) * omegaX;
        v0 = aY * Math.cos(phaseY) * omegaY;
        boolean c1, c2, c3, c4, c0;
        
        System.out.printf("%4d %+f %+f %+f %+f\n", nCycles, x0, y0, u0, v0);
        
        do {
            nCycles += 1;
            // Position and velocity after one cycle of the faster oscillator.
            xt = aX * Math.sin(nCycles * omegaX * tMin + phaseX);
            yt = aY * Math.sin(nCycles * omegaY * tMin + phaseY);
            ut = aX * Math.cos(nCycles * omegaX * tMin + phaseX) * omegaX;
            vt = aY * Math.cos(nCycles * omegaY * tMin + phaseY) * omegaY;
            c1 = Math.abs(xt-x0) > tol;
            c2 = Math.abs(yt-y0) > tol;
            c3 = Math.abs(ut-u0) > tol;
            c4 = Math.abs(vt-v0) > tol;
            c0 = c1 || c2 || c3 || c4;
            System.out.printf("%4d %+f %+f %+f %+f\n", nCycles, xt, yt, ut, vt);
        } while (c0);

        // Time step.
        double dt = tMin / 200.0;

        double t = 0.0;

        StdDraw.enableDoubleBuffering();
        // Evolve until the phase covered by the both the oscillators matches.
        while (omegaX * t + phaseX < nCycles * 2.0 * Math.PI) {
            double x = aX * Math.sin(omegaX * t + phaseX);
            double y = aY * Math.sin(omegaY * t + phaseY);
            StdDraw.point(x, y);
            t += dt;
        }
        StdDraw.show();
        System.out.println("Finished after t = " + t + " s");

    }
}
