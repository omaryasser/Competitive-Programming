import java.io.*;
import java.math.BigInteger;
import java.util.*;


public class J {

    public static void main(String [] args) throws  Exception
    {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();
        int T = sc.nextInt();
        while (T -- > 0)
        {

            BigInteger p [] = new BigInteger[41];
            int n = 0;
            for (int i = 0 ; true ; ++i)
            {
                p[i] = new BigInteger(sc.next());
                if (p[i].compareTo(BigInteger.ZERO) == 0) break;
                n ++;
            }

            Arrays.sort(p , 0 , n);


            BigInteger res = BigInteger.ZERO;
            boolean ok = true;
            for (int i = n - 1 ; i >= 0 ; --i)
            {
                res = res.add((BigInteger.valueOf(2).multiply(p[i].pow(n - i))));
                if (res.compareTo(BigInteger.valueOf(5000000)) > 0)
                {
                    ok = false;
                    break;
                }
            }

            if (ok)
                out.print(res.toString() +'\n');
            else out.print("Too expensive\n");



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
