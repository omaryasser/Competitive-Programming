import java.io.*;
import java.util.*;


public class Main {
    static int INF = (int)1e9;
    static int V, s, t, c[][];
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        for (int tc = 1;; tc++){
            V = sc.nextInt();
            if (V == 0) break;
            s = sc.nextInt() - 1;
            t = sc.nextInt() - 1;
            int E = sc.nextInt();
            c = new int[V][V];
            while (E-- > 0) {
                int u = sc.nextInt() - 1, v = sc.nextInt() - 1, w = sc.nextInt();
                c[u][v] += w;
                c[v][u] += w;
            }

            out.printf("Network %d\n" +
                    "The bandwidth is %d.\n\n", tc, pushRelabel());
        }

        out.close();
    }

    static int pushRelabel () {
        int[] h = new int[V], e = new int[V], f[] = new int[V][V];
        h[s] = V - 1;

        Queue<Integer> q = new LinkedList<>();
        boolean[] isActive = new boolean[V];
        for (int i = 0; i < V; i++) {
            f[i][s] = -(f[s][i] = e[i] = c[s][i]);
            if (i != s && i != t && e[i] > 0) {
                isActive[i] = true;
                q.add(i);
            }
        }

        while (!q.isEmpty()) {
            int u = q.peek();
            boolean pushed = false;
            for (int v = 0; v < V && e[u] != 0; v++) {
                if (h[u] > h[v] && c[u][v] - f[u][v] > 0) {
                    int df = Math.min(e[u], c[u][v] - f[u][v]);
                    f[u][v] += df;
                    f[v][u] -= df;
                    e[u] -= df;
                    e[v] += df;
                    if (v != s && v != t && !isActive[v]) {
                        isActive[v] = true;
                        q.add(v);
                    }
                    pushed = true;
                }
            }
            if (e[u] == 0) {
                isActive[u] = false;
                q.remove();
            }

            if (!pushed) {
                h[u] = INF;
                for (int v = 0; v < V; ++v)
                    if (c[u][v] - f[u][v] > 0 && h[v] <= h[u])
                        h[u] = h[v] + 1;
            }
        }
        return e[t];
    }




































    static class Scanner {
        StringTokenizer st;
        BufferedReader br;

        public Scanner(InputStream s) {
            br = new BufferedReader(new InputStreamReader(s));
        }
        public Scanner(FileReader s) {
            br = new BufferedReader(s);
        }

        public String next() {
            while (st == null || !st.hasMoreTokens()) try {
                st = new StringTokenizer(br.readLine());
            } catch (Exception e) {
            }
            return st.nextToken();
        }

        public String nextLine() throws IOException {
            return br.readLine();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }

        public boolean ready() throws IOException {
            return br.ready();
        }
    }
}
