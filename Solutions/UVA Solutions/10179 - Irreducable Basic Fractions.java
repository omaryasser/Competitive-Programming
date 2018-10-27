import java.io.*;
import java.util.*;


public class Main {

    static int MAX = 31623 ;
    static int primes [] = new int[MAX  +1];
    static int N = 0;

    static void seive ()
    {
        boolean isComposite [] = new boolean[MAX  +1];
        isComposite[0] = isComposite[1] = true;
        for (int i = 2 ; i <= MAX ; ++i)
            if (!isComposite[i])
            {
                primes[N++] = i;
                for (int j = i + i ; j <= MAX  ; j += i)
                    isComposite[j] = true;
            }
    }

    static long eulerphi (int n)
    {
        long ans = n ;
        for (int PF_IDX = 0 , PF = 2 ; PF * PF <= n ; PF = primes[++PF_IDX])
        {
            if (n % PF == 0)
            {
                ans -= ans / PF ;
                while (n % PF == 0) {n /= PF;}
            }
            if (PF_IDX == N - 1) break;
        }
        if (n != 1)
            ans -= ans / n ;
        return ans ;
    }
    public static void main(String [] args) throws  Exception
    {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();
        seive();
        while (1 == 1)
        {
            int n = sc.nextInt();
            if (n == 0) break;

            out.printf("%d\n" , eulerphi (n));
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
