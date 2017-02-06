package hengxiu.courseraPA.w1;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
public class PercolationStats {
	// perform trials independent experiments on an n-by-n grid
	private Percolation p;
	private double mean = 0;
	private double stddev = 0;
	private double confidenceLo = 0;
	private double confidenceHi = 0;
	private double meanArr[];
	public PercolationStats(int n, int trials) {
		meanArr = new double[trials];
		for (int i = 0; i < trials; i++) {
			p = new Percolation(n);
			int[] position = new int[n * n];
			for (int k = 0; k < position.length; k++) {
				position[k] = k;
			}
			shuffle(position);
			int index = 0;
			while (!p.percolates()) {
				int row = position[index] / n;
				int col = position[index] % n;
				index++;
				//System.out.println(row + ", " + col);
				p.open(row, col);
			}
			double ratio = (double)(p.numberOfOpenSites()) / (double)(n * n);
			//System.out.println("i = " + i + ", ratio="+ratio);
			meanArr[i] = ratio;
		}
		mean = StdStats.mean(meanArr);
		stddev = StdStats.stddev(meanArr);
		double con = 1.96 * stddev / (double)n;
		confidenceLo = mean - con;
		confidenceHi = mean + con;
	}
	
	private void shuffle(int[] position) {
		for (int i = 0; i < position.length; i++) {
			int j = StdRandom.uniform(position.length);
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
		PercolationStats p = new PercolationStats(100, 2000);
		System.out.println("mean="+p.mean);
	}
}
	
	
	
	
	