
import java.io.*;
import java.util.*;

public class Main {


    static int V;
    static int adjMat [][];
    static int p [][];
    static ArrayList<Integer> res = new ArrayList<>();
    public static int floyd_warshall(int first , int second)
    {
        p = new int[V][V];
        res = new ArrayList<>();
        for(int i = 0; i < V; i++)
            for(int j = 0; j < V; j++)
                p[i][j] = i;
//        System.out.println(p[1][0]);
        for(int k = 0 ; k < V ; ++k)
            for(int i = 0 ; i < V ; ++i)
                for(int j = 0 ; j < V ; ++j)
                {
                    if (adjMat[i][k] + adjMat[k][j] < adjMat[i][j]) {
                        adjMat[i][j] = adjMat[i][k] + adjMat[k][j];
                        p[i][j] = p[k][j];
                    }
                }
//        System.out.println(p[1][0]);
        return adjMat[first][second];
    }

    static void getPath(int i, int j)
    {
        if(i != j) getPath(i, p[i][j]);
        res.add(j);
    }

    public static void main(String [] args) throws  Exception
    {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();
        for (int T = 1 ; ; T ++)
        {
            V = sc.nextInt();
            if (V == 0) break;
            adjMat = new int[V][V];
            for (int i = 0 ; i < V ; ++i)Arrays.fill(adjMat[i] , (int)1e9);
            for (int i = 0 ; i < V ;++i)
            {
                int N = sc.nextInt();
                while (N -- > 0)
                {
                    int v = sc.nextInt() - 1 , w = sc.nextInt();
                    adjMat[i][v] = w;
                }
            }
            int u = sc.nextInt() - 1, v = sc.nextInt() -1 ;
            int time = floyd_warshall(u , v);
            getPath(u , v);
            out.printf("Case %d: Path =" , T);
            for (int i = 0 ;i  < res.size() ; ++i)
                out.printf(" %d" , res.get(i) + 1);
            out.printf("; %d second delay\n" , time);
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
