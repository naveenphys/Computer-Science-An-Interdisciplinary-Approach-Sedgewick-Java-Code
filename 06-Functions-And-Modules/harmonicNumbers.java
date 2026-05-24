/* Program: harmonicNumbers
 * Description: Generates the nth harmonic number.
 * H_n = 1 + 1/2 + 1/3 + ... + 1/n
 * 
 * To evaluate the 0th, 3rd, 7th and 4th harmonic number use:
 * java harmonicNumbers.java 0 3 7 4
 */
public class harmonicNumbers {
    public static void main(String[] args) {
        int n;
        if (args.length == 0) {
            System.out.println("""
                    Example usage: To evaluate the 0th, 3rd, 7th and 4th 
                    harmonic number use:
                    java harmonicNumbers.java 0 3 7 4
                    """);
        }

        for (String arg : args) {
            n = Integer.parseInt(arg);
            System.out.printf("%6d %.15f\n", n, Hn(n));
        }
    }
    
    public static double Hn(int n) {
        assert(n > 0);

        double hn = 0.0;
        for (int i = 1; i <= n; i++) {
            hn += 1.0 /i;
        }
        return hn;
    }

}
