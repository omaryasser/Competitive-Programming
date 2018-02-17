import java.io.*;
import java.util.*;


public class Main {

    public static void main(String [] args) throws  Exception
    {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(sc.nextLine());
        while (n -- > 0)
        {
            StringTokenizer st = new StringTokenizer(sc.nextLine());
            long arr [] = new long[st.countTokens()];
            for (int i = 0 ; i < arr.length ; ++i)
                arr[i] = Long.parseLong(st.nextToken());

            long max =  1 ;
            for (int i = 0 ; i < arr.length - 1 ; ++i)
                for (int j = i + 1 ; j < arr.length ; ++j)
                    max = Math.max(max , gcd(arr[i] , arr[j]));

            out.printf("%d\n" , max);

        }
        out.flush();
        out.close();
    }


    public static long gcd (long a , long b)
    {
        while (b > 0)
        {
            long t = a % b;
            a = b;
            b = t;
        }
        return a;
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
