/* Program: selfAvoidingWalks
 * Description: In mathematics, a self-avoiding walk (SAW) is a sequence of 
 *              moves on a lattice (a lattice path) that does not visit the 
 *              same point more than once. This is a special case of the graph
 *              theoretical notion of a path. 
 */

public class selfAvoidingWalks {
    public static enum corners {
        None, TL, BL, BR, TR
    };

    public static enum edges {
        None, LL, BB, RR, TT
    };

    public static int[][] bulk_moves = { { 0, -1 }, { 0, 1 }, { -1, 0 }, { 1, 0 } };

    public static int[][] TL_corner_moves = { { 0, 1 }, { 1, 0 } };

    public static int[][] BL_corner_moves = { { 0, -1 }, { 1, 0 } };

    public static int[][] BR_corner_moves = { { 0, -1 }, { -1, 0 } };

    public static int[][] TR_corner_moves = { { 0, 1 }, { -1, 0 } };

    public static int[][] LL_edge_moves = { { 0, -1 }, { 0, 1 }, { 1, 0 } };

    public static int[][] BB_edge_moves = { { -1, 0 }, { 1, 0 }, { 0, -1 } };

    public static int[][] RR_edge_moves = { { 0, -1 }, { 0, 1 }, { -1, 0 } };

    public static int[][] TT_edge_moves = { { -1, 0 }, { 1, 0 }, { 0, 1 } };

    public static void main(String[] args) {

        int N = 10;
        if (args.length == 1) N = Integer.parseInt(args[0]);

        int[][] lattice = new int[N][N];
        int[][] direction = new int[N][N];
        int[][] path = new int [N*N+1][2];

        int[] old_pos = new int[2];
        old_pos[0] = (int) (N * Math.random());
        old_pos[1] = (int) (N * Math.random());

        System.out.println("Beg: i: " + old_pos[0] + "\t" + "j: " + old_pos[1]);

        direction[old_pos[0]][old_pos[1]] = 1;

        int[] del_pos = tryMoving(lattice, old_pos);

        int pathLength = 0;
        // Mark the initial position as visited.
        lattice[old_pos[0]][old_pos[1]] = 1;

        // Save the path.
        path[0][0] = old_pos[0];
        path[0][1] = old_pos[1];

        while (del_pos[0] != 0 || del_pos[1] != 0) {
            pathLength++;
            if (del_pos[0] == -1)
                direction[old_pos[0]][old_pos[1]] = 2;
            if (del_pos[0] == 1)
                direction[old_pos[0]][old_pos[1]] = 3;
            if (del_pos[1] == -1)
                direction[old_pos[0]][old_pos[1]] = 4;
            if (del_pos[1] == 1)
                direction[old_pos[0]][old_pos[1]] = 5;
            old_pos[0] += del_pos[0];
            old_pos[1] += del_pos[1];
            // Save the path.
            path[pathLength][0] = old_pos[0];
            path[pathLength][1] = old_pos[1];
            // Mark the visited position.
            lattice[old_pos[0]][old_pos[1]] = 1;
            printLattice(lattice, direction);
            del_pos = tryMoving(lattice, old_pos);
        }
        direction[old_pos[0]][old_pos[1]] = 6;
        printLattice(lattice, direction);
        System.out.println("Number of steps: " + pathLength);
        pathLength++;
        path[pathLength][0] = old_pos[0];
        path[pathLength][1] = old_pos[1];
        System.out.println("End: i: " + old_pos[0] + "\t" + "j: " + old_pos[1]);
        printPath(path, pathLength);
    }

    public static int[] tryMoving(int[][] lattice, int[] old_pos) {
        int[] del_pos = new int[2];
        corners wc = whichCorner(old_pos, lattice.length);
        edges we = whichEdge(old_pos, lattice.length);
        if (wc != corners.None) {
            int[][] moves = new int[2][2];
            switch (wc) {
                case corners.TL -> moves = TL_corner_moves;
                case corners.BL -> moves = BL_corner_moves;
                case corners.BR -> moves = BR_corner_moves;
                case corners.TR -> moves = TR_corner_moves;
            }

            // Set of valid moves.
            int[][] g_moves = new int[2][2];
            int nValid = 0;
            for (int[] move : moves) {
                if (isValid(lattice, old_pos, move)) {
                    g_moves[nValid][0] = move[0];
                    g_moves[nValid][1] = move[1];
                    nValid++;
                }

            }

            // No change.
            if (nValid == 0) {
                del_pos[0] = 0;
                del_pos[1] = 0;
            }

            // Case of equal probabilities.
            if (nValid >= 1) {
                int r = randomInteger(nValid);
                del_pos[0] = g_moves[r][0];
                del_pos[1] = g_moves[r][1];
            }

        } else if (we != edges.None) {
            int[][] moves = new int[3][2];
            switch (we) {
                case edges.LL -> moves = LL_edge_moves;
                case edges.BB -> moves = BB_edge_moves;
                case edges.RR -> moves = RR_edge_moves;
                case edges.TT -> moves = TT_edge_moves;
            }

            // Set of valid moves.
            int[][] g_moves = new int[3][2];
            int nValid = 0;
            for (int[] move : moves) {
                if (isValid(lattice, old_pos, move)) {
                    g_moves[nValid][0] = move[0];
                    g_moves[nValid][1] = move[1];
                    nValid++;
                }
            }

            // No change.
            if (nValid == 0) {
                del_pos[0] = 0;
                del_pos[1] = 0;
            }

            // Case of equal probabilities.
            if (nValid >= 1) {
                int r = randomInteger(nValid);
                del_pos[0] = g_moves[r][0];
                del_pos[1] = g_moves[r][1];
            }
        } else {
            int[][] moves = bulk_moves;
            int[][] g_moves = new int[4][2];
            int nValid = 0;
            for (int[] move : moves) {
                if (isValid(lattice, old_pos, move)) {
                    g_moves[nValid][0] = move[0];
                    g_moves[nValid][1] = move[1];
                    nValid++;
                }
            }

            // No change.
            if (nValid == 0) {
                del_pos[0] = 0;
                del_pos[1] = 0;
            }

            // Case of equal probabilities.
            if (nValid >= 1) {
                int r = randomInteger(nValid);
                del_pos[0] = g_moves[r][0];
                del_pos[1] = g_moves[r][1];
            }
        }
        return del_pos;
    }

    public static corners whichCorner(int[] pos, int N) {
        if (pos[0] == 0 && pos[1] == 0)
            return corners.TL;
        if (pos[0] == 0 && pos[1] == N - 1)
            return corners.BL;
        if (pos[0] == N - 1 && pos[1] == N - 1)
            return corners.BR;
        if (pos[0] == N - 1 && pos[1] == 0)
            return corners.TR;
        return corners.None;
    }

    public static edges whichEdge(int[] pos, int N) {
        if (pos[0] == 0 && (pos[1] > 0 && pos[1] < N - 1))
            return edges.LL;
        if ((pos[0] > 0 && pos[0] < N - 1) && pos[1] == N - 1)
            return edges.BB;
        if (pos[0] == N - 1 && (pos[1] > 0 && pos[1] < N - 1))
            return edges.RR;
        if ((pos[0] > 0 && pos[0] < N - 1) && pos[1] == 0)
            return edges.TT;
        return edges.None;
    }

    public static boolean isValid(int[][] lattice, int[] cur_pos, int[] move) {
        // Check if the new position is already visited.
        return lattice[cur_pos[0] + move[0]][cur_pos[1] + move[1]] != 1;
    }

    public static void printLattice(int[][] lattice, int[][] dir) {
        for (int i = 0; i < lattice.length; i++) {
            for (int j = 0; j < lattice[i].length; j++) {
                if (lattice[i][j] != 1) {
                    System.err.printf("%s ", "*");
                } else if (lattice[i][j] == 1 && dir[i][j] == 0) {
                    System.err.printf("%s ", "x");
                } else {
                    switch (dir[i][j]) {
                        case 1 -> System.err.printf("%s ", "S");
                        case 2 -> System.err.printf("%s ", "U");
                        case 3 -> System.err.printf("%s ", "D");
                        case 4 -> System.err.printf("%s ", "L");
                        case 5 -> System.err.printf("%s ", "R");
                        case 6 -> System.err.printf("%s ", "E");
                    }
                }
            }
            System.err.println("");
        }
        System.err.println("");
    }

    public static void printPath(int [][] path, int n) {
        System.err.println("Trajectory:");
        for (int i = 0; i < n; i++) {
            System.err.printf("%2d\t%2d\n", path[i][0], path[i][1]);
        }
    }
    public static int randomInteger(int N) {
        return (int) (N * Math.random());
    }
}
