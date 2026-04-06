/* Program: floatOperations
 * Description: Demonstrations the multiplication and division operations.
 *              a / b gives quotient.
 *              a % b gives remainder.
 */
public class floatOperations {
    public static void main(String[] args)
    {
        float a = Float.parseFloat(args[0]);
        float b = Float.parseFloat(args[1]);
        float r1 = a + b;
        float r2 = a - b;
        float r3 = a * b;
        float r4 = a / b;
        float r5 = a % b;
        System.out.println(a + " + " + b + " = " + r1);
        System.out.println(a + " - " + b + " = " + r2);
        System.out.println(a + " * " + b + " = " + r3);
        System.out.println(a + " / " + b + " = " + r4);
        System.out.println(a + " % " + b + " = " + r5);
        // 1 / 0 and 1 % 0 lead to run-time exception.
        System.out.println("1.0 / 0.0 : " + (1.0 / 0.0));
        System.out.println("1.0 % 0.0 : " + (1.0 % 0.0));
    }
}
