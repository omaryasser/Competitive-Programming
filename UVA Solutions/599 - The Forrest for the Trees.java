
import java.io.*;
import java.util.*;

public class Main {

    static ArrayList<Integer> adjList[] ;
    static boolean visited [] ;
    public static void dfs (int u)
    {
        visited[u] = true;
        for(int i : adjList[u])
            if (!visited[i])
                dfs(i);
    }
    public static void main(String[]args) throws Exception {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(sc.nextLine());
        while (T -- > 0)
        {
            adjList = new ArrayList[26];
            visited = new boolean[26];
            for(int i = 0 ; i < 26 ; ++i) adjList[i] = new ArrayList<>();
            String inp ;
            while (true)
            {
                inp = sc.nextLine();
                if (inp.charAt(0) == '*') break;
                adjList[inp.charAt(1) - 'A'].add(inp.charAt(3) - 'A');
                adjList[inp.charAt(3) - 'A'].add(inp.charAt(1) - 'A');
            }

            String [] V = sc.nextLine().split(",");
            int trees = 0 , dot = 0;
            for (int i = 0 ; i < V.length ; ++i)
                if(adjList[V[i].charAt(0) - 'A'].size() == 0) ++dot;
                else if (!visited[V[i].charAt(0) - 'A']) {
                    ++trees;
                    dfs(V[i].charAt(0) - 'A');
                }

            out.printf("There are %d tree(s) and %d acorn(s).\n" , trees , dot);
        }
        out.flush();
        out.close();
    }

    

    static  class Scanner
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
