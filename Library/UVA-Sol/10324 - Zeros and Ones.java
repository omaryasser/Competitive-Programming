
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[]args) throws Exception {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();
        int CASE = 1;
        while (sc.ready())
        {
            String inp = sc.nextLine();
            if(inp.length() == 0) break;
            char [] arr = inp.toCharArray();
            int prefix [] = new int[inp.length() + 1];
            for(int i = 1 ; i <= arr.length ; ++i)
                if(i == 1)
                    prefix[1] = arr[i - 1] - '0';
                else prefix[i] = prefix[i-1] + (arr[i - 1] - '0');

            out.printf("Case %d:\n" , CASE++);
            int n = Integer.parseInt(sc.nextLine());
            while(n -- > 0)
            {
                StringTokenizer st = new StringTokenizer(sc.nextLine());
                int i = Integer.parseInt(st.nextToken()) + 1 , j = Integer.parseInt(st.nextToken()) + 1;
                int res = prefix[Math.max(i , j)] - prefix[Math.min(i , j) - 1] ;
                out.print( (res == 0 || res == Math.max(i , j) - Math.min(i , j) + 1) ? "Yes\n" : "No\n");
            }
        }
        out.flush();
        out.close();
    }
    static  class Scanner
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
