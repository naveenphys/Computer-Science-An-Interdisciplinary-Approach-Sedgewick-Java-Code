/* Program: divisorPattern
 * Description: Constructs an N X N square matrix. All entries are zero
 * except the one where row % col == 0 || col % row == 0.
 * 
 * This program demonstrates nested for loops.
 */
public class divisorPattern {
    public static void main(String[] args) {
        int N = 4;
        if (args.length == 1) {
            N = Integer.parseInt(args[0]);
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if ( i % j == 0 || j % i == 0 )
                    System.out.print("* ");
                else
                    System.out.print("  ");
            }
            System.out.println();
        }
    }
}
