import java.io.*;
import java.util.*;

public class E {
    static final int INF = (int)1e9;
    static ArrayList<Edge>[] adjList;
    static int N, s, t, p[], banks;
    static double cost;
    static Edge[] edges;

    static double minCost()
    {
        double res = 0;
        cost = 0;
        int flow = 0;
        while(true)
        {
            double[] dist = new double[N];
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
            flow += aug(t, INF);
        }
    }

    static int aug(int v, int flow)
    {
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

    static void addEdge(int u, int v, int c, double w)
    {
        if(adjList[u] == null)
            adjList[u] = new ArrayList<Edge>();
        if(adjList[v] == null)
            adjList[v] = new ArrayList<Edge>();
        Edge e1 = new Edge(v, c, w), e2 = new Edge(u, 0, -w);
        e1.rev = e2; e2.rev = e1;
        adjList[u].add(e1);
        adjList[v].add(e2);
    }

    static class Edge
    {
        int node, cap;
        double cost;
        Edge rev;

        Edge(int x, int y, double z) { node = x; cap = y; cost = z; }
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        while (true) {
            int n = sc.nextInt(), m = sc.nextInt();
            if (n == 0 && m == 0) break;
            N = n + m + 2;
            banks = n;
            s = N - 1;
            t = N - 2;
            adjList = new ArrayList[N];
            for (int i = 0; i < N; i++) adjList[i] = new ArrayList<>();
            for (int i = 0; i < n; i++) addEdge(s, i, 1, 0);
            for (int i = 0; i < m; i++) addEdge(i + n, t, 1, 0);
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++)
                    addEdge(i, j + n, 1, sc.nextDouble());
            }
            out.printf("%.2f\n", minCost() / n);
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
