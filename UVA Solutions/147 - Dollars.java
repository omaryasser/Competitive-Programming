

import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

public class Main {
    static int arr [] = {5,10,20,50,100,200,500,1000,2000,5000,10000};
    static long memo[][];
    public static long dp (int idx , int rem)
    {
        if (rem == 0) return 1;
        if (idx == 11) return 0;
        if (memo[idx][rem] != -1) return memo[idx][rem];

        return memo[idx][rem] = (rem >= arr[idx] ? dp(idx , rem - arr[idx]) : 0) + dp(idx + 1 , rem);
    }
    public static void main(String[]args) throws Exception {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();
        memo = new long[11][30000 + 11];
        for (int i = 0 ; i < 11 ; ++i)Arrays.fill(memo[i] , -1);
        dp(0 , 30000);
        while (true)
        {
            String inp = sc.next();
            StringTokenizer st = new StringTokenizer(inp , ".");
            String first = st.nextToken() , second = st.nextToken();
            int num = Integer.parseInt(first) * 100 + Integer.parseInt(second);
            if (num == 0) break;
            while (inp.length() < 6) inp = " " + inp;
            out.printf(inp + "%17d\n" ,  memo[0][num]);
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
