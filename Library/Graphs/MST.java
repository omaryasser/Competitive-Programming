// 1.kruskal O(E log E)
static Edge[] edgeList;
static int V;

static int kruskal()
{
        int mst = 0;
        shuffle(edgeList);
        Arrays.sort(edgeList);
        UnionFind uf = new UnionFind(V);

        for(int i = 0; i < edgeList.length && uf.numSets > 1; ++i) {
                Edge e = edgeList[i];
                if (!uf.isSameSet(e.from, e.to)) {
                        mst += e.cost;
                        uf.unionSet(e.from, e.to);
                }
        }

        return mst;
}

static class Edge implements Comparable<Edge>
{
int from, to, cost;

Edge(int a, int b, int c)
{
        from = a; to = b; cost = c;
}

public int compareTo(Edge e)
{
        if(cost != e.cost)
                return cost - e.cost;
        if(from != e.from)
                return from - e.from;
        return to - e.to;
}
}

static class UnionFind {
int[] p, rank, setSize;
int numSets;

public UnionFind(int N)
{
        p = new int[N];
        rank = new int[N];
        setSize = new int[N];
        numSets = N;
        for (int i = 0; i < N; i++) {  p[i] = i; setSize[i] = 1; }
}

public int findSet(int i)
{
        if (p[i] == i) return i;
        else return p[i] = findSet(p[i]);
}

public Boolean isSameSet(int i, int j) {
        return findSet(i) == findSet(j);
}

public void unionSet(int i, int j)
{
        if (isSameSet(i, j))
                return;
        numSets--;
        int x = findSet(i), y = findSet(j);
        // rank is used to keep the tree short
        if (rank[x] > rank[y]) { p[y] = x; setSize[x] += setSize[y]; }
        else
        { p[x] = y; setSize[y] += setSize[x];
          if(rank[x] == rank[y]) rank[y]++; }
}

public int numDisjointSets() {
        return numSets;
}

public int sizeOfSet(int i) {
        return setSize[findSet(i)];
}
}



// 2.Prim O(E log E)
static int prim()
{
        int mst = 0;
        boolean[] visited = new boolean[V];
        PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
        pq.add(new Edge(0, 0));
        while(!pq.isEmpty())
        {
                Edge cur = pq.remove();
                if(visited[cur.to])
                        continue;
                visited[cur.to] = true;
                mst += cur.cost;
                for(Edge nxt : adjList[cur.to])
                        if(!visited[nxt.to])
                                pq.add(nxt);
        }

        return mst;
}



static class Edge implements Comparable<Edge>
{
int to, cost;

Edge(int a, int b)
{
        to = a;
        cost = b;
}

public int compareTo(Edge e)
{
        if(cost != e.cost)
                return cost - e.cost;
        return to - e.to;
}

}
