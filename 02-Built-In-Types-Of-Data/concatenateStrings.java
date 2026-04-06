/* Program: concatenateStrings
 * Description: Demonstrates concatenation of strings.
 *              '+' is the operator used for combining String variables.
 * Example:
 *         java concatenateStrings hello who are you
 *         hello.
 *         hello.who.
 *         hello.who.are.
 *         hello.who.are.you.
 */

public class concatenateStrings {
    public static void main(String[] args) {
        String result = "";
        System.out.println(result);
        for (String arg : args) {
            result = result + arg + ".";
            System.out.println(result);
        }
    }
}