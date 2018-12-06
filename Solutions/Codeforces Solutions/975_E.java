import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;

/**
 * Created by omar on 07/04/18.
 */
public class A {

    public static void main(String[] args) throws Exception {
//        System.out.println(+Math.toDegrees(-Math.atan2(-2,-5)));
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        int n = sc.nextInt(), q = sc.nextInt();
        Point[] points = new Point[n + 1];
        for (int i = 0; i < n; i++)
            points[i] = new Point(sc.nextInt(), sc.nextInt());
        points[n] = new Point(points[0].x, points[0].y);
        double x1 = points[0].x, x2 = points[0].x, y1 = points[0].y, y2 = points[0].y;
        for (Point p : points) {
            x1 = Math.min(x1, p.x);
            x2 = Math.max(x2, p.x);
            y1 = Math.min(y1, p.y);
            y2 = Math.max(y2, p.y);
        }
        double sx = (x1 + x2) / 2;
        double sy = (y1 + y2) / 2;
        for (Point p : points) {
            p.x -= sx;
            p.y -= sy;
        }

        double cx = 0, cy = 0;
        double a = 0;
        for (int i = 0; i < n; i++) {
            double r = points[i].x * points[i + 1].y - points[i + 1].x * points[i].y;
            a += r;
        }
        a /= 2.0;
        for (int i = 0; i < n; i++) {
            double r = (points[i].x * points[i + 1].y - points[i + 1].x * points[i].y) / 6.0 / a;
            cx += (points[i].x + points[i + 1].x) * r;
            cy += (points[i].y + points[i + 1].y) * r;
        }
        for (int i = 0; i < n; i++) {
            points[i].x -= cx;
            points[i].y -= cy;
        }

        Point centroid = new Point(0, 0);

        sx += cx;
        sy += cy;

        double T[][] = {{1, 0, 0},
                {0, 1, 0},
                {0, 0, 1}};

//        System.err.println(sx + " " + sy);
        int first = 0, second = 1;
        while (q-- > 0) {
            int op = sc.nextInt();
            if (op == 1) {
                int from = sc.nextInt() - 1, to = sc.nextInt() - 1;
                if (from != first) {
                    second = first;
                }
                Point p2 = vec_mult(T, centroid);
                Point p1 = vec_mult(T, points[second]);
                double angle = -Math.atan2(p2.x - p1.x, -(p2.y - p1.y));

                T = multiply(new double[][]{{1, 0, -p1.x},
                        {0, 1, -p1.y},
                        {0, 0, 1}}, T);

                double s = Math.sin(angle), c = Math.cos(angle);
                T = multiply(new double[][]{
                        {c, -s, 0},
                        {s, c, 0},
                        {0, 0, 1}}, T);
                T = multiply(new double[][]{{1, 0, p1.x},
                        {0, 1, p1.y},
                        {0, 0, 1}}, T);

//                System.err.println(Arrays.deepToString(T));
                first = to;

            } else {
                int wanted = sc.nextInt() - 1;
                Point pp = vec_mult(T, points[wanted]);
                out.printf("%.15f %.15f\n", pp.x + sx, pp.y + sy);
            }
            if (q % 100 == 0) {

                for (int i = 0; i < points.length; i++) {
                    points[i] = vec_mult(T, points[i]);

                }
                centroid = vec_mult(T, centroid);
                for (int i = 0; i < points.length; i++) {
                    points[i].x -= centroid.x;
                    points[i].y -= centroid.y;
                }
                sx += centroid.x;
                sy += centroid.y;
                centroid = new Point(0, 0);
                T = new double[][]{
                        {1, 0, 0},
                        {0, 1, 0},
                        {0, 0, 1}
                };
            }
        }
        out.flush();
    }

    public static Point vec_mult(double[][] A, Point p) {
        double B[] = {p.x, p.y, 1};
        double[] C = new double[A.length];
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B.length; j++) {
                C[i] = (C[i] + A[i][j] * B[j]);
            }
        }
        return new Point(C[0], C[1]);
    }

    static Point transform(Point w, double[][] T) {
        double[][] r = multiply(T, new double[][]{{w.x, 0, 0},
                {w.y, 0, 0},
                {1, 0, 0}});
        return new Point(r[0][0], r[1][0]);
    }

    static boolean ccw(Point p, Point q, Point r) {
        return new Vector(p, q).cross(new Vector(p, r)) > 0;
    }

    static class Vector {

        double x, y;

        Vector(double a, double b) {
            x = a;
            y = b;
        }

        Vector(Point a, Point b) {
            this(b.x - a.x, b.y - a.y);
        }

        Vector scale(double s) {
            return new Vector(x * s, y * s);
        }              //s is a non-negative value

        double dot(Vector v) {
            return (x * v.x + y * v.y);
        }

        double cross(Vector v) {
            return x * v.y - y * v.x;
        }

        double norm2() {
            return x * x + y * y;
        }

        Vector reverse() {
            return new Vector(-x, -y);
        }

        Vector normalize() {
            double d = Math.sqrt(norm2());
            return scale(1 / d);
        }
    }

    static double angle(Point a, Point o, Point b)   // angle AOB
    {
        Vector oa = new Vector(o, a), ob = new Vector(o, b);
        return Math.acos(oa.dot(ob) / Math.sqrt(oa.norm2() * ob.norm2()));
    }

    /* Matrices Basic Operations */
    static double[][] zero(int n, int m) {
        return new double[n][m];
    }

    static double[][] identity(int n) { // Always square matrix
        double[][] x = zero(n, n);
        for (int i = 0; i < x.length; i++)
            x[i][i]++;
        return x;
    }


    static double[][] add(double[][] a, double[][] b) {
        double[][] ret = zero(a.length, a[0].length);
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                ret[i][j] = a[i][j] + b[i][j];
            }
        }
        return ret;
    }

    static double[][] addIdentity(double[][] a) {
        for (int i = 0; i < a.length; i++) {
            a[i][i]++;
        }
        return a;
    }

    static double[][] multiply(double[][] a, double[][] b) {
        double[][] ret = zero(a.length, b[0].length);
        // Optimizations here not to enter the last loop of a[i][k] = 0
        // Also we can swtich last two loops if b[k][j] has more zeros

        //for MOD optimization we use the following
        for (int i = 0; i < a.length; ++i) {
            for (int k = 0; k < a[0].length; ++k) {

                for (int j = 0; j < b[0].length; ++j) {
                    ret[i][j] += a[i][k] * b[k][j];
                }
            }

        }
        return ret;
    }


    // XY-plane operation using Matices

    	/*
        Rotation clockwise for point (x,y)

    	[x_,y_] = [cos(theta) sin(theta)]  * [x,y]
    	          [-sin(theta) cos(theta)]

    	Rotation counterclockwise

    	[x_,y_] = [cos(theta) -sin(theta)]  * [x,y]
    	          [sin(theta) cos(theta)]

    	*/

    static class Polygon {
        // Cases to handle: collinear points, polygons with n < 3

        static final double EPS = 1e-9;

        Point[] g;            //first point = last point, counter-clockwise representation

        Polygon(Point[] o) {
            g = o;
        }

        double area()        //clockwise/anti-clockwise check, for convex/concave polygons
        {
            double area = 0.0;
            for (int i = 0; i < g.length - 1; ++i)
                area += g[i].x * g[i + 1].y - g[i].y * g[i + 1].x;
            return area / 2.0;            //negative value in case of clockwise
        }


        Point centroid()        //center of mass
        {
            double cx = 0.0, cy = 0.0;
            for (int i = 0; i < g.length - 1; i++) {
                double x1 = g[i].x, y1 = g[i].y;
                double x2 = g[i + 1].x, y2 = g[i + 1].y;

                double f = x1 * y2 - x2 * y1;
                cx += (x1 + x2) * f;
                cy += (y1 + y2) * f;
            }
            double area = area();        //remove abs
            cx /= 6.0 * area;
            cy /= 6.0 * area;
            return new Point(cx, cy);
        }
    }

    static class Point {
        double x, y;

        Point(double xx, double yy) {
            x = xx;
            y = yy;
        }

        Point rotate(double angle) {
            double c = Math.cos(angle), s = Math.sin(angle);
            return new Point(x * c - y * s, x * s + y * c);
        }

        Point rotate(double theta, Point p)    //rotate around p
        {
            Vector v = new Vector(p, new Point(0, 0));
            return new Point(x, y).translate(v).rotate(theta).translate(v.reverse());
        }

        Point translate(Vector v) {
            return new Point(x + v.x, y + v.y);
        }
    }

    static class Scanner {
        private InputStream stream;
        private byte[] buf = new byte[1 << 16];
        private int curChar;
        private int numChars;

        public Scanner(InputStream stream) {
            this.stream = stream;
        }

        public int read() {
            if (this.numChars == -1) {
                throw new InputMismatchException();
            } else {
                if (this.curChar >= this.numChars) {
                    this.curChar = 0;

                    try {
                        this.numChars = this.stream.read(this.buf);
                    } catch (IOException var2) {
                        throw new InputMismatchException();
                    }

                    if (this.numChars <= 0) {
                        return -1;
                    }
                }

                return this.buf[this.curChar++];
            }
        }

        public int nextInt() {
            int c;
            for (c = this.read(); isSpaceChar(c); c = this.read()) {
                ;
            }

            byte sgn = 1;
            if (c == 45) {
                sgn = -1;
                c = this.read();
            }

            int res = 0;

            while (c >= 48 && c <= 57) {
                res *= 10;
                res += c - 48;
                c = this.read();
                if (isSpaceChar(c)) {
                    return res * sgn;
                }
            }

            throw new InputMismatchException();
        }

        public static boolean isSpaceChar(int c) {
            return c == 32 || c == 10 || c == 13 || c == 9 || c == -1;
        }

    }
}