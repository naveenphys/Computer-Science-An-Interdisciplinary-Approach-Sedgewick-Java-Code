/* Program: guessingGame
 * Description: In this program, we will read from the standard input using
 *              the System.in.parseInt
 */

public class guessingGame {
    public static void main(String[] args) {

        int secret = (int) (Math.random() * 100);

        int guess = 0;

        System.out.println("Enter an int in [0 and " + 1000 + ")");

        int nGuesses = 0;

        while (guess != secret) {
            guess = readInt();
            nGuesses += 1;
            if (guess < secret)
                System.out.println("Too small");
            if (guess > secret)
                System.out.println("Too large");
        }

        System.out.println("You won in " + nGuesses + " attempts!");
    }

/* Read an integer from standard input (input stream)
 */
    public static int readInt() {
        String data = "";
        try {
            int input;
            while ((input = System.in.read()) != '\n') {
                data += (char) input;
            }
        } catch (java.io.IOException e) {
            System.err.println("Error reading input: " + e.getMessage());
        }

        return Integer.parseInt(data);
    }
}
