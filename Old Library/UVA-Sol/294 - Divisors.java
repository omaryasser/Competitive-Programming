import java.io.*;
import java.util.*;


public class Main {
    static int MAX = 31623;
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
    static int numDiv (int n)
    {
        int tmp = n;
        int res = 1;
        for (int i = 0 ; i < primes.size() ; ++i)
        {
            int p = primes.get(i);
            if (p * p > n) break;
            int cnt = 0 ;
            if (n % p == 0)
            {
                while (n % p == 0) {n /= p ; ++ cnt;}
                res = res * (cnt + 1);
            }
        }
        if (n != 1) res <<= 1;


        return res;
    }
    public static void main(String [] args) throws  Exception
    {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();
        seive();
        int T = sc.nextInt();
        while (T -- > 0)
        {
            int L = sc.nextInt() , R = sc.nextInt();
            int maxD = 0 , maxNum = L;
            for (int i = L ; i <= R ; ++i)
            {
                int div = numDiv (i);
                if (div > maxD)
                {
                    maxD = div;
                    maxNum = i;
                }
            }

            out.printf("Between %d and %d, %d has a maximum of %d divisors.\n" , L , R , maxNum , maxD);
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
