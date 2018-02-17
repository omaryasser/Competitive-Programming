import java.io.*;
import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collector;

/**
 * Created by aa on 1/06/2016.
 */
public class Main {

    static int N , M;
    static int cnt ;
    static int dx [] = {0,0,1,-1,1,1,-1,-1};
    static int dy [] = {1,-1,0,0,1,-1,1,-1};
    static char [][] arr;
    public static boolean valid (int i , int j)
    {
        return i >= 0 && j>= 0 && i < N && j < M && arr[i][j] == '*';
    }
    public static void dfs (int i , int j)
    {
        arr[i][j] = '.';
        ++cnt;
        for (int k = 0 ; k < 8 ; ++k)
            if (valid(i + dx[k] , j + dy[k]))
                dfs(i + dx[k] , j + dy[k]);
    }
    public static void main(String [] args) throws  Exception
    {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();

        while (true)
        {
            N = sc.nextInt(); M = sc.nextInt();
            if (N == 0 && M == 0) break;
            arr = new char[N][M];
            for (int i = 0 ; i < N ; ++i) arr[i] = sc.next().toCharArray();
            int res = 0;
            for (int i = 0 ; i < N ; ++i)
                for (int j = 0 ; j < M ;++j)
                    if (valid(i,j))
                    {
                        cnt = 0;
                        dfs(i,j);
                        if (cnt == 1) ++res;
                    }
            out.printf("%d\n" , res);
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
