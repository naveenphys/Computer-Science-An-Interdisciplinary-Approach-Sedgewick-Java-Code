/* Program: intOperations
 * Description: Demonstrations the multiplication and division operations.
 *              a / b gives quotient.
 *              a % b gives remainder.
 * byte:   8-bit
 * short: 16-bit
 * int:   32-bit
 * long:  64-bit
 */
public class intOperations {
    public static void main(String[] args)
    {
        int a = Integer.parseInt(args[0]);
        int b = Integer.parseInt(args[1]);
        int r1 = a + b;
        int r2 = a - b;
        int r3 = a * b;
        int r4 = a / b;
        int r5 = a % b;
        System.out.println(a + " + " + b + " = " + r1);
        System.out.println(a + " - " + b + " = " + r2);
        System.out.println(a + " * " + b + " = " + r3);
        System.out.println(a + " / " + b + " = " + r4);
        System.out.println(a + " % " + b + " = " + r5);
        // 1 / 0 and 1 % 0 lead to run-time exception.
        System.out.println("1 / 0 : " + (1 / 0));
        System.out.println("1 % 0 : " + (1 % 0));
    }
}
