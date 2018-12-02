// Maximum Independent Set = V - MCBM
// Minimum Vertex Cover = MCBM
// O(V * E)
static boolean visited [];
static int matched [];    // intialize with - 1
static ArrayList<Integer> adjList [];
static int V;
static boolean aug (int u) {
        if (visited[u]) return false;
        visited[u] = true;

        for (int node : adjList[u]) {
                if (matched [node] == -1 || aug (matched[node])) {
                        matched[node] =  u; return true;
                }
        }

        return false;
}

static int MCBM (int leftSize) {
        int res = 0;
        matched = new int[V];
        Arrays.fill(matched, -1);
        for (int i = 0; i < leftSize; ++i) {
                visited = new boolean[V];
                res += (aug(i) ? 1 : 0);
        }

        return res;
}

//	O(sqrt(V)E)
//	Vertices of left/right set start from 1 (u, v > 0)
static int[] pair_U, pair_V, dist;
static final int NIL = 0, INF = (int)1e9;
static int hopcroftKarp()
{
        pair_U = new int[n + 1]; //filled with NIL
        pair_V = new int[m + 1]; //filled with NIL
        dist = new int[n + 1];

        int matching = 0;
        while(bfs())
                for(int u = 1; u <= n; ++u)
                        if(pair_U[u] == NIL && dfs(u))
                                ++matching;
        return matching;
}
static boolean bfs()
{
        Queue<Integer> q = new LinkedList<Integer>();
        for(int u = 1; u <= n; ++u)
                if(pair_U[u] == NIL)
                {
                        dist[u] = 0;
                        q.add(u);
                }
                else
                        dist[u] = INF;
        dist[NIL] = INF;
        while(!q.isEmpty())
        {
                int u = q.remove();
                if(dist[u] < dist[NIL])
                        for(int v : adjList[u])
                                if(dist[pair_V[v]] == INF)
                                {
                                        dist[pair_V[v]] = dist[u] + 1;
                                        q.add(pair_V[v]);
                                }
        }
        return dist[NIL] != INF;
}

static boolean dfs(int u)
{
        if(u == NIL)
                return true;

        for(int v : adjList[u])
                if(dist[pair_V[v]] == dist[u] + 1 && dfs(pair_V[v]))
                {
                        pair_U[u] = v;
                        pair_V[v] = u;
                        return true;
                }
        dist[u] = INF;
        return false;
}
