
/* Program: sinFunction
 * Description: Calculates the sin of a real variable x using Taylor exmpansion
 * sin(x) = x - x^3/3! + x^5/5! +...
 */
public class sinFunction {
    public static void main(String[] args) {
        final double tol = 1e-12;
        final double xmin = -1.0;
        final double xmax = 1.0;
        double[] x;
        double[] y, z, w;
        int nSamples = 1;
        if (args.length == 1) {
            x = new double[nSamples];
            y = new double[nSamples];
            z = new double[nSamples];
            w = new double[nSamples];
            x[0] = Double.parseDouble(args[0]);
        } else {
            nSamples = 2 * 4 + 1;
            x = new double[nSamples];
            y = new double[nSamples];
            z = new double[nSamples];
            w = new double[nSamples];
            double dx = (xmax - xmin) / (nSamples - 1);
            for (int i = 0; i < nSamples; i++) {
                x[i] = i * dx - xmax;
                x[i] *= 5.0*Math.PI;
            }
        }

        System.out.println("PI = " + Math.PI);
        System.out.printf(
            "%-8s\t%-23s\t%-23s\t%-23s\t%-10s\t%-10s\t%-10s\n",
            "x/PI", "sin(x)", "Sin(x)", "Math.sin(x)", "Cost(sin)",
            "Cost(Sin)", "Cost(Math.sin)");
        for (int i = 0; i < x.length; i++) {
            long e0 = System.nanoTime();
            y[i] = sin(x[i], tol, true);
            long e1 = System.nanoTime();
            z[i] = Sin(x[i], tol);
            long e2 = System.nanoTime();
            w[i] = Math.sin(x[i]);
            long e3 = System.nanoTime();
            System.out.printf(
                "%+.8f\t%+.16e\t%+.16e\t%+.16e\t%-10d\t%-10d\t%-10d\n",
                x[i] / Math.PI, y[i], z[i], w[i], e1 - e0, e2 - e1, e3 - e2);
        }
    }

    private static double sin(double x, double tol, boolean use_symmetry) {
        // Calculate the sin of x.
        // Sin is odd function.
        // Sin(nPi + x) = -Sin(x), n is odd.
        // Sin(nPi + x) = +Sin(x), n is even.

        final double PI_div_2 = 0.5 * Math.PI;
        // Save the sign of number. Sin function is odd.
        double sign_1 = Math.signum(x);

        // We can calculate sin(x) = signum(x)*sin(abs(x))
        x = Math.abs(x);

        if (use_symmetry == true) {
            // Calculate x/Math.Pi;
            int n = (int) Math.floor(x / Math.PI);
            sign_1 *= 1.0;
            if (n % 2 != 0)
                sign_1 *= -1.0;

            // Linear transformation to bring the value in interval [0, Pi)
            x = x - n * Math.PI;

            // If x is in interval [0, Pi), then, the function values are symmetric
            // around PI/2.
            // sin(Pi) = sin(0); sin(3/4 *Pi) = sin(Pi/4.0)

            if (x > PI_div_2) {
                x = Math.PI - x;
            }
        }

        double sum_0 = x;
        int sign = -1;
        long count = 1;
        long den = 1;
        den *= (++count);
        den *= (++count);
        double num = ipow(x, count);
        double sum_1 = sum_0 + sign * (num / den);
        // System.out.printf("%.18e\t%.18e\t%d\t%.18e\t%d\t%d\n", sum_0, num, den,
        // sum_1, count, sign);

        while (Math.abs(sum_1 - sum_0) > tol) {
            sum_0 = sum_1;
            sign *= -1;
            den *= (++count);
            if (den < 0)
                break;
            den *= (++count);
            if (den < 0)
                break;
            num = ipow(x, count);
            sum_1 += ((sign * num) / den);
            // System.out.printf("%.18e\t%.18e\t%d\t%.18e\t%d\t%d\n", sum_0, num, den,
            // sum_1, count, sign);
        }
        if ((sign_1 * sum_1) < 0.0) {
            return Math.max(-1.0, -sum_1);
        } else {
            return Math.min(1.0, sum_1);
        }
    }

    private static double Sin(double x, double tol) {
        // Calculate the sin of x.
        // Sin is odd function.
        // Sin(nPi + x) = -Sin(x), n is odd.
        // Sin(nPi + x) = +Sin(x), n is even.

        final double PI_div_2 = 0.5 * Math.PI;
        // Save the sign of number. Sin function is odd.
        double sign_1 = Math.signum(x);

        // We can calculate sin(x) = signum(x)*sin(abs(x))
        x = Math.abs(x);

        // Calculate x/Math.Pi;
        int n = (int) Math.floor(x / Math.PI);
        sign_1 *= 1.0;
        if (n % 2 != 0)
            sign_1 *= -1.0;

        // Linear transformation to bring the value in interval [0, Pi)
        x = x - n * Math.PI;

        // If x is in interval [0, Pi), then, the function values are symmetric
        // around PI/2.
        // sin(Pi) = sin(0); sin(3/4 *Pi) = sin(Pi/4.0)

        if (x > PI_div_2) {
            x = Math.PI - x;
        }

        double sum_0 = x;
        double iterm = -ipow(x, 3) / 6.0;
        double sum_1 = sum_0 + iterm;
        // System.out.printf("%.18e\t%.18e\t%.18e\n", sum_0, iterm, sum_1);
        long count = 3;
        while (Math.abs(sum_1 - sum_0) > tol) {
            sum_0 = sum_1;
            count += 2;
            iterm *= -((x * x) / (double) (count * (count - 1)));
            if (Math.abs(iterm) < 1e-16)
                break;
            sum_1 += iterm;
            // System.out.printf("%.18e\t%.18e\t%.18e\n", sum_0, iterm, sum_1);
        }
        if (sign_1 * sum_1 < 0.0) {
            return Math.max(-1.0, -sum_1);
        } else {
            return Math.min(1.0, sum_1);
        }
    }

    private static double ipow(double x, long n) {
        double result = x;
        long counter = 1;
        while (counter < n) {
            result *= x;
            counter += 1;
        }
        return result;
    }
}
