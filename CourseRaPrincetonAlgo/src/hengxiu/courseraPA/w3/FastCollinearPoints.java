package hengxiu.courseraPA.w3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class FastCollinearPoints {
	
	private List<Point> pointList;
	private List<LineSegment> segList;
	private HashMap<Point, HashSet<Point>> visitedMap;
	
	// finds all line segments containing 4 or more points
	public FastCollinearPoints(Point[] points) {
		if (points == null) {
			throw new java.lang.NullPointerException();
		}
		pointList = new ArrayList<>();
		segList = new ArrayList<>();
		visitedMap = new HashMap<>();
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
		for (int i = 0; i < n; i++) {
			List<Point> pointCopyList = new ArrayList<>(pointList);
			List<Point> tempList = new ArrayList<>();
			Point pivot = pointList.get(i);
			int start = 0;
			Collections.sort(pointCopyList, pivot.slopeOrder());
			//System.out.println("Sorted list, pivot point=" + pivot.toString());
			//for (Point p : pointCopyList) {
			//	System.out.println("p: " + p + ", slope=" + pivot.slopeTo(p));
			//}
			for (int j = 1; j < n; j++) {
				Point curent = pointCopyList.get(j);
				Point pre = pointCopyList.get(j - 1);
				//System.out.println(pivot.slopeTo(curent) + ", " + pivot.slopeTo(pre));
				if (Double.compare(pivot.slopeTo(curent), pivot.slopeTo(pre)) != 0) {
					//System.out.println("not equals, j-start=" + (j - start));
					if (j - start >= 3) {
						
						tempList.clear();
						tempList.add(pivot);
						for (int k = start; k < j; k++) {
							tempList.add(pointCopyList.get(k));
						}
						Collections.sort(tempList);
						LineSegment newLine = new LineSegment(tempList.get(0), tempList.get(tempList.size() - 1));
						boolean containsFlag = false;
						for (LineSegment s : segList) {
							if (s.toString().equals(newLine.toString())) {
								containsFlag = true;
							}
						}
						if (!containsFlag) {
							segList.add(newLine);
						}
						
						
					}
					start = j;
				} else {
					//System.out.println("equals, j-start=" + (j - start));
					if (j - start >= 2 && j == n - 1) {
						tempList.clear();
						tempList.add(pivot);
						for (int k = start; k <= j; k++) {
							tempList.add(pointCopyList.get(k));
						}
						Collections.sort(tempList);
						LineSegment newLine = new LineSegment(tempList.get(0), tempList.get(tempList.size() - 1));
						boolean containsFlag = false;
						for (LineSegment s : segList) {
							if (s.toString().equals(newLine.toString())) {
								containsFlag = true;
							}
						}
						if (!containsFlag) {
							//System.out.println("added to seg, start = " + tempList.get(0) + " ,end = " + tempList.get(tempList.size() - 1));
							segList.add(newLine);
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
	
	// the line segments
	public LineSegment[] segments() {
		return segList.toArray(new LineSegment[segList.size()]);
	}
}
