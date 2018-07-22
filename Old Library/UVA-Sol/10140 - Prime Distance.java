

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class Main {

    static int MAX = 46341 , MAX_IDX;
    static int primes [] ;
    static boolean prime [];
    public static void seive ()
    {
        MAX_IDX = 0;
        primes = new int [46342];
        prime = new boolean[MAX + 1];

        prime[0] = prime[1] = true;
        for (int i = 2 ; i<= MAX ; ++i)
            if (!prime[i]) {
                primes[MAX_IDX ++] = i;
                for (int j = i + i; j <= MAX; j += i)
                    prime[j] = true;
            }
    }

    public static boolean isPrime (int N)
    {
        if (N < MAX) return !prime[N];
        for (int i = 0 ; i < MAX_IDX && primes[i] < N; ++i)
            if (N % primes[i] == 0)
                return false;
        return true;
    }
    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();
        seive();
        int t=  0;
        while (sc.ready())
        {
            int l = sc.nextInt() , u = sc.nextInt();
            int farL = - 1 , nearL = - 1 , nearR = - 1 , farR = -1 , last = - 1;
            for (int i = l ; i <= u ; ++i){
                {
                    if (isPrime(i))
                    {
                        if (nearL == -1)
                            nearL = farL = i;

                        else if (nearR == -1)
                            nearR = farR = i;
                        else
                        {
                            if (nearR - nearL > i - last)
                            {
                                nearL = last;
                                nearR = i;
                            }
                            if (farR - farL < i - last)
                            {
                                farL = last;
                                farR = i;
                            }
                        }
                        last = i;
                    }
                }
                if (i == 2147483647) break;
            }
            if (nearR == -1 || nearL == -1)
                sb.append("There are no adjacent primes.\n");
            else sb.append(nearL +","+nearR+" are closest, "+farL+","+farR+" are most distant.\n");
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
