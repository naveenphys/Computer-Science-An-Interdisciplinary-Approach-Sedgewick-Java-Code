/* Program: mathFunctions
 * Description: Demonstrates functions from the Math library
 * Method call is an expression, so it can be used as part of expressions.
 * For example:
 * double a = Math.sqrt(2) * Math.sin(x)
 * Arguments are also expressions, so we can write expression in functions.
 * For example:
 * double a = Math.cos(Math.sqrt(2) * Math.sin(x))
 */
public class mathFunctions {
    public static void main(String[] args) {
        // Using a mathod call to build an expression.
        double PI = 4.0 * Math.atan(1.0);
        System.out.println("PI: " + PI);
        System.out.println("PI/Math.PI: " + (PI / Math.PI));
        // Using an expression as function argument.
        System.out.println("abs(PI)  : " + Math.abs(4.0 * Math.atan(1.0)));
        System.out.println("sin(PI)  : " + Math.sin(4.0 * Math.atan(1.0)));
        System.out.println("cos(PI)  : " + Math.cos(4.0 * Math.atan(1.0)));
        System.out.println("tan(PI)  : " + Math.tan(4.0 * Math.atan(1.0)));
        System.out.println("exp(PI)  : " + Math.exp(4.0 * Math.atan(1.0)));
        System.out.println("log(PI)  : " + Math.log(4.0 * Math.atan(1.0)));
        System.out.println("PI^(0.1) : " + Math.pow(4.0 * Math.atan(1.0), 0.1));
        System.out.println("round(PI): " + Math.round(4.0 * Math.atan(1.0)));
        System.out.println("sqrt(PI) : " + Math.sqrt(4.0 * Math.atan(1.0)));

        System.out.println("Using a number x generated using Math.random()");
        double x = Math.random();
        System.out.println("abs(x)  : " + Math.abs(x));
        System.out.println("sin(x)  : " + Math.sin(x));
        System.out.println("cos(x)  : " + Math.cos(x));
        System.out.println("tan(x)  : " + Math.tan(x));
        System.out.println("exp(x)  : " + Math.exp(x));
        System.out.println("log(x)  : " + Math.log(x));
        System.out.println("x^(0.1) : " + Math.pow(x, 0.1));
        System.out.println("round(x): " + Math.round(x));
        System.out.println("sqrt(x) : " + Math.sqrt(x));

    }

}
