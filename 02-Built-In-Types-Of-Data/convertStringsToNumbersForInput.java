/* Program: convertStringsToNumbersForInput
 * Description: Demonstrates the converion of a String to a number.
 *              It is done using Java library methods:
 *              Integer.parseInt(String s)
 *              Double.parseDouble(String s)
 * Note: No exception handing is done for this example.
 */
public class convertStringsToNumbersForInput {
    public static void main(String[] args) {
        double a = Double.parseDouble(args[0]);
        double b = Double.parseDouble(args[1]);
        System.out.println("Parsing each input numbers as a Double");
        System.out.println(a + " + " + b + " = " + (a + b));

        int c = Integer.parseInt(args[0]);
        int d = Integer.parseInt(args[1]);
        System.out.println("Parsing each input number as an Integer");
        System.out.println(c + " + " + d + " = " + (c + d));
    }
}
