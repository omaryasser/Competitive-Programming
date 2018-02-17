
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
        while (true){
            int r = sc.nextInt();
            int c = sc.nextInt();
            if (r == 0 && c == 0) break;

            int inRow = (r / 4) * 2 + ((r % 4 == 0) ? 0 : (r % 4 == 1) ? 1 : 2);
            int inCol = (c / 4) * 2 + ((c % 4 == 0) ? 0 : (c % 4 == 1) ? 1 : 2);
            int first = inRow * inCol;

            out.printf("%d knights may be placed on a %d row %d column board.\n" , r == 0 ? 0 : c == 0 ? 0 : (c == 1 || r == 1) ?  Math.max(r , c) : Math.max(first , (r * c + 1) >> 1) , r , c);
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
