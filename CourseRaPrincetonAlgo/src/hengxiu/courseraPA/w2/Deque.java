package hengxiu.courseraPA.w2;

import java.util.Iterator;



public class Deque<Item> implements Iterable<Item> {
	
	private class Node {
		Item val;
		Node next;
		Node pre;
		public Node(Item val) {
			this.val = val;
		}
	}
	
	private Node head;
	private Node tail;
	private int size = 0;
	
	// construct an empty deque
	public Deque() {
		head = null;
		tail = null;
		size = 0;
	}
	
	// is the deque empty?
	public boolean isEmpty() {
		return head == null;
	}
	
	// return the number of items on the deque
	public int size() {
		return size;
	}
	
	// add the item to the front
	public void addFirst(Item item) {
		if (item == null) {
			throw new java.lang.NullPointerException();
		}
		size++;
		if (head == null) {
			head = new Node(item);
			tail = head;
		} else {
			Node newNode = new Node(item);
			newNode.next = head;
			newNode.pre = null;
			head.pre = newNode;
			head = newNode;
		}
	}
	
	// add the item to the end
	public void addLast(Item item) {
		if (item == null) {
			throw new java.lang.NullPointerException();
		}
		size++;
		if (tail == null) {
			tail = new Node(item);
			head = tail;
		} else {
			Node newNode = new Node(item);
			newNode.pre = tail;
			newNode.next = null;
			tail.next = newNode;
			tail = newNode;
		}
	}
	
	// remove and return the item from the front
	public Item removeFirst() {
		if (isEmpty()) {
			throw new java.util.NoSuchElementException();
		}
		size--;
		Item result = head.val;
		head = head.next;
		if (head != null) {
			head.pre = null;
		} else {
			tail = null;
		}
		
		return result;
	}
	
	// remove and return the item from the end
	public Item removeLast() {
		if (isEmpty()) {
			throw new java.util.NoSuchElementException();
		}
		size--;
		Item result = tail.val;
		tail = tail.pre;
		if (tail != null) {
			tail.next = null;
		} else {
			head = null;
		}
		
		return result;
	}
	
	// return an iterator over items in order from front to end
	public Iterator<Item> iterator() {
		return new DequeIterator();
	}
	
	private class DequeIterator implements Iterator<Item>{
		Node first = head;
		@Override
		public boolean hasNext() {
			return first != null;
		}

		@Override
		public Item next() {
			if (!hasNext()) {
				throw new java.util.NoSuchElementException();
			}
			Item result = first.val;
			first = first.next;
			return result;
		}
		
		public void remove() { 
			/* not supported */ 
			throw new UnsupportedOperationException();
		} 
		
	}
	
	// unit testing (optional)
	public static void main(String[] args) {
		Deque<Integer> d = new Deque<>();
		//print(d);
		d.addFirst(1);
		//print(d);
		d.addLast(2);
		d.addLast(3);
		d.addLast(4);
		d.addFirst(0);
		print(d);
		print(d);
	}
	private static void print(Deque<Integer> d) {

		System.out.print("SIZE=" + d.size + " ,");
		if (d.isEmpty()) {
			System.out.print("EMPTY");
		}
		for (Integer i : d) {
			System.out.print(i + " ");
		}
		System.out.println();
	}
}