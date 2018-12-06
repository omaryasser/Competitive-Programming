import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Collection;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Queue;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedList;
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
        TaskC solver = new TaskC();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskC {
        public void solve(int testNumber, Scanner sc, PrintWriter out) {
            int n = sc.nextInt(), m = sc.nextInt();
            int[] c = new int[n];
            for (int i = 0; i < n; i++)
                c[i] = sc.nextInt();
            ArrayList<Integer> adjList[] = new ArrayList[n];
            for (int i = 0; i < n; i++)
                adjList[i] = new ArrayList<>();
            while (m-- > 0) {
                int x = sc.nextInt() - 1, y = sc.nextInt() - 1;
                adjList[x].add(y);
                adjList[y].add(x);
            }

            long res = 0;
            boolean taken[] = new boolean[n];
            for (int i = 0; i < n; i++) {
                if (!taken[i]) {
                    int min = c[i];
                    Queue<Integer> q = new LinkedList<>();
                    taken[i] = true;
                    q.add(i);
                    while (!q.isEmpty()) {
                        int cur = q.poll();
                        min = Math.min(min, c[cur]);
                        for (int nxt : adjList[cur])
                            if (!taken[nxt]) {
                                q.add(nxt);
                                taken[nxt] = true;
                            }
                    }
                    res += min;
                }
            }
            out.println(res);
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

