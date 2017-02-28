package hengxiu.courseraPA.w3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class FastCollinearPoints {
	private List<LineSegment> segList;
	
	// finds all line segments containing 4 or more points
	public FastCollinearPoints(Point[] points) {
		if (points == null) {
			throw new java.lang.NullPointerException();
		}
		int n = points.length;
		Point[] pointArr = Arrays.copyOf(points, n);
		Arrays.sort(pointArr);
		segList = new ArrayList<>();
		//System.out.println("Sorted");
		for (int i = 0; i < pointArr.length; i++) {
			Point p = pointArr[i];
			//System.out.println(p);
			if (p == null) {
				throw new java.lang.NullPointerException();
			}
		}
		
		
		ArrayList<Point> tempList = new ArrayList<>();
		HashSet<Double> slopeSet = new HashSet<>();
		//Check for duplicate
		for (int i = 0; i < n; i++) {
			Point[] pointCopyArr = Arrays.copyOf(pointArr, n);
			Point pivot = pointCopyArr[i];
			Arrays.sort(pointCopyArr, 0, i , pivot.slopeOrder());
			Arrays.sort(pointCopyArr, i + 1, n, pivot.slopeOrder());
			for (int j = i + 1; j < n ; j++) {
				if (pivot.slopeTo(pointCopyArr[j]) == Double.NEGATIVE_INFINITY) {
					throw new java.lang.IllegalArgumentException();
				}
			}
		}
		
		for (int i = 0; i < n - 3; i++) {
			Point[] pointCopyArr = Arrays.copyOf(pointArr, n);
			Point pivot = pointCopyArr[i];
			slopeSet.clear();
			Arrays.sort(pointCopyArr, 0, i , pivot.slopeOrder());
			Arrays.sort(pointCopyArr, i + 1, n, pivot.slopeOrder());
			int start = i + 1;
			
			//System.out.println("\nSorted list, pivot point=" + pivot.toString());
			
			for (int j = 0; j < i; j++) {
				slopeSet.add(pivot.slopeTo(pointCopyArr[j]));
			}
			/*
			System.out.println("SET:");
			for (double d : slopeSet) {
				System.out.println(d);
			}
			
			for (Point p : pointCopyArr) {
				System.out.println("p: " + p + ", slope=" + pivot.slopeTo(p));
			}
			*/
			
			for (int j = i + 2; j < n; j++) {
				Point curent = pointCopyArr[j];
				Point pre = pointCopyArr[j - 1];
				//System.out.println("j="+ j + " : pivot slope to current = " + pivot.slopeTo(curent) + ", pivot slope to pre = " + pivot.slopeTo(pre));
				if (slopeSet.contains(pivot.slopeTo(pre))) {
					//System.out.println("--set contains--");
					start = j;
					continue;
				}
				
				if (Double.compare(pivot.slopeTo(curent), pivot.slopeTo(pre)) != 0) {
					//System.out.println("not equals, j-start=" + (j - start));
					if (j - start >= 3) {
						tempList.clear();
						tempList.add(pivot);
						for (int k = start; k < j; k++) {
							tempList.add(pointCopyArr[k]);
						}
						Collections.sort(tempList);
						LineSegment newLine = new LineSegment(tempList.get(0), tempList.get(tempList.size() - 1));
						//System.out.println("*** added to seg, start = " + tempList.get(0) + " ,end = " + tempList.get(tempList.size() - 1));
						segList.add(newLine);
						
						
					}
					start = j;
				} else {
					//System.out.println("equals, j-start=" + (j - start));
					if (j - start >= 2 && j == n - 1) {
						tempList.clear();
						tempList.add(pivot);
						for (int k = start; k <= j; k++) {
							tempList.add(pointCopyArr[k]);
						}
						Collections.sort(tempList);
						LineSegment newLine = new LineSegment(tempList.get(0), tempList.get(tempList.size() - 1));
						//System.out.println("*** added to seg, start = " + tempList.get(0) + " ,end = " + tempList.get(tempList.size() - 1));
						segList.add(newLine);
					}
					
				}
				
			}
			//break;
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
