package hengxiu.courseraPA.w1;


import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
public class PercolationStats {
	// perform trials independent experiments on an n-by-n grid
	private double mean = 0;
	private double stddev = 0;
	private double confidenceLo = 0;
	private double confidenceHi = 0;
	private double meanArr[];
	public PercolationStats(int n, int trials) {
		if (n <= 0 || trials <= 0) {
			throw new java.lang.IllegalArgumentException();
		}
		meanArr = new double[trials];
		int[] position = new int[n * n];
		for (int k = 0; k < position.length; k++) {
			position[k] = k;
		}
		for (int i = 0; i < trials; i++) {
			Percolation p = new Percolation(n);

			shuffle(position);
			int index = 0;
			while (!p.percolates()) {
				int row = position[index] / n;
				int col = position[index] % n;
				index++;
				//System.out.println(row + ", " + col);
				p.open(row + 1, col + 1);
			}
			double ratio = (double)(p.numberOfOpenSites()) / (double)(n * n);
			//System.out.println("i = " + i + ", ratio="+ratio);
			meanArr[i] = ratio;
		}
		mean = StdStats.mean(meanArr);
		stddev = StdStats.stddev(meanArr);
		double con = 1.96 * stddev / Math.sqrt(trials);
		confidenceLo = mean - con;
		confidenceHi = mean + con;
	}

	private void shuffle(int[] position) {
		for (int i = 0; i < position.length; i++) {
			int j = i + StdRandom.uniform(position.length - i);
			swap(position, i, j);
		}
	}

	private void swap(int[] position, int p, int q) {
		int temp = position[p];
		position[p] = position[q];
		position[q] = temp;
	}

	// sample mean of percolation threshold
	public double mean() {
		return mean;
	}

	// sample standard deviation of percolation threshold
	public double stddev() {
		return stddev;
	}

	// low  endpoint of 95% confidence interval
	public double confidenceLo() {

		return confidenceLo;
	}
	// high endpoint of 95% confidence interval
	public double confidenceHi() {
		return confidenceHi;
	}
	// test client (described below)
	public static void main(String[] args) {
		PercolationStats p = new PercolationStats(5, 1);
		System.out.println("mean="+p.mean);
	}
}
