/* Program: diceSimulation
 * Description: In a throw of a pair of independent dice. Following 
 *              microstates are possible.
 * (1,1) (1,2) (1,3) (1,4) (1,5) (1,6) 
 * (2,1) (2,2) (2,3) (2,4) (2,5) (2,6) 
 * (3,1) (3,2) (3,3) (3,4) (3,5) (3,6) 
 * (4,1) (4,2) (4,3) (4,4) (4,5) (4,6) 
 * (5,1) (5,2) (5,3) (5,4) (5,5) (5,6) 
 * (6,1) (6,2) (6,3) (6,4) (6,5) (6,6)
 * 
 * which correspond to the following values of the sum
 * (2) (3) (4) ( 5) ( 6) ( 7) 
 * (3) (4) (5) ( 6) ( 7) ( 8) 
 * (4) (5) (6) ( 7) ( 8) ( 9) 
 * (5) (6) (7) ( 8) ( 9) (10) 
 * (6) (7) (8) ( 9) (10) (11) 
 * (7) (8) (9) (10) (11) (12) 
 * 
 * Therefore, we find that the 
 * 2 and 12 appear  1 times. 
 * 3 and 11 appear  2 times.
 * 4 and 10 appear  3 times.
 * 5 and  9 appear  4 times.
 * 6 and  8 appear  5 times.
 * 7        appears 6 times.
 * 
 * The program evaluates these probabilities for n Dice simulation.
 */
public class diceSimulation {
    final static boolean showState = false;
    public static void main (String [] args) {
        int N = 10;
        if (args.length == 1) N = Integer.parseInt(args[0]);

        // With N arrays we have 6^N states.
        int [] mstates = new int [(int)Math.pow(6,N)];
        int [] state = new int [N];
        int [] freq  = new int [6*N+1];

        for (int m = 1; m <= mstates.length; m++ ) {
            int ms = m;
            for (int d = 1; d <= N; d++) {
               state[d-1] = (ms-1) / (int)Math.pow(6,N-d) + 1;
               ms -= (int)Math.pow(6,N-d)*(state[d-1]-1);
            }
            int sum = 0;
            for (int i= 0; i < N-1; i ++){
                if (showState) System.out.print(state[i] + ",");
                sum += state[i];
            }
            if (showState) System.out.println(state[N-1]);
            sum += state[N-1];
            freq[sum]++;
        }

        // Print the histogram.
        for (int i= N; i < 6*N+1; i ++){ 
            System.out.println(freq[i]);
        }

    }
}