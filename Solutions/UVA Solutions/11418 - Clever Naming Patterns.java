import java.io.*;
import java.util.*;


public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        int tc = sc.nextInt();
        for (int testCase = 1; testCase <= tc; testCase++) {
            probemsNum = sc.nextInt();
            V = 2 + probemsNum + 26;
            c = new int[V][V];
            t = V - 1;
            String words[][] = new String[26][probemsNum];
            for (int i = 0; i < probemsNum; i++) {
                c[s][problem(i)] = 1;
                int k = sc.nextInt();
                while (k-- > 0) {
                    char[] nxtt = sc.next().toCharArray();
                    for (int j = 0; j < nxtt.length; j++) {
                        if (j == 0) {
                            if (Character.isLowerCase(nxtt[0])) nxtt[0] = Character.toUpperCase(nxtt[0]);
                        }
                        else
                            if (Character.isUpperCase(nxtt[j])) nxtt[j] = Character.toLowerCase(nxtt[j]);
                    }
                    String nxt = new String(nxtt);
                    words[nxt.charAt(0) - 'A'][i] = nxt;
                    c[problem(i)][letter(nxt.charAt(0) - 'A')] = 1;
                }
            }
            for (int i = 0; i < probemsNum; i++) c[letter(i)][t] = 1;
            pushRelabel();
            out.printf("Case #%d:\n", testCase);
            for (int i = 0; i < probemsNum; i++) {
                for (int j = 0; j < probemsNum; j++) {
                    if (f[problem(j)][letter(i)] == 1) out.println(words[i][j]);
                }
            }
        }

        out.close();
    }

    static int problem (int idx) {return idx + 1;}
    static int letter (int idx) {return idx + 1 + probemsNum;}
    static final int INF = (int)1e9;
    static int V, s, t, probemsNum, c[][], f[][];

    static int pushRelabel () { // O(V ^ 3)
        int[] h = new int[V], e = new int[V];
        f = new int[V][V];
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
