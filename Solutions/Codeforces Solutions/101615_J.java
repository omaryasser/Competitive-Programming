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
        TaskA solver = new TaskA();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskA {
        int n;
        ArrayList<Integer>[] adjList;
        int[] lvl;

        void dfs(int node, int parent, int L) {
            lvl[L]++;
            for (int nxt : adjList[node])
                if (nxt != parent)
                    dfs(nxt, node, L + 1);
        }

        public void solve(int testNumber, Scanner sc, PrintWriter out) {
            n = sc.nextInt();
            adjList = new ArrayList[n];
            lvl = new int[n + 1];
            for (int i = 0; i < n; i++)
                adjList[i] = new ArrayList<>();
            for (int i = 0; i < n - 1; i++) {
                adjList[sc.nextInt() - 1].add(i + 1);
            }
            dfs(0, -1, 0);
            int cnt = 0;
            for (int num : lvl)
                cnt += num % 2;
            out.println(cnt);
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

