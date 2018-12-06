import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collection;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.Vector;
import java.util.StringTokenizer;
import java.util.Queue;
import java.io.BufferedReader;
import java.util.LinkedList;
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
        int n;
        int m;
        int[][] adjList;
        int[] state;
        int VISITED = 1;
        int UNVISITED = 0;
        int EXPLORED = 2;
        boolean cycleExist;

        void dfs(int node) {
            state[node] = EXPLORED;
            for (int nxt : adjList[node])
                if (state[nxt] == EXPLORED)
                    cycleExist = true;
                else if (state[nxt] == UNVISITED)
                    dfs(nxt);
            state[node] = VISITED;
        }

        public void solve(int testNumber, Scanner sc, PrintWriter out) {
            n = sc.nextInt();
            m = sc.nextInt();
            state = new int[n];
            int[] to = new int[m], from = new int[m];
            boolean[] dead = new boolean[n];
            Arrays.fill(dead, true);
            for (int i = 0, idx = 0; i < n; i++) {
                int c = sc.nextInt();
                while (c-- > 0) {
                    to[idx] = sc.nextInt() - 1;
                    from[idx] = i;
                    dead[from[idx++]] = false;
                }
            }
            PACK(to, from);
            boolean[][] visited = new boolean[n][2];
            int[][] p = new int[n][2];
            Queue<Integer> q = new LinkedList<>();
            int start = sc.nextInt() - 1;
            q.add(start);
            q.add(0);
            visited[start][0] = true;
            int last = -1;
            while (!q.isEmpty()) {
                int node = q.poll(), parity = q.poll();
                if (parity == 1 && dead[node]) {
                    last = node;
                    break;
                }
                for (int nxt : adjList[node])
                    if (!visited[nxt][1 ^ parity]) {
                        visited[nxt][1 ^ parity] = true;
                        p[nxt][1 ^ parity] = node;
                        q.add(nxt);
                        q.add(1 ^ parity);
                    }
            }
            if (last != -1) {
                out.println("Win");
                int parity = 1;
                Stack<Integer> s = new Stack<>();
                while (last != start || parity != 0) {
                    s.add(last);
                    last = p[last][parity];
                    parity ^= 1;
                }
                s.add(last);
                out.print(s.pop() + 1);
                while (!s.isEmpty())
                    out.print(" " + (s.pop() + 1));
                out.println();
            } else {
                dfs(start);

                if (cycleExist) {
                    out.println("Draw");
                } else {
                    out.println("Lose");
                }
            }
        }

        void PACK(int[] to, int[] from) {
            adjList = new int[n][];
            int[] idx = new int[n];
            for (int i = 0; i < m; i++)
                idx[from[i]]++;
            for (int i = 0; i < n; i++)
                adjList[i] = new int[idx[i]];
            for (int i = 0; i < to.length; i++)
                adjList[from[i]][--idx[from[i]]] = to[i];
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

