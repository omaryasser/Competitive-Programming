import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by omar on 01/08/17.
 */
public class ConvexPolygons {
    
    public double overlap(String[] polygon1, String[] polygon2) {
        Point[] points1 = new Point[polygon1.length + 1];
        Point[] points2 = new Point[polygon2.length + 1];
        for (int i = 0; i < polygon1.length; i++) {
            String[] s = polygon1[i].split(" ");
            points1[i] = new Point(Integer.parseInt(s[0]), Integer.parseInt(s[1]));
        }
        points1[points1.length - 1] = points1[0];
        for (int i = 0; i < polygon2.length; i++) {
            String[] s = polygon2[i].split(" ");
            points2[i] = new Point(Integer.parseInt(s[0]), Integer.parseInt(s[1]));
        }
        points2[points2.length - 1] = points2[0];
        Polygon p1 = new Polygon(points1), p2 = new Polygon(points2);
        ArrayList<Point> inside = new ArrayList<>();
        for (int i = 0; i < points1.length - 1; i++)
            if (p2.inside(points1[i]))
                inside.add(points1[i]);
        for (int i = 0; i < points2.length - 1; i++)
            if (p1.inside(points2[i]))
                inside.add(points2[i]);

        for (int i = 0; i < points1.length - 1; i++) {
            LineSegment l1 = new LineSegment(points1[i], points1[i + 1]);
            for (int j = 0; j < points2.length - 1; j++) {
                LineSegment l2 = new LineSegment(points2[j], points2[j + 1]);
                Point intersection = l1.intersect(l2);
                if (intersection != null) inside.add(intersection);
            }
        }

        Point[] arr = new Point[inside.size()];
        for (int i = 0; i < inside.size(); i++)
            arr[i] = inside.get(i);
        if (arr.length == 0) return 0;
        return Polygon.convexHull(arr).area();
    }

    static class Point implements Comparable<Point>{
        static final double EPS = 1e-9;
        double x, y;
        Point (double xx, double yy) {x = xx; y = yy;}
        static boolean ccw(Point p, Point q, Point r)
        {
            return new Vector(p, q).cross(new Vector(p, r)) > 0;
        }
        static double angle(Point a, Point o, Point b)  // angle AOB
        {
            Vector oa = new Vector(o, a), ob = new Vector(o, b);
            return Math.acos(oa.dot(ob) / Math.sqrt(oa.norm2() * ob.norm2()));
        }
        boolean between(Point p, Point q)
        {
            return x < Math.max(p.x, q.x) + EPS && x + EPS > Math.min(p.x, q.x)
                    && y < Math.max(p.y, q.y) + EPS && y + EPS > Math.min(p.y, q.y);
        }

        @Override
        public int compareTo(Point p2) {
            return Math.abs(x - p2.x) <= EPS ? Double.compare(p2.y, y) : Double.compare(x, p2.x);
        }
    }
    static class Polygon {
        static final double EPS = 1e-9;
        Point[] g; 			//first point = last point, counter-clockwise representation
        Polygon(Point[] o) { g = o;}
        double area() 		//clockwise/anti-clockwise check, for convex/concave polygons
        {
            double area = 0.0;
            for(int i = 0; i < g.length - 1; ++i)
                area += g[i].x * g[i+1].y - g[i].y * g[i+1].x;
            return Math.abs(area) / 2.0;			//negative value in case of clockwise
        }
        boolean inside(Point p)	//for convex/concave polygons - winding number algorithm
        {
            double sum = 0.0;
            for(int i = 0; i < g.length - 1; ++i)
            {
                double angle = Point.angle(g[i], p, g[i+1]);
                if((Math.abs(angle) < EPS || Math.abs(angle - Math.PI) < EPS) && p.between(g[i], g[i+1]))
                    return true;
                if(Point.ccw(p, g[i], g[i+1]))
                    sum += angle;
                else
                    sum -= angle;
            }

            return Math.abs(2 * Math.PI - Math.abs(sum)) < EPS;		//abs makes it work for clockwise
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
    static class Vector {

        double x, y;

        Vector(double a, double b) { x = a; y = b; }

        Vector(Point a, Point b) { this(b.x - a.x, b.y - a.y); }

        double dot(Vector v) { return (x * v.x + y * v.y); }

        double cross(Vector v) { return x * v.y - y * v.x; }

        double norm2() { return x * x + y * y; }

    }
    static class LineSegment {

        Point p, q;

        LineSegment(Point a, Point b) { p = a; q = b; }

        Point intersect(LineSegment ls)
        {
            Line l1 = new Line(p, q), l2 = new Line(ls.p, ls.q);
            if(l1.parallel(l2))
            {
                if(l1.same(l2))
                    return p.between(ls.p, ls.q) ? p : q.between(ls.p, ls.q) ? q : ls.p.between(p, q) ? ls.p : ls.q.between(p, q) ? ls.q : null;
                return null;
            }
            Point c = l1.intersect(l2);
            return (c.between(p, q) && c.between(ls.p, ls.q)) ? c : null;
        }

    }
    static class Line {

        static final double INF = 1e9, EPS = 1e-9;

        double a, b, c;

        Line(Point p, Point q)
        {
            if(Math.abs(p.x - q.x) < EPS) {	a = 1; b = 0; c = -p.x;	}
            else
            {
                a = - (p.y - q.y) / (p.x - q.x);
                b = 1.0;
                c = -(a * p.x + p.y);
            }

        }

        boolean parallel(Line l) { return Math.abs(a - l.a) < EPS && Math.abs(b - l.b) < EPS; }

        boolean same(Line l) { return parallel(l) && Math.abs(c - l.c) < EPS; }

        Point intersect(Line l)
        {
            if(parallel(l))
                return null;
            double x = (b * l.c - c * l.b) / (a * l.b - b * l.a);
            double y;
            if(Math.abs(b) < EPS)
                y = -l.a * x - l.c;
            else
                y = -a * x - c;

            return new Point(x, y);
        }
    }
}
