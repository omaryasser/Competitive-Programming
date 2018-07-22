import java.io.*;
import java.util.*;


public class Main {

    static int nCr(int N, int K)
    {
        if(K > N)
            return 0;
        int res = 1;
        for(int i = 1; i <= K; ++i)
            res = (N - i + 1) * res / i;
        return res;
    }


    static long D (int n , int m)
    {
        return D(n - m) * nCr(n , m);
    }

    static long D(int n){
        if(n <= 1) return 0;
        else if(n == 2) return 1;
        else return (n-1)*(D(n-1) + D(n-2));
    }


    public static void main(String [] args) throws  Exception
    {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();
        while (sc.ready())
        {
            int n = sc.nextInt() , m = sc.nextInt();
            long ans = 0 ;
            for (int i = m ; i >= 0 ; --i)
                ans += D(n , i);

            out.printf("%d\n" , n == m ? ans + 1 : ans);
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
