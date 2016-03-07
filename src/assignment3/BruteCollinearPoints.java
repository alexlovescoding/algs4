package assignment3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class BruteCollinearPoints {

    private LineSegment[] lineSegments;

    public BruteCollinearPoints(Point[] points) {
        ArrayList<LineSegment >lineSegments = new ArrayList<LineSegment>();
        if (points == null) {
            throw new java.lang.NullPointerException("Null points array");
        }
        for (int i = 0; i < points.length; i++) {
            for (int j = points.length-1; j > i; j--) {
                for (int k = points.length-1; k > j; k--) {
                    for (int l = points.length-1; l > k; l--) {
                        if (points[i] == null || points[j] == null || points[k] == null || points[l] == null) {
                            throw new java.lang.NullPointerException("Array contains null point");
                        }

                        double slope1 = points[i].slopeTo(points[j]);
                        double slope2 = points[k].slopeTo(points[l]);
                        double slope3 = points[i].slopeTo(points[l]);
                        if (slope1 == slope2 && slope1 == slope3) {
                            if (points[i].compareTo(points[j]) == 0 || points[k].compareTo(points[l]) == 0
                                    || points[i].compareTo(points[l]) == 0) {
                                throw new java.lang.IllegalArgumentException("Repeated points found.");
                            }

                            Point[] tempPoints = { points[i], points[j], points[k], points[l] };
                            Point min = Collections.min(Arrays.asList(tempPoints));
                            Point max = Collections.max(Arrays.asList(tempPoints));
                            LineSegment lineSegment = new LineSegment(min, max);
                            if (!lineSegments.contains(lineSegment)) {
                                lineSegments.add(lineSegment);
                            }
                        }
                    }
                }
            }
        }
        this.lineSegments = new LineSegment[lineSegments.size()];
        lineSegments.toArray(this.lineSegments);
    }

    public int numberOfSegments() {
        // the number of line segments
        return lineSegments.length;
    }

    public LineSegment[] segments() {
        return lineSegments;
    }

    public static void main(String[] args) {

        // read the N points from a file
        In in = new In(StdIn.readLine());
        int N = in.readInt();
        Point[] points = new Point[N];
        for (int i = 0; i < N; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.show(0);
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
    }
}