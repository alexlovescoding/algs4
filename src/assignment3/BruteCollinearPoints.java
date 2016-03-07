package assignment3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class BruteCollinearPoints {

    private LineSegment[] lineSegments;
    
    public BruteCollinearPoints(Point[] points) {
        ArrayList<Double> track = new ArrayList<Double>();
        ArrayList<LineSegment> lineList = new ArrayList<LineSegment>();

        if(points == null) {
            throw new java.lang.NullPointerException("Null points array");
        }
        for(int i = 0; i < points.length; i++) {
            for(int j = points.length; j > i; j--) {
                for(int k = points.length; k > j; k--) {
                    for(int l = points.length; l > k; l--) {
                        if(points[i] == null || points[j] == null || 
                                points[k] == null || points[l] == null) {
                            throw new java.lang.NullPointerException("Array contains null point");
                        }

                        double slope1 = points[i].slopeTo(points[j]);
                        double slope2 = points[k].slopeTo(points[l]);
                        if(slope1 == slope2) {
                            if (points[i].compareTo(points[j]) == 0 || points[k].compareTo(points[l]) == 0 ||
                                    points[i].compareTo(points[l]) == 0) {
                                throw new java.lang.IllegalArgumentException("Repeated points found.");
                            }

                            if(!track.contains(slope1)) {
                                track.add(slope1);
                                Point[] tempPoints = {points[i], points[j], points[k], points[l]};
                                Point min = Collections.min(Arrays.asList(tempPoints));
                                Point max = Collections.max(Arrays.asList(tempPoints));
                                lineList.add(new LineSegment(min, max));
                            }
                        }
                    }
                }
            }
        }
        this.lineSegments = (LineSegment[]) lineList.toArray();
    }

    public int numberOfSegments() {
        // the number of line segments
        return lineSegments.length;
    }

    public LineSegment[] segments() {
       return lineSegments;
    }
}