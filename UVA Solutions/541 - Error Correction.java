import java.io.*;
import java.util.*;


public class Main {

    public static void main(String [] args) throws  Exception
    {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();
        while (true){
            int n = sc.nextInt();
            if (n == 0) break;
            int col [] = new int[n];
            int row [] = new int[n];
            for (int i = 0 ; i < n ; ++i){
                for (int j = 0 ; j < n ; ++j){
                    int nxt = sc.nextInt();
                    col[j] += nxt;
                    row[i] += nxt;
                }
            }

            int cntRow = 0 , cntCol = 0 ;
            int rowWanted = 0 , colWanted = 0;
            for (int i = 0 ; i < n ; ++i){
                if ((row[i] & 1) == 1) {
                    ++cntRow;
                    rowWanted = i;
                }
            }

            for (int i = 0 ; i < n ; ++i){
                if ((col[i] & 1) == 1) {
                    ++cntCol;
                    colWanted = i;
                }
            }
            if (cntRow == 0 && cntCol == 0) sb.append("OK\n");
            else if (cntRow == 1 && cntCol == 1) sb.append("Change bit (" + ++rowWanted + "," + ++colWanted + ")\n");
            else sb.append("Corrupt\n");

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
