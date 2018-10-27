import java.io.*;
import java.util.*;


public class C {
    static ArrayList<Integer> adjList[];
    static int adjMat [][]; // Initialize with Edge weights and if no Edge -> INF
    static int V , S;
    public static Pair floyd_warshall()
    {

        for(int k = 0 ; k < V ; ++k)
            for(int i = 0 ; i < V ; ++i)
                for(int j = 0 ; j < V ; ++j)
                    if (adjMat[i][k] + adjMat[k][j] < adjMat[i][j]) {
                        adjMat[i][j] = Math.min(adjMat[i][j], adjMat[i][k]  +  adjMat[k][j]);
                    }
        int max = 0 , best = 0;
        for (int i = 0 ; i < V ; ++i)
            if (max < Math.abs(adjMat[S][i]))
            {
                max = -adjMat[S][i];
                best = i;
            }
        return new Pair(-max , best);
    }

    static class Pair
    {
        int x , y;
        Pair(int xx , int yy) {x = xx ; y = yy;}
    }
    public static void main(String [] args) throws  Exception
    {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();
        for (int T = 1 ;; T++)
        {
            V = sc.nextInt();
            if (V == 0 )break;
            adjList = new ArrayList[V];
            adjMat = new int[V][V];
            for (int i = 0 ; i < V ; ++i)
                adjList[i] = new ArrayList<>();

            S = sc.nextInt() - 1;
            HashMap<Long,Integer> idx = new HashMap<>();
            int currentIdx = 0;
            for (int i = 0 ; i < V ; ++i)
                Arrays.fill(adjMat[i] , (int)(1e6));
            for (int i = 0 ; i < V ; ++i)
                adjMat[i][i] = 0;
            while (true)
            {

                int u = sc.nextInt() , v = sc.nextInt();
                if (u == 0 && v == 0) break;
                adjList[u - 1].add(v - 1);
                adjMat[u - 1][v - 1] = -1;
            }
            Pair res = floyd_warshall();
            out.printf("Case %d: The longest path from %d has length %d, finishing at %d.\n\n" , T , S + 1 , -res.x  , res.y + 1);
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
