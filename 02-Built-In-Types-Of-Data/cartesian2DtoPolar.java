/* Program: cartesian2DtoPolar
 * Description: Converts the Cartesian coordinates of a 2D point to polar
 *              coordinates.
 */
public class cartesian2DtoPolar {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Missing command line inputs");
            System.out.println("x and y coordinates");
            return;
        }
        double x = Double.parseDouble(args[0]);
        double y = Double.parseDouble(args[1]);

        double r = Math.sqrt(x*x + y*y);
        // theta is in the range (-Pi, Pi]
        double theta = Math.atan2(y, x);
        System.out.println("(r, theta) = (" + r + ", " + theta + ")");
    }
}