/*
 * Program: selfAvoidingWalks
 * Description: In mathematics, a self-avoiding walk (SAW) is a sequence of 
 *              moves on a lattice (a lattice path) that does not visit the 
 *              same point more than once. This is a special case of the graph
 *              theoretical notion of a path. Other examples of random walk
 *              include "random walk", and "non-reversing random walk".
 * https://www.americanscientist.org/article/how-to-avoid-yourself
 * 
 * # Compilation.
 * gcc -o selfAvoidingWalks selfAvoidingWalks.c
 * # Run with default 10x10 lattice and new random seed
 * ./selfAvoidingWalks
 * 
 * # Run with 20x20 lattice and new random seed
 * ./selfAvoidingWalks 20
 * 
 * # Run with saved seed (reproduce previous simulation)
 * ./selfAvoidingWalks --load-seed
 * 
 * # Run with saved seed and custom lattice size
 * ./selfAvoidingWalks --load-seed 15
 * 
 * # Display help
 * ./selfAvoidingWalks --help
 * “The sum of human wisdom is not contained in any one language, and no 
 *  single language is capable of expressing all forms and degrees of human 
 *  comprehension" -Ezra Pound.
 */

#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <string.h>

typedef enum {
    CORNER_NONE, CORNER_TL, CORNER_BL, CORNER_BR, CORNER_TR
} corners;

typedef enum {
    EDGE_NONE, EDGE_LL, EDGE_BB, EDGE_RR, EDGE_TT
} edges;

// Move definitions
const int bulk_moves[4][2] = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
const int TL_corner_moves[2][2] = {{0, 1}, {1, 0}};
const int BL_corner_moves[2][2] = {{0, -1}, {1, 0}};
const int BR_corner_moves[2][2] = {{0, -1}, {-1, 0}};
const int TR_corner_moves[2][2] = {{0, 1}, {-1, 0}};
const int LL_edge_moves[3][2] = {{0, -1}, {0, 1}, {1, 0}};
const int BB_edge_moves[3][2] = {{-1, 0}, {1, 0}, {0, -1}};
const int RR_edge_moves[3][2] = {{0, -1}, {0, 1}, {-1, 0}};
const int TT_edge_moves[3][2] = {{-1, 0}, {1, 0}, {0, 1}};

// Function prototypes
corners whichCorner(int pos[], int N);
edges whichEdge(int pos[], int N);
int isValid(int **lattice, int cur_pos[], int move[], int N);
int* tryMoving(int **lattice, int old_pos[], int N);
void printLattice(int **lattice, int **dir, int N);
void printPathToFile(int **path, int n, const char *filename);
void saveLatticeToFile(int **lattice, int **dir, int N, const char *filename);
void createGnuplotScript(int **path, int n, int N, unsigned int seed, const char *filename);
int randomInteger(int N);
void saveSeedToFile(unsigned int seed, const char *filename);
int loadSeedFromFile(unsigned int *seed, const char *filename);
void printUsage(const char *program_name);

corners whichCorner(int pos[], int N) {
    if (pos[0] == 0 && pos[1] == 0)
        return CORNER_TL;
    if (pos[0] == 0 && pos[1] == N - 1)
        return CORNER_BL;
    if (pos[0] == N - 1 && pos[1] == N - 1)
        return CORNER_BR;
    if (pos[0] == N - 1 && pos[1] == 0)
        return CORNER_TR;
    return CORNER_NONE;
}

edges whichEdge(int pos[], int N) {
    if (pos[0] == 0 && (pos[1] > 0 && pos[1] < N - 1))
        return EDGE_LL;
    if ((pos[0] > 0 && pos[0] < N - 1) && pos[1] == N - 1)
        return EDGE_BB;
    if (pos[0] == N - 1 && (pos[1] > 0 && pos[1] < N - 1))
        return EDGE_RR;
    if ((pos[0] > 0 && pos[0] < N - 1) && pos[1] == 0)
        return EDGE_TT;
    return EDGE_NONE;
}

int isValid(int **lattice, int cur_pos[], int move[], int N) {
    int new_x = cur_pos[0] + move[0];
    int new_y = cur_pos[1] + move[1];
    
    // Check bounds
    if (new_x < 0 || new_x >= N || new_y < 0 || new_y >= N)
        return 0;
    
    // Check if the new position is already visited
    return lattice[new_x][new_y] != 1;
}

int* tryMoving(int **lattice, int old_pos[], int N) {
    int *del_pos = (int*)malloc(2 * sizeof(int));
    corners wc = whichCorner(old_pos, N);
    edges we = whichEdge(old_pos, N);
    
    if (wc != CORNER_NONE) {
        const int (*moves)[2] = NULL;
        int move_count = 2;
        
        switch (wc) {
            case CORNER_TL: moves = TL_corner_moves; break;
            case CORNER_BL: moves = BL_corner_moves; break;
            case CORNER_BR: moves = BR_corner_moves; break;
            case CORNER_TR: moves = TR_corner_moves; break;
            default: break;
        }
        
        // Set of valid moves
        int g_moves[2][2];
        int nValid = 0;
        for (int i = 0; i < move_count; i++) {
            const int *move = moves[i];
            if (isValid(lattice, old_pos, (int*)move, N)) {
                g_moves[nValid][0] = move[0];
                g_moves[nValid][1] = move[1];
                nValid++;
            }
        }
        
        // No change
        if (nValid == 0) {
            del_pos[0] = 0;
            del_pos[1] = 0;
        }
        
        // Case of equal probabilities
        if (nValid >= 1) {
            int r = randomInteger(nValid);
            del_pos[0] = g_moves[r][0];
            del_pos[1] = g_moves[r][1];
        }
    } 
    else if (we != EDGE_NONE) {
        const int (*moves)[2] = NULL;
        int move_count = 3;
        
        switch (we) {
            case EDGE_LL: moves = LL_edge_moves; break;
            case EDGE_BB: moves = BB_edge_moves; break;
            case EDGE_RR: moves = RR_edge_moves; break;
            case EDGE_TT: moves = TT_edge_moves; break;
            default: break;
        }
        
        // Set of valid moves
        int g_moves[3][2];
        int nValid = 0;
        for (int i = 0; i < move_count; i++) {
            const int *move = moves[i];
            if (isValid(lattice, old_pos, (int*)move, N)) {
                g_moves[nValid][0] = move[0];
                g_moves[nValid][1] = move[1];
                nValid++;
            }
        }
        
        // No change
        if (nValid == 0) {
            del_pos[0] = 0;
            del_pos[1] = 0;
        }
        
        // Case of equal probabilities
        if (nValid >= 1) {
            int r = randomInteger(nValid);
            del_pos[0] = g_moves[r][0];
            del_pos[1] = g_moves[r][1];
        }
    } 
    else {
        int move_count = 4;
        int g_moves[4][2];
        int nValid = 0;
        for (int i = 0; i < move_count; i++) {
            const int *move = bulk_moves[i];
            if (isValid(lattice, old_pos, (int*)move, N)) {
                g_moves[nValid][0] = move[0];
                g_moves[nValid][1] = move[1];
                nValid++;
            }
        }
        
        // No change
        if (nValid == 0) {
            del_pos[0] = 0;
            del_pos[1] = 0;
        }
        
        // Case of equal probabilities
        if (nValid >= 1) {
            int r = randomInteger(nValid);
            del_pos[0] = g_moves[r][0];
            del_pos[1] = g_moves[r][1];
        }
    }
    
    return del_pos;
}

void printLattice(int **lattice, int **dir, int N) {
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            if (lattice[i][j] != 1) {
                printf("%s ", "*");
            } 
            else if (lattice[i][j] == 1 && dir[i][j] == 0) {
                printf("%s ", "x");
            } 
            else {
                switch (dir[i][j]) {
                    /*
                    case 1: printf("%s ", "S"); break;
                    case 2: printf("%s ", "U"); break;
                    case 3: printf("%s ", "D"); break;
                    case 4: printf("%s ", "L"); break;
                    case 5: printf("%s ", "R"); break;
                    case 6: printf("%s ", "E"); break;
                    */

                    /*
                    http://xahlee.info/comp/unicode_arrows.html
                    */
                    case 1: printf("%s ", "\u25CE"); break;
                    case 2: printf("%s ", "\u2B06"); break;
                    case 3: printf("%s ", "\u2B07"); break;
                    case 4: printf("%s ", "\u2B05"); break;
                    case 5: printf("%s ", "\u27A1"); break;
                    case 6: printf("\x1b[31m%s \x1b[0m", "\u25A0"); break;

                    default: printf("%d ", dir[i][j]);
                }
            }
        }
        printf("\n");
    }
    printf("\n");
}

void saveLatticeToFile(int **lattice, int **dir, int N, const char *filename) {
    FILE *file = fopen(filename, "w");
    if (file == NULL) {
        printf("Error opening file %s for writing\n", filename);
        return;
    }
    
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            if (lattice[i][j] != 1) {
                fprintf(file, "* ");
            } 
            else if (lattice[i][j] == 1 && dir[i][j] == 0) {
                fprintf(file, "x ");
            } 
            else {
                switch (dir[i][j]) {
                    case 1: fprintf(file, "S "); break;
                    case 2: fprintf(file, "U "); break;
                    case 3: fprintf(file, "D "); break;
                    case 4: fprintf(file, "L "); break;
                    case 5: fprintf(file, "R "); break;
                    case 6: fprintf(file, "E "); break;
                    default: fprintf(file, "%d ", dir[i][j]);
                }
            }
        }
        fprintf(file, "\n");
    }
    
    fclose(file);
}

void printPathToFile(int **path, int n, const char *filename) {
    FILE *file = fopen(filename, "w");
    if (file == NULL) {
        printf("Error opening file %s for writing\n", filename);
        return;
    }
    
    fprintf(file, "# x y\n");
    for (int i = 0; i < n; i++) {
        fprintf(file, "%d %d\n", path[i][1], path[i][0]);
    }
    
    fclose(file);
}

void createGnuplotScript(int **path, int n, int N, unsigned int seed, const char *filename) {
    FILE *script = fopen(filename, "w");
    if (script == NULL) {
        printf("Error opening file %s for writing\n", filename);
        return;
    }
    
    fprintf(script, "# Gnuplot script for self-avoiding walk trajectory\n");
    fprintf(script, "# Generated by selfAvoidingWalks program\n");
    fprintf(script, "# Usage: gnuplot -persist %s\n\n", filename);
    
    // Set UTF-8 encoding to handle special characters properly
    fprintf(script, "#set encoding utf8\n");
    fprintf(script, "set terminal x11 size 600,600 enhanced font 'Arial,12'\n");
    fprintf(script, "set title \"Self-Avoiding Walk Trajectory (N=%d, Steps=%d)\" font \",16\"\n", N, n-1);
    fprintf(script, "set xlabel \"X Coordinate\" font \",12\"\n");
    fprintf(script, "set ylabel \"Y Coordinate\" font \",12\"\n");
    fprintf(script, "set grid\n");
    fprintf(script, "set size square\n");
    fprintf(script, "set xrange [0:%d]\n", N);
    fprintf(script, "set yrange [0:%d]\n", N);
    fprintf(script, "set xtics 1\n");
    fprintf(script, "set ytics 1\n");
    
    // Draw lattice grid
    fprintf(script, "\n# Draw the lattice grid\n");
    fprintf(script, "set arrow from 0,0 to %d,0 nohead lc rgb \"gray\" lw 0.5\n", N-1);
    fprintf(script, "set arrow from 0,0 to 0,%d nohead lc rgb \"gray\" lw 0.5\n", N-1);
    fprintf(script, "set for [i=0:%d] arrow from i,0 to i,%d nohead lc rgb \"light-gray\" lw 0.5\n", N, N-1);
    fprintf(script, "set for [i=0:%d] arrow from 0,i to %d,i nohead lc rgb \"light-gray\" lw 0.5\n", N, N-1);
 
   // Plot the path
    fprintf(script, "\n# Plot the self-avoiding walk\n");
    fprintf(script, "plot \"path_output.txt\" using 1:2 with linespoints \\\n");
    fprintf(script, "     lt 1 lc rgb \"blue\" lw 2 pt 7 ps 1.5 title \"Walk Path\", \\\n");
    fprintf(script, "     \"path_output.txt\" using 1:2 every ::0::0 with points \\\n");
    fprintf(script, "     lt 1 lc rgb \"green\" pt 9 ps 2 title \"Start\", \\\n");
    fprintf(script, "     \"path_output.txt\" using 1:2 every ::%d::%d with points \\\n", n-1, n-1);
    fprintf(script, "     lt 1 lc rgb \"red\" pt 7 ps 2 title \"End\"\n");

    // Add statistics text box
    char label_text[256];
    double density = (double)(n) / (double)(N*N) * 100.0;
    // Add labels - format strings in C, not in gnuplot
    fprintf(script, "\n# Add statistics labels\n");
    
    snprintf(label_text, sizeof(label_text), "Total Steps: %d", n-1);
    fprintf(script, "set label 1 at screen 0.2, 0.90 \"%s\" front textcolor rgb \"black\"\n", label_text);
    
    snprintf(label_text, sizeof(label_text), "Lattice Size: %dx%d", N, N);
    fprintf(script, "set label 2 at screen 0.2, 0.88 \"%s\" front textcolor rgb \"black\"\n", label_text);
    
    snprintf(label_text, sizeof(label_text), "Random Seed: %u", seed);
    fprintf(script, "set label 3 at screen 0.2, 0.86 \"%s\" front textcolor rgb \"black\"\n", label_text);
    
    snprintf(label_text, sizeof(label_text), "Coverage: %.1f%%", density);
    fprintf(script, "set label 4 at screen 0.2, 0.84 \"%s\" front textcolor rgb \"black\"\n", label_text);
    
    snprintf(label_text, sizeof(label_text), "Start: (%d,%d)", path[0][1], path[0][0]);
    fprintf(script, "set label 5 at screen 0.2, 0.82 \"%s\" front textcolor rgb \"green\"\n", label_text);
    
    snprintf(label_text, sizeof(label_text), "End: (%d,%d)", path[n-1][1], path[n-1][0]);
    fprintf(script, "set label 6 at screen 0.2, 0.80 \"%s\" front textcolor rgb \"red\"\n", label_text);
    
    fprintf(script, "\nreplot\n");
    
    fprintf(script, "\n# Uncomment to save as SVG\n");
    fprintf(script, "#set terminal png size 800,800 enhanced font 'Arial,12'\n");
    fprintf(script, "#set output \"saw_trajectory.png\"\n");
    fprintf(script, "#replot\n");
    
    fclose(script);
    printf("Gnuplot script created: %s\n", filename);
}

int randomInteger(int N) {
    if (N <= 0) return 0;
    return rand() % N;
}

void saveSeedToFile(unsigned int seed, const char *filename) {
    FILE *file = fopen(filename, "w");
    if (file == NULL) {
        printf("Warning: Could not save seed to %s\n", filename);
        return;
    }
    
    fprintf(file, "# Random seed for self-avoiding walk simulation\n");
    fprintf(file, "# Format: seed\n");
    fprintf(file, "%u\n", seed);
    fprintf(file, "# Timestamp: %ld\n", time(NULL));
    
    fclose(file);
    printf("Seed saved to %s (seed = %u)\n", filename, seed);
}

int loadSeedFromFile(unsigned int *seed, const char *filename) {
    FILE *file = fopen(filename, "r");
    if (file == NULL) {
        return 0;  // File doesn't exist
    }
    
    char line[256];
    int found = 0;
    
    while (fgets(line, sizeof(line), file)) {
        // Skip comments
        if (line[0] == '#') continue;
        
        // Try to parse seed
        if (sscanf(line, "%u", seed) == 1) {
            found = 1;
            break;
        }
    }
    
    fclose(file);
    
    if (found) {
        printf("Loaded seed %u from %s\n", *seed, filename);
        return 1;
    }
    
    return 0;
}

void printUsage(const char *program_name) {
    printf("Usage: %s [OPTIONS] [N]\n", program_name);
    printf("\nOptions:\n");
    printf("  --load-seed    Load random seed from seed.txt file\n");
    printf("  --help         Display this help message\n");
    printf("\nArguments:\n");
    printf("  N              Lattice size (default: 10)\n");
    printf("\nExamples:\n");
    printf("  %s 20                 Run with 20x20 lattice, random seed\n", program_name);
    printf("  %s --load-seed 15     Run with 15x15 lattice, load previous seed\n", program_name);
    printf("  %s --load-seed        Run with default 10x10 lattice, load previous seed\n", program_name);
    printf("  To generate multiple runs:\n");
    printf("  \t for i in {1..10..1}; do %s 20; sleep 1; done", program_name);
    printf("\nNote: The seed is automatically saved to seed.txt after each run.\n");
}

int main(int argc, char *argv[]) {
    int N = 10;
    int load_seed = 0;
    unsigned int seed = 0;
    
    // Parse command line arguments
    for (int i = 1; i < argc; i++) {
        if (strcmp(argv[i], "--help") == 0) {
            printUsage(argv[0]);
            return 0;
        }
        else if (strcmp(argv[i], "--load-seed") == 0) {
            load_seed = 1;
        }
        else {
            // Try to parse as N (lattice size)
            int val = atoi(argv[i]);
            if (val > 0) {
                N = val;
            }
        }
    }
    
    // Seed management
    const char *seed_file = "seed.txt";
    
    if (load_seed) {
        // Try to load seed from file
        if (!loadSeedFromFile(&seed, seed_file)) {
            printf("No seed file found. Generating new random seed...\n");
            seed = time(NULL);
        }
    } else {
        // Generate new random seed based on current time
        seed = time(NULL);
    }
    
    // Initialize random number generator with the seed
    srand(seed);
    printf("Using random seed: %u\n", seed);
    
    // Allocate 2D arrays
    int **lattice = (int**)malloc(N * sizeof(int*));
    int **direction = (int**)malloc(N * sizeof(int*));
    for (int i = 0; i < N; i++) {
        lattice[i] = (int*)calloc(N, sizeof(int));
        direction[i] = (int*)calloc(N, sizeof(int));
    }
    
    int **path = (int**)malloc((N*N+1) * sizeof(int*));
    for (int i = 0; i < N*N+1; i++) {
        path[i] = (int*)malloc(2 * sizeof(int));
    }
    
    int old_pos[2];
    //old_pos[0] = rand() % N;
    //old_pos[1] = rand() % N;
    old_pos[0] = N/2;
    old_pos[1] = N/2;
    
    printf("Beg: i: %d\tj: %d\n", old_pos[0], old_pos[1]);
    
    direction[old_pos[0]][old_pos[1]] = 1;
    
    int *del_pos = tryMoving(lattice, old_pos, N);
    
    int pathLength = 0;
    // Mark the initial position as visited
    lattice[old_pos[0]][old_pos[1]] = 1;
    
    // Save the path
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
        
        // Save the path
        path[pathLength][0] = old_pos[0];
        path[pathLength][1] = old_pos[1];
        
        // Mark the visited position
        lattice[old_pos[0]][old_pos[1]] = 1;
        // printLattice(lattice, direction, N);
        
        free(del_pos);
        del_pos = tryMoving(lattice, old_pos, N);
    }
    
    free(del_pos);  // Free the last allocation
    
    direction[old_pos[0]][old_pos[1]] = 6;
    printLattice(lattice, direction, N);
    printf("Number of steps: %d\n", pathLength);
    
    pathLength++;
    path[pathLength][0] = old_pos[0];
    path[pathLength][1] = old_pos[1];
    printf("End: i: %d\tj: %d\n", old_pos[0], old_pos[1]);
    
    // Save outputs to ASCII files
    printPathToFile(path, pathLength, "path_output.txt");
    saveLatticeToFile(lattice, direction, N, "lattice_output.txt");
    createGnuplotScript(path, pathLength, N, seed, "plot_trajectory.gnu");
    
    // Save the seed for future runs
    saveSeedToFile(seed, seed_file);
    
    printf("\nResults saved to:\n");
    printf("  - Path: path_output.txt\n");
    printf("  - Lattice: lattice_output.txt\n");
    printf("  - Gnuplot script: plot_trajectory.gnu\n");
    printf("  - Random seed: %s\n", seed_file);
    printf("\nTo generate the plot, run:\n");
    printf("  gnuplot -persist plot_trajectory.gnu\n");
    printf("\nTo repeat this exact simulation, run:\n");
    printf("  %s --load-seed %d\n", argv[0], N);
    
    // Free allocated memory
    for (int i = 0; i < N; i++) {
        free(lattice[i]);
        free(direction[i]);
    }
    free(lattice);
    free(direction);
    
    for (int i = 0; i < N*N+1; i++) {
        free(path[i]);
    }
    free(path);

    // Run the gnuplot script.
    system("gnuplot -persist plot_trajectory.gnu");
    return 0;
}