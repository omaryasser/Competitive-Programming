import java.io.*;
import java.util.*;

public class E {
    static final int INF = (int)1e9;
    static ArrayList<Edge>[] adjList;
    static int N, s, t, p[];
    static int cost;
    static Edge[] edges;

    static int minCost() {
        cost = 0;
        while(true) {
            int[] dist = new int[N];
            Arrays.fill(dist, INF);
            dist[s] = 0;
            p = new int[N];
            edges = new Edge[N];
            for(int k = 0; k < N - 1; ++k)
                for(int u = 0; u < N; ++u)
                    if(adjList[u] != null)
                        for(Edge e: adjList[u]) {
                            if (e.cap > 0 && dist[e.node] > dist[u] + e.cost) {
                                dist[e.node] = dist[u] + e.cost;
                                p[e.node] = u;
                                edges[e.node] = e;
                            }
                        }

            if(edges[t] == null)
                return cost;
            aug(t, INF);
        }
    }

    static long aug(int v, long flow) {
        if(v == s)
            return flow;
        int u = p[v];
        Edge e = edges[v];
        flow = aug(u, Math.min(flow, e.cap));
        e.cap -= flow;
        e.rev.cap += flow;
        cost += flow * e.cost;
        return flow;
    }

    static void addEdge(int u, int v, int c, int w) {
        if(adjList[u] == null)
            adjList[u] = new ArrayList<Edge>();
        if(adjList[v] == null)
            adjList[v] = new ArrayList<Edge>();
        Edge e1 = new Edge(v, c, w), e2 = new Edge(u, 0, -w);
        e1.rev = e2; e2.rev = e1;
        adjList[u].add(e1);
        adjList[v].add(e2);
    }

    static class Edge {
        int node, cap, cost;
        Edge rev;
        Edge(int x, int y, int z) { node = x; cap = y; cost = z; }
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);


        int tc = sc.nextInt();
        while (tc-- > 0) {
            int n = sc.nextInt(), m = sc.nextInt();
            char[][] arr = new char[n][m];
            int[][] map = new int[n][m];
            int mapIdx = 0;
            for (int i = 0; i < n; i++) {
                arr[i] = sc.next().toCharArray();
                for (int j = 0; j < m; j++)
                    if (arr[i][j] == 'B' || arr[i][j] == 'X')
                        map[i][j] = mapIdx++;
            }
            N = mapIdx + 2;
            s = N - 1; t = N - 2;
            adjList = new ArrayList[N];
            for (int i = 0; i < N; i++) adjList[i] = new ArrayList<>();
            for (int i = 0; i < n; i++) for (int j = 0; j < m; j++)
                if (arr[i][j] == 'B') addEdge(s, map[i][j], 1, 0);
                else if (arr[i][j] == 'X') addEdge(map[i][j], t, 1, 0);

            int[] dx = {0, 0, 1, -1}, dy = {1, -1, 0, 0};
            for (int i = 0; i < n; i++) for (int j = 0; j < m; j++) {
                if (arr[i][j] == 'B') {
                    int[][] dist = new int[n][m];
                    for (int[] d : dist) Arrays.fill(d, INF);
                    dist[i][j] = 0;
                    Queue<Integer> q = new LinkedList<>();
                    q.add(i);
                    q.add(j);
                    while (!q.isEmpty()) {
                        int row = q.poll(), col = q.poll();
                        for (int k = 0; k < 4; k++) {
                            int nr = row + dx[k], nc = col + dy[k];
                            if (nr >= 0 && nc >= 0 && nr < n && nc < m && arr[nr][nc] != '#' && dist[nr][nc] > dist[row][col] + 1) {
                                dist[nr][nc] = dist[row][col] + 1;
                                if (arr[nr][nc] == 'X') addEdge(map[i][j], map[nr][nc], 1, dist[nr][nc]);
                                q.add(nr);
                                q.add(nc);
                            }
                        }
                    }
                }
            }
            out.println(minCost());
        }

        out.flush();
        out.close();
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

        public long nextLong() {
            return Long.parseLong(next());
        }

        public String nextLine() {
            try {
                return br.readLine();
            } catch (Exception e) {
                return null;
            }
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

        public boolean ready() {
            try {
                return br.ready();
            } catch (Exception e) {
                return false;
            }
        }
    }

}
