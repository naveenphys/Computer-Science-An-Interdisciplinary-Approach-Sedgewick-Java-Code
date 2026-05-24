
/* Program: gaussianProbabilityDistribution
 * Description: Returns the value of the Gaussian probability distribution 
 *              function
 *              \phi(x,mu,sigma) = 1/(sqrt(2*pi)*sigma) * exp(-z*z/2)
 *              where z = (x-mu)/sigma
 * When mu = 0.0, and sigma = 1.0, we get the standard normal distribution.
 */
public class gaussianProbabilityDistribution {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("""
                    Enter x, mean, and sigma on the command line.
                    """);
        }

        double x = Double.parseDouble(args[0]);
        double mu = Double.parseDouble(args[1]);
        double sigma = Double.parseDouble(args[2]);


        double gpdf = gaussianPDF(x, mu, sigma);
        double cpdf = gaussianCDF(x, mu, sigma);

        System.out.printf("%.15f = %.15f\n", x, gpdf);
        System.out.printf("%.15f = %.15f\n", x, cpdf);
    }

    /*
     * Gaussian probability density function.
     * In case of mean, mu, and variance sigma, we have
     * \phi(x,mu,sigma) = 1/sigma * exp(-z*z/2) / sqrt(2*pi)
     * where z = (x-mu)/sigma
     */
    public static double gaussianPDF(double x, double mu, double sigma) {
        double z = (x - mu) / sigma;
        return (1.0 / sigma) *
                Math.exp(-z * z / 2.0) / (Math.sqrt(2.0 * Math.PI));
    }

    /*
     * Cumulative distribution of the Gaussian probability density function.
     * PHI(x) = Integrate[Phi(x), {x, -Infinity, x}]
     * 
     * The integral approaches 1 as x -> Infinity.
     * 
     * A related function is error function.
     * Erf(x) which gives the probability of a random variable, with
     * normal distribution of mean 0 and variance 1/2, falling in the range
     * ⁠[-x,x]
     * 
     * The cumulative distribution function (CDF) of the standard normal
     * distribution, usually denoted with the capital Greek letter ⁠
     * \Phi(x) = 1/2 * (1 + Erf[x/sqrt(2)])
     * 
     * The cumulative distribution function (CDF) of the normal
     * distribution, usually denoted with the capital Greek letter ⁠
     * \Phi(x) = 1/2 + \phi(z) * Erf[z/sqrt(2)])
     * where z = (x - mu)/sigma, and \phi(z) is the standard normal 
     * distribution.
     * 
     */
    public static double gaussianCDF(double x, double mu, double sigma) {
        double z = (x - mu) / sigma;
        if (z < -8.0)
            return 0.0;
        if (z > 8.0)
            return 1.0;

        // Using Taylor series expansion of Erf(x)
        double sum = 0.0;
        double term = z;
        for (int i = 3; sum != (sum + term); i += 2) {
            sum += term;
            term *= ((z * z) / i);
        }

        return (0.5 + gaussianPDF(z, 0, 1) * sum);
    }

}
