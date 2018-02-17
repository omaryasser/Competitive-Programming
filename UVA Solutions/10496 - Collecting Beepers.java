import javax.jws.soap.SOAPBinding;
import java.io.*;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.DoubleStream;

/**
 * Created by aa on 1/06/2016.
 */
public class Main {
    static int R , C ;
    static int N ;
    static int dist [][];
    public static int dp (int idx , int mask)
    {
        if (mask == (1 << N) - 1)
            return dist[idx][0];

        int min = (int)(1e9);
        for (int i = 0 ; i < N  ; ++i)
                if (((1 << i) & mask) == 0)
                    min = Math.min(min , dist[idx][i] + dp(i , mask | (1 << i)));
        return min;
    }
    public static void main(String [] args) throws  Exception
    {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();
        int T = sc.nextInt();
        while (T -- > 0)
        {
            R = sc.nextInt() ; C = sc.nextInt();
            int x = sc.nextInt() , y = sc.nextInt();
            N = sc.nextInt() + 1;
            int points [][] = new int[N][2];
            points[0][0] = x; points[0][1] = y;
            for (int i  = 1 ; i < N ; ++i){points[i][0] = sc.nextInt() ; points[i][1] = sc.nextInt();}
            dist = new int[N][N];
            for (int i = 0 ; i < N ; ++i) for (int j = 0 ; j < N ; ++j) dist[i][j] = dist[j][i] = Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]);

            out.printf("The shortest path has length %d\n" , dp(0 ,1));
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
