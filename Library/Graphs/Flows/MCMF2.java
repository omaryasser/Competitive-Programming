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
                                        for(Edge e : adjList[u]) {
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

Edge(int x, int y, int z) {
        node = x; cap = y; cost = z;
}
}
