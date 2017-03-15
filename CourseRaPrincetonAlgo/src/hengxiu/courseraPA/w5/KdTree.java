package hengxiu.courseraPA.w5;

import java.util.ArrayList;
import java.util.List;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

public class KdTree {
	KdNode root;
	int size;
	private class KdNode {
		//Odd level : horizontal, even level : vertical
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
		root = null;
		size = 0;
	}
	
	// is the set empty? 
	public boolean isEmpty() {
		return size() == 0;
	}
	
	// number of points in the set 
	public int size() {
		return size();
	}
	
	// add the point to the set (if it is not already in the set)
	public void insert(Point2D p) {
		if (p == null) {
			throw new java.lang.NullPointerException();
		}
		if (isEmpty()) {
			root = new KdNode(0, p);
		} else {
			insertTo(root, p);
		}
		
	}
	
	private void insertTo(KdNode root, Point2D p) {
		if (root == null) {
			return;
		}
		//Vertical level, compare x
		if (root.level % 2 == 0) {
			//target point is on the left of the root point
			if (p.x() < root.point.x()) {
				if (root.left == null) {
					root.left = new KdNode(root.level + 1, p);
					return;
				}
				insertTo(root.left, p);
			} else {
			//target point is on the right of the root point
				if (root.right == null) {
					root.right = new KdNode(root.level + 1, p);
					return;
				}
				insertTo(root.right, p);
			}
		} else {
		//Horizontal level, compare y

			//target point is on the down of the root point
			if (p.y() < root.point.y()) {
				if (root.left == null) {
					root.left = new KdNode(root.level + 1, p);
					return;
				}
				insertTo(root.left, p);
			} else {
			//target point is on the up of the root point
				if (root.right == null) {
					root.right = new KdNode(root.level + 1, p);
					return;
				}
				insertTo(root.right, p);
			}
		
		}
	}
	
	// does the set contain point p? 
	public boolean contains(Point2D p) {
		if (p == null) {
			throw new java.lang.NullPointerException();
		}
		return containsHelper(root, p);
	}
	
	private boolean containsHelper(KdNode root, Point2D p) {
		if (root == null) {
			return false;
		}
		if (root.point.equals(p)) {
			return true;
		}
		//Vertical level, compare x
		if (root.level % 2 == 0) {
			//target point is on the left of the root point
			if (p.x() < root.point.x()) {
				return containsHelper(root.left, p);
			} else {
			//target point is on the right of the root point
				return containsHelper(root.right, p);
			}
		} else {
		//Horizontal level, compare y

			//target point is on the down of the root point
			if (p.y() < root.point.y()) {
				return containsHelper(root.left, p);
			} else {
			//target point is on the up of the root point
				return containsHelper(root.right, p);
			}
		
		}

	}

	
	// draw all points to standard draw
	public void draw() {
		traverseAndDraw(root);
	}
	
	private void traverseAndDraw(KdNode root) {
		traverseAndDraw(root.left);
		root.point.draw();
		traverseAndDraw(root.right);
	}
	
	// all points that are inside the rectangle 
	public Iterable<Point2D> range(RectHV rect) {
		if (rect == null) {
			throw new java.lang.NullPointerException();
		}
		return rangeHelper(root, rect);
	}
	
	private List<Point2D> rangeHelper(KdNode root, RectHV rect) {
		List<Point2D> resultList = new ArrayList<>();
		if (root == null) {
			return resultList;
		}
		
		if (Double.compare(rect.distanceSquaredTo(root.point), 0.0) == 0) {
			resultList.addAll(rangeHelper(root.left, rect));
			resultList.addAll(rangeHelper(root.right, rect));
		} else {
			if (root.level % 2 == 0) {
			//Vertical level, compare x
				if (root.point.x() < rect.xmin()) {
					resultList.addAll(rangeHelper(root.right, rect));
				} else if (root.point.x() > rect.xmax()) {
					resultList.addAll(rangeHelper(root.left, rect));
				} else {
					resultList.addAll(rangeHelper(root.left, rect));
					resultList.addAll(rangeHelper(root.right, rect));
				}
			} else {
			//Horizontal level, compare y
				if (root.point.y() < rect.ymin()) {
					resultList.addAll(rangeHelper(root.right, rect));
				} else if (root.point.y() > rect.ymax()) {
					resultList.addAll(rangeHelper(root.left, rect));
				} else {
					resultList.addAll(rangeHelper(root.left, rect));
					resultList.addAll(rangeHelper(root.right, rect));
				}
			}
		}
		return resultList;
	}
	
	// a nearest neighbor in the set to point p; null if the set is empty 
	public Point2D nearest(Point2D p) {
		if (p == null) {
			throw new java.lang.NullPointerException();
		}
		nearestResult result = new nearestResult(root.point);
		nearestHelper(root, p, result);
		return result.pn;
	}
	
	private class nearestResult{
		Point2D pn;
		nearestResult(Point2D p){
			pn = new Point2D(p.x(), p.y());
		}
	}
	
	private void nearestHelper(KdNode root, Point2D p, nearestResult result) {
		if (root == null) {
			return;
		}
		if (p.distanceTo(root.point) < p.distanceTo(result.pn)) {
			result.pn = root.point;
		}
		if (root.level % 2 == 0) {
		//Vertical level, compare x
			if (p.x() < root.point.x()) {
				nearestHelper(root.left, p, result);
			} else {
				nearestHelper(root.right, p, result);
			}
		} else {
		//Horizontal level, compare y
			if (p.y() < root.point.y()) {
				nearestHelper(root.left, p, result);
			} else {
				nearestHelper(root.right, p, result);
			}
		}
		
	}
	
	// unit testing of the methods (optional) 
	public static void main(String[] args) {
		
	}    
}