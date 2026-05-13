/* Program: setOfLines
 * Description: Draw a set of lines in the first quadrant, and scale the
 *              axis.
 */
public class setOfLines {
    public static void main(String[] args) {
        int n = 30;

        StdDraw.setPenRadius(0.005);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setCanvasSize(600, 600);
        StdDraw.setXscale(-1, (double)n);
        StdDraw.setYscale(-1, (double)n);
        

        for (int i = 0; i <= n; i++) {
            StdDraw.line(0, n-i, i, 0);
        }
    }
}
