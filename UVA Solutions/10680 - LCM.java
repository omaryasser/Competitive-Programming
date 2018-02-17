import java.io.*;
import java.util.*;


public class Main {
    static int MAX = (int)1e6;
    static ArrayList<Integer> primes = new ArrayList<>();
    static void seive ()
    {
        boolean isPrime [] = new boolean[MAX + 1];
        Arrays.fill(isPrime , true);
        isPrime[0] = isPrime[1] = false;
        for (int i = 2 ; i <= MAX ; ++i)
            if (isPrime[i])
            {
                primes.add(i);
                for (int j = i + i ; j <= MAX ; j += i)
                    isPrime[j] = false;
            }
    }
    public static void main(String [] args) throws  Exception
    {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();
        seive();
        while (true)
        {
            int n = sc.nextInt();
            if (n == 0) break;

            int twos = 0 , fives = 0;
            for (int i = 2 ; i <= n ; i <<= 1 , ++twos);
            for (int i = 5 ; i <= n ; i *= 5  , ++fives);

            long res = 1l ;
            for (int i = 0 ; i < twos - fives ; ++i) res = (res << 1) % 10;
            for (int i = 1 ; i < primes.size() ; ++i)
                if (primes.get(i) > n) break;
                else if (primes.get(i) == 5) continue;
                else for (long j = primes.get(i) ; j <= n ; j *= primes.get(i))
                    res = (res * primes.get(i)) % 10;

            out.printf("%d\n" , res);
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
