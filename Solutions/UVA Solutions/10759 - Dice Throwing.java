import java.io.*;
import java.util.*;


public class Main {
    static int n , m ;
    static long memo[][];
    public static void main(String [] args) throws  Exception
    {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();
        while (1 == 1)
        {
            n = sc.nextInt() ; m = sc.nextInt();
            if (n == 0 && m == 0) break;
            memo = new long[n + 1][145];
            for (int i = 0 ; i <= n ; ++ i)
                Arrays.fill(memo[i] , -1);
            long cnt = backtrack(0,0);
            long pow = (long)Math.pow(6 , n);
            long gcd = gcd (cnt , pow);
            if (cnt == pow)
                out.printf("%d\n" , 1);
            else if (cnt == 0)
                out.printf("%d\n" , 0);
            else out.printf("%d/%d\n" , cnt / gcd , pow / gcd);
        }
        out.flush();
        out.close();
    }
    static long backtrack (int idx , int sum)
    {
        if (memo[idx][sum] != -1) return memo[idx][sum];

        if (idx == n)
            return sum >= m ? 1 : 0;

        long res = 0 ;
        for (int i = 1;  i <= 6 ; ++i)
            res += backtrack(idx + 1 , sum + i);
        return memo[idx][sum] = res;
    }
    static long gcd (long a , long b)
    {
        while ( b > 0)
        {
            long t = a % b;
            a = b ;
            b = t;
        }
        return a;
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
