/* Program: rumorSpreading
 * Description: Assume that we have a group of N people. R starts a rumor.
 *              Each person starting the rumor will pass it to another 
 *              person in the remaining group excluding R and the person who
 *              passed the rumor the individual. The propagation of rumor 
 *              pauses as soon as a person hears it the second time. What is
 *              the probability that everyone at the party will hear the rumor
 *              before it stops propagating.
 * To simulate the spreading of rumor in a group of 1000 people, with 1000000
 * trials, use:
 * java rumorSpreading.java 1000 1000000 > rumors_1000.dat
 */
public class rumorSpreading {
    public static void main(String[] args) {
        int N = 5;
        int nTrials = 1000;

        if (args.length >= 1)
            N = Integer.parseInt(args[0]);
        if (args.length >= 2)
            nTrials = Integer.parseInt(args[1]);

        int[] freq = new int[N];

        int res = spreadRumor(N);

        int cntr = 1;
        while (cntr < nTrials) {
            cntr++;
            res = spread(N);
            freq[res-1]++;
        }

        System.out.println("#Number of trials: " + nTrials);
        for (int i = 3; i < N; i++) {
            System.out.printf("%d\t%f\n",i+1, ((double) freq[i]/nTrials));
        }
        System.out.println();
    }

    public static int spreadRumor(int N) {
        boolean[] iKnow = new boolean[N];

        /*
         * iKnow[0] is the initiator.
         * iKnow[1] is the person who passed the rumor.
         * iKnow[2] is the person who recieved the rumor.
         */

        iKnow[0] = iKnow[1] = true;

        // Number of people who have heard the rumor.
        int cntr = 2;
        int next = 2;

        do {
            iKnow[next] = true;
            cntr++;
            next = 3 + (int) (Math.random() * (N - 3));

        } while (!iKnow[next]);
        return cntr;
    }

    public static void printVector(boolean[] vector, int next) {
        System.out.printf("%2d: ", next);
        for (boolean val : vector) {
            System.out.print((val ? "T " : "F "));
        }
        System.err.println("");
    }
}
