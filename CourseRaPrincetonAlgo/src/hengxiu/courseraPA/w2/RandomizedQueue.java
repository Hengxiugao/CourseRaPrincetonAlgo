package hengxiu.courseraPA.w2;

import java.util.Iterator;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
	
	
	private Deque<Item> deque;
	//HashMap<Integer, Item> index2ItemMap;
	//HashMap<Item, Integer> item2IndexMap;
	
	// construct an empty randomized queue
	public RandomizedQueue() {
		deque = new Deque<>();
		//index2ItemMap = new HashMap<>();
		//item2IndexMap = new HashMap<>();
	}
	
	// is the queue empty?
	public boolean isEmpty() {
		return deque.isEmpty();
	}    
	   
	// return the number of items on the queue
	public int size() {
		return deque.size();
	}
	
	// add the item
	public void enqueue(Item item) {
		if (item == null) {
			throw new java.lang.NullPointerException();
		}
		deque.addLast(item);
	}
	
	// remove and return a random item
	public Item dequeue() {
		if (isEmpty()) {
			throw new java.util.NoSuchElementException();
		}
		return deque.removeFirst();
	}
	
	// return (but do not remove) a random item
	public Item sample() {
		if (isEmpty()) {
			throw new java.util.NoSuchElementException();
		}
		int index = StdRandom.uniform(this.size());
		Item result = null;
		Iterator<Item> temp = this.iterator();
		for (int i = 0; i <= index; i++) { 
			result = temp.next();
		}
		return result;
	}
	

	
	// return an independent iterator over items in random order
	public Iterator<Item> iterator() {
		return deque.iterator();
	}
	
	// unit testing (optional)
	public static void main(String[] args) {
		RandomizedQueue<Integer> r = new RandomizedQueue<>();
		print(r);
		r.enqueue(1);
		print(r);
		r.enqueue(2);
		print(r);
		r.dequeue();
		print(r);
		r.dequeue();
		r.enqueue(5);
		//r.enqueue(4);
		//r.enqueue(3);
		//r.enqueue(2);
		//r.enqueue(1);
		print(r);
		System.out.println(r.sample());
		System.out.println(r.sample());
		System.out.println(r.sample());
		System.out.println(r.sample());
		System.out.println(r.sample());
		System.out.println(r.sample());
		System.out.println(r.sample());
		System.out.println(r.sample());
	}
	
	private static void print(RandomizedQueue<Integer> r) {

		System.out.print("SIZE=" + r.size() + " ,");
		if (r.isEmpty()) {
			System.out.print("EMPTY");
		}
		for (Integer i : r) {
			System.out.print(i + " ");
		}
		System.out.println();
	}
}