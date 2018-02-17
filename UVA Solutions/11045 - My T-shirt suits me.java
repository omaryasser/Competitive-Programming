import java.io.*;
import java.util.*;


public class Main {
    static final int INF = (int) 1e9;
    static int V, s, t, c[][], numV;

    static int shirt(int num) {
        return num + numV + 1;
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        String[] sizes = {"XXL", "XL", "L", "M" , "S", "XS"};
        HashMap<String,Integer> sizeMap = new HashMap<>();
        for (int i = 0; i < 6; i++)
            sizeMap.put(sizes[i], i);

        int T = sc.nextInt();
        while (T-- > 0) {
            int numT = sc.nextInt();
            numV = sc.nextInt();
            V = 6 + numV + 2;
            t = V - 1;
            c = new int[V][V];
            for (int i = 0; i < 6; i++)
                c[shirt(i)][t] = numT / 6;

            for (int i = 0; i < numV; i++) {
                c[i + 1][shirt(sizeMap.get(sc.next()))] = 1;
                c[i + 1][shirt(sizeMap.get(sc.next()))] = 1;
                c[0][i + 1] = 1;
            }

            if (pushRelabel() == numV) out.println("YES");
            else out.println("NO");
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
