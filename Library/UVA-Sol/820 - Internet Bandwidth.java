import java.io.*;
import java.util.*;

public class Main {

    static final int INF = (int)1e9;
    static int V, s, t;						//input
    static ArrayList<Integer>[] adjList;	//input
    static int[][] res;						//input
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

    public static void main (String [] args) throws IOException {

        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        for (int T = 1; ; T ++)
        {
            V = sc.nextInt();
            if (V == 0) break;
            s = sc.nextInt() - 1; t = sc.nextInt() - 1; int E = sc.nextInt();
            adjList = new ArrayList[V];
            res = new int [V][V];
            for (int i = 0 ; i < V ; ++i)
                adjList[i] = new ArrayList<>();
            while (E -- >0)
            {
                int u = sc.nextInt() - 1 , v = sc.nextInt() - 1 , w = sc.nextInt();
                adjList[u].add(v);
                adjList[v].add(u);
                res[u][v] += w ;
                res[v][u] += w ;
            }
            out.printf("Network %d\n" , T);
            out.printf("The bandwidth is %d.\n" , edmondsKarp());
            out.print("\n");
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
