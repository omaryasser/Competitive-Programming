import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 */
public class Main {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        Scanner in = new Scanner(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        TaskC solver = new TaskC();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskC {
        public void solve(int testNumber, Scanner sc, PrintWriter out) {
            long r = sc.nextInt(), x1 = sc.nextInt(), y1 = sc.nextInt(), x2 = sc.nextInt(), y2 = sc.nextInt();
            if ((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2) >= r * r)
                out.printf("%.10f %.10f %.10f\n", (double) x1, (double) y1, (double) r);
            else {
                double d = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
                d += r;
                double newR = d / 2.0;
                Vector v1 = new Vector(new Point(x2, y2), new Point(x1, y1));
                if (x2 == x1 && y2 == y1)
                    v1 = new Vector(new Point(x1, y1), new Point(x1 + r, y1));
                v1 = v1.normalize();
                v1 = v1.scale((2 * r - d) / 2.0);
                Point mid = new Point(x1, y1).translate(v1);
                out.printf("%.10f %.10f %.10f\n", mid.x, mid.y, newR);
            }
        }

        public class Vector {
            double x;
            double y;

            Vector(double a, double b) {
                x = a;
                y = b;
            }

            Vector(Point a, Point b) {
                this(b.x - a.x, b.y - a.y);
            }

            Vector scale(double s) {
                return new Vector(x * s, y * s);
            }

            double norm2() {
                return x * x + y * y;
            }

            Vector normalize() {
                double d = Math.sqrt(norm2());
                return scale(1 / d);
            }

        }

        public class Point implements Comparable<Point> {
            static final double EPS = 1e-9;
            double x;
            double y;

            Point(double a, double b) {
                x = a;
                y = b;
            }

            public int compareTo(Point p) {
                if (Math.abs(x - p.x) > EPS) return x > p.x ? 1 : -1;
                if (Math.abs(y - p.y) > EPS) return y > p.y ? 1 : -1;
                return 0;
            }

            Point translate(Vector v) {
                return new Point(x + v.x, y + v.y);
            }

        }

    }

    static class Scanner {
        StringTokenizer st;
        BufferedReader br;

        public Scanner(InputStream s) {
            br = new BufferedReader(new InputStreamReader(s));
        }

        public String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (Exception e) {
                }
                ;
            }
            return st.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

    }
}

