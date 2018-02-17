import com.sun.org.apache.bcel.internal.generic.BREAKPOINT;

import java.io.*;
import java.util.*;

/**
 * Created by aa on 1/06/2016.
 */
public class Main {
    static int  E ;
    static HashMap<String , Integer> idx ;
    static HashMap<Integer , String> name ;
    static ArrayList<Integer> adjList [];
    static int [] dfs_num , dfs_low , visited;
    static int dfsNumberCounter , numSCC;
    static Stack <Integer> S;
    static int UNVISITED = 0;
    static int V;
    static StringBuilder sb ;
    public static void tarjanSCC(int u)
    {
        dfs_low[u] = dfs_num[u] = ++dfsNumberCounter;
        S.push(u);
        visited[u] = 1;
        for (int v : adjList[u])
        {
            if (dfs_num[v] == UNVISITED)
                tarjanSCC(v);
            if (visited[v] == 1)
                dfs_low[u] = Math.min(dfs_low[u] , dfs_low[v]);
        }

        if (dfs_low[u] == dfs_num[u])
        {
            boolean first = true;
            while (true)
            {
                if (first) first = false;
                else sb.append(", ");
                int v = S.pop(); 
                visited[v] = UNVISITED;
                sb.append(name.get(v));
                if (u == v) break;
            }
            sb.append("\n");

        }
    }

    public static void tarjanSCC ()
    {
        dfs_low = new int[V];
        dfs_num = new int[V];
        visited = new int[V];
        S = new Stack<>();
        dfsNumberCounter = 0;
        numSCC = 0;
        for (int i = 0 ; i < V ; ++i)
            if (dfs_num[i] == UNVISITED)
                tarjanSCC(i);
    }
    public static void main(String [] args) throws  Exception
    {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        sb = new StringBuilder();
        int T = 1;
        boolean first = true;
        while(true)
        {
            V = sc.nextInt() ; E = sc.nextInt();
            if (V == 0 && E == 0) break;
            adjList = new ArrayList[V];
            for (int i = 0 ; i < V ; ++i) adjList[i] = new ArrayList<>();
            idx = new HashMap<>();
            name = new HashMap<>();
            int cnt = 0;
            while (E --  >0)
            {
                String name1 = sc.next() , name2 = sc.next();
                if (!idx.containsKey(name1)){
                    idx.put(name1 , cnt ++);
                    name.put(cnt - 1 , name1);
                }
                if (!idx.containsKey(name2)){
                    idx.put(name2 , cnt ++);
                    name.put(cnt - 1 , name2);
                }
                adjList[idx.get(name1)].add(idx.get(name2));
            }
            if (first) first = false;
            else sb.append("\n");
            sb.append("Calling circles for data set "+T ++ +":\n");
            tarjanSCC();

        }
        out.print(sb.toString());
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
