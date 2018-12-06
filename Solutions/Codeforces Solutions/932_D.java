import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
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
        TaskD solver = new TaskD();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskD {
        public void solve(int testNumber, Scanner sc, PrintWriter out) {
            int q = sc.nextInt();
            long last = 0;
            int MAX = 400001;
            int LOG = (int) ((Math.log(MAX) / Math.log(2)) + 3);
            int P[][] = new int[LOG][MAX];
            int normalParent[] = new int[MAX];
            Arrays.fill(normalParent, -1);
            int firstBiggerOrEqual[] = new int[MAX];
            Arrays.fill(firstBiggerOrEqual, -1);
            long sum[][] = new long[LOG][MAX];
            long[] weight = new long[MAX];
            int[] power = new int[LOG];
            int[] lvl = new int[MAX];
            power[0] = 1;
            for (int i = 1; i < LOG; i++)
                power[i] = 2 * power[i - 1];
            for (int[] PP : P) Arrays.fill(PP, -1);
            int cnt = 0;
            while (q-- > 0) {
                int op = sc.nextInt();
                int node = (int) ((last ^ sc.nextLong()) - 1);
                if (op == 1) {
                    long W = (last ^ sc.nextLong());
                    int cur = ++cnt;
                    weight[cur] = W;
                    normalParent[cur] = node;
                    int parent = node;
                    while (true) {
                        if (parent == -1) break;
                        if (weight[parent] >= W) {
                            firstBiggerOrEqual[cur] = parent;
                            break;
                        } else
                            parent = firstBiggerOrEqual[parent];
                    }
                    P[0][cur] = firstBiggerOrEqual[cur];
                    lvl[cur] = firstBiggerOrEqual[cur] == -1 ? 0 : lvl[firstBiggerOrEqual[cur]] + 1;
                    for (int log = 1; log < LOG; log++)
                        if (P[log - 1][cur] != -1)
                            P[log][cur] = P[log - 1][P[log - 1][cur]];
                    sum[0][cur] = weight[cur];
                    for (int log = 1; log < LOG; log++) {
                        if (P[log - 1][cur] != -1)
                            sum[log][cur] = sum[log - 1][cur] + sum[log - 1][P[log - 1][cur]];
                    }
                } else {
                    long L = (last ^ sc.nextLong());
                    int max = 0;
                    for (int log = LOG - 1; log >= 0; log--) {
                        if (node != -1 && (lvl[node] - (1 << log)) >= -1 && sum[log][node] <= L) {
                            max += power[log];
                            L -= sum[log][node];
                            node = P[log][node];
                        }
                    }

                    out.println(last = max);
                }
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

