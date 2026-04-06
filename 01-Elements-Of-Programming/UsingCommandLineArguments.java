/* Program: UsingCommandLineArguments
 *
 * Description: Accept the command line arguments after program name and 
 *              prints them back to the terminal
 * Example:
 *         java UsingCommandLineArguments hello
 *         Here, "hello" is the command line argument stored in args[0], and 
 *         is printed to the stdout
 *         java UsingCommandLineArguments @!&^%
 *         In a terminal, a single & operator is used to run a process in the 
 *         background. This means that you can go ahead and run other commands
 *         on the command line while it is still running. Therefore, the java
 *         program will accept the characters upto '&', and will treat 
 *         characters after & as next commands. It will cause an error, and to
 *         avoid it the string can be enclosed in quotation marks.
*/

public class UsingCommandLineArguments {
    public static void main(String[] args) {
        // Print number of arguments.
        System.out.println("Number of arguments: " + args.length);
        // Loop over the arguments.
        for (String arg : args) {
            System.out.println(arg);
        }
    }
}
