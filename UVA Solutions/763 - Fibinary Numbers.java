
import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {



    public static void main (String [] args) throws IOException {

        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();
        BigInteger [] fib = new BigInteger[482];
        fib[0] = BigInteger.ZERO;
        fib[1] = BigInteger.ONE;
        for (int i = 2; i <= 481 ; ++i)
            fib[i] = fib[i - 1] .add(fib[i - 2]);
        boolean firstT = true;
        int t = 0;
        while (sc.ready())
        {
            if (firstT) firstT = false;
            else sb.append("\n");
            String x = sc.next() , y = sc.next();
            BigInteger first = BigInteger.ZERO , second = BigInteger.ZERO;
            for (int i = x.length() - 1 ; i >= 0 ; --i)
                if (x.charAt(i) == '1')
                    first = first.add(fib[x.length() - i + 1]);

            for (int i = y.length() - 1 ; i >= 0 ; --i)
                if (y.charAt(i) == '1')
                    second = second.add(fib[y.length() - i + 1]);
            first = first.add(second);
            if (first.compareTo(BigInteger.ZERO) == 0){sb.append("0\n"); continue;}
            boolean found = false;
            for (int i = 481 ; i > 1 ; --i)
                if (fib[i].compareTo(first) <= 0)
                {
                    found = true;
                    sb.append('1');
                    first = first.subtract(fib[i]);
                }
                else if (found) sb.append('0');

            sb.append("\n");

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
