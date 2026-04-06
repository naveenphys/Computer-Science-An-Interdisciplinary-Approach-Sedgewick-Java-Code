/* Program: plottingFunction
 * Description: Demostrate the creation of a simple plot.
 */
public class plottingFunction {
    public static void main(String[] args) {

        if (args.length != 3)
        {
            System.out.println("Provide the following value on command line");
            System.out.println("xmin: Start of the plot");
            System.out.println("xmax: End of the plot");
            System.out.println("nsamples: Number of samples on [xmin,xmax]");
            return;
        }

        int N = Integer.parseInt(args[0]);

        double a = Double.parseDouble(args[1]);
        double b = Double.parseDouble(args[2]);
        assert (b > a);

        double[] x = new double[N];
        double[] y = new double[N];

        double dx = (b - a) / (double) N;

        for (int i = 0; i < N; i++) {
            x[i] = a + i * dx;
            y[i] = Math.cos(Math.PI * x[i]);
        }

        System.out.println("x\ty");
        for (int i = 0; i < N; i++) {
            System.out.println(x[i]+ "\t"+ y[i]);
        }
    }
}
