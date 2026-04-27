/* Program: boltzmannDistribution
 * Description: Program to generate samples from a Boltzmann distribution.
 */
public class boltzmannDistribution {
    public static void main(String[] args) {
        int nSamples = 1000;
        int sampleSize = 10;
        int min = 1;
        int max = 10;
        int sum = 500;

        int[][] samples;

        samples = generateSamples(nSamples, sampleSize, min, max, sum);

        int[] hist = new int[max - min + 1];
        for (int i = 0; i < nSamples; i++) {
            for (int j = 0; j < sampleSize; j++) {
                hist[samples[i][j]]++;
            }
        }

        for (int h : hist) {
            System.out.println(h);
        }

    }

    /*
     * Generate k sets of n random number between min and max whose sum
     * matches sum.
     */
    public static int[][] generateSamples(int nSamples, int sampleSize, int min, int max, int sum) {
        int[][] samples = new int[nSamples][sampleSize];

        int count = 0;
        while (count < nSamples) {
            int[] sample = generateOneSample(sampleSize, min, max, sum);
            System.arraycopy(sample, 0, samples[count], 0, sampleSize);
            count++;
        }
        return samples;
    }

    /*
     * Generate a set of n random number between min and max whose sum matches
     * s.
     */
    public static int[] generateOneSample(int n, int min, int max, int sum) {
        int[] samples = randVec(n, min, max);
        while (vecSum(samples) != sum) {
            samples = randVec(n, min, max);
        }
        return samples;
    }

    /*
     * Generate n random numbers in the interval [min, max).
     */
    public static int[] randVec(int n, int min, int max) {
        int[] rand = new int[n];
        int range = max - min;
        for (int i = 0; i < n; i++) {
            rand[i] = min + (int) (Math.random() * range);
        }
        return rand;
    }

    /*
     * Calculate the sum of all elements of an array.
     */
    public static int vecSum(int[] vec) {
        int s = 0;
        for (int v : vec)
            s += v;
        return s;
    }

    /*
     * Print a vector such that each row has length N
     */
    public static void printVector(int[] vec, int N) {
        for (int i = 0; i < vec.length; i++) {
            if (i != 0 && i % N == 0) {
                System.out.println("");
                System.out.printf("%3d ", vec[i]);
            } else {
                System.out.printf("%3d ", vec[i]);
            }
        }
        System.out.println();
    }
}
