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
        TaskE solver = new TaskE();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskE {
        public void solve(int testNumber, Scanner sc, PrintWriter out) {

            int n = sc.nextInt();
            long[] a = new long[n + 2];
            long sum[] = new long[n + 2];
            long cum[] = new long[n + 2];
            for (int i = 1; i <= n; i++) {
                sum[i] += (a[i] = sc.nextLong()) * i;
                sum[i] += sum[i - 1];
                cum[i] = a[i] + cum[i - 1];
            }

            ConvexHullOptimization1 convexHull = new ConvexHullOptimization1(n + 2);
            long best = sum[n];
            for (int i = 1; i <= n; i++) {
                if (i > 1) {
                    long here = sum[n] - a[i] * i + cum[i - 1] + -convexHull.queryBS(a[i]);
                    best = Math.max(best, here);
                }
                convexHull.addLine(-i, cum[i - 1]);
            }
            convexHull = new ConvexHullOptimization1(n + 2);
            for (int i = n; i >= 1; i--) {
                if (i < n) {
                    long here = sum[n] - a[i] * i + cum[i] + -convexHull.queryBS2(a[i]);
                    best = Math.max(best, here);
                }
                convexHull.addLine2(-i, cum[i]);
            }
            out.println(best);
        }

        public class ConvexHullOptimization1 {
            long[] A;
            long[] B;
            long[] starts;
            long[] ends;
            int len;
            long INF = (long) 1e10;

            ConvexHullOptimization1(int n) {
                A = new long[n];
                B = new long[n];
                starts = new long[n];
                ends = new long[n];
            }

            void addLine(long a, long b) {
                while (len >= 2 &&
                        (A[len - 1] - A[len - 2]) * (B[len - 1] - b) <= (A[len - 1] - a) * (B[len - 1] - B[len - 2]))
                    --len;
                A[len] = a;
                B[len] = b;
                if (len == 0) starts[len] = -INF;
                else {
                    starts[len] = (B[len] - B[len - 1] + A[len - 1] - A[len] - 1) / (A[len - 1] - A[len]);
                }
                ++len;
            }

            void addLine2(long a, long b) {
                while (len >= 2 &&
                        (A[len - 1] - A[len - 2]) * (B[len - 1] - b) >= (A[len - 1] - a) * (B[len - 1] - B[len - 2]))
                    --len;
                A[len] = a;
                B[len] = b;
                if (len == 0) ends[0] = INF;
                else {
                    ends[len] = (B[len] - B[len - 1]) / (A[len - 1] - A[len]);
                }
                ++len;
            }

            long queryBS(long x) {
                int ans = 0;
                int lo = 1, hi = len - 1;
                while (lo <= hi) {
                    int mid = lo + hi >> 1;
                    if (starts[mid] <= x) {
                        ans = mid;
                        lo = mid + 1;
                    } else
                        hi = mid - 1;
                }
                return x * A[ans] + B[ans];
            }

            long queryBS2(long x) {
                int ans = 0;
                int lo = 1, hi = len - 1;
                while (lo <= hi) {
                    int mid = lo + hi >> 1;
                    if (ends[mid] >= x) {
                        ans = mid;
                        lo = mid + 1;
                    } else
                        hi = mid - 1;
                }
                return x * A[ans] + B[ans];
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

        public long nextLong() {
            return Long.parseLong(next());
        }

    }
}

