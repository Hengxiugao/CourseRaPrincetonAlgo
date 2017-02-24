package hengxiu.courseraPA.w2;

import java.util.Iterator;

import edu.princeton.cs.algs4.StdIn;

public class Permutation {
	
	private static RandomizedQueue<String> rQ;
	
	public static void main(String[] args) {
		rQ = new RandomizedQueue<String>();
		int k = Integer.parseInt(args[0]);
		rQ.enqueue(StdIn.readString());
		if (k > rQ.size()) {
			k = rQ.size();
		}
		Iterator<String> iterator = rQ.iterator();
		for (int i = 0; i < k; i++) {
			System.out.println(iterator.next());
		}
		
	}
}
