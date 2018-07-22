package codes;

import java.util.Arrays;

public class TVTower {
    static class Point {
        double x, y;
        Point(double a, double b) { x = a; y = b; }
        Point rotate(double theta, Point p) {
            Vector v = new Vector(p, new Point(0, 0));
            return translate(v).rotate(theta).translate(v.reverse());
        }
        Point rotate(double angle) {
            double c = Math.cos(angle), s = Math.sin(angle);
            return new Point(x * c - y * s, x * s + y * c);
        }
        public double dist(Point p) { return Math.sqrt(sq(x - p.x) + sq(y - p.y)); }
        static double sq(double x) { return x * x; }
        Point translate(Vector v) { return new Point(x + v.x , y + v.y); }
    }
    static class Vector {
        double x, y;
        Vector(double a, double b) { x = a; y = b; }
        Vector(Point a, Point b) { this(b.x - a.x, b.y - a.y); }
        Vector reverse() { return new Vector(-x, -y);}
        Vector scale(double s) { return new Vector(x * s, y * s); }
        double norm2() { return x * x + y * y; }
        Vector normalize()
        {
            double d = Math.sqrt(norm2());
            return scale(1 / d);
        }
    }
    static boolean okk = false;
    static class Circle {
        static final double EPS = 1e-9;
        Point c;
        double r;
        Circle(Point p, double k) {
            c = p;
            r = k;
        }
        Circle (Point cc, Point a, Point b) { // lineSegment1 (cc=>a), lineSegment2 (cc=>b)
            Point midCA = cc.translate(new Vector(cc, a).normalize().scale(cc.dist(a) / 2));
            Point midCB = cc.translate(new Vector(cc, b).normalize().scale(cc.dist(b) / 2));
            Line perpendicularCA = new Line(midCA, cc.rotate(degToRad(90), midCA));
            Line perpendicularCB = new Line(midCB, cc.rotate(degToRad(90), midCB));
            c = perpendicularCA.intersect(perpendicularCB);
            if (c == null) {
                c = new Point(0, 0);
                r = 1e9;
            }
            else r = c.dist(cc);
        }
        int inside(Point p) {
            double d = p.dist(c);
            return d + EPS < r ? 1 : Math.abs(d - r) < EPS ? 0 : -1;
        }
    }

    static double degToRad (double deg) {return deg * Math.PI / 180;}
    static class Line {
        static final double INF = 1e9, EPS = 1e-9;
        double a, b, c;
        Line(Point p, Point q) {
            if (Math.abs(p.x - q.x) < EPS) {
                a = 1;
                b = 0;
                c = -p.x;
            } else {
                a = -(p.y - q.y) / (p.x - q.x);
                b = 1.0;
                c = -(a * p.x + p.y);
            }
        }
        boolean parallel(Line l) { return Math.abs(a - l.a) < EPS && Math.abs(b - l.b) < EPS; }
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
    public double minRadius(int[] x, int[] y) {

        int n = x.length;
        if (n == 1) return 0;
        else if (n == 2) return new Point(x[0], y[0]).dist(new Point(x[1], y[1])) / 2.0;
        double min = Double.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    if (i != j && j != k && i != k) {
                        if (i == 1 && j == 2 && k == 0) {
                            okk = true;
                        } else okk = false;
                        Circle c = new Circle (new Point(x[i], y[i]), new Point(x[j], y[j]), new Point(x[k], y[k]));
                        boolean ok = true;
                        for (int f = 0; f < n; f++) {
                            ok &= c.inside(new Point(x[f], y[f])) != -1;
                        }
                        if (ok) min = Math.min(min, c.r);
                    }
                }
            }
        }
        for (int i = 0; i < n; i++) for (int j = i + 1; j < n; j++) {
            Point a = new Point(x[i], y[i]), b = new Point(x[j], y[j]);
            Point mid = new Point((a.x + b.x) / 2, (a.y + b.y) / 2);
            Circle c = new Circle(mid, mid.dist(a));
            boolean ok = true;
            for (int k = 0; k < n; k++) {
                ok &= c.inside(new Point(x[k], y[k])) != -1;
            }
            if (ok) min = Math.min(min, c.r);
        }
        return min;
    }
}
