package hengxiu.courseraPA.w3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BruteCollinearPoints {
	
	private List<Point> pointList;
	private List<LineSegment> segList;
	// finds all line segments containing 4 points
	public BruteCollinearPoints(Point[] points) {
		if (points == null) {
			throw new java.lang.NullPointerException();
		}
		pointList = new ArrayList<>();
		segList = new ArrayList<>();
		for (Point p : points) {
			if (p == null) {
				throw new java.lang.NullPointerException();
			}
			if (pointList.contains(p)) {
				throw new java.lang.IllegalArgumentException();
			}
			pointList.add(p);
		}
		
		int n = pointList.size();
		for (int i = 0; i < n - 3; i++) {
			 for (int j = i + 1; j < n - 2; j++) {
				 for (int k = j + 1; k < n - 1; k++) {
					 for (int l = k + 1; l < n; l++) {
						 Point p = pointList.get(i);
						 Point q = pointList.get(j);
						 Point s = pointList.get(k);
						 Point r = pointList.get(l);
						 if (p.slopeTo(q) == p.slopeTo(s) && p.slopeTo(q) == p.slopeTo(r)) {
							 ArrayList<Point> tList = new ArrayList<>(Arrays.asList(p, q, s, r));
							 Collections.sort(tList);
							 segList.add(new LineSegment(tList.get(0), tList.get(tList.size() - 1)));
						 }
					 }
				 }
			 }
		 } 
	}
	
	// the number of line segments
	public int numberOfSegments() {
		return segList.size();
	}
	
	 // the line segments;
	 public LineSegment[] segments() {
		 return segList.toArray(new LineSegment[segList.size()]);
	 }
}
