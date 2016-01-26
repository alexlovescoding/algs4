package percolation;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
  private Percolation perc;
  private int gridSize;
  private int percCount;
  private double[] openCounter;

  public PercolationStats(int size, int count) {
    gridSize = size;
    percCount = count;
    openCounter = new double[count];
  }

  public double mean() {
    return StdStats.mean(openCounter);
  }

  public double stddev() {
    return StdStats.stddev(openCounter);
  }

  public double confidenceLo() {
    return mean() - ((1.96 * stddev()) / Math.sqrt(openCounter.length));
  }

  public double confidenceHi() {
    return mean() + ((1.96 * stddev()) / Math.sqrt(openCounter.length));
  }

  private void run() {
    for (int i = 0; i < percCount; i++) {
      int count = 0;
      perc = new Percolation(gridSize);
      while (!perc.percolates()) {
        int row = StdRandom.uniform(1, gridSize + 1);
        int column = StdRandom.uniform(1, gridSize + 1);
        if (!perc.isOpen(row, column)) {
          perc.open(row, column);
          count++;
        }
      }
      openCounter[i] = (double) count / (gridSize * gridSize);
    }
  }

  public static void main(String[] args) {
    int size = Integer.parseInt(args[0]);
    int count = Integer.parseInt(args[1]);
    PercolationStats percStats = new PercolationStats(size, count);
    percStats.run();
    String format = "%-24s= %s\n";
    StdOut.printf(format, "mean", Double.toString(percStats.mean()));
    StdOut.printf(format, "stddev", Double.toString(percStats.stddev()));
    StdOut.printf(format, "95% confidence interval",
        Double.toString(percStats.confidenceLo()) + ", " 
        + Double.toString(percStats.confidenceHi()));
  }
}