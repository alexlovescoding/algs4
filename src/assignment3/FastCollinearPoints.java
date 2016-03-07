package assignment3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class FastCollinearPoints {
    private LineSegment[] lineSegments;

    public FastCollinearPoints(Point[] points) {
        ArrayList<LineSegment> lineSegmentsList = new ArrayList<LineSegment>();
        Point[] tempPoints = points.clone();

        outer: for (int i = 0; i < points.length; i++) {
            Arrays.sort(tempPoints, points[i].slopeOrder());
            for (int j = 0; j < tempPoints.length; j++) {
                try {
                    double firstSlope = points[i].slopeTo(tempPoints[j]);
                    ArrayList<Point> myPoints = new ArrayList<Point>();
                    myPoints.add(points[i]);
                    myPoints.add(tempPoints[j]);
                    int count = 0;
                    while (firstSlope == points[i].slopeTo(tempPoints[j + ++count])) {
                        myPoints.add(tempPoints[j + count]);
                    }
                    if(myPoints.size() == 4) {
                        Point min = Collections.min(myPoints);
                        Point max = Collections.max(myPoints);
                        LineSegment lineSegment = new LineSegment(min, max);
                        LineSegment reverse = new LineSegment(max, min);
                        if (!lineSegmentsList.contains(lineSegment) && !lineSegmentsList.contains(reverse)) {
                            lineSegmentsList.add(lineSegment);
                        }
                        continue outer;
                    }
                } catch (IndexOutOfBoundsException e) {
                    continue outer;
                }
            }
        }

        this.lineSegments = new LineSegment[lineSegmentsList.size()];
        lineSegmentsList.toArray(this.lineSegments);
    }

    public int numberOfSegments() {
        return lineSegments.length;
    }

    public LineSegment[] segments() {
        return lineSegments;
    }
}
