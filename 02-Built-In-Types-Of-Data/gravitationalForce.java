/* Program: gravitationalForce
 * Description: Calculate the gravitational force between two masses m1 and m2
 *              separated by a distance r0.
 * Formula: F = G * m1 * m2 / r0^2
 * G is the universal Gravitational constant.
 */
public class gravitationalForce {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Missing command line inputs");
            System.out.println("m1: Mass of object 1");
            System.out.println("m2: Mass of object 2");
            System.out.println("r0: Distance between object 1 and 2");
            return;
        }

        final double G = 6.67430e11;
        // Read inputs from command line.
        double m1 = Double.parseDouble(args[0]);
        double m2 = Double.parseDouble(args[1]);
        double r0 = Double.parseDouble(args[2]);
        if (m1 < 0.0 || m2 < 0.0 || r0 < 0.0) {
            System.err.println("m1, m2, r0 must be positive");
            return;
        }

        double force = G * (m1 * m2) / (r0 * r0);
        System.out.println("f_gravitational = " + force + " N");
    }
}
