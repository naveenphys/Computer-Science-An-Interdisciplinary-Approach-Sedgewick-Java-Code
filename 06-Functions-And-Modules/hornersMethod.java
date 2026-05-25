/* Program: hornersMethod
 * Description: A polynomial of degree n-1 is 
 *              p_{0}+p_{1}*x^1+p_{2}*x^2+…+p_{n−2}*x^n−2 + p_{n−1}*x^{n−1}
 *              n - 1 additions,
 *              n - 1 multiplications.
 *              n - 2 power evaluations.
 * In the Horner's approach, the evaultion is done using:
 * p_{0} + x*(p_{1} + x*(p_{2} + … + x*(p_{n−2} + x*p_{n−1})…))
 */

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class hornersMethod {
    public static void main(String[] args) {
        // Coefficient of the polynomial.
        ArrayList<Double> coefficients = new ArrayList<>();
        if (args.length == 1) {
            // Generate random coefficients.
            int degree = Integer.parseInt(args[0]);
            Random rand = new Random();
            rand.setSeed(Long.MAX_VALUE);
            for (int i = 0; i < degree; i++) {
                coefficients.add(rand.nextDouble());
            }

        } else {
            // Read coefficients from console.
            System.out.println("Enter the coefficients of the polynomial");
            try (Scanner scanner = new Scanner(System.in)) {
                while (scanner.hasNextDouble()) {
                    coefficients.add(scanner.nextDouble());
                }
                scanner.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        if (coefficients.isEmpty())
            return;

        // Save coeffs into an array.
        double[] coeffs = new double[coefficients.size()];
        // Copy the constants.
        int count = 0;
        for (double elem : coefficients) {
            coeffs[count++] = elem;
        }

        System.out.println("Degree of polynomial = " + coefficients.size());

        long beg, end;

        {
            // Using direct method and passing a list.
            beg = System.nanoTime();
            double result = polynomial(coefficients.toArray(), 1.0);
            end = System.nanoTime();
            System.out.println("Elapsed time (Direct) = " + (end - beg));
            System.out.printf("result = %.15e\n", result);
        }

        {
            // Using Horner's method and passing a list.
            beg = System.nanoTime();
            double result = polynomialUsingHorner(coefficients.toArray(), 1.0);
            end = System.nanoTime();
            System.out.println("Elapsed time (Horner) = " + (end - beg));
            System.out.printf("result = %.15e\n", result);
        }

        {
            // Using direct method and passing an array.
            beg = System.nanoTime();
            double result = polynomial(coeffs, 1.0);
            end = System.nanoTime();
            System.out.println("Elapsed time (Direct) = " + (end - beg));
            System.out.printf("result = %.15e\n", result);
        }

        {
            // Using Horner's method and passing an array.
            beg = System.nanoTime();
            double result = polynomialUsingHorner(coeffs, 1.0);
            end = System.nanoTime();
            System.out.println("Elapsed time (Horner) = " + (end - beg));
            System.out.printf("result = %.15e\n", result);
        }
    }

    // Evaluate a polynomial using direct approach.
    static double polynomial(Object[] coefficients, double x) {
        double result = 0.0;
        double xp = 1.0;
        for (Object elem : coefficients) {
            result += (double) elem * xp;
            xp *= x;
        }
        return result;
    }

    // Overloaded version of polynomial using an array parameter.
    static double polynomial(double[] coefficients, double x) {
        double result = 0.0;
        double xp = 1.0;
        for (double elem : coefficients) {
            result += elem * xp;
            xp *= x;
        }
        return result;
    }

    // Evaluate a polynomial using Horner's approach.
    static double polynomialUsingHorner(Object[] coefficients, double x) {
        // Number of terms in the polynomial.
        int n = coefficients.length;

        double result = (double) coefficients[n - 1];
        int count = n - 1;
        while (count > 0) {
            count--;
            result *= x;
            result += (double) coefficients[count];
        }
        return result;
    }

    // Overloaded version of polynomialUsingHorner using an array parameter.
    static double polynomialUsingHorner(double[] coefficients, double x) {
        // Number of terms in the polynomial.
        int n = coefficients.length;

        double result = coefficients[n - 1];
        int count = n - 1;
        while (count > 0) {
            count--;
            result = coefficients[count] + x*result;
            //result *= x;
            //result += coefficients[count];
        }
        return result;
    }
}
