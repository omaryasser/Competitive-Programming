import java.io.*;
import java.util.*;

public class E {
    static final long INF = (long)1e18;
    static ArrayList<Edge>[] adjList;
    static int N, s, t, p[], amount;
    static long cost;
    static Edge[] edges;

    static boolean minCost()
    {
        cost = 0;
        int flow = 0;
        while(true)
        {
            long[] dist = new long[N];
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

            if (flow == amount) return true;
            if(edges[t] == null)
                return false;
            if (flow == amount) return true;
            flow += aug(t, INF);
            if (flow == amount) return true;
        }
    }

    static long aug(int v, long flow)
    {
        if(v == s)
            return flow;
        int u = p[v];
        Edge e = edges[v];
        flow = aug(u, Math.min(flow, e.cap));
        e.cap -= flow;
        e.rev.cap += flow;
        cost += 1L * flow * e.cost;
        return flow;
    }

    static void addEdge(int u, int v, int c, int w)
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
        int node, cap, cost;
        Edge rev;

        Edge(int x, int y, int z) { node = x; cap = y; cost = z; }
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

//        int tc = 1;
        while (sc.ready()) {
//        while (tc-- > 0) {
            N = sc.nextInt() + 1;
            int m = sc.nextInt();
            int[][] inp = new int[m][3];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < 3; j++)
                    inp[i][j] = sc.nextInt();
            }
            s = 0;
            t = N - 1;
            amount = sc.nextInt();
            int cap = sc.nextInt();
            adjList = new ArrayList[N];
            for (int i = 0; i < N; i++) adjList[i] = new ArrayList<>();
            addEdge(s, 1, amount, 0);
            for (int i = 0; i < m; i++) {
                addEdge(inp[i][0], inp[i][1], cap, inp[i][2]);
                addEdge(inp[i][1], inp[i][0], cap, inp[i][2]);
            }
            out.println(minCost() ? cost : "Impossible.");
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
