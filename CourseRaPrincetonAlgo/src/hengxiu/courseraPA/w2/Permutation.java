package hengxiu.courseraPA.w2;

import edu.princeton.cs.algs4.StdRandom;

public class Permutation {
	
	private static Deque<String> deque;
	
	public static void main(String[] args) {
		deque = new Deque<String>();
		int k = Integer.valueOf(args[0]);
		String fileName = args[2];
		String content = readLineFromFile(fileName);
		readDeque(content);
		if (k > deque.size()) {
			k = deque.size();
		}
		for (int i = 0; i < k; i++) {
			System.out.println(popRandom());
		}
		
	}
	private static String popRandom() {
		if (deque.size() == 1) {
			return deque.removeFirst();
		}
		int index = StdRandom.uniform(deque.size());
		for (int i = 0; i < index; i++) {
			deque.addLast(deque.removeFirst());
		}
		return deque.removeFirst();
	}
	private static void print() {
		for (String str : deque) {
			System.out.print(str + " ");
		}
	}
	
	private static void readDeque(String content) {
		StringBuffer sb = new StringBuffer(content);
		while (sb.indexOf(" ") > 0) {
			deque.addLast(sb.substring(0, sb.indexOf(" ")));
			sb.delete(0, sb.indexOf(" ") + 1);
		}
		deque.addLast(sb.toString());
	}
	
	private static String readLineFromFile(String fileName) {
		return "A B C D E F G H I";
	}

}
