
import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {



    public static void main (String [] args) throws IOException {

        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();
        int T = sc.nextInt();
        int arr [] = {0,60,300,1500,15000};
        BigInteger fib [] = new BigInteger[15002];
        fib[0] = BigInteger.ZERO;
        fib[1] = BigInteger.ONE;
        for (int i = 2 ; i <= 15001 ; ++i)
            fib[i] = fib[i - 1].add(fib[i - 2]);

        while (T -- > 0)
        {
            int a = sc.nextInt() , b = sc.nextInt() , n = sc.nextInt() , m = sc.nextInt();

            n %= arr[m];
            String wanted = ((new BigInteger((b) + "").multiply(fib[n])).add(new BigInteger(a +"").multiply(n == 0 ? BigInteger.ONE: fib[n - 1]))).toString();
            sb.append(Integer.parseInt(wanted.substring(Math.max(0,wanted.length() - m) , wanted.length()))+"\n");
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
