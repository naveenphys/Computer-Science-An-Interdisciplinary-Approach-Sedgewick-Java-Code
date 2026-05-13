/* Program: plotCities
 * Description: Read a sequence of points from standard input and plot them.
 *              The input is redirected from a file. The first four numbers
 *              are minimum and maximum x- and y- coordinates.
 */
public class plotCities {
    public static void main(String[] args) {

        // Read bounds of the box.
        double xMin = StdIn.readDouble();
        double yMin = StdIn.readDouble();
        double xMax = StdIn.readDouble();
        double yMax = StdIn.readDouble();

        // Set the canvas properties.
        StdDraw.setPenRadius(0.001);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setCanvasSize(1000, 1000);
        StdDraw.setXscale(xMin, xMax);
        StdDraw.setYscale(yMin, yMax);
        
        while (!StdIn.isEmpty()) {
            double x = StdIn.readDouble();
            double y = StdIn.readDouble();
            StdDraw.point(x, y);
        }
    }
    
}
