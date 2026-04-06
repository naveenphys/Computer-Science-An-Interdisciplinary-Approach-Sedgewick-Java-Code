/* Program: convertNumberToStringForOutput
 * Description: Demonstrates the conversion a value of any type to a String. 
 *              It is done using '+' operator.
 *              If one of the operand of the '+' operator is a String 
 *              the other operand is converted to a String.
 */
public class convertNumberToStringForOutput {
    public static void main(String[] args) {
        // Add a char to String.
        System.out.println("Hello" + "World" + '1');
        // Add an int number to String.
        System.out.println("Hello" + "World" + 1);
        System.out.println();
        System.out.println(
            """
            In the next three cases, we added a double to a String. However,
            the length of resulting strings is different, as the number of
            decimal places used for String is different for each case!
            """);
        // Add a double to String.
        String temp;
        temp = "HelloWorld_" + (double) 1.0;
        System.out.println(temp + " has length " + temp.length());
        // Add a double to String.
        temp = "HelloWorld_" + (double) Math.PI;
        System.out.println(temp + " has length " + temp.length());
        // Add a double to String.
        temp = "HelloWorld_" + (double) (1.0 / 3.0);
        System.out.println(temp + " has length " + temp.length());
    }
}