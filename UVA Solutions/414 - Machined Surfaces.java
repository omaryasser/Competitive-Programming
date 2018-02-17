
import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {



    public static void main(String[]args) throws Exception {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();

        while(true)
        {
            int n = Integer.parseInt(sc.nextLine());
            if(n == 0) break;

            char [][] arr = new char[n][25];
            for(int i = 0 ; i < n ; ++i) {
                String line = sc.nextLine();
                for(int j = 0 ; j < 25 ; ++j)
                    arr[i][j] = line.charAt(j);
            }
            int max = -1 , all = 0;
            for(int i = 0 ; i < n ; ++i)
            {
                int cnt = 0;
                int j = 0;
                while(j < 25 && arr[i][j++]=='X') cnt++;
                j = 24;
                int tmp = cnt;
                while(j >=0 && j!=tmp -1 && arr[i][j--] == 'X') cnt++;

                max = Math.max(max , cnt);
                all +=cnt;
            }

            out.println( ((n-1) * max ) - (all - max));
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
