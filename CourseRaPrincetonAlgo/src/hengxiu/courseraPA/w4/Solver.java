package hengxiu.courseraPA.w4;

import edu.princeton.cs.algs4.MinPQ;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
public class Solver {
	
	private MinPQ<Board> pq;
	private int moves = 0;
	private ArrayList<Board> solList;
	// find a solution to the initial board (using the A* algorithm)
	public Solver(Board initial) {
		if (!isSolvable()) {
			solList = null;
		} else {
			pq = new MinPQ<Board>(new Comparator<Board>() {
				@Override
				public int compare(Board o1, Board o2) {
					int s1 = o1.hamming() + o1.manhattan();
					int s2 = o2.hamming() + o2.manhattan();
					return s1 - s2;
				}
			});
			solList = new ArrayList<>();
			
			pq.insert(initial);
			boolean finished = false;
			while (!pq.isEmpty() && !finished) {
				int size = pq.size();
				for (int i = 0; i < size; i++) {
					Board curBoard = pq.delMin();
					solList.add(curBoard);
					//System.out.println(curBoard);
					if (curBoard.isGoal()) {
						finished = true;
						break;
					}
					for (Board nextBoard : curBoard.neighbors()) {
						if (solList.contains(nextBoard)) {
							continue;
						}
						pq.insert(nextBoard);
					}
				}
				moves++;
			}
		}
		
	}
	
	// is the initial board solvable?
    public boolean isSolvable() {
    	return true;
    }
    
    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
    	return moves;
    }
    
    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
    	return new SolIterable();
    }
    
    private class SolIterable implements Iterable<Board>{

		@Override
		public Iterator<Board> iterator() {
			return solList.iterator();
		}
		
	}
    
	// solve a slider puzzle (given below)
    public static void main(String[] args) {
    	Board initBoard = new Board(new int[][]{{4, 1, 3}, {0, 2, 5}, {7, 8, 6}});
    	System.out.println(initBoard.twin());
    	/*
    	Solver s = new Solver(initBoard);
    	System.out.println("Moves=" + s.moves());
    	for (Board b : s.solution()) {
    		System.out.println(b);
    	}
    	*/
    }

}
