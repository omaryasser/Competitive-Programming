import java.io.*;
import java.util.*;

public class A {

    public static void main(String [] args) throws  Exception
    {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(sc.nextLine());
        boolean first = true;
        while (T -- > 0)
        {
            sc.nextLine();
            StringTokenizer p = new StringTokenizer(sc.nextLine());
            StringTokenizer arr = new StringTokenizer(sc.nextLine());

            String x [] = new String[arr.countTokens()];
            for (int i = 0 ; i < x.length ; ++i)
                x[Integer.parseInt(p.nextToken()) - 1] = arr.nextToken();

            if (first) first = false;
            else sb.append("\n");

            for (int i = 0 ; i < x.length ; ++i)
                sb.append(x[i] + "\n");
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
