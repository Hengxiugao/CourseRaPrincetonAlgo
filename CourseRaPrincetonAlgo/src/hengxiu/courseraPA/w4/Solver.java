package hengxiu.courseraPA.w4;


import edu.princeton.cs.algs4.MinPQ;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
public class Solver {

	private int moves = -1;
	private ArrayList<Board> solList = null;
	private boolean isSolvable = true;
	private int count = 0;
	private class BoardNode {
		Board node;
		BoardNode parentNode;
		int move;
		int priority = -1;
		List<BoardNode> subNode;
		BoardNode (Board node, BoardNode parentNode, int move) {
			this.node = node;
			this.parentNode = parentNode;
			this.move = move;
			priority = node.hamming() + (node.manhattan() + move) * 10;
			subNode = new ArrayList<>();
		}
	}

	private void solve(MinPQ<BoardNode> pq) {
		BoardNode curNode = pq.delMin();
		//System.out.println(curBoard);
		for (Board nextBoard : curNode.node.neighbors()) {
			if (curNode.parentNode != null && curNode.parentNode.node.equals(nextBoard)) {
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

		pq.insert(new BoardNode(initial, null, 0));
		pqTwin.insert(new BoardNode(initial.twin(), null, 0));


		while (!pq.isEmpty() && !pqTwin.isEmpty()) {
			if (pq.min().node.isGoal()) {
				curNode = pq.min();
				break;
			}
			//System.out.println(pq.min().node.hamming() + (pq.min().node.manhattan() + pq.min().move) * 4);
			if (pqTwin.min().node.isGoal()) {
				isSolvable = false;
				break;
			}
			count++;
			solve(pq);
			solve(pqTwin);

		}
		if (isSolvable()) {
			//System.out.println(count);
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
			int p1 = o1.priority;
			int p2 = o2.priority;
			if (p1 == p2) {
				return o1.node.manhattan() - o2.node.manhattan();
			}
			return p1 - p2;
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
