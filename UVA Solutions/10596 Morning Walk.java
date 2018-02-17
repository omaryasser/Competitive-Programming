import java.io.*;
import java.util.*;

public class Main {
    static ArrayList<Integer> adjList [];
    static int cnt;
    static int V, E;
    static boolean visited[];
    static void dfs (int node) {
        visited[node] = true;
        cnt++;
        for (int child : adjList[node]) {
            if (!visited[child]) {
                dfs(child);
            }
        }
    }
    public static void main(String [] args) throws  Exception
    {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);



        while (sc.ready()) {
            V = sc.nextInt();
            E = sc.nextInt();
            visited = new boolean[V];
            adjList = new ArrayList[V];
            for (int i = 0; i < V; i ++) adjList[i] = new ArrayList<>();
            boolean can = E != 0;
            while (E-- > 0) {
                int u = sc.nextInt(), v = sc.nextInt();
                adjList[u].add(v);
                adjList[v].add(u);
            }
            cnt = 0;
            for (int i = 0; i < V; i++)
                if (adjList[i].size() != 0) {
                    dfs(i);
                    break;
                }

            for (int i = 0; i < V; i++) can &= (adjList[i].size() % 2 == 0);

            int cntNeeded = 0;
            for (int i = 0; i < V; i++)
                cntNeeded += (adjList[i].size() != 0 ? 1 : 0);

            if (can && cnt == cntNeeded) out.println("Possible");
            else out.println("Not Possible");
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
