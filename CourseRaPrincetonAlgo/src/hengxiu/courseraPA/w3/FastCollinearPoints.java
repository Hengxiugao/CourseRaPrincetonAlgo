package hengxiu.courseraPA.w3;

import java.util.ArrayList;
import java.util.List;

public class FastCollinearPoints {
	
	List<Point> pointList;
	List<LineSegment> segList;
	
	
	// finds all line segments containing 4 or more points
	public FastCollinearPoints(Point[] points) {
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
	}
	
	// the number of line segments
	public int numberOfSegments() {
		return 0;
	}
	
	// the line segments
	public LineSegment[] segments() {
		return null;
	}
}
