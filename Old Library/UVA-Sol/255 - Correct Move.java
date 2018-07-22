
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
        int t = 0 ;
        while (sc.ready()){
            int K = sc.nextInt();
            int Q = sc.nextInt();
            int Q2 = sc.nextInt();
            int kRow = K / 8 , kCol = K % 8;
            int qRow = Q / 8 , qCol = Q % 8;
            int qRow2 = Q2 / 8 , qCol2 = Q2 % 8;

            if (K == Q) {sb.append("Illegal state\n"); continue;}
            if (K == Q2 ||
                Q == Q2 ||
                !(qRow == qRow2 || qCol == qCol2) ||
                (qRow == kRow && (qCol - kCol) * (qCol2 - kCol) < 0) ||
                (qCol == kCol && (qRow - kRow) * (qRow2 - kRow) < 0)) {sb.append("Illegal move\n"); continue;}
            int kingDX [] = {0,0,1,-1};
            int kingDY [] = {1,-1,0,0};
            boolean found = false;
            boolean allowed = false;
            for (int i = 0 ; i < 4 ; ++i){
                {
                    if (qRow2 == kingDX[i] + kRow && qCol2 == kingDY[i] + kCol) found = true;
                    if (valid (kRow + kingDX[i] , kCol + kingDY[i]) && qRow2 != kingDX[i] + kRow && qCol2 != kingDY[i] + kCol) allowed = true;

                }
            }
            if (found) {sb.append("Move not allowed\n"); continue;}
            if (allowed) sb.append("Continue\n");
            else sb.append("Stop\n");

        }
        out.printf(sb.toString());
        out.flush();
        out.close();
    }
    static boolean valid (int x , int y){
        return x >= 0 && y >= 0 && x < 8 && y < 8;
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
