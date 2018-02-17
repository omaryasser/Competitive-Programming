import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class Main
{

    static long augment(int v, long flow)
    {
        if(v == s)
            return flow;
        flow =  augment(p[v], Math.min(res[p[v]][v],flow));
        res[p[v]][v] -= flow;
        res[v][p[v]] += flow;

        return flow;
    }

    static long edmondsKarp()
    {
        long mf = 0;
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
                for(int v: adjList[u])
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

    static final long INF = (int)1e12;
    static int V, s = 0, t = 1;
    static ArrayList<Integer>[] adjList;
    static long[][] res;
    static int[] p;

    public static void main(String[] args) throws IOException
    {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        while (true) {
            V = sc.nextInt();
            int E = sc.nextInt();
            if (V == 0 && E == 0) break;
            adjList = new ArrayList[V];
            for (int i = 0; i < V; i++) adjList[i] = new ArrayList<>();
            res = new long[V][V];
            boolean was [][] = new boolean[V][V];
            while (E-- > 0) {
                int u = sc.nextInt() - 1, v = sc.nextInt() - 1;
                int cost = sc.nextInt();
                adjList[u].add(v);
                adjList[v].add(u);
                res[u][v] = res[v][u] = cost;
                was[u][v] = was[v][u] = true;
            }
            edmondsKarp();
            boolean [] visited = new boolean[V];
            Queue<Integer> reachable = new LinkedList<>();
            reachable.add(0);

            while (!reachable.isEmpty()) {
                int cur = reachable.poll();
                visited[cur] = true;
                for (int nxt : adjList[cur])
                    if (!visited[nxt] && res[cur][nxt] != 0)
                        reachable.add(nxt);
            }

            for (int i = 0; i < V; i++)
                for (int nxt : adjList[i]) {
                    if (visited[i] && !visited[nxt])
                        out.println((i + 1) + " " + (nxt + 1));
                }
            out.println();
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

        public double nextDouble() throws IOException { return Double.parseDouble(next()); }

        public boolean ready() throws IOException {return br.ready();}
    }
}
