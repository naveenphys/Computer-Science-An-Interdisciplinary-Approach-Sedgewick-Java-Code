/* Program: triangle
 * Description:
 *
 * Java JDK 26 Documentation
 * https://docs.oracle.com/en/java/javase/26/
 * 
 * Java Development Kit (JDK) APIs
 * https://docs.oracle.com/en/java/javase/26/docs/api/index.html
 * 
 * Java-Desktop: Defines the AWT and Swing user interface toolkits, plus APIs 
 * for accessibility, audio, imaging, printing, and JavaBeans.
 * https://docs.oracle.com/en/java/javase/26/docs/api/java.desktop/module-summary.html
 * 
 * Package java.awt
 * Contains all of the classes for creating user interfaces and for painting 
 * graphics and images.
 */
public class triangle {
    public static void main(String[] args) {
        StdDraw.setPenRadius(0.01);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setCanvasSize(600, 600);
        StdDraw.setXscale(-0.1, 1.1);
        StdDraw.setYscale(-0.1, 1.1);
        double t = Math.sqrt(3.0)/2.0;
        StdDraw.line(0.0, 0.0, 1.0, 0.0);
        StdDraw.line(1.0, 0.0, 0.5, t);
        StdDraw.line(0.5, t, 0.0, 0.0);
        StdDraw.point(0.5,t/3.0);
    }
}