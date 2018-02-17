// O(min(flow * E, V * E^2))
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
