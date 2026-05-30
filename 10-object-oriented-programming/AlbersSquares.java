/* Program: AlbersSquares
 * Description: Demonstrating the usage of class and reading from input
 *              using the Scanner class.
 *              The program shows a art construction called Albers Square
 *              that was defined by Josef Albers.
 *              https://en.wikipedia.org/wiki/Homage_to_the_Square
 *              In 1963, Albers published Interaction of Color, which is a 
 *              record of an experiential way of studying and teaching color.
 */

import java.awt.Color;
import java.util.Scanner;

public class AlbersSquares {
    public static void main(String[] args) {
        Color c1, c2;

        if (args.length != 6) {
            // Read an RGB color.
            int[] rgb1 = getRGB();
            c1 = new Color(rgb1[0], rgb1[1], rgb1[2]);
        } else {
            c1 = new Color(Integer.parseInt(args[0]),
                    Integer.parseInt(args[1]),
                    Integer.parseInt(args[2]));
        }
        
        if (args.length != 6) {
            // Read an RGB color.
            int[] rgb2 = getRGB();
            c2 = new Color(rgb2[0], rgb2[1], rgb2[2]);
        } else {
            c2 = new Color(Integer.parseInt(args[4]),
                    Integer.parseInt(args[5]),
                    Integer.parseInt(args[6]));
        }

        System.out.println("c1 = " + c1);
        System.out.println("c2 = " + c2);
        // Set the canvas properties.
        StdDraw.enableDoubleBuffering();
        StdDraw.setPenRadius(0.01);
        StdDraw.setCanvasSize(800, 800);
        // Draw two boxes and subboxes inside.
        StdDraw.setPenColor(c1);
        StdDraw.filledSquare(0.25, 0.5, 0.2);
        StdDraw.setPenColor(c2);
        StdDraw.filledSquare(0.25, 0.5, 0.1);

        // Draw two boxes and subboxes inside.
        StdDraw.setPenColor(c2);
        StdDraw.filledSquare(0.75, 0.5, 0.2);
        StdDraw.setPenColor(c1);
        StdDraw.filledSquare(0.75, 0.5, 0.1);
        StdDraw.show();

    }

    public static int[] getRGB() {
        int[] rgb = new int[3];

        System.out.println("Enter an RGB color (each value in [0, 255])");
        Scanner sc = new Scanner(System.in);
        try {
            for (int count = 0; count < 3; count++) {
                rgb[count] = sc.nextInt();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // Check if the code is valid.
        if (!isValidRGBCode(rgb)) {
            throw new RuntimeException("Invalid RGB code");
        }
        return rgb;
    }

    public static boolean isValidRGBCode(int[] code) {
        if (code.length != 3) {
            System.out.println("RGB code must be a length three array");
            return false;
        }
        boolean result = true;
        for (int i = 0; i < 3; i++) {
            if (!withInBounds(code[i], 0, 255, true, true)) {
                String sc = "[" + code[0] + ", " + code[1] + ", " + code[2] + "]";
                System.out.println("RGB code " + sc + " invalid at index " + i);
                result = false;
            }
        }
        return result;
    }

    public static boolean withInBounds(int c, int lowerBound, int upperBound,
            boolean lowerIncluded, boolean upperIncluded) {
        // Check lower bound.
        if (lowerIncluded) {
            if (c < lowerBound)
                return false;
        } else {
            if (c <= lowerBound)
                return false;
        }
        // Check upper bound.
        if (upperIncluded) {
            if (c > upperBound)
                return false;
        } else {
            if (c >= lowerBound)
                return false;
        }
        return true;
    }
}
