import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class Main
{
    static int V, E, S, T;
    static ArrayList<Edge> [] adjList;
    static int cost [][];
    static int [][] res;

    public static void main(String[] args) throws IOException
    {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        while (true) {
            V = sc.nextInt();
            if (V == 0) break;
            E = sc.nextInt();
            V++;
            T = V - 1;
            res = new int[V][V];
            adjList = new ArrayList[V];
            cost = new int[V][V];
            for (int i = 0; i < V; i++) adjList[i] = new ArrayList<>();
            adjList[V - 2].add(new Edge(T, 0));
            res[V - 2][T] = 2;
            while (E-- > 0) {
                int u = sc.nextInt() - 1, v = sc.nextInt() - 1, costt = sc.nextInt();
                adjList[u].add(new Edge(v, costt));
                adjList[v].add(new Edge(u, costt));
                res[u][v] = res[v][u] = 1;
                cost[u][v] = cost[v][u] = costt;
            }
            Pair res = edmondsKarp();
            if (res.maxFlow == 2) {
                out.println(res.cost);
            } else {
                out.println("Back to jail");
            }
        }

        out.flush();
        out.close();
    }

    static class Edge {
        int to, cost;
        Edge (int tt, int cc) {
            to = tt;
            cost = cc;
        }
    }


    static int[] p;

    static Pair augment(int v, int flow, int costt)
    {
        if(v == S)
            return new Pair(flow, costt);
        Pair pair = augment(p[v], Math.min(res[p[v]][v],flow), costt + cost[p[v]][v]);
        if (v == T) {
            res[p[v]][v]--;
            res[v][p[v]]++;
        } else {
            cost[v][p[v]] = -cost[p[v]][v];
            cost[p[v]][v] = 0;
            res[p[v]][v] = 0;
            res[v][p[v]] = 1;
        }
        return pair;
    }
    static class Pair {
        int maxFlow, cost;
        Pair (int m, int c) {
            maxFlow = m;
            cost = c;
        }
    }
    static int INF = (int)1e9;
    static Pair edmondsKarp()
    {
        int mf = 0, costt = 0;
        while(true)
        {
            p = new int[V];
            Arrays.fill(p, -1);
            p[S] = S;
            int[] dist = new int[V];
            Arrays.fill(dist, INF);
            dist[S] = 0;
            boolean modified = true;
            for(int k = 0; modified && k < V - 1; ++k)
            {
                modified = false;
                for(int u = 0; u < V; ++u)
                    for(Edge nxt: adjList[u])
                        if(dist[u] + cost[u][nxt.to] < dist[nxt.to] && res[u][nxt.to] > 0)
                        {
                            modified = true;
                            dist[nxt.to] = dist[u] + nxt.cost;
                            p[nxt.to] = u;
                        }
            }

            if(p[T] == -1)
                break;
            Pair res = augment(T, INF, 0);
            mf += res.maxFlow;
            costt += res.cost;
        }
        return new Pair(mf, costt);
    }
































    static class Scanner
    {
        StringTokenizer st;
        BufferedReader br;

        public Scanner(InputStream s){	br = new BufferedReader(new InputStreamReader(s));}

        public String next() throws IOException
        {
            while (st == null || !st.hasMoreTokens())
                st = new StringTokenizer(br.readLine());
            return st.nextToken();
        }

        public int nextInt() throws IOException {return Integer.parseInt(next());}

        public long nextLong() throws IOException {return Long.parseLong(next());}

        public String nextLine() throws IOException {return br.readLine();}

        public double nextDouble() throws IOException { return Double.parseDouble(next()); }

        public boolean ready() throws IOException {return br.ready();}
    }
}
