package hengxiu.courseraPA.w2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import edu.princeton.cs.algs4.StdRandom;

public class Permutation {
	
	private static RandomizedQueue<String> rQ;
	
	public static void main(String[] args) {
		rQ = new RandomizedQueue<String>();
		int k = Integer.parseInt(args[0]);
		String fileName = args[2];
		String content = readLineFromFile(fileName);
		readDeque(content);
		if (k > rQ.size()) {
			k = rQ.size();
		}
		Iterator<String> iterator = rQ.iterator();
		for (int i = 0; i < k; i++) {
			System.out.println(iterator.next());
		}
		
	}
	
	private static void readDeque(String content) {
		StringBuffer sb = new StringBuffer(content);
		while (sb.indexOf(" ") > 0) {
			rQ.enqueue(sb.substring(0, sb.indexOf(" ")));
			sb.delete(0, sb.indexOf(" ") + 1);
		}
		rQ.enqueue(sb.toString());
	}
	
	private static String readLineFromFile(String fileName) {
		BufferedReader br = null;
		String line = "";
		try {
			br = new BufferedReader(new FileReader(fileName));
		    
		    line = br.readLine();

		} catch (IOException e) {
			e.printStackTrace();
		}finally {
		    try {
		    	if (br != null) {
		    		br.close();
		    	}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return line;
	}

}
