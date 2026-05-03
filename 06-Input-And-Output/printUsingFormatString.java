/* Program: printUsingFormatString
 * Description: Demonstrate the use of format string.
 *              Generate Hermite polynomials H_n and print a table with 
 *              values from n = 0 to n = 5 at 10 values of x in [0.0, 1.0].
 * 
 * Physicist's Hermite polynomials
 * https://en.wikipedia.org/wiki/Hermite_polynomials
 *              
 * Gnuplot 
 * plot for [j = 1:6] 'hermitePoly.dat' u 1:j w l title sprintf("H_%d",j-1)
 * plot for [j = 1:6] 'hermitePoly.dat' u 1:j w l dt j lc 'black' title sprintf("H_%d",j-1)
 */
public class printUsingFormatString {
    public static void main(String[] args) {

        // Range of x values.
        final double xMin = -2.0;
        final double xMax = 2.0;

        // Range of Hermite polynomials nMin and nMax are includes.
        final int nMin = 0;
        final int nMax = 10;

        int nSamples = 11;

        if (args.length == 1) {
            nSamples = Integer.parseInt(args[0]);
        }

        double delX = (xMax - xMin) / (nSamples - 1);

        double[] x = new double[nSamples];

        for (int i = 0; i < nSamples; i++) {
            x[i] = xMin + i * delX;
        }

        double[][] Hn = new double[nSamples][nMax - nMin + 1];

        for (int i = 0; i < nSamples; i++) {
            for (int j = nMin; j <= nMax; j++) {
                Hn[i][j - nMin] = HermitePolyN(j, x[i]);
            }
        }

        String fss = "%-22s ";
        fss += "%-22s %-22s %-22s %-22s %-22s ";
        fss += "%-22s %-22s %-22s %-22s %-22s %-22s\n";

        System.out.printf(fss, "#x",
                "H0", "H1", "H2", "H3", "H4",
                "H5", "H6", "H7", "H8", "H9", "H10");

        String fsd = "%+22.15e ";
        fsd += "%+22.15e %+22.15e %+22.15e %+22.15e ";
        fsd += "%+22.15e %+22.15e %+22.15e %+22.15e %+22.15e\n";

        for (int i = 0; i < nSamples; i++) {
            System.out.printf(fsd, x[i],
                Hn[i][0], Hn[i][1], Hn[i][2], Hn[i][3], Hn[i][4],
                Hn[i][5], Hn[i][6], Hn[i][7], Hn[i][8], Hn[i][9], Hn[i][10]);
        }

    }

    /*
     * Physicist's Hermite polynomials.
     * Reference: https://en.wikipedia.org/wiki/Hermite_polynomials
     */
    public static double HermitePolyN(int n, double x) {

        if (n < 0) {
            return 0.0;
        }
        if (n == 0) {
            return 1.0;
        }

        return 2.0 * x * HermitePolyN(n - 1, x) - 2.0
                * (n - 1) * HermitePolyN(n - 2, x);

    }
}
