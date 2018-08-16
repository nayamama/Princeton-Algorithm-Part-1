
import java.util.ArrayList;
import java.util.Arrays;


public class FastCollinearPoints {
    private ArrayList<LineSegment> segments = new ArrayList<>();

    //check corner case: null point, null constructor or repeated points
    private void preCheck(Point[] points) {
        if (points == null || points.length == 0) {
            throw new IllegalArgumentException("The array of points is null or empty");
        }
        for (int i = 0; i < points.length; i++) {
            if (points[i] == null) {
                throw new IllegalArgumentException("The point is null.");
            }
        }
        for (int i = 0; i < points.length - 1; i++) {
            for (int j = i + 1; j < points.length; j++) {
                if (points[i].compareTo(points[j]) == 0) {
                    throw new IllegalArgumentException("There are duplicated entries.");
                }
            }
        }
    }

    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points) {
        preCheck(points);
        // think each p as origin

        Point[] pointsCopy = points.clone();
        Arrays.sort(pointsCopy);

        for (int i = 0; i < points.length - 3; i++) {
            Arrays.sort(pointsCopy);

            // Sort the points according to the slopes they makes with p.
            Arrays.sort(pointsCopy, pointsCopy[i].slopeOrder());
            // Check if any 3 (or more) adjacent points in the sorted order have equal slopes with respect to p.
            // If so, these points, together with p, are collinear.

            for (int p = 0, first = 1, last = 2; last < pointsCopy.length; last++) {
                while (last < pointsCopy.length && Double.compare(pointsCopy[p].slopeTo(pointsCopy[first]),
                        pointsCopy[p].slopeTo(pointsCopy[last])) == 0) {
                    last++;
                }
                if (last - first >= 3 && pointsCopy[p].compareTo(pointsCopy[first]) < 0) {
                    segments.add(new LineSegment(pointsCopy[p], pointsCopy[last - 1]));
                }
                first = last;
            }
        }
    }

    // the number of line segments
    public int numberOfSegments() {
        return segments.size();
    }

    public LineSegment[] segments() {
        return segments.toArray(new LineSegment[segments.size()]);
    }
/*
    public static void main(String[] args) {

        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
  */
}
