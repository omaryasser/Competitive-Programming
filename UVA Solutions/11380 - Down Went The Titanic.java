import java.io.*;
import java.util.*;


public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        int tc = 3;
        while (sc.ready()) {
//        while (tc-- > 0) {
            int n = sc.nextInt(), m = sc.nextInt(), p = sc.nextInt();
            char[][] grid = new char[n][m];
            V = 2;
            int[][] map = new int[n][m];
            int idx = 1;
            for (int i = 0; i < n; i++) {
                grid[i] = sc.next().toCharArray();
                for (int j = 0; j < m; j++) {
                    if (grid[i][j] != (char)(126)) {
                        map[i][j] = idx++;
                        V++;
                    }
                    if (grid[i][j] == '.') {
                        idx++;
                        V++;
                    }
                }
            }
            t = V - 1;
            res = new int[V][V];
            adjList = new ArrayList[V];
            for (int i = 0; i < V; i++) adjList[i] = new ArrayList<>();
            int[] dx = {0, 0, 1, -1};
            int[] dy = {1, -1, 0, 0};

            for (int i = 0; i < n; i++) for (int j = 0; j < m; j++) {
                int idx1 = map[i][j];
                if (grid[i][j] == '.') {
                    res[idx1][idx1 + 1] = 1;
                    edge(idx1, idx1 + 1);
                } else if (grid[i][j] == '#') {
                    res[idx1][t] = p;
                    edge(idx1, t);
                } else if (grid[i][j] == '*') {
                    res[s][idx1] = 1;
                    edge(s, idx1);
                }
            }
            for (int i1 = 0; i1 < n; i1++) {
                for (int j1 = 0; j1 < m; j1++) {
                    for (int k = 0; k < 4; k++) {
                        int i2 = i1 + dx[k], j2 = j1 + dy[k];
                        if (i2 >= 0 && i2 < n && j2 >= 0 && j2 < m) {
                            char first = grid[i1][j1], second = grid[i2][j2];
                            int idx1 = map[i1][j1], idx2 = map[i2][j2];
                            if (first == '*' && second == '.' || first == '*' && second == '@' || first == '*' && second == '#'
                                    || first == '@' && second == '.' || first == '#' && second == '.') {
                                res[idx1][idx2] = 1;
                                edge(idx1, idx2);
                            } else if (first == '.' && second == '.' || first == '.' && second == '@' || first == '.' && second == '#') {
                                res[idx1 + 1][idx2] = 1;
                                edge(idx1 + 1, idx2);
                            } else if (first == '#' && second == '#' || first == '@' && second == '#' ||
                                    first == '#' && second == '@' || first == '@' && second == '@') {
                                res[idx1][idx2] = INF;
                                edge(idx1, idx2);
                            }
                        }
                    }
                }
            }
            out.println(edmondsKarp());
        }

        out.close();
    }

    static void edge (int u, int v) {
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
    }
}
