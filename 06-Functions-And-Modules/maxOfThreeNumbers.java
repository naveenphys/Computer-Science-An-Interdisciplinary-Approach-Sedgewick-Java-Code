/* Program: maxOfThreeNumbers
 * Description: Function max3 takes three int arguments and returns the 
 *              value of the largest one. An overloadeded function max3
 *              does the same thing with three double values.
 */
public class maxOfThreeNumbers {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Enter three numbers");
        }
        {
            int a = Integer.parseInt(args[0]);
            int b = Integer.parseInt(args[1]);
            int c = Integer.parseInt(args[2]);

            System.out.println("max3(" + a + ", " + b + ", " + c + ") = "
                    + max3(a, b, c));
        }
        {
            double a = Double.parseDouble(args[0]);
            double b = Double.parseDouble(args[1]);
            double c = Double.parseDouble(args[2]);

            System.out.println("max3(" + a + ", " + b + ", " + c + ") = "
                    + max3(a, b, c));
        }

    }

    public static int max3(int a, int b, int c) {
        int max = a >= b ? a : b;
        max = max >= c ? max : c;
        return max;
    }

    public static double max3(double a, double b, double c) {
        double max = a >= b ? a : b;
        max = max >= c ? max : c;
        return max;
    }
}
