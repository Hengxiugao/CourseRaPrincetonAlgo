package hengxiu.courseraPA.w4;

import edu.princeton.cs.algs4.MinPQ;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
public class Solver {
	
	private MinPQ<BoardNode> pq;
	private int moves = -1;
	private ArrayList<Board> solList;
	private HashSet<String> visited;
	
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
	
	// find a solution to the initial board (using the A* algorithm)
	public Solver(Board initial) {
		solList = new ArrayList<>();
		BoardNode curNode = new BoardNode(initial, null, 0);
		if (isSolvable()) {
			visited = new HashSet<>();
			pq = new MinPQ<BoardNode>(new MinPQComparator());
			pq.insert(curNode);
			visited.add(initial.toString());
			
			boolean finished = false;
			while (!pq.isEmpty() && !finished) {
				int size = pq.size();
				for (int i = 0; i < size; i++) {
					curNode = pq.delMin();
					//System.out.println(curBoard);
					if (curNode.node.isGoal()) {
						finished = true;
						break;
					}
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
				
			}
			do  {
				solList.add(curNode.node);
				curNode = curNode.parentNode;
				moves++;
			} while (curNode != null);
			Collections.reverse(solList);
			
		} else {
			moves = -1;
			solList = null;
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
    	Board initBoard = new Board(new int[][]{{1, 0, 2}, {7, 5, 4}, {8, 6, 3}});
    	
    	Solver s = new Solver(initBoard);
    	System.out.println("Moves=" + s.moves());
    	for (Board b : s.solution()) {
    		System.out.println(b);
    	}
    	
    }

}
