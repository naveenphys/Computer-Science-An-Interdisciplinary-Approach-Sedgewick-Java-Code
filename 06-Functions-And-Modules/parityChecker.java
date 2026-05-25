/* Program: parityChecker
 * Description: Implements a finite state machine for parity checking.
 */
public class parityChecker {
    static int state = 0; // Initial state of the parity checker.

    public static void main(String[] args) {
        String input = args[0];
        System.out.println("Read the string: " + input);

        // Check that the input is made of 0's and 1's only.
        validateInput(input);
        checkParity(input);

    }

    public static void validateInput(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '0' || s.charAt(i) == '1')
                continue;
            System.err.println("Error: Not a valid input");
            System.err.println("Only 0 and 1 are allowed characters");
            break;
        }
    }

    public static void checkParity(String s) {

        for (int i = 0; i < s.length(); i++) {
            stateTransition(s.charAt(i));
            
        }
        
        switch (state) {
            case 0 -> System.out.println("Even Parity");
            case 1 -> System.out.println("Odd Parity");
            default -> throw new AssertionError();
        }
    }

    public static void stateTransition(char b) {
        if (state == 0 && b == '1') {
            state = 1;
            return;
        }
        if (state == 1 && b == '1')
            state = 0;
    }
}
