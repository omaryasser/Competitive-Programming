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
        TaskF solver = new TaskF();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskF {
        static int[] memo;

        static int d(int n) {
            return memo[n];
        }

        public void solve(int testNumber, Scanner sc, PrintWriter out) {
            memo = new int[(int) 1e6 + 2];
            for (int i = 1; i < (int) 1e6 + 2; i++)
                for (int j = i; j < (int) 1e6 + 2; j += i)
                    memo[j]++;
            int n = sc.nextInt(), m = sc.nextInt();
            int[] a = new int[n];
            for (int i = 0; i < n; i++) a[i] = sc.nextInt();
            TaskF.Seg seg = new TaskF.Seg(a);
            while (m-- > 0) {
                int t = sc.nextInt(), l = sc.nextInt() - 1, r = sc.nextInt() - 1;
                if (t == 1) seg.update(1, 0, n - 1, l, r);
                else out.println(seg.query(1, 0, n - 1, l, r));
            }
        }

        static class Seg {
            int[] a;
            long[] tree;
            boolean[] finished;
            int n;

            Seg(int[] aa) {
                a = aa;
                n = a.length;
                tree = new long[n << 2];
                finished = new boolean[n << 2];
                build(1, 0, n - 1);
            }

            void build(int node, int s, int e) {
                if (s == e) {
                    tree[node] = a[s];
                    finished[node] = a[s] <= 2;
                } else {
                    int mid = s + e >> 1;
                    build(node << 1, s, mid);
                    build(node << 1 | 1, mid + 1, e);
                    tree[node] = tree[node << 1] + tree[node << 1 | 1];
                    finished[node] = finished[node << 1] && finished[node << 1 | 1];
                }
            }

            void update(int node, int s, int e, int l, int r) {
                if (s > r || e < l) return;
                if (finished[node]) return;
                if (s == e) {
                    finished[node] = (tree[node] = d((int) tree[node])) <= 2;
                } else {
                    int mid = s + e >> 1;
                    update(node << 1, s, mid, l, r);
                    update(node << 1 | 1, mid + 1, e, l, r);
                    tree[node] = tree[node << 1] + tree[node << 1 | 1];
                    finished[node] = finished[node << 1] && finished[node << 1 | 1];
                }
            }

            long query(int node, int s, int e, int l, int r) {
                if (s > r || e < l) return 0;
                if (s >= l && e <= r) return tree[node];
                int mid = s + e >> 1;
                return query(node << 1, s, mid, l, r) + query(node << 1 | 1, mid + 1, e, l, r);
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

