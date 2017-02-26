package hengxiu.courseraPA.w3;

import java.util.ArrayList;
import java.util.List;

public class BruteCollinearPoints {
	
	List<Point> pointList;
	
	// finds all line segments containing 4 points
	public BruteCollinearPoints(Point[] points) {
		if (points == null) {
			throw new java.lang.NullPointerException();
		}
		pointList = new ArrayList<>();
		for (Point p : points) {
			if (p == null) {
				throw new java.lang.NullPointerException();
			}
			if (pointList.contains(p)) {
				throw new java.lang.IllegalArgumentException();
			}
			pointList.add(p);
		}
	}
	
	// the number of line segments
	public int numberOfSegments() {
		
	}
	
	 // the line segments
	 public LineSegment[] segments() {
		 
	 }
}
