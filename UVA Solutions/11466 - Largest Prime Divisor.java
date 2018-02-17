import java.io.*;
import java.util.*;


public class Main {
    static ArrayList<Integer> primes = new ArrayList<>();
    public static void seive(int n)
    {
        boolean isPrime [] = new boolean[n + 1];
        isPrime[0] = isPrime [1] = true;
        for (int i = 2 ; i  <= n ; ++i)
            if (!isPrime[i])
            {
                primes.add(i);
                if (1l * i * i <= n)
                for (int j = i + i ; j <= n ; j += i)
                    isPrime[j] = true;
            }
    }
    public static void main(String [] args) throws  Exception
    {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();
        int N = 4 * (int) 1e7;
        seive(N);
        while (true)
        {
            long n = sc.nextLong();
            if (n == 0) break;
            n = Math.abs(n);
            int cnt = 0 ; long  ans = - 1;
            for (int i = 0 ; i < primes.size() ; ++i)
            {
                int prime = primes.get(i);
                if (1L * prime * prime > n) break;

                if (n % prime == 0)
                {
                    while (n % prime == 0) n /= prime;
                    cnt ++;
                    ans = prime;
                }
            }

            if (n != 1) {cnt ++ ; ans = n;}
            out.printf("%d\n" , cnt <= 1 ? - 1: ans);
        }
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
