
import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

public class Main {

    public static void main(String[]args) throws Exception {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();
        for(int x = 1 ; x * 4 <= 2000 ; ++x)
            for(int y = x ; 3 * y + x <= 2000 ; ++y)
                for(int z = y ; 2 * z + y + x <= 2000 ; ++z)
                {

                    int A = x + y + z;
                    int B = x * y * z ;

                    if(B <= 1000000) continue;

                    int f = (A * 1000000)/ (B - 1000000);
                    if(f < z || y + z + x + f >2000 || (x+y+z+f)*1000000 != x*y*z*f) continue;
                    DecimalFormat format = new DecimalFormat("0.00");
                    sb.append(format.format(x / 100.0) + " " + format.format(y / 100.0) + " " + format.format(z / 100.0) + " " + format.format(f / 100.0) +"\n");
                }
        out.print(sb.toString());
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
