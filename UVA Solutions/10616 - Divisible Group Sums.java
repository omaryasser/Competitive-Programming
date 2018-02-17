

import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int arr [];
    static int D;
    static long memo [][][];
    public static long dp (int idx , int rem , int mod)
    {
        if (rem == 0)
            if (mod == 0) return 1;
            else return 0;
        if (idx == N) return 0;

        if (memo[idx][rem][mod] != -1) return memo[idx][rem][mod];
        long take = dp(idx + 1 , rem - 1 , (((mod + arr[idx]) %D) + D) % D );
        long leave = dp(idx + 1 , rem , mod);
        return memo[idx][rem][mod] = take + leave;
    }
    public static void main(String[]args) throws Exception {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();
        int tt = 1;
        while (true)
        {
            N = sc.nextInt() ; int  Q = sc.nextInt();
            if (N == 0 &&  Q == 0) break;
            arr = new int[N];
            for (int i = 0 ; i < N ; ++i) arr[i] = sc.nextInt();

            out.printf("SET %d:\n" , tt++);
            for (int q = 1 ; q <= Q ; ++q)
            {
                D = sc.nextInt() ; int M = sc.nextInt();
                memo = new long [N][M + 1][D];
                for (int i = 0 ; i < N ; ++i) for (int j = 0 ; j < M + 1 ; ++j) Arrays.fill(memo[i][j] , -1);
                out.printf("QUERY %d: %d\n" , q , dp (0 , M , 0));
            }
        }
        out.flush();
        out.close();
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
