package hengxiu.courseraPA.w5;

import java.util.TreeSet;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

public class PointSET {
	TreeSet<Point2D> pointSet;
	
	// construct an empty set of points 
	public PointSET() {
		pointSet = new TreeSet<>();
	}
	
	// is the set empty? 
	public boolean isEmpty() {
		return pointSet.isEmpty();
	}
	
	// number of points in the set 
	public int size() {
		return pointSet.size();
	}
	
	// add the point to the set (if it is not already in the set)
	public void insert(Point2D p) {
		pointSet.add(p);
		
	}
	
	// does the set contain point p? 
	public boolean contains(Point2D p) {
		return pointSet.contains(p);
	}
	
	// draw all points to standard draw
	public void draw() {
		for (Point2D p : pointSet) {
			p.draw();
		}
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
