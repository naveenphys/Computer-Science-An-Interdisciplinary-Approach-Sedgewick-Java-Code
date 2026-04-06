/* Excercise: 1.1.2
 * Program: omittingAttributes
 * Description: Effect of missing attributes
 * 1. class omittingPublicAttribute works without public.
 * 2. The method main must be declared public, otherwise it is not visible.
 * 3. static attribute of the main function can also be removed.
 * 4. void cannot be removed because return type of each method is required.
 * 5. If the argument of main "args" is removed, then the function cannot
 *    read command line arguments. We can use a name of choice in place of the
 *    conventional name "args". 
 * 6. strings can not be broken over multiple lines. 
 */
public class omittingAttributes {
    public static void main(String[] args) {
        System.out.println("Hello world");
        // Text Blocks by declaring string with """ (3 double quote marks):
        System.out.println(
            """
            Hello world:
            I am printing using a TextBlock.
            """
        );
    }
}
