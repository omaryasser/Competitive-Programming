import java.io.*;
import java.util.*;


public class H {

    public static void main(String [] args) throws  Exception
    {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();
        int complete [] = new int[31];
        int with_out_square [] = new int[31];
        complete[0] = with_out_square[1] = 1 ; complete[1] = with_out_square[0] = 0;
        complete[2] = 3;
        for (int i = 3 ; i < 31 ; ++i)
        {
            complete[i] = complete[i - 2] + 2 * with_out_square[i - 1];
            with_out_square[i] = complete[i - 1] + with_out_square[i - 2];
        }

        while (true)
        {
            int n = sc.nextInt();
            if (n == - 1) break;

            out.printf("%d\n" , ((n & 1) == 1) ? 0 : complete[n] + with_out_square[n]);
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
