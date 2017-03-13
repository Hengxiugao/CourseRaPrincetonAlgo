package hengxiu.courseraPA.w5;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

public class KdTree {
	
	private class KdNode {
		int level;
		Point2D point;
		KdNode left;
		KdNode right;
		KdNode (int level, Point2D point) {
			this.level = level;
			this.point = point;
		}
	}
	
	// construct an empty set of points 
	public KdTree() {
		
	}
	
	// is the set empty? 
	public boolean isEmpty() {
		
	}
	
	// number of points in the set 
	public int size() {
		
	}
	
	// add the point to the set (if it is not already in the set)
	public void insert(Point2D p) {
		
		
	}   
	
	// does the set contain point p? 
	public boolean contains(Point2D p) {
		
	}   
	
	// draw all points to standard draw
	public void draw() {
		
	}                 
	
	// all points that are inside the rectangle 
	public Iterable<Point2D> range(RectHV rect) {
		
	}   
	
	// a nearest neighbor in the set to point p; null if the set is empty 
	public Point2D nearest(Point2D p) {
		
	}
	
	// unit testing of the methods (optional) 
	public static void main(String[] args) {
		
	}    
}