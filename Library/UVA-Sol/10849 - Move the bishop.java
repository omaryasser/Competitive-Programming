
import java.io.*;
import java.util.*;

/**
 * Created by aa on 1/06/2016.
 */
public class Main {
    static char grid [][];
    static int MAX_R;
    public static void main(String [] args) throws  Exception
    {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();
        int T = sc.nextInt();
        while (T -- > 0){
            int TT = sc.nextInt();
            int N = sc.nextInt();

            while (TT -- > 0){
                int row1 = sc.nextInt();
                int col1 = sc.nextInt();
                int row2 = sc.nextInt();
                int col2 = sc.nextInt();
                if (!valid(row1 , N) || !valid(row2 , N) || !valid(col1 , N) || !valid(col2 , N)) sb.append("no move\n");
                else if (!(row1 == row2 && col1 == col2) && ((Math.abs(Math.abs(row1 - row2) - Math.abs(col1 - col2)))&1) == 1) sb.append("no move\n");
                else if (row1 == row2 && col1 == col2) sb.append("0\n");
                else sb.append(((Math.abs(row1 - row2) == Math.abs(col1 - col2)) ? "1" : "2")  +"\n");
            }
        }
        out.print(sb.toString());
        out.flush();
        out.close();
    }

    static boolean valid (int x , int n){
        return x >= 1 && x <= n ;
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
