import java.io.*;
import java.util.*;


public class F {

    public static void main(String [] args) throws  Exception
    {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();

        int n = 100010041;
        boolean primes [] = new boolean[n+1];
        Arrays.fill(primes, true);
        primes[0]=primes[1]=false;
        for(int i =0 ; i*i<=n; i++)
            if(primes[i])
                for(int j =2 ;i*j <=n ; j++)
                    primes [i*j]=false;

        int cum [] = new int[n+1];
        for (int i = 0 ; i < 10000 + 1 ; ++i)
        {
            int num = i * i + i + 41 ;
            if (i == 0) cum[i] = primes[num] ? 1 : 0 ;
            else cum[i] = cum[i - 1] + (primes[num] ? 1 : 0 );
        }
        int t = 0 ;
        //1029
        while (sc.ready())
        {
            int a  = sc.nextInt() , b = sc.nextInt();
            if (a > b)
            {
                int tt = b;
                b = a;
                a = tt;
            }
            out.printf("%.2f\n" , Math.max(0 ,100.0 * (cum[b] - (a == 0 ? 0 : cum[a - 1])) / (double)(b - a + 1)));
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
