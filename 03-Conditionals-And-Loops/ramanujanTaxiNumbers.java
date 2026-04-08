/* Program: ramanujanTaxiNumbers
 * Description: S. Ramanujan was an Indian mathematician who became famous for 
 * his intuition for numbers. When the English mathematician G. H. Hardy came to
 * visit him in the hospital one day, Hardy remarked that the number of his taxi
 * was 1729, a rather dull number. To which Ramanujan replied, No, Hardy! It is 
 * a very interesting number. It is the smallest number expressible as the sum 
 * of two cubes in two different ways." 
 * 
 * The program takes integer command-line argument N0 and N1 and prints the
 * different ways in which these integers [N0, N1] can be expressed as the sum 
 * of two cubes.
 * Also check if a license plate 87539319 would be a rather dull number. 
 */
public class ramanujanTaxiNumbers {
    public static void main(String[] args) {
        int N0 = 1;
        int N1 = 2000;
        System.out.println("Max 32 bit integer = " + Integer.MAX_VALUE);
        if (args.length == 2) {
            N0 = Integer.parseInt(args[0]);
            N1 = Integer.parseInt(args[1]);
        }


        // Loop on the numbers from 1 to N0.
        for (int N = N0; N <= N1; N++) {
            int nMax = (int) Math.pow((double)N, 1.0/3.0);
            
            // Number of solutions.
            int nSolutions = 0;
            for (int m = 1; m <= nMax ; m++) {
                for (int n = nMax; n >= m ; n--) {
                    if (m*m*m + n*n*n == N) {
                        nSolutions+= 1;
                        System.out.println("\t"+ m + "^3 + " + n + "^3 = " + N);
                    }
                }
            }
            if (nSolutions > 0)
            {
                System.out.println("\tNumber of solution for " + N 
                + " = " + nSolutions);
            }
        }
    }

}
