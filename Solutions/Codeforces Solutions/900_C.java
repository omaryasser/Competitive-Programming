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
            int n = sc.nextInt();
            int[] p = new int[n];
            FenwickTree ft = new FenwickTree(n + 2);
            boolean[] can = new boolean[n];
            boolean[] already = new boolean[n];
            int best = 0;
            int[] pnt = new int[n + 1];
            int max = 0;
            int res = 1;
            int was = 0;
            for (int i = 0; i < n; i++) {
                p[i] = sc.nextInt();
                int biggerBef = ft.rsq(p[i], n);
                if (biggerBef > 1) can[i] = false;
                else {
                    if (max > p[i]) pnt[max]++;
                    can[i] = true;
                }
                already[i] = biggerBef == 0;
                best += already[i] ? 1 : 0;
                was += already[i] ? 1 : 0;
                ft.point_update(p[i], 1);
                max = Math.max(max, p[i]);
            }
            best = 0;
            for (int i = 0; i < n; i++) {
                int here = was - (already[i] ? 1 : 0) + pnt[p[i]];
                if (here > best) res = p[i];
                else if (here == best) res = Math.min(res, p[i]);
                best = Math.max(best, here);
            }
            out.println(res);
        }

        public class FenwickTree {
            int n;
            int[] ft;

            FenwickTree(int size) {
                n = size;
                ft = new int[n + 1];
            }

            int rsq(int b) {
                int sum = 0;
                while (b > 0) {
                    sum += ft[b];
                    b -= b & -b;
                } //min?
                return sum;
            }

            int rsq(int a, int b) {
                return rsq(b) - rsq(a - 1);
            }

            void point_update(int k, int val) {
                while (k <= n) {
                    ft[k] += val;
                    k += k & -k;
                } //min?
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

