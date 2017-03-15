package hengxiu.courseraPA.w5;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

public class PointSET {
	private TreeSet<Point2D> pointSet;
	
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
		if (p == null) {
			throw new java.lang.NullPointerException();
		}
		pointSet.add(p);
		
	}
	
	// does the set contain point p? 
	public boolean contains(Point2D p) {
		if (p == null) {
			throw new java.lang.NullPointerException();
		}
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
		if (rect == null) {
			throw new java.lang.NullPointerException();
		}
		List<Point2D> resultList = new ArrayList<>();
		for (Point2D p : pointSet) {
			if (Double.compare(rect.distanceSquaredTo(p), 0.0) == 0) {
				resultList.add(p);
			}
		}
		return resultList;
	}
	
	// a nearest neighbor in the set to point p; null if the set is empty 
	public Point2D nearest(Point2D p) {
		if (p == null) {
			throw new java.lang.NullPointerException();
		}
		double min = Double.MAX_VALUE;
		Point2D minPoint = null;
		for (Point2D point : pointSet) {
			double curDis = point.distanceTo(p);
			if (min > curDis) {
				min = curDis;
				minPoint = point;
			}
		}
		return minPoint;
	}
	
	// unit testing of the methods (optional) 
	public static void main(String[] args) {
		In in = new In(args[0]);
		PointSET pset = new PointSET();
		
		while(in.hasNextLine()) {
			double x = in.readDouble();
			double y = in.readDouble();
			Point2D p = new Point2D(x, y);
			pset.insert(p);
		}
		System.out.println("pSet, size=" + pset.size());
		StdDraw.setPenRadius(0.01);
		
		pset.draw();
		
		/*
		StdDraw.setPenColor(StdDraw.RED);
		Point2D p = new Point2D(0.5, 0.1);
		p.draw();
		
		StdDraw.setPenColor(StdDraw.GREEN);
		pset.nearest(p).draw();
		*/
		
		RectHV rect = new RectHV(0, 0, 0.5, 0.904508);
		rect.draw();
		StdDraw.setPenColor(StdDraw.RED);
		Iterable<Point2D> pointList = pset.range(rect);
		for (Point2D p : pointList) {
			p.draw();
		}
	}
}
