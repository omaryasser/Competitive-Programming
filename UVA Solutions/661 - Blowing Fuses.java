import java.io.*;
import java.util.*;

/**
 * Created by aa on 1/06/2016.
 */
public class Main {

    public static void main(String [] args) throws  Exception
    {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();
        for (int t = 1 ; ; ++ t){
            int n = sc.nextInt();
            int m = sc.nextInt();
            int c = sc.nextInt();

            if (n == 0 && m == 0 && c == 0) break;

            long cap [] = new long[n];
            for (int i = 0 ; i < n ; ++i) cap[i] = sc.nextLong();

            long max = 0;
            long cnt = 0;
            boolean blown = false;
            for (int i = 0 ; i < m ; ++i){
                int idx = sc.nextInt() - 1;
                cnt += cap[idx];
                cap[idx] *= -1;
                if (cnt > c) blown = true;
                max = Math.max(max , cnt);
            }

            out.printf("Sequence %d\n" , t);
            if (blown) out.print("Fuse was blown.\n\n");
            else out.printf("Fuse was not blown.\nMaximal power consumption was %d amperes.\n\n" , max);
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
