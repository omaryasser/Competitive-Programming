package graphsCamp;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class MST_Prim {


static ArrayList<Edge>[] adjList;
static int V;

static int prim()     //O(E log E)
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

}
