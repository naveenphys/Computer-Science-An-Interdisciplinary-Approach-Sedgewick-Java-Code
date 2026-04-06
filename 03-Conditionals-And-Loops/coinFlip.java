/* Program: coinFlip
 * Description: Simulation N tosses of a fair coin.
 */
public class coinFlip {
    public static void main(String[] args) {
        int nFlips = 1;
        if (args.length == 1)
        {
            nFlips = Integer.parseInt(args[0]);
        }

        int nHeads = 0;
        for (int i = 0; i < nFlips; i++) {
            boolean isHead = (Math.random() < 0.5);
            if (isHead)
            {
                System.out.print("H ");
                nHeads += 1;
            }
            else
            {
                System.out.print("T ");
            }
            if (((i + 1) % 32 == 0) || (i == nFlips -1)) System.err.println();
        }
        

        System.out.println("nHeads/nFlips = " + nHeads + "/" + nFlips);
    }
}
