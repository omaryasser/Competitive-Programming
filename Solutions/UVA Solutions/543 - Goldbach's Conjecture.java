

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class Main {


    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();
        boolean primes [] = new boolean[(int)(1e6) + 1];
        Arrays.fill(primes , true);
        primes[0] = primes[1] = false;
        for (int i = 2 ; i * i <= (int) (1e6) + 1 ; ++i)
            if (primes[i])
                for (int j = i + i ; j <= (int)(1e6) ; j += i)
                    primes[j] = false;
        while(true)
        {
            int n = sc.nextInt();
            if (n == 0) break;
            boolean found = false;
            for (int i = n ; i >= n >> 1  ; --i)
            {
                if (n - i > 2 && primes[i] && primes[n - i])
                {
                    found = true;
                    sb.append(n + " = " + (n - i) + " + " + i +"\n");
                    break;
                }
            }

            if (!found)
                sb.append("Goldbach's conjecture is wrong.\n");

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
