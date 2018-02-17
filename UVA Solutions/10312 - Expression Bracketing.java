
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static long cat [];
    public static void cat (int n)
    {
        cat = new long[n + 1];
        cat[0] = 1;
        for(int i = 1; i <= n; ++i)
            cat[i] = cat[i-1] * (i<<1) * ((i<<1) - 1) / (i * (i + 1));
    }

    static long memo [][];
    public static long dp (boolean isPrefix , int len)
    {
        if (len <= 1) return 1;
        if (memo[isPrefix ? 1 : 0][len] != -1) return memo[isPrefix? 1 : 0][len];

        long ans = 0;
        for (int i = 1 ; i < len ; ++i)
            ans += (dp(true , i) * dp(false , len - i ));

        return memo[isPrefix ? 1 : 0 ][len] = isPrefix ? ans : ans << 1 ;
    }
    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();

        cat(26);
        memo = new long[2][27];
        Arrays.fill(memo[0] , -1);
        Arrays.fill(memo[1] , -1);
        while (sc.ready())
        {
            int n = sc.nextInt();
            sb.append((dp(true,n) - cat[n - 1]) + "\n");
        }
        out.print(sb.toString());
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
