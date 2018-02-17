
import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {

    public static void main (String [] args) throws IOException {
        // Naive solution. The right one is to take (log10(n) + log10(n - 1) + ... + log10 (n - k + 1) )/( log10(k) + log10 (k - 1) + ... + log10(1))
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();
        while (sc.ready()) {
            int n = sc.nextInt() , k = sc.nextInt();
                BigInteger res = BigInteger.ONE;
                for (int i = 1; i <= k; ++i)
                    res =  res.multiply(BigInteger.valueOf(n - i + 1)).divide(BigInteger.valueOf(i));
            sb.append(res.toString().length()+"\n");
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
