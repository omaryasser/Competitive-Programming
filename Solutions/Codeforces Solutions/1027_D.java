import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        V = sc.nextInt();
        cost = new int[V];
        dfs_num = new int[V];
        dfs_low = new int[V];
        inSCC = new int[V];
        stack = new Stack<>();
        adjList = new ArrayList[V];
        for(int i = 0; i < V; i++)
            adjList[i] = new ArrayList<>();

        nodesIn = new ArrayList[V];
        for(int i = 0; i < V; i++)
            nodesIn[i] = new ArrayList<>();

        for(int i = 0; i < V; i++)
            cost[i] = sc.nextInt();

        for(int i = 0; i < V; i++)
        {
            int nxt = sc.nextInt()  -1;
            adjList[i].add(nxt);
        }

        Arrays.fill(inSCC, -1);
        tarjanSCC();
        boolean good[] = new boolean[SCC];
        Arrays.fill(good, true);
        for(int i = 0; i < V; i++)
            if(inSCC[i] != inSCC[adjList[i].get(0)])
                good[inSCC[i]] = false;

        long res = 0;
        for(int i = 0; i < SCC; i++)
        {
            if(good[i])
            {
                int min = Integer.MAX_VALUE;
                for(int node : nodesIn[i])
                    min = Math.min(min, cost[node]);
                res += min;
            }
        }

        out.println(res);
        out.flush();
        out.close();
    }


    static ArrayList<Integer>[] adjList, nodesIn;
    static int V, counter, SCC, dfs_num[], dfs_low[], cost[], inSCC[];
    static Stack<Integer> stack;

    static void tarjanSCC()
    {
        for(int i = 0; i < V; ++i)
            if(dfs_num[i] == 0)
                tarjanSCC(i);
    }

    static void tarjanSCC(int u)
    {
        dfs_num[u] = dfs_low[u] = ++counter;
        stack.push(u);

        for(int v : adjList[u])
        {
            if(dfs_num[v] == 0)
                tarjanSCC(v);
            if(inSCC[v] == -1)
                dfs_low[u] = Math.min(dfs_low[u], dfs_low[v]);
        }
        if(dfs_num[u] == dfs_low[u])
        {
            while(true)
            {
                int v = stack.pop();
                inSCC[v] = SCC;
                nodesIn[SCC].add(v);
                if(v == u)
                    break;
            }
            SCC++;
        }


    }






















    static class Scanner {
        StringTokenizer st;
        BufferedReader br;

        public Scanner(InputStream s) {
            br = new BufferedReader(new InputStreamReader(s));
        }

        public String next() throws IOException {
            while (st == null || !st.hasMoreTokens())
                st = new StringTokenizer(br.readLine());
            return st.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }

        public long nextLong() throws IOException {
            return Long.parseLong(next());
        }

        public String nextLine() throws IOException {
            return br.readLine();
        }

        public double nextDouble() throws IOException {
            String x = next();
            StringBuilder sb = new StringBuilder("0");
            double res = 0, f = 1;
            boolean dec = false, neg = false;
            int start = 0;
            if (x.charAt(0) == '-') {
                neg = true;
                start++;
            }
            for (int i = start; i < x.length(); i++)
                if (x.charAt(i) == '.') {
                    res = Long.parseLong(sb.toString());
                    sb = new StringBuilder("0");
                    dec = true;
                } else {
                    sb.append(x.charAt(i));
                    if (dec)
                        f *= 10;
                }
            res += Long.parseLong(sb.toString()) / f;
            return res * (neg ? -1 : 1);
        }

        public boolean ready() throws IOException {
            return br.ready();
        }

    }
}