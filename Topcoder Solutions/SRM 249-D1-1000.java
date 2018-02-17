import java.util.Arrays;

/**
 * Created by omar on 01/08/17.
 */
public class CultureGrowth {
    public double finalTray(int[] xs, int[] ys) {
        Point[] p = new Point[xs.length];
        for (int i = 0; i < p.length; i++)
            p[i] = new Point(xs[i], ys[i]);
        return Polygon.convexHull(p).area();
    }

    static class Polygon {
        // Cases to handle: collinear points, polygons with n < 3

        static final double EPS = 1e-9;

        Point[] g; 			//first point = last point, counter-clockwise representation

        Polygon(Point[] o) { g = o; }

        double area() 		//clockwise/anti-clockwise check, for convex/concave polygons
        {
            double area = 0.0;
            for(int i = 0; i < g.length - 1; ++i)
                area += g[i].x * g[i+1].y - g[i].y * g[i+1].x;
            return Math.abs(area) / 2.0;			//negative value in case of clockwise
        }
        static Polygon convexHull(Point[] points)	//all points are unique, remove duplicates, edit ccw to accept collinear points
        {
            int n = points.length;
            Arrays.sort(points);
            Point[] ans = new Point[n<<1];
            int size = 0, start = 0;

            for(int i = 0; i < n; i++)
            {
                Point p = points[i];
                while(size - start >= 2 && !Point.ccw(ans[size-2], ans[size-1], p))	--size;
                ans[size++] = p;
            }
            start = --size;

            for(int i = n-1 ; i >= 0 ; i--)
            {
                Point p = points[i];
                while(size - start >= 2 && !Point.ccw(ans[size-2], ans[size-1], p))	--size;
                ans[size++] = p;
            }
            //			if(size < 0) size = 0			for empty set of points
            return new Polygon(Arrays.copyOf(ans, size));
        }
    }
    static class Point implements Comparable<Point>{
        static final double EPS = 1e-9;
        double x, y;
        Point (double xx, double yy) {x = xx; y = yy;}
        static boolean ccw(Point p, Point q, Point r)
        {
            return new Vector(p, q).cross(new Vector(p, r)) > 0;
        }
        @Override
        public int compareTo(Point p2) {
            return Math.abs(x - p2.x) <= EPS ? Double.compare(p2.y, y) : Double.compare(x, p2.x);
        }
    }
    static class Vector {

        double x, y;

        Vector(double a, double b) { x = a; y = b; }

        Vector(Point a, Point b) { this(b.x - a.x, b.y - a.y); }

        double cross(Vector v) { return x * v.y - y * v.x; }

    }
}
