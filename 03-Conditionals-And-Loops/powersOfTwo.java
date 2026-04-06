/* Program: powersOfTwo
 * Description: Calculate powers of two.
 */
public class powersOfTwo {
    public static void main(String[] args) {
        long nPowers = 1;
        if (args.length == 1) {
            nPowers = Long.parseLong(args[0]);
        }

        long count = 0;
        long value = 1;
        while (count < nPowers) {
            value *= 2;
            count += 1;
            System.out.println(count + "\t" + value +
                    "\t continue? " + (count < nPowers));
        }
    }
}
