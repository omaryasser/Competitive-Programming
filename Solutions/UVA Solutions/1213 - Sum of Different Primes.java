

import java.io.*;
import java.util.*;

public class Main {
    static int prime [];
    static int N;
    static long memo [][][];
    public static long dp (int idx , int remK , int remNum)
    {
        if (remK == 0 && remNum == 0) return 1;
        if (remK == 0 || idx == N) return 0;
        if (memo[idx][remK][remNum] != -1) return memo[idx][remK][remNum];
        int cnt = 0;
        if (prime[idx] <= remNum) cnt +=dp(idx + 1 , remK - 1 , remNum - prime[idx]);
        return memo[idx][remK][remNum] = cnt + dp(idx + 1 , remK , remNum);
    }
    public static void main(String[]args) throws Exception {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();
        while (true)
        {
            int n = sc.nextInt() , k = sc.nextInt();
            if (n == 0 && k ==0) break;
            seive(n);
            memo = new long [N][k + 1][n + 1];
            for (int i = 0 ; i < N ; ++i) for (int j = 0 ; j < k + 1 ; ++j) Arrays.fill(memo[i][j] , - 1);
            out.printf("%d\n" , dp (0 , k , n));
        }
        out.flush();
        out.close();
    }


    public static void seive (int n)
    {
        boolean primes [] = new boolean[n+1];
        Arrays.fill(primes, true);
        primes[0]=primes[n == 0 ? 0 : 1]=false;
        for(int i = 2 ; i*i<=n; i++)
            if(primes[i])
                for(int j =2 ;i*j <=n ; j++)
                    primes [i*j]=false;

        N = 0 ;
        for (int i = 0 ; i <= n ; ++i)
            if (primes[i]) ++N;
        prime = new int[N];
        for (int i = 0 , idx = 0; i <= n ; ++i)
            if (primes[i])
                prime[idx ++] = i;
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
