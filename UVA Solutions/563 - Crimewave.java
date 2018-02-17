import java.io.*;
import java.util.*;


public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        int tc = sc.nextInt();
        while (tc-- > 0) {
            int n = sc.nextInt(), m = sc.nextInt(), num = sc.nextInt();
            V = n * m + 2;
            s = V - 2;
            t = V - 1;
            res = new int[V][V];
            adjList = new ArrayList[V];
            for (int i = 0; i < V; i++) adjList[i] = new ArrayList<>();

            boolean[][] found = new boolean[n][m];
            for (int i = 0; i < num; i++) {
                int x, y;
                found[x = (sc.nextInt() - 1)][y = (sc.nextInt() - 1)] = true;
                edge(s, x * m + y);
            }

            int[] dx = {0, 0, 1, -1}, dy = {1, -1, 0, 0};
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    for (int k = 0; k < 4; k++) {
                        int ni = i + dx[k], nj = j + dy[k];
                        if (ni >= 0 && ni < n && nj >= 0 && nj < m) {
                            if (!found[ni][nj]) edge(i * m + j, ni * m + nj);
                        } else {
                            edge(i * m + j, t);
                        }
                    }
                }
            }

            if (edmondsKarp() == num) out.println("possible");
            else out.println("not possible");
        }

        out.close();
    }

    static void edge (int u, int v) {
        res[u][v] = 1;
        adjList[u].add(v);
        adjList[v].add(u);
    }
    static final int INF = (int)1e9;
    static int V, s, t;
    static ArrayList<Integer>[] adjList;
    static int[][] res;
    static int[] p;

    static int augment(int v, int flow)
    {
        if(v == s)
            return flow;
        flow =  augment(p[v], Math.min(res[p[v]][v],flow));
        res[p[v]][v] -= flow;
        res[v][p[v]] += flow;

        return flow;
    }

    static int edmondsKarp()
    {
        int mf = 0;
        while(true)
        {
            Queue<Integer> q = new LinkedList<Integer>();
            p = new int[V];
            Arrays.fill(p, -1);
            p[s] = s;
            q.add(s);
            while(!q.isEmpty())
            {
                int u = q.remove();
                if(u == t)
                    break;
                for(int v : adjList[u])
                    if(res[u][v] > 0 && p[v] == -1)
                    {
                        p[v] = u;
                        q.add(v);
                    }
            }

            if(p[t] == -1)
                break;
            mf += augment(t, INF);

        }



        return mf;
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
        public double nextDouble() {
            String x = next();
            StringBuilder sb = new StringBuilder("0");
            double res = 0, f = 1;
            boolean dec = false, neg = false;
            int start = 0;
            if (x.charAt(0) == '-') {
                neg = true;
                start++;
            }
            for (int i = start; i < x.length(); i++)
                if (x.charAt(i) == '.') {
                    res = Long.parseLong(sb.toString());
                    sb = new StringBuilder("0");
                    dec = true;
                } else {
                    sb.append(x.charAt(i));
                    if (dec)
                        f *= 10;
                }
            res += Long.parseLong(sb.toString()) / f;
            return res * (neg ? -1 : 1);
        }
    }
}
