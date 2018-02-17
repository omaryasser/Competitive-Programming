

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;
// TOP DOWN 
// BOTTOM UP is in the bottom .
public class Main {
    static int N ;
    static int [] val ;
    static int memo [][];
    public static int dp (int idx , int remSum)
    {
        if (idx == N) return 0;
        if (memo[idx][remSum] != -1) return memo[idx][remSum];
        int take = remSum - val[idx] >= 0 ? val[idx] + dp(idx + 1 , remSum - val[idx]) : 0;
        int leave = dp(idx + 1 , remSum);
        return memo[idx][remSum] = Math.max(take , leave);
    }
    public static void main(String[]args) throws Exception {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();
        int T = sc.nextInt();
        while (T -- > 0)
        {
            N = sc.nextInt(); val = new int[N];
            memo = new int[N][100 * 501];
            for (int i = 0 ; i < N ; ++i) Arrays.fill(memo[i] , -1);
            int all = 0;
            for (int i = 0 ; i < N ; ++i) {val[i] = sc.nextInt(); all += val[i];}
            int nearest = dp (0 ,all / 2);
            out.printf("%d\n" , Math.abs(all - nearest - nearest));
        }
        out.flush();
        out.close();
    }

// BOTTOM UP
/*
    static int N ;
    static int [] val ;
    public static int ans (int sum)
    {
        int dp [][] = new int[N][sum + 1];
        if (N != 0) for (int i = 0 ; i < sum + 1 ; ++i) dp[0][i] = val[0] <= i ? val[0] : 0;
        for (int i = 1 ; i < N ; ++i)
            for (int j = 0 ; j < sum + 1; ++j)
                dp[i][j] = val[i] <= j ? Math.max(dp[i - 1][j] , val[i] + dp[i - 1][j - val[i]]) : dp[i - 1][j];
        return N == 0 ? 0 : dp[N - 1][sum];
    }
    public static void main(String[]args) throws Exception {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();
        int T = sc.nextInt();
        while (T -- > 0)
        {
            N = sc.nextInt(); val = new int[N];
            int all = 0;
            for (int i = 0 ; i < N ; ++i) {val[i] = sc.nextInt(); all += val[i];}
            int nearest = ans (all / 2);
            out.printf("%d\n" , Math.abs(all - nearest - nearest));
        }
        out.flush();
        out.close();
    }



*/










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
