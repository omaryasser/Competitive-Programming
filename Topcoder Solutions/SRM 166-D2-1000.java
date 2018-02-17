package codes;

public class ConvexPolygon {
    class Point {
        long x, y;
        Point (long xx, long yy) {
            x = xx;
            y = yy;
        }
    }
    Point [] g;
    double area()
    {
        long area = 0;
        for(int i = 0; i < g.length - 1; ++i)
            area += g[i].x * g[i+1].y - g[i].y * g[i+1].x;
        return Math.abs(area) / 2.0;
    }
    public double findArea(int[] x, int[] y) {
        g = new Point[x.length + 1];
        for (int i = 0; i < x.length; i++)
            g[i] = new Point(x[i], y[i]);
        g[x.length] = new Point(x[0], y[0]);
        return area();
    }
}
