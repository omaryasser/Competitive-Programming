package codes;

import java.util.StringTokenizer;

public class PointInPolygon {
    static class Point {
        double x, y;
        static final double INF = 1e9, EPS = 1e-9;
        Point (double aa, double bb) {x = aa; y = bb;}
        double distance (Point b) {
            return Math.sqrt((x - b.x) * (x - b.x) + (y - b.y) * (y - b.y));
        }
        boolean onSegment (Point a, Point b) {
            return Math.abs(this.distance(a) + this.distance(b) - a.distance(b)) < 1e-7;
        }
        boolean between(Point p, Point q)
        {
            return x < Math.max(p.x, q.x) + EPS && x + EPS > Math.min(p.x, q.x)
                    && y < Math.max(p.y, q.y) + EPS && y + EPS > Math.min(p.y, q.y);
        }
    }
    static class LineSegment {
        Point p, q;

        LineSegment(Point a, Point b) { p = a; q = b; }

        boolean intersect(LineSegment ls)
        {
            Line l1 = new Line(p, q), l2 = new Line(ls.p, ls.q);
            if(l1.parallel(l2))
            {
                if(l1.same(l2))
                    return p.between(ls.p, ls.q) || q.between(ls.p, ls.q) || ls.p.between(p, q) || ls.q.between(p, q);
                return false;
            }
            Point c = l1.intersect(l2);
            return c.between(p, q) && c.between(ls.p, ls.q);
        }

    }

    static class Line {
        double a, b, c;
        static final double INF = 1e9, EPS = 1e-9;

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

    public String testPoint(String[] vertices, int testPointX, int testPointY) {
        for (int i = 0; i < vertices.length; i++) {
            StringTokenizer st = new StringTokenizer(vertices[i]);
            Point first = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())), second = null;
            if (i == vertices.length - 1) {
                st = new StringTokenizer(vertices[0]);
                second = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            } else {
                st = new StringTokenizer(vertices[i + 1]);
                second = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            }
            if (new Point(testPointX, testPointY).onSegment(first, second)) return "BOUNDARY";
        }

        int cnt = 0;
        for (int i = 0; i < 6; i++) {
            Point other = new Point((int)(Math.random() * 1000000) + 1e6, (int)(Math.random() * 1000000) + 1e6);
            LineSegment lineSegment = new LineSegment(new Point(testPointX, testPointY), other);
            int cntIntersect = 0;
            for (int j = 0; j < vertices.length; j++) {
                StringTokenizer st = new StringTokenizer(vertices[j]);
                Point first = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())), second = null;
                if (j == vertices.length - 1) {
                    st = new StringTokenizer(vertices[0]);
                    second = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
                } else {
                    st = new StringTokenizer(vertices[j + 1]);
                    second = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
                }
                if (lineSegment.intersect(new LineSegment(first, second))) cntIntersect++;
            }
            if (cntIntersect % 2 == 1) cnt++;
        }
        if (cnt>= 4) return "INTERIOR";
        else return "EXTERIOR";
    }
}
