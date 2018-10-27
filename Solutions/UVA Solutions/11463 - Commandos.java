
import java.io.*;
import java.util.*;

public class Main {

    static int V ;
    static int adjMat [][];

    public static void main(String [] args) throws  Exception
    {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();
        int T = sc.nextInt();
        for (int t = 1 ; t <= T ; t++)
        {
            V = sc.nextInt();
            int E = sc.nextInt();
            adjMat = new int[V][V];
            for (int i = 0 ; i < V ; ++i) Arrays.fill(adjMat[i] , (int)1e9);
            for (int i = 0 ; i < V ; ++i)adjMat[i][i] = 0;
            while (E -- > 0)
            {
                int u = sc.nextInt() , v = sc.nextInt();
                adjMat[u][v] = 1;
                adjMat[v][u] = 1;
            }


            for (int k = 0 ; k < V ; ++k)
                for (int i = 0 ; i < V ; ++i)
                    for (int j = 0 ; j < V ; ++j)
                        adjMat[i][j] = Math.min(adjMat[i][j] , adjMat[i][k] + adjMat[k][j]);

            int start = sc.nextInt() , end = sc.nextInt();
            int max = 0;
            for (int i = 0 ; i < V ; ++i)
                max = Math.max(max , adjMat[start][i] + adjMat[end][i]);

            out.printf("Case %d: %d\n" , t , max);
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
