import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
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
        TaskB solver = new TaskB();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskB {
        static int n;
        static ArrayList<Integer>[] adjList;
        static int cnt = 0;
        static int[] c;

        public void solve(int testNumber, Scanner sc, PrintWriter out) {
            n = sc.nextInt();
            adjList = new ArrayList[n];
            c = new int[n];
            for (int i = 0; i < n; i++) adjList[i] = new ArrayList<>();
            for (int i = 0; i < n - 1; i++) {
                int p = sc.nextInt() - 1;
                adjList[i + 1].add(p);
                adjList[p].add(i + 1);
            }
            for (int i = 0; i < n; i++) c[i] = sc.nextInt();
            dfs(0, -1, 0);
            out.println(cnt);
        }

        static void dfs(int node, int p, int col) {
            if (c[node] != col) {
                cnt++;
            }
            for (int nxt : adjList[node])
                if (nxt != p)
                    dfs(nxt, node, c[node]);
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