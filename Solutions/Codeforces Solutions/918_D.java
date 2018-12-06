import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
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
        static int n;
        static int m;
        static ArrayList<TaskD.Edge>[] adjList;
        static Boolean[][][] memo;

        static boolean dp(int f, int s, int m) {
            if (memo[f][s][m] != null) return memo[f][s][m];
            boolean can = false;
            for (TaskD.Edge nxt : adjList[f]) if (nxt.c >= m) can = true;
            if (!can) return memo[f][s][m] = false;
            boolean way = false;
            for (TaskD.Edge nxt : adjList[f]) if (nxt.c >= m) way |= !dp(s, nxt.to, nxt.c);
            return memo[f][s][m] = way ? true : false;
        }

        public void solve(int testNumber, Scanner sc, PrintWriter out) {
            n = sc.nextInt();
            m = sc.nextInt();
            adjList = new ArrayList[n];
            memo = new Boolean[n][n][30];
            for (int i = 0; i < n; i++) adjList[i] = new ArrayList<>();
            while (m-- > 0) {
                adjList[sc.nextInt() - 1].add(new TaskD.Edge(sc.nextInt() - 1, sc.next().charAt(0) - 'a'));
            }
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    out.print(dp(i, j, 0) ? "A" : "B");
                }
                out.println();
            }
        }

        static class Edge {
            int to;
            int c;

            Edge(int t, int cc) {
                to = t;
                c = cc;
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

