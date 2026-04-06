/* Program: greatCircleDistance.
 * Description: A great circle is a great circle is the circular intersection 
 *              of a sphere and a plane passing through the sphere's center 
 *              point. In this example, we calculate the distance between two 
 *              point on the sphere. The coordinates of these points are 
 *              (lat_1, long_1) and (lat_2, long_2).
 * Note: Assumes that the sphere is centered on the origin of the coordinate
 *       system. Radius is assumed to be that of the Earth.
 */
public class greatCircleDistance {
    public static void main(String[] args) {
        if (args.length != 5) {
            System.out.println("Missing command line inputs");
            System.out.println("x1: Latitude of the point 1 (Degrees)");
            System.out.println("y1: Longitude of the point 1 (Degrees)");
            System.out.println("x2: Latitude of the point 2 (Degrees)");
            System.out.println("y2: Longitude of the point 2 (Degrees)");
            return;
        }
        
        final double RE_KM = 6378.137;

        // Read inputs from command line.
        double x1 = Math.toRadians(Double.parseDouble(args[0]));
        double y1 = Math.toRadians(Double.parseDouble(args[1]));
        double x2 = Math.toRadians(Double.parseDouble(args[2]));
        double y2 = Math.toRadians(Double.parseDouble(args[3]));

        System.out.println("Point 1 = (" + x1 + ", " + y1 + ")");
        System.out.println("Point 2 = (" + x2 + ", " + y2 + ")");

        double xa, ya, za;
        double xb, yb, zb;

        // Convert the coordinates to Cartesian.
        xa = Math.sin(x1) * Math.cos(y1);
        xb = Math.sin(x2) * Math.cos(y2);

        ya = Math.sin(x1) * Math.sin(y1);
        yb = Math.sin(x2) * Math.sin(y2);

        za = Math.cos(x1);
        zb = Math.cos(x2);

        // Angle between the position vectors from origin to the points.
        double theta = Math.acos (xa*xb + ya*yb + za*zb);

        // Calculate the distance.
        double distance = RE_KM * theta;

        System.out.println("Distance = " + distance + " km");

    }
}