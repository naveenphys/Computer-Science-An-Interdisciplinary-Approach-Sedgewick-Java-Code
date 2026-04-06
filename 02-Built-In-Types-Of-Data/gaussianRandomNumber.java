/* Program: gaussianRandomNumber
 * Description: Generation of random number distributed according to a 
 *              Gaussian distribution.
 * A normal distribution or Gaussian distribution is a type of continuous 
 * probability distribution for a real-valued random variable. The general 
 * form of its probability density function is
 * f(x) = 1/sqrt(2*PI*sigma^2) * exp {-((x - mu)^2)/(2*sigma^2)}
 * 
 * Method: Box-Muller formula is used. It is a random number sampling method 
 * for generating pairs of independent, standard, normally distributed (zero 
 * mean, unit variance) random numbers, given a source of uniformly 
 * distributed random numbers. 
 * z(x) = 1/sqrt(2*PI) * exp (-x^2/2)
 * 
 * If ⁠Z⁠ is a standard normal deviate, then X = sigma * Z + mu will have a 
 * normal distribution with expected value ⁠mu, and⁠ and standard deviation ⁠
 * sigma.
 * https://en.wikipedia.org/wiki/Box%E2%80%93Muller_transform
 */
public class gaussianRandomNumber {
    public static void main(String[] args) {
        // Mean of the distribution
        double mean = Double.parseDouble(args[0]);
        // Standard deviation of the distribution
        double sdev = Double.parseDouble(args[1]);

        // Generate the standard normal variate.
        double u = Math.random();
        double v = Math.random();
        double z = Math.sin(2.0 * Math.PI * v) *
                Math.sqrt((-2.0 * Math.log(u)));
        // Transform to a Gaussian random variate.
        double x = sdev * z + mean;
        System.out.println(x);
    }
}
