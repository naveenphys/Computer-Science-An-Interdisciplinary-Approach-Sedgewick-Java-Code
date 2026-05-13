/* Program: BouncingBall
 * Description: The program simulates the motion of a bouncing ball in the box
 *              inside a box stretching from -1 and +1 in x and y directions.
 *              The ball bounces at the boundaries according to the laws of 
 *              inelastic collisions.
 */

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random; 

public class bouncingBall {
    static Random rnd = new Random();
    static final int NO_COLLISION = -1;
    static final int XL_COLLISION = 1;
    static final int XR_COLLISION = 2;
    static final int YL_COLLISION = 3;
    static final int YR_COLLISION = 4;

    public static void main(String[] args) throws IOException {
        // Box dimensions.
        final double xMin = -1.0;
        final double xMax = 1.0;
        final double yMin = -1.0;
        final double yMax = 1.0;
        // Radius of the ball
        final double ballRadius = 0.05;
        // Velocity range.
        final double vMin = 0.05;
        final double vMax = 0.10;
        // Step size (static in this case)
        final double stepSize = 1e-1;

        rnd.setSeed(System.currentTimeMillis());

        double[] iPos = randomPosition(xMin, xMax, yMin, yMax);
        double[] iVel = randomVelocity(vMin, vMax);

        // If we want to test the corner collision.
        // iPos[0] = iPos[1] = 0.0;
        // iVel[0] = iVel[1] = vMax;

        System.out.printf("x0 = %+22.15e\ty0 = %+22.15e\n", iPos[0], iPos[1]);
        System.out.printf("vx = %+22.15e\tvy = %+22.15e\n", iVel[0], iVel[1]);

        System.out.println("Enter the final time > 0.0");
        double tFinal = StdIn.readDouble();

        // Set the canvas properties.
        StdDraw.setPenRadius(0.001);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setCanvasSize(600, 600);
        StdDraw.setXscale(xMin - 0.1, xMax + 0.1);
        StdDraw.setYscale(yMin - 0.1, yMax + 0.1);

        StdDraw.enableDoubleBuffering();

        // Time-stepping.
        double tOld = 0.0;
        double[] positionOld = new double[2];
        double[] positionNew = new double[2];
        // Copy the initial position.
        System.arraycopy(iPos, 0, positionOld, 0, 2);
        double tNew = tOld + stepSize;

        // Open a file
        FileWriter myWriter = new FileWriter("output.dat"); 

        int stepCounter = 0;
        while (tNew < tFinal) {

            // Get the new position.
            positionNew[0] = positionOld[0] + (tNew - tOld) * iVel[0];
            positionNew[1] = positionOld[1] + (tNew - tOld) * iVel[1];

            // Check if there is a collision.
            int xCollision = hasXCollision(ballRadius, xMin, xMax, positionNew[0]);

            // In case there is collision, find the corrected time.
            double tXcollision = tNew;
            if (xCollision != NO_COLLISION) {
                tXcollision = setNewTime(tOld, positionOld[0], iVel[0], xCollision, ballRadius,
                        xMin, xMax);
            }

            int yCollision = hasYCollision(ballRadius, yMin, yMax, positionNew[1]);
            double tyCollision = tNew;
            // In case there is collision, find the corrected time.
            if (yCollision != NO_COLLISION) {
                tyCollision = setNewTime(tOld, positionOld[1], iVel[1], yCollision, ballRadius,
                        yMin, yMax);
            }

            if (xCollision != NO_COLLISION || yCollision != NO_COLLISION) {
                tNew = Math.min(tNew, Math.min(tXcollision, tyCollision));
                // Update the new positions.
                positionNew[0] = positionOld[0] + (tNew - tOld) * iVel[0];
                positionNew[1] = positionOld[1] + (tNew - tOld) * iVel[1];
                // Check for the collision. As a result of going back in
                // time, the collision detection needs to be redone. For eg,
                // if tXcollision is smaller than tyCollision, then we will
                // hit the x-boundary with the reduced time step, but will
                // not hit the y-boundary in the same time.
                if (tXcollision < tyCollision) {
                    yCollision = hasYCollision(ballRadius, yMin, yMax, positionNew[1]);
                } else {
                    xCollision = hasXCollision(ballRadius, xMin, xMax, positionNew[0]);
                }
                // Make the collision.
                makeCollision(xCollision, iVel);
                makeCollision(yCollision, iVel);
            }
            System.arraycopy(positionNew, 0, positionOld, 0, 2);
            tOld = tNew;
            tNew += stepSize;
            myWriter.write(tOld + "\t" + positionOld[0] + "\t" + positionOld[1] + "\n");
            // Plot the point on screen.
            if (stepCounter % 10 == 0) {
                StdDraw.clear();
            }
            // Make a box;
            StdDraw.line(xMin, yMin, xMin, yMax);
            StdDraw.line(xMin, yMax, xMax, yMax);
            StdDraw.line(xMax, yMax, xMax, yMin);
            StdDraw.line(xMax, yMin, xMin, yMin);
            StdDraw.circle(positionOld[0], positionOld[1], ballRadius);
            StdDraw.show();
            stepCounter += 1;
        }
        myWriter.flush();
        myWriter.close();
    }

    public static double setNewTime(double tNow,
            double pos,
            double vel,
            int which,
            double bSize,
            double min, double max) {
        double tNew = tNow;

        switch (which) {
            case NO_COLLISION -> {
            }
            case XL_COLLISION -> {
                tNew = tNow + (min + bSize - pos) / vel;
            }
            case XR_COLLISION -> {
                tNew = tNow + (max - bSize - pos) / vel;
            }
            case YL_COLLISION -> {
                tNew = tNow + (min + bSize - pos) / vel;
            }
            case YR_COLLISION -> {
                tNew = tNow + (max - bSize - pos) / vel;
            }
            default -> throw new AssertionError();
        }
        return 1.05*tNew;
    }

    public static void makeCollision(int which, double[] velocity) {
        switch (which) {
            case NO_COLLISION -> {
            }
            case XL_COLLISION -> {
                velocity[0] *= -1.0;
                break;
            }
            case XR_COLLISION -> {
                velocity[0] *= -1.0;
                break;
            }
            case YL_COLLISION -> {
                velocity[1] *= -1.0;
                break;
            }
            case YR_COLLISION -> {
                velocity[1] *= -1.0;
                break;
            }
            default -> throw new AssertionError();
        }
    }

    /*
     * Check if the ball is hitting an x wall.
     */
    public static int hasXCollision(double bSize, double xMin, double xMax,
            double xNow) {
        // Check for collision with the four boundaries.
        int collision = NO_COLLISION;
        double xPos = xNow;

        // Check if there is a collision on x-boundaries.
        if (xPos <= xMin + bSize) {
            collision = XL_COLLISION;
        }
        if (xPos >= xMax - bSize) {
            collision = XR_COLLISION;
        }

        return collision;
    }

    /*
     * Check if the ball is hitting an x wall.
     */
    public static int hasYCollision(double bSize, double yMin, double yMax,
            double yNow) {
        // Check for collision with the four boundaries.
        int collision = NO_COLLISION;
        double yPos = yNow;

        // Check if there is a collision on x-boundaries.
        if (yPos <= yMin + bSize) {
            collision = YL_COLLISION;
        }
        if (yPos >= yMax - bSize) {
            collision = YR_COLLISION;
        }

        return collision;
    }

    /*
     * Generate a random position of the ball inside a box.
     */
    public static double[] randomPosition(double xMin, double xMax,
            double yMin, double yMax) {
        double[] position = new double[2];
        position[0] = rnd.nextDouble(xMin, xMax);
        position[1] = rnd.nextDouble(yMin, yMax);
        return position;
    }

    /*
     * Generate a random velocity of the ball inside a box.
     */
    public static double[] randomVelocity(double vMin, double vMax) {
        double[] velocity = new double[2];
        velocity[0] = rnd.nextDouble(vMin, vMax);
        velocity[1] = rnd.nextDouble(vMin, vMax);
        return velocity;
    }
}
