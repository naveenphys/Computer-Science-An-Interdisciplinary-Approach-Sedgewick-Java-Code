/* Program: expFunction
 * Description: Evaluate the exponential function using its Taylor expansion.
 * exp (x) = 1 + x + x^2/2! + x^2/3! + ..
 */
public class expFunction {
    public static void main(String[] args) {
        final double tol = 1e-16;
        final double xmin = -1.0;
        final double xmax = 1.0;

        double[] x;
        double[] y, z;
        int nSamples = 1;
        if (args.length == 1) {
            x = new double[nSamples];
            y = new double[nSamples];
            z = new double[nSamples];
            x[0] = Double.parseDouble(args[0]);
        } else {
            nSamples = 2 * 4 + 1;
            x = new double[nSamples];
            y = new double[nSamples];
            z = new double[nSamples];
            double dx = (xmax - xmin) / (nSamples - 1);
            for (int i = 0; i < nSamples; i++) {
                x[i] = i * dx - xmax;
            }
        }

        System.out.println("Math.E = " + Math.E);
        System.out.printf("%-16s\t%-16s\t%-16s\t%-15s\t%-15s\n",
                "x", "exp(x)", "Math.exp(x)", "Cost(exp)",
                "Cost(Math.exp)");
        for (int i = 0; i < x.length; i++) {
            long e0 = System.nanoTime();
            y[i] = exp(x[i], tol);
            long e1 = System.nanoTime();
            z[i] = Math.exp(x[i]);
            long e2 = System.nanoTime();
            System.out.printf("%+.16e\t%+.16e\t%+.16e\t%-15d\t%-15d\n",
                    x[i], y[i], z[i], e1 - e0, e2 - e1);
        }
    }

    public static double exp(double x, double tol) {
        double sign = Math.signum(x);
        x = Math.abs(x);
        double sum_0 = 1.0;
        double sum_1 = 1 + x;
        double iterm = x;
        long count = 1;
        while (Math.abs(sum_1 - sum_0) > tol) {
            iterm *= (x / (++count));
            sum_0 = sum_1;
            sum_1 += iterm;
        }

        if (sign < 0.0) {
            return 1.0 / sum_1;
        } else {
            return sum_1;
        }
    }
}