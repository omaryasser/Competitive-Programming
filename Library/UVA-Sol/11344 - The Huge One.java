import java.io.*;
import java.math.BigInteger;
import java.util.*;


public class Main {
    static char [] num;
    public static void main(String [] args) throws  Exception
    {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();
        int T = sc.nextInt();
        while (T -- > 0)
        {
            String inp = sc.next();
            num = inp.toCharArray();
            int n = sc.nextInt();
            int arr [] = new int[n];
            for (int i = 0 ; i < n ; ++i) arr[i] = sc.nextInt();
            boolean ok = true;
            for (int i = 0 ; i < n && ok; ++i)
                if (!check (arr[i]))
                    ok = false;

            out.printf("%s - %s.\n" , inp , ok ? "Wonderful" : "Simple");
        }
        out.flush();
        out.close();
    }

    static boolean check (int div)
    {
        int mod = 0;
        for (int i = 0 ; i < num.length ; ++i)
        {
            mod = ((mod * 10) + num[i] - '0') % div ;
        }

        return mod == 0;

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
