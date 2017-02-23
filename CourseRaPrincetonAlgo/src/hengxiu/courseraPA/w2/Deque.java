package hengxiu.courseraPA.w2;

import java.util.Iterator;

class Node<Item> {
	Item val;
	Node next;
	Node pre;
}

public class Deque<Item> implements Iterable<Item> {
	
	Node head;
	Node tail;
	
	// construct an empty deque
	public Deque() {
		head = null;
		tail = null;
	}
	
	// is the deque empty?
	public boolean isEmpty() {
		
	}
	
	// return the number of items on the deque
	public int size() {
		
	}
	
	// add the item to the front
	public void addFirst(Item item) {
		if (item == null) {
			throw new java.lang.NullPointerException();
		}
	}
	
	// add the item to the end
	public void addLast(Item item) {
		if (item == null) {
			throw new java.lang.NullPointerException();
		}
	}
	
	// remove and return the item from the front
	public Item removeFirst() {
		if (head == null) {
			throw new java.util.NoSuchElementException();
		}
	}
	
	// remove and return the item from the end
	public Item removeLast() {
		if (head == null) {
			throw new java.util.NoSuchElementException();
		}
	}
	
	// return an iterator over items in order from front to end
	public Iterator<Item> iterator() {
		
	}
	
	// unit testing (optional)
	public static void main(String[] args) {
		
	}
}