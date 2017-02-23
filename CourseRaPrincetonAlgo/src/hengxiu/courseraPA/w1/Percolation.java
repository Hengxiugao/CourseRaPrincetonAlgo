package hengxiu.courseraPA.w1;


import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

	private WeightedQuickUnionUF wuf;
	private boolean isOpen[];
	private int n = 0;
	private int openSiteCount = 0;
	private boolean isP = false;
	// create n-by-n grid, with all sites blocked
	public Percolation(int n){
		if (n <= 0) {
			throw new java.lang.IllegalArgumentException();
		}
		wuf = new WeightedQuickUnionUF(n * n + 2);
		isOpen = new boolean[n * n + 2];
		this.n = n;
	}

	// open site (row, col) if it is not open already
	public void open(int row, int col) {
		if (row < 1 || row > n || col < 1 || col > n) {
			throw new java.lang.IndexOutOfBoundsException();
		}
		row -= 1;
		col -= 1;
		int ori = row * n + col;
		if (isOpen[ori]) {
			return;
		}
		isOpen[ori] = true;
		if (row == 0 && !wuf.connected(ori, n * n)) {
			//System.out.println("ori = "+ ori + " , first= "+ n * n +" connected");
			wuf.union(ori, n * n);
		}
		if (row == n - 1 && !isP && !wuf.connected(ori, n * n + 1)) {
			//System.out.println("ori = "+ ori + " , first= "+ n * n +" connected");
			wuf.union(ori, n * n + 1);
		}
		openSiteCount++;

		openSite(ori, row, col - 1);
		openSite(ori, row, col + 1);
		openSite(ori, row - 1, col);
		openSite(ori, row + 1, col);
	}

	//Zero based index;
	private void openSite(int ori, int row, int col) {
		if (row < 0 || row > n - 1 || col < 0 || col > n - 1) {
			return;
		}
		int index = row * n + col;
		if (isOpen[index] && !wuf.connected(ori, index)) {
			//System.out.println("ori = "+ ori + " , index= "+ index +" connected");
			wuf.union(ori, index);
		}
	}

	// is site (row, col) open?
	public boolean isOpen(int row, int col) {
		if (row < 1 || row > n || col < 1 || col > n) {
			throw new java.lang.IndexOutOfBoundsException();
		}
		row -= 1;
		col -= 1;
		return isOpen[row * n + col];
	}

	// is site (row, col) full?
	public boolean isFull(int row, int col){
		if (row < 1 || row > n || col < 1 || col > n) {
			throw new java.lang.IndexOutOfBoundsException();
		}
		boolean isOpen = isOpen(row, col);
		row -= 1;
		col -= 1;
		int index = row * n + col;
		return isOpen && wuf.connected(index, n * n);
	}

	// number of open sites
	public int numberOfOpenSites() {
		return openSiteCount;
	}

	// does the system percolate?
	public boolean percolates() {
		if (isP) {
			return isP;
		}
		if (wuf.connected(n * n, n * n + 1)) {
			isP = true;
		}

		return openSiteCount > 0 && isP;
	}

	// test client (optional)
	public static void main(String[] args) {
		Percolation p = new Percolation(3);
		testOpen(p, 1, 3);
		testOpen(p, 2, 3);
		testOpen(p, 3, 3);
		testOpen(p, 3, 1);
		testOpen(p, 2, 1);
		testOpen(p, 1, 1);

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
