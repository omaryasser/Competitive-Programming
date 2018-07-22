
import java.io.*;
import java.util.*;

public class Main {


    static int V ;
    static ArrayList<Edge> adjList[];
    static class Edge implements Comparable<Edge>
    {
        int node, cost;

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

    static int dijkstra(int S, int T)
    {
        int[] dist = new int[V];
        Arrays.fill(dist, (int)(2e9));
        PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
        dist[S] = 0;
        pq.add(new Edge(S, 0));
        while(!pq.isEmpty())
        {
            Edge cur = pq.remove();
            if(cur.node == T)
                return dist[T];
            if(cur.cost > dist[cur.node])
                continue;
            for(Edge nxt: adjList[cur.node])
                if(cur.cost + nxt.cost < dist[nxt.node])
                {
                    dist[nxt.node] = cur.cost + nxt.cost;
                    pq.add(new Edge(nxt.node, dist[nxt.node]));
                }

        }
        return -1;
    }

    public static void main(String [] args) throws  Exception
    {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();
        int testCase = sc.nextInt();
        for (int tt = 1 ; tt <= testCase ; ++tt)
        {
            out.printf("Case #%d: " , tt);
            V = sc.nextInt(); int E = sc.nextInt();
            int S = sc.nextInt() , T = sc.nextInt();
            adjList = new ArrayList[V];
            for (int i = 0 ; i < V ; ++i) adjList[i] = new ArrayList<>();
            while (E -- > 0)
            {
                int u = sc.nextInt() , v = sc.nextInt() , w = sc.nextInt();
                adjList[u].add(new Edge(v , w));
                adjList[v].add(new Edge(u , w));
            }

            int res = dijkstra(S , T);
            if (res != -1) out.printf("%d\n" , res);
            else out.print("unreachable\n");
        }
        out.flush();
        out.close();
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

        public double nextDouble() throws IOException
        {
            String x = next();
            StringBuilder sb = new StringBuilder("0");
            double res = 0, f = 1;
            boolean dec = false, neg = false;
            int start = 0;
            if(x.charAt(0) == '-')
            {
                neg = true;
                start++;
            }
            for(int i = start; i < x.length(); i++)
                if(x.charAt(i) == '.')
                {
                    res = Long.parseLong(sb.toString());
                    sb = new StringBuilder("0");
                    dec = true;
                }
                else
                {
                    sb.append(x.charAt(i));
                    if(dec)
                        f *= 10;
                }
            res += Long.parseLong(sb.toString()) / f;
            return res * (neg?-1:1);
        }

        public boolean ready() throws IOException {return br.ready();}


    }
}
