/* Program: quadraticSolver
 * Description: Solves a quadratic equation, ax^2 + bx + c
 */
public class quadraticSolver {
    public static void main(String[] args) {
        int nargs = args.length;
        if (nargs == 0) {
            System.out.println("No coefficients given on command line");
            return;
        }
        double a = 0.0, b = 0.0, c = 0.0;
        if (nargs > 0) {
            a = Double.parseDouble(args[0]);
        }
        if (nargs > 1) {
            b = Double.parseDouble(args[1]);
            b /= a;
        }
        if (nargs > 2) {
            c = Double.parseDouble(args[2]);
            c /= a;
        }
        double disc2 = b * b - 4 * c;
        System.out.println("Equation: " + a + " x^2 + " + b + " x + " + c);
        System.out.println("Discriminant^2 = " + disc2);
        if (disc2 >= 0.0) {
            double r1 = (-b + Math.sqrt(disc2)) / 2.0;
            double r2 = (-b - Math.sqrt(disc2)) / 2.0;
            System.out.println("1st root = " + r1);
            System.out.println("2nd root = " + r2);
        } else {
            System.out.println("Quadratic has complex roots");
            double real = -b/2.0;
            double imag = Math.sqrt(-disc2)/2.0;
            System.out.println("1st root = " + real + " + " + imag +"i");
            System.out.println("2nd root = " + real + " - " + imag +"i");

        }
    }
}
