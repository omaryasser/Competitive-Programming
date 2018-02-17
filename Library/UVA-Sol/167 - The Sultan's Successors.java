
import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

public class Main {
    static int board [][];
    static int max ;
    static int row [];
    public static void backtrack (int col)
    {
        if(col == 8) {
            maximize ();
            return;
        }

        for(int i = 0 ; i < 8 ; ++i)
            if(available(i , col))
            {
                row[col] = i;
                backtrack(col + 1);
                row[col] = Integer.MAX_VALUE;
            }
    }

    public static void maximize ()
    {
        int sum = 0;
        for(int i = 0 ; i < 8; ++i) sum += board[row[i]][i];
        max = Math.max(max , sum);
    }

    public static boolean available (int r , int c)
    {
        for(int i = 0 ; i < c ; ++i)
            if(row[i] == r || Math.abs(row[i] - r) == Math.abs(i - c) )
                return false;
        return true;
    }
    public static void main(String[]args) throws Exception {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();
        int T = sc.nextInt();
        while (T -- > 0)
        {
            board = new int[8][8];
            row = new int[8];
            Arrays.fill(row , Integer.MAX_VALUE);
            for(int i = 0 ; i < 8 ; ++i) for(int j = 0 ; j < 8 ; ++j) board[i][j] = sc.nextInt();
            max = 0;
            backtrack(0);
            out.printf("%5d\n" , max);
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
