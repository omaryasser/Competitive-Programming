import javax.jws.soap.SOAPBinding;
import java.io.*;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collector;

/**
 * Created by aa on 1/06/2016.
 */
public class Main {

    static int [] val1 , val2;
    static int N , S;
    static long memo[][][];
    public static long dp (int idx , int x , int y)
    {
        if (x * x + y * y == S * S) return 0;
        if (idx == N || x > S || y > S) return Integer.MAX_VALUE;
        if (memo[idx][x][y] != -1) return memo[idx][x][y];
        return memo[idx][x][y] = Math.min(1 + dp(idx , x + val1[idx] , y + val2[idx]) , dp(idx + 1 , x , y));
    }
    public static void main(String [] args) throws  Exception
    {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();
        int T = sc.nextInt();
        while (T -- > 0)
        {
            N = sc.nextInt() ; S =sc.nextInt();
            val1 = new int[N];
            val2 = new int[N];
            memo = new long[N][S + 1][S + 1];
            for (int i = 0 ; i < N ; ++i) for (int j = 0 ; j < S + 1 ; ++j) Arrays.fill(memo[i][j] , -1);
            for (int i = 0 ; i < N ; ++i)
            {
                val1[i] = sc.nextInt();
                val2[i] = sc.nextInt();
            }
            long res = dp(0,0,0);
            if (res >= Integer.MAX_VALUE)
                out.print("not possible\n");
            else out.printf("%d\n" , res);
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
