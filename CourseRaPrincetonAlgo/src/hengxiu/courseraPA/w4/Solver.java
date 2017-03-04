package hengxiu.courseraPA.w4;

import edu.princeton.cs.algs4.MinPQ;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
public class Solver {
	
	private int moves = -1;
	private ArrayList<Board> solList = null;
	private boolean isSolvable = true;
	
	private class BoardNode {
		Board node;
		BoardNode parentNode;
		int move;
		List<BoardNode> subNode;
		BoardNode (Board node, BoardNode parentNode, int move) {
			this.node = node;
			this.parentNode = parentNode;
			this.move = move;
			subNode = new ArrayList<>();
		}
	}
	
	private void solve(MinPQ<BoardNode> pq, HashSet<String> visited) {
		BoardNode curNode = pq.delMin();
		//System.out.println(curBoard);
		visited.add(curNode.node.toString());
		for (Board nextBoard : curNode.node.neighbors()) {
			if (visited.contains(nextBoard.toString())) {
				continue;
			}
			BoardNode nextNode = new BoardNode(nextBoard, curNode, curNode.move + 1);
			curNode.subNode.add(nextNode);
			pq.insert(nextNode);
		}
	}
	
	// find a solution to the initial board (using the A* algorithm)
	public Solver(Board initial) {
		
		BoardNode curNode = null;
		MinPQ<BoardNode> pq = new MinPQ<BoardNode>(new MinPQComparator());
		MinPQ<BoardNode> pqTwin = new MinPQ<BoardNode>(new MinPQComparator());
		HashSet<String> visited= new HashSet<>();
		HashSet<String> visitedTwin= new HashSet<>();
		
		pq.insert(new BoardNode(initial, null, 0));
		pqTwin.insert(new BoardNode(initial.twin(), null, 0));
		visited.add(initial.toString());
		visitedTwin.add(initial.twin().toString());
		
		
		while (!pq.isEmpty() && !pqTwin.isEmpty()) {
			if (pq.min().node.isGoal()) {
				curNode = pq.min();
				break;
			}
			if (pqTwin.min().node.isGoal()) {
				isSolvable = false;
				break;
			}
			solve(pq, visited);
			solve(pqTwin, visitedTwin);
			
		}
		if (isSolvable()) {
			solList = new ArrayList<>();
			do  {
				solList.add(curNode.node);
				curNode = curNode.parentNode;
				moves++;
			} while (curNode != null);
			Collections.reverse(solList);
		}
	}
	
	// is the initial board solvable?
    public boolean isSolvable() {
    	return isSolvable;
    }
    
    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
    	return moves;
    }
    
    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
    	return solList;
    }
    
    private class MinPQComparator implements Comparator<BoardNode>{
		@Override
		public int compare(BoardNode o1, BoardNode o2) {
			int s1 = o1.node.hamming() + o1.node.manhattan() + o1.move;
			int s2 = o2.node.hamming() + o2.node.manhattan() + o2.move;
			return s1 - s2;
		}
	}
    
	// solve a slider puzzle (given below)
    public static void main(String[] args) {
    	Board initBoard = new Board(new int[][]{{2,  3,  4,  8}, {1,  6,  0, 12}, {5, 10,  7, 11}, {9, 13, 14, 15}});
    	
    	Solver s = new Solver(initBoard);
    	System.out.println("Moves=" + s.moves());
    	if (s.isSolvable()) {
    		for (Board b : s.solution()) {
        		System.out.println(b);
        	}
    	}
    	
    	
    }

}
