import java.io.*;
import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collector;

/**
 * Created by aa on 1/06/2016.
 */
public class A {

    static int val [] = {1 , 5 , 10 , 25 , 50};
    static long memo[][];
    public static long dp (int idx , int rem)
    {
        if (idx == 5)
            if (rem == 0) return 1;
            else return 0;
        if (rem == 0) return 1;

        if (memo[idx][rem] != -1) return memo[idx][rem];
        return memo[idx][rem] = (val[idx] <= rem ? dp(idx , rem - val[idx]) : 0) + dp(idx + 1 , rem);
    }
    public static void main(String [] args) throws  Exception
    {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();
        memo = new long[5][30001];
        for (int i = 0 ; i < 5 ; ++i) Arrays.fill(memo[i] , -1);
        int t = 0;
        while (sc.ready())
        {
            int n = sc.nextInt();
            long res = dp(0 , n);
            if (res != 1)
                out.printf("There are %d ways to produce %d cents change.\n" , res , n);
            else out.printf("There is only %d way to produce %d cents change.\n" , res , n);
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
