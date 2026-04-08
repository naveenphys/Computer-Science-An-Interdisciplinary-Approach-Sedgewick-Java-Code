/* Program: integerBinaryRepresentation.
 * Description: Binary representation of integers.
 */
public class integerBinaryRepresentation {
    public static void main(String[] args) {
        
        int N = Integer.MAX_VALUE;
        if (args.length == 1) {
            N = Integer.parseInt(args[0]);
        }
        else
        {
            System.err.println("Using N = " + Integer.MAX_VALUE);
        }

        // Power of two: b = 2^i, i = 0, 1, 2,.. 31
        int b = 1;
        for (int i = 0; i <  31; i++)  {
            // Print the AND of the input and the power of 2.
            System.out.print((N & b) >> i);
            // Shift bit to the left by one place.
            b <<= 1;
        }
        System.err.println();
    }
}
