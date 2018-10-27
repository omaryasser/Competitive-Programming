
static final int INF = (int)1e9;

//don't increase, avoid overflow
static ArrayList<Edge>[] adjList;
static int V;
//O(ElogV)
static int dijkstra(int S, int T)
{
        int[] dist = new int[V];
        Arrays.fill(dist, INF);
        PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
        dist[S] = 0;
        pq.add(new Edge(S, 0));
        while(!pq.isEmpty())
        {
                Edge cur = pq.remove();
                if(cur.node == T) //remove if all nodes are sinks
                        return dist[T];
                if(cur.cost > dist[cur.node])
                        continue;
                for(Edge nxt : adjList[cur.node])
                        if(cur.cost + nxt.cost < dist[nxt.node])
                        {
                                dist[nxt.node] = cur.cost + nxt.cost;
                                pq.add(new Edge(nxt.node, dist[nxt.node]));
                        }

        }
        return -1;
}

static boolean bellmanFord(int S)
{
        int[] dist = new int[V];
        Arrays.fill(dist, INF);
        dist[S] = 0;
        boolean modified = true;
        for(int k = 0; modified && k < V - 1; ++k)
        {
                modified = false;
                for(int u = 0; u < V; ++u) // these two loops run in O(E) in total
                        for(Edge nxt : adjList[u])
                                if(dist[u] + nxt.cost < dist[nxt.node])
                                {
                                        modified = true;
                                        dist[nxt.node] = dist[u] + nxt.cost;
                                }
        }

<<<<<<< HEAD

		boolean negative_cycle_exists = false;
		for(int u = 0; u < V; ++u)
			for(Edge nxt: adjList[u])
				if(dist[u] + nxt.cost < dist[nxt.node])
					negative_cycle_exists = true;
		return negative_cycle_exists;

		/*
			To get the Negative Cycle's Nodes.
			- Start from any Affected node, say node A (Get this node by iterating one more time and looking for a changed node)
			- It is either in cycle or cycle reached it.
			- Go back N times
			- Now you must be in the cycle, say node B
			- Keep going back agin till you see B again
		*/
	}
	static class Edge implements Comparable<Edge>
	{
		int node, cost;
=======
        boolean negative_cycle_exists = false;
        for(int u = 0; u < V; ++u)
                for(Edge nxt : adjList[u])
                        if(dist[u] + nxt.cost < dist[nxt.node])
                                negative_cycle_exists = true;
        return negative_cycle_exists;
}
static class Edge implements Comparable<Edge>
{
int node, cost;
>>>>>>> 56a558d15fded1110f61556e56a59791a3df1d80

Edge(int a, int b)
{
        node = a;
        cost = b;
}

public int compareTo(Edge e)
{
        if(cost != e.cost)
                return cost - e.cost;
        return node - e.node;
}

}
