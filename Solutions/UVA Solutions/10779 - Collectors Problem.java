import java.io.*;
import java.util.*;


public class Main {
    static final int INF = (int) 1e9;
    static int V, s, t, c[][], numPeople, numStickers;

    static int person(int num) {
        return num;
    }

    static int sticker(int num) {
        return num + numPeople;
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        int T = sc.nextInt();
        for (int tc = 1; tc <= T; tc++) {
            numPeople = sc.nextInt();
            numStickers = sc.nextInt();
            V = numPeople + numStickers + 1;
            t = V - 1;
            c = new int[V][V];
            int[][] cnt = new int[numPeople][numStickers];
            for (int i = 0; i < numPeople; i++) {
                int k = sc.nextInt();
                while (k-- > 0) cnt[i][sc.nextInt() - 1]++;
            }

            int res = 0;
            for (int i = 0; i < numStickers; i++)
                if (cnt[0][i] >= 1) {
                    res++;
                    c[0][sticker(i)] = cnt[0][i] - 1;
                } else c[sticker(i)][t] = 1;
            for (int i = 1; i < numPeople; i++)
                for (int j = 0; j < numStickers; j++)
                    if (cnt[i][j] == 0)
                        c[sticker(j)][person(i)] = 1;
                    else
                        c[person(i)][sticker(j)] = cnt[i][j] - 1;


            out.printf("Case #%d: %d\n", tc, res + pushRelabel());
        }

        out.close();
    }


    static int pushRelabel() {
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
