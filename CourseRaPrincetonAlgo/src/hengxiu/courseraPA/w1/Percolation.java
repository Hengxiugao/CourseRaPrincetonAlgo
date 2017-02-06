package hengxiu.courseraPA.w1;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	
	private WeightedQuickUnionUF wuf;
	private boolean isOpen[];
	private int n = 0;
	private int openSiteCount = 0;
	// create n-by-n grid, with all sites blocked
	public Percolation(int n){ 
		wuf = new WeightedQuickUnionUF(n * n + 2);
		isOpen = new boolean[n * n + 2];
		this.n = n;
		for (int i = 0; i < n; i++) {
			wuf.union(n * n, i);
			wuf.union(n * n + 1, (n - 1) * n + i);
		}
	}
	
	// open site (row, col) if it is not open already
	public void open(int row, int col) {
		row -= 1;
		col -= 1;
		int ori = row * n + col;
		if (isOpen[ori]) {
			return;
		}
		isOpen[ori] = true;
		openSiteCount++;
		int left = row * n + col - 1;
		int right = row * n + col + 1;
		int up = (row - 1) * n + col;
		int down = (row + 1) * n + col;
		
		openSite(ori, left);
		openSite(ori, right);
		openSite(ori, up);
		openSite(ori, down);
	}
	
	private void openSite(int ori, int index) {
		if (index < 0 || index > n * n - 1) {
			return;
		}
		if (isOpen[index]) {
			wuf.union(ori, index);
		}
	}
	
	// is site (row, col) open?
	public boolean isOpen(int row, int col) {
		row -= 1;
		col -= 1;
		return isOpen[row * n + col];
	}
	
	// is site (row, col) full?
	public boolean isFull(int row, int col){ 
		row -= 1;
		col -= 1;
		int index = row * n + col;
		return wuf.connected(index, n * n);
	}
	
	// number of open sites
	public int numberOfOpenSites() {
		return openSiteCount;
	}
	
	// does the system percolate?
	public boolean percolates() {
		return wuf.connected(n * n, n * n + 1);
	}
	
	// test client (optional)
	public static void main(String[] args) {
		Percolation p = new Percolation(3);
		testOpen(p, 1, 1);
		testOpen(p, 1, 2);
		testOpen(p, 2, 1);
		testOpen(p, 2, 3);
		testOpen(p, 3, 3);
		testOpen(p, 3, 2);
		//testOpen(p, 1, 1);
	}
	
	private static void testOpen(Percolation p, int row, int col){
		System.out.println("open " + row + ", " + col);
		p.open(row, col);
		System.out.println("isOpen = "+p.isOpen(row, col));
		System.out.println("isFull = " + p.isFull(row, col));
		System.out.println("percolates = " + p.percolates());
		System.out.println("open site count = " + p.numberOfOpenSites());
		System.out.println();
	}
}



