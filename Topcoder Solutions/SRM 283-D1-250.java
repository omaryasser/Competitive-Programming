/**
 * Created by omar on 01/08/17.
 */
public class PowerSupply {
    double degToRad(double d) { return d * Math.PI / 180.0; }
    Point rotate(Point p, double angle) {
        angle = degToRad(angle);
        double c = Math.cos(angle), s = Math.sin(angle);
        return new Point(p.x * c - p.y * s, p.x * s + p.y * c);
    }
    Point[] rotate (double angle, Point[] p) {
        Point[] res = new Point[p.length];
        for (int i = 0; i < p.length; i++)
            res[i] = rotate(p[i], angle);
        return res;
    }

    double EPS = 1e-9;

    int solve (Point[] p, int D) {
        D <<= 1;
        int n = p.length;
        int res = 0;
        for (int i = 0; i < n; i++) {
            int resRight = 0, resLeft = 0, resTop = 0, resBot = 0;
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    double dist = p[i].x - p[j].x;
                    if (dist + EPS >= 0 && dist <= D + EPS) resLeft++;
                    else if (-dist + EPS >= 0 && -dist <= D + EPS) resRight++;

                    double dist2 = p[i].y - p[j].y;
                    if (dist2 + EPS >= 0 && dist2 <= D + EPS) resTop++;
                    else if (-dist2 + EPS >= 0 && -dist2 <= D + EPS) resBot++;
                }
            }
            res = Math.max(res, 1 + Math.max(resLeft, Math.max(resRight, Math.max(resTop, resBot))));
        }
        return res;
    }
    public int maxProfit(int[] x, int[] y, int D) {
        int n = x.length;
        Point[] p = new Point[n];
        for (int i = 0; i < n; i++)
            p[i] = new Point(x[i], y[i]);

        int res = solve (p, D);
        res = Math.max(res, solve(rotate(45, p), D));
        return Math.max(res, solve(rotate(-45, p), D));
    }
    class Point {
        double x, y;
        Point (double xx, double yy) {
            x = xx;
            y = yy;
        }
    }
}
