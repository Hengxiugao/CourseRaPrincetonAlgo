package hengxiu.courseraPA.w4;

import java.util.ArrayList;

public class Board {
	
	private int[][] blocks;
	private int hamScore = -1;
	private int manScore = -1;
	private boolean isGoal = false;
	private boolean isGoalCal = false;
	// construct a board from an n-by-n array of blocks
	// (where blocks[i][j] = block in row i, column j)
	public Board(int[][] blocks) {
		this.blocks = new int[blocks.length][blocks.length];
		for (int i = 0; i < dimension(); i++) {
			for (int j = 0 ; j < dimension(); j++) {
				this.blocks[i][j] = blocks[i][j];
			}
		}
	}
	
	// board dimension n
	public int dimension() {
		return blocks.length;
	}
	
	// number of blocks out of place
	public int hamming() {
		if (hamScore != -1) {
			return hamScore;
		}
		int result = 0;
		for (int i = 0; i < dimension(); i++) {
			for (int j = 0 ; j < dimension(); j++) {
				if (blocks[i][j] == 0) {
					continue;
				}
				if (blocks[i][j] != i * dimension() + j + 1) {
					result++;
				}
			}
		}
		hamScore = result;
		return result;
	}
	
	// sum of Manhattan distances between blocks and goal
	public int manhattan() {
		if (manScore != -1) {
			return manScore;
		}
		int result = 0;
		for (int i = 0; i < dimension(); i++) {
			for (int j = 0 ; j < dimension(); j++) {
				if (blocks[i][j] == 0) {
					continue;
				}
				int goalIndex = blocks[i][j] - 1;
				int targetCol = goalIndex % dimension();
				int targetRow = goalIndex / dimension();
				result += Math.abs(targetRow - i) + Math.abs(targetCol - j);
			}
		}
		manScore = result;
		return result;
	}
	
	// is this board the goal board?
	public boolean isGoal() {
		if (isGoalCal) {
			return isGoal;
		}
		for (int i = 0; i < dimension(); i++) {
			for (int j = 0 ; j < dimension(); j++) {
				if (blocks[i][j] != i * dimension() + j + 1) {
					if ((i == dimension() - 1) && (j == dimension() - 1)) {
						if (blocks[i][j] != 0) {
							return false;
						}
					} else {
						return false;
					}
					
				}
			}
		}
		isGoal = true;
		isGoalCal = true;
		return true;
	}
	
	// a board that is obtained by exchanging any pair of blocks
	public Board twin() {
		int[][] twinBlocks = new int[blocks.length][blocks.length];
		
		for (int i = 0; i < dimension(); i++) {
			for (int j = 0 ; j < dimension(); j++) {
				twinBlocks[i][j] = blocks[i][j];
			}
		}
		
		if (blocks[0][0] == 0 || blocks[0][1] == 0) {
			swap(twinBlocks, 1, 0, 1, 1);
		} else {
			swap(twinBlocks, 0, 0, 0, 1);
		}
		return new Board(twinBlocks);
	}
	
	private void swap(int[][] arr, int i1, int j1, int i2, int j2) {
		int temp = arr[i1][j1];
		arr[i1][j1] = arr[i2][j2];
		arr[i2][j2] = temp;
	}
	
	// does this board equal y?
	public boolean equals(Object y) {
		if (y == null) {
			return false;
		}
		if (y.getClass() != this.getClass()) {
			return false;
		}
		Board other = (Board)y;
		if (other.dimension() != this.dimension()) {
			return false;
		}
		for (int i = 0; i < this.dimension(); i++) {
			for (int j = 0; j < this.dimension(); j++) {
				if (other.blocks[i][j] != this.blocks[i][j]) {
					return false;
				}
			}
		}
		return true;
	}
	
	// all neighboring boards
	public Iterable<Board> neighbors() {
		int[][] neighborBlocks = new int[blocks.length][blocks.length];
		ArrayList<Board> boardList = new ArrayList<>();
		int zeroRow = 0;
		int zeroCol = 0;
		for (int i = 0; i < dimension(); i++) {
			for (int j = 0 ; j < dimension(); j++) {
				neighborBlocks[i][j] = blocks[i][j];
				if (blocks[i][j] == 0) {
					zeroRow = i;
					zeroCol = j;
				}
			}
		}
		
		if (zeroRow >= 1) {
			swap(neighborBlocks, zeroRow, zeroCol, zeroRow - 1, zeroCol);
			boardList.add(new Board(neighborBlocks));
			swap(neighborBlocks, zeroRow, zeroCol, zeroRow - 1, zeroCol);
		}
		if (zeroRow < dimension() - 1) {
			swap(neighborBlocks, zeroRow, zeroCol, zeroRow + 1, zeroCol);
			boardList.add(new Board(neighborBlocks));
			swap(neighborBlocks, zeroRow, zeroCol, zeroRow + 1, zeroCol);
		}
		if (zeroCol >= 1) {
			swap(neighborBlocks, zeroRow, zeroCol, zeroRow, zeroCol - 1);
			boardList.add(new Board(neighborBlocks));
			swap(neighborBlocks, zeroRow, zeroCol, zeroRow, zeroCol - 1);
		}
		if (zeroCol < dimension() - 1) {
			swap(neighborBlocks, zeroRow, zeroCol, zeroRow, zeroCol + 1);
			boardList.add(new Board(neighborBlocks));
			swap(neighborBlocks, zeroRow, zeroCol, zeroRow, zeroCol + 1);
		}
		
		return boardList;
	}
	
	// string representation of this board (in the output format specified below)
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(dimension()).append("\n");
		for (int i = 0; i < dimension(); i++) {
			for (int j = 0 ; j < dimension(); j++) {
				sb.append(blocks[i][j]).append(" ");
			}
			sb.append("\n");
		}
		return  sb.toString();
	}
	
	// unit tests (not graded)
	public static void main(String[] args) {
		Board testBoard = new Board(new int[][]{{0, 2, 4}, {5, 7, 1}, {8, 3, 6}});
		System.out.println("Board:\n"+testBoard);
		System.out.println("Twin Board:\n"+testBoard.twin());
		System.out.println("Neighbours:");
		for (Board b : testBoard.neighbors()) {
			System.out.println(b);
		}
		System.out.println("score: hamming = " + testBoard.hamming() + ", manhattan = " + testBoard.manhattan() + ", isGoal=" + testBoard.isGoal());
		for (Board b : testBoard.neighbors()) {
			System.out.println(b);
			System.out.println("score: hamming = " + b.hamming() + ", manhattan = " + b.manhattan() + ", isGoal=" + b.isGoal());
		}
		
	}
}
