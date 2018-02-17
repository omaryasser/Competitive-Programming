import java.io.*;
import java.util.*;


public class Main {

    static long mu [] ;
    static long M [] ;
    static int NOT_SQUARE_FREE = -3;

    static void seive (int MAX)
    {
        int UN_TOUCHED = 0;
        mu= new long[MAX + 1];

        mu[1] = 1;
        for (int i = 2 ; i <= MAX ; ++i)
            if (mu[i] == UN_TOUCHED)
            {
                mu[i] = 1;
                for (int j = i + i ; j <= MAX ; j += i)
                    if (mu[j] != NOT_SQUARE_FREE)
                        mu[j] = j % (1l * i * i) == 0 ? NOT_SQUARE_FREE : mu[j] + 1;
            }
        M = new long[MAX + 1];
        for (int i = 1 ; i <= MAX ; ++i)
            M[i] = M[i - 1] + ((mu[i] == NOT_SQUARE_FREE) ? 0 : mu[i] % 2 == 0 || i == 1 ? 1 : - 1);
    }
    public static void main(String [] args) throws  Exception
    {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();
        seive ((int)1e6);
        while (true)
        {
            int n = sc.nextInt();
            if (n == 0) break;

            out.printf("%8d%8d%8d\n" , n , n == 1 ? 1 : mu[n] == NOT_SQUARE_FREE ? 0 : mu[n] % 2 == 0 ? 1 : - 1 , M[n]);
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
