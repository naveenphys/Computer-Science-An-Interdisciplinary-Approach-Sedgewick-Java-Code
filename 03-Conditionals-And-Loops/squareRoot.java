/* Program: squareRoot
 * Description: Calculate the square root of a number using Newton-Raphson 
 * method.
 * 
 * Consider a real number s, for which y = sqrt(s)
 * We are solving the equation f := y - sqrt(s) = 0 or g:= y^2 - s = 0.
 * Here the independent variable is y. As we vary y, we arrive at a value where
 * the function g = 0.
 * 
 * We assume that we are close to the solution, and curve between the guess and
 * solution can be approximated by a straight line.
 * 
 * If yGuess is the initial guess. The slope of the tangent is dg/dy = 2*yGuess. 
 * Equation of the straight line between (ySol, 0) and (yGuess, f(yGuess)) is
 * f - f(yGuess)/(y - yGuess) = dg/dy
 * yGuess - f(yGuess)/(dg/dy) = y
 * 
 */
public class squareRoot {
    public static void main(String[] args) {
        final int MAXITER = 10;
        // Default value of x
        double x = 2.0;
        if (args.length == 1) {
            x = Double.parseDouble(args[0]);
        }
        System.out.println("Finding square root of " + x);


        System.out.println("iter\ty_est\ty_sol\tabs(y_est-y_sol)");
        // Initial guess.
        double yGuess1 = x / 2.0;
        int count = 0;
        while (count <= MAXITER) {
            count += 1;
            double yGuess2 = yGuess1 - (yGuess1 * yGuess1 - x) / (2 * yGuess1);
            double error = Math.abs(yGuess2 - Math.sqrt(x));
            System.out.println(count + "\t" +
                    yGuess2 + "\t" + Math.sqrt(x) + "\tΔ =" + error);
            yGuess1 = yGuess2;
        }
    }

}
