import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.InputStream;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 *
 * @author OmarYasser
 */
public class Main {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        Scanner in = new Scanner(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        TaskD solver = new TaskD();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskD {
        static int n;
        static long[][][] distances;
        static long[][][] sum;
        static int[] p;
        static long[] cost;
        static int[] LEN;

        static long[] getSum(long[] here) {
            long[] s = new long[here.length];
            if (here.length == 0) return s;
            long last;
            s[0] = last = here[0];
            for (int i = 1, len = here.length; i < len; i++) {
                s[i] = last = (last + here[i]);
            }
            return s;
        }

        static long[] dfs(int node, int parent) {
            if (node >= n) return new long[]{};
            cost[node] = node == 0 ? 0 : LEN[node - 1];
            p[node] = parent;
            long[] left = dfs(node * 2 + 1, node), right = dfs(node * 2 + 2, node);
            long[] here = new long[left.length + right.length + 1];
            here[0] = cost[node];
            for (int i = 0, j = 0, idx = 1, lenL = left.length, lenR = right.length; i < lenL || j < lenR; ) {
                if (i == lenL)
                    here[idx++] = right[j++] + cost[node];
                else if (j == lenR)
                    here[idx++] = left[i++] + cost[node];
                else if (left[i] < right[j])
                    here[idx++] = left[i++] + cost[node];
                else
                    here[idx++] = right[j++] + cost[node];
            }
            distances[0][node] = left;
            distances[1][node] = right;
            sum[0][node] = getSum(left);
            sum[1][node] = getSum(right);
            return here;
        }

        public void solve(int testNumber, Scanner sc, PrintWriter out) {
            n = sc.nextInt();
            int m = sc.nextInt();
            distances = new long[2][n][];
            sum = new long[2][n][];
            cost = new long[n];
            p = new int[n];
            LEN = new int[n];
            for (int i = 0; i < n - 1; i++)
                LEN[i] = sc.nextInt();
            dfs(0, -1);
            while (m-- > 0) {
                int node = sc.nextInt() - 1, h = sc.nextInt();
                long res = 0;
                int was = node;
                int parity = 0;
                while (node != -1 && h >= 0) {
                    if (node == was) {
                        int idx = BS(node, h, 0);
                        if (idx != -1) {
                            res += (1L * h * (idx + 1) - sum[0][node][idx]);
                        }
                        idx = BS(node, h, 1);
                        if (idx != -1) {
                            res += (1L * h * (idx + 1) - sum[1][node][idx]);
                        }
                    } else {
                        int idx = BS(node, h, parity);
                        if (idx != -1) {
                            res += (1L * h * (idx + 1) - sum[parity][node][idx]);
                        }
                    }
                    res += h;
                    parity = (node & 1);
                    h -= cost[node];
                    node = p[node];
                }
                out.println(res);
            }
        }

        static int BS(int node, int h, int p) {
            long cur[] = distances[p][node];
            int lo = 0, hi = Math.max(0, cur.length - 1), best = -1;
            if (cur.length == 0) return best;
            while (lo <= hi) {
                int mid = (lo + hi) >> 1;
                if (cur[mid] <= h) {
                    best = mid;
                    lo = mid + 1;
                } else
                    hi = mid - 1;
            }
            return best;
        }

    }

    static class Scanner {
        StringTokenizer st;
        BufferedReader br;

        public Scanner(InputStream s) {
            br = new BufferedReader(new InputStreamReader(s));
        }

        public Scanner(FileReader f) {
            br = new BufferedReader(f);
        }

        public String next() {
            while (st == null || !st.hasMoreTokens())
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (Exception e) {
                }
            return st.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

    }
}

