import javax.jws.soap.SOAPBinding;
import java.io.*;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collector;

/**
 * Created by aa on 1/06/2016.
 */
public class Main {

    static int  N , arr [] , ALL;
    static Pair memo [][];
    static class Pair
    {
        long val , coins;
        Pair(long v , long c) {val = v ; coins = c;}
    }

    public static Pair dp (int idx , int sum)
    {
        if (idx == N)
            if (sum < ALL) return new Pair(Integer.MAX_VALUE , Integer.MAX_VALUE);
            else return new Pair(0,0);
        if (sum > ALL)
            return new Pair(0,0);

        if (memo[idx][sum] != null) return memo[idx][sum];
        Pair take = dp(idx + 1, sum + arr[idx]);
        Pair leave = dp(idx + 1 , sum);

        if (take.val + arr[idx] < leave.val) return memo[idx][sum] = new Pair(take.val + arr[idx] , take.coins + 1);
        else if (take.val + arr[idx] > leave.val) return memo[idx][sum] = leave;
        else return memo[idx][sum] = take.coins + 1 < leave.coins ? new Pair(take.val + arr[idx] , take.coins + 1) : leave;
    }
    public static void main(String [] args) throws  Exception
    {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();
        int T = sc.nextInt();
        while (T -- > 0)
        {
            ALL = sc.nextInt();
            N = sc.nextInt();
            memo = new Pair[N][10001];
            arr = new int[N];
            for (int i = 0 ; i < N ; ++i) arr[i] = sc.nextInt();
            Pair best = dp (0 , 0);
            out.printf("%d %d\n" , best.val , best.coins);
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
