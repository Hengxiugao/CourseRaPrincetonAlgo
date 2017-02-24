package hengxiu.courseraPA.w2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
	
	private LinkedHashMap<Integer, Item> index2ItemMap;
	private LinkedHashMap<Item, Integer> item2IndexMap;
	private int size = 0;
	// construct an empty randomized queue
	public RandomizedQueue() {
		index2ItemMap = new LinkedHashMap<>();
		item2IndexMap = new LinkedHashMap<>();
		size = 0;
	}
	
	// is the queue empty?
	public boolean isEmpty() {
		return size == 0;
	}    
	   
	// return the number of items on the queue
	public int size() {
		return size;
	}
	
	// add the item
	public void enqueue(Item item) {
		if (item == null) {
			throw new java.lang.NullPointerException();
		}
		index2ItemMap.put(size, item);
		item2IndexMap.put(item, size);
		size++;
	}
	
	// remove and return a random item
	public Item dequeue() {
		if (isEmpty()) {
			throw new java.util.NoSuchElementException();
		}
		int randomIndex = StdRandom.uniform(size);
		Item result = null;
		if (randomIndex < size - 1) {
			Item lastItem = index2ItemMap.get(size - 1);
			result = index2ItemMap.get(randomIndex);
			index2ItemMap.put(randomIndex, lastItem);
			item2IndexMap.put(lastItem, randomIndex);
			index2ItemMap.remove(size - 1);
			item2IndexMap.remove(lastItem);
		} else {
			result = index2ItemMap.get(randomIndex);
			index2ItemMap.remove(size - 1);
			item2IndexMap.remove(result);
		}
		size--;
		return result;
	}
	
	// return (but do not remove) a random item
	public Item sample() {
		if (isEmpty()) {
			throw new java.util.NoSuchElementException();
		}
		int randomIndex = StdRandom.uniform(size);
		return index2ItemMap.get(randomIndex);
	}
	
	// return an independent iterator over items in random order
	public Iterator<Item> iterator() {
		return new RandomizedQueueIterator();
	}
	
	private class RandomizedQueueIterator implements Iterator<Item> {
		HashMap<Integer, Item> idx2ItMap;
		HashMap<Item, Integer> it2IdxMap;
		int s = 0;
		RandomizedQueueIterator() {
			idx2ItMap = new HashMap<>();
			it2IdxMap = new HashMap<>();
			s = size;
			for (Map.Entry<Integer, Item> set : index2ItemMap.entrySet()) {
				it2IdxMap.put(set.getValue(), set.getKey());
				idx2ItMap.put(set.getKey(), set.getValue());
			}
		}
		
		@Override
		public boolean hasNext() {
			return s != 0;
		}

		@Override
		public Item next() {
			if (!hasNext()) {
				throw new java.util.NoSuchElementException();
			}
			int randomIndex = StdRandom.uniform(s);
			Item result = null;
			if (randomIndex < s - 1) {
				Item lastItem = idx2ItMap.get(s - 1);
				result = idx2ItMap.get(randomIndex);
				idx2ItMap.put(randomIndex, lastItem);
				it2IdxMap.put(lastItem, randomIndex);
				idx2ItMap.remove(s - 1);
				it2IdxMap.remove(lastItem);
			} else {
				result = idx2ItMap.get(randomIndex);
				idx2ItMap.remove(s - 1);
				it2IdxMap.remove(result);
			}
			s--;
			return result;
		}
		
		public void remove() { 
			/* not supported */ 
			throw new UnsupportedOperationException();
		}
	}
	
	// unit testing (optional)
	public static void main(String[] args) {
		RandomizedQueue<Integer> r = new RandomizedQueue<>();
		//print(r);
		r.enqueue(1);
		//print(r);
		r.enqueue(2);
		//print(r);
		r.enqueue(5);
		r.enqueue(4);
		r.enqueue(3);
		
		System.out.println("out " + r.dequeue());
		print(r);
		
		System.out.println("out " + r.dequeue());
		print(r);
		
		System.out.println("out " + r.dequeue());
		print(r);
		
		System.out.println("out " + r.dequeue());
		print(r);
			
		r.enqueue(10);
		System.out.println("out " + r.dequeue());
		print(r);
		
		/*
		System.out.println(r.sample());
		System.out.println(r.sample());
		System.out.println(r.sample());
		System.out.println(r.sample());
		System.out.println(r.sample());
		System.out.println(r.sample());
		System.out.println(r.sample());
		System.out.println(r.sample());
		*/
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