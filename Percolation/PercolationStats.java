import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private final double[] x;
    private final int experiments;
    //private int numSites;

    // perform T independent computational experiments on an N-by-N grid
    public PercolationStats(int N, int T) {
        experiments = T;
        int numSites = N * N;
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException("Given N <= 0 || T <= 0");
        }

        x = new double[experiments];
        for (int t = 0; t < experiments; t++) {
            Percolation pc = new Percolation(N);
            int[] perm = StdRandom.permutation(numSites);
            int ptr = 0;
            while (!pc.percolates()) {
                int index = perm[ptr];
                int row = index / N + 1;
                int col = index % N + 1;
                // Open a new site
                pc.open(row, col);
                ++ptr;
            }
            x[t] = (double) pc.numberOfOpenSites() / numSites;
        }


    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(x);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(x);
    }

    // returns lower bound of the 95% confidence interval
    public double confidenceLo() {
        return mean() - ((1.96 * stddev()) / Math.sqrt(experiments));
    }

    // returns upper bound of the 95% confidence interval
    public double confidenceHi() {
        return mean() + ((1.96 * stddev()) / Math.sqrt(experiments));
    }

    public static void main(String[] args) {
        PercolationStats ps = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));

        //String confidence = ps.confidenceLo() + ", " + ps.confidenceHi();
        //StdOut.println("mean                    = " + ps.mean());
        //StdOut.println("stddev                  = " + ps.stddev());
        //StdOut.println("95% confidence interval = " + confidence);
        System.out.format("mean                     = %f\n", ps.mean());
        System.out.format("stddev                   = %f\n", ps.stddev());
        System.out.format("95%% confidence interval  = [%f, %f]\n", ps.confidenceLo(), ps.confidenceHi());
    }
}