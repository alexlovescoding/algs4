package assignment3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class FastCollinearPoints {
    private LineSegment[] lineSegments;

    public FastCollinearPoints(Point[] points) {
        ArrayList<LineSegment> lineSegments = new ArrayList<LineSegment>();
        Point[] tempPoints = points;

        outer: for(int i = 0; i < points.length; i++) {
            Arrays.sort(tempPoints, points[i].slopeOrder());
            for(int j = 0; j < tempPoints.length; j++) {
                boolean check = false;
                try {
                    double firstSlope = points[i].slopeTo(tempPoints[j]);
                    if(firstSlope == points[i].slopeTo(tempPoints[j+1]) 
                            && firstSlope == points[i].slopeTo(tempPoints[j+2])) {
                        check = true;
                        if(firstSlope != points[i].slopeTo(tempPoints[j+3])) {
                            Point[] myPoints = { points[i], tempPoints[j], tempPoints[j+1], tempPoints[j+2] };
                            Point min = Collections.min(Arrays.asList(myPoints));
                            Point max = Collections.max(Arrays.asList(myPoints));
                            LineSegment lineSegment = new LineSegment(min, max);
                            if (!lineSegments.contains(lineSegment)) {
                                lineSegments.add(lineSegment);
                            }
                        }
                        continue outer;
                    }
                }
                catch(IndexOutOfBoundsException e) {
                    if(check) {
                        Point[] myPoints = { points[i], tempPoints[j], tempPoints[j+1], tempPoints[j+2] };
                        Point min = Collections.min(Arrays.asList(myPoints));
                        Point max = Collections.max(Arrays.asList(myPoints));
                        LineSegment lineSegment = new LineSegment(min, max);
                        if (!lineSegments.contains(lineSegment)) {
                            lineSegments.add(lineSegment);
                        }
                    }
                    continue outer;
                }
            }
        }

        this.lineSegments = new LineSegment[lineSegments.size()];
        lineSegments.toArray(this.lineSegments);
    }

    public int numberOfSegments() {
        return lineSegments.length;
    }
    public LineSegment[] segments() {
        return lineSegments;
    }
}
