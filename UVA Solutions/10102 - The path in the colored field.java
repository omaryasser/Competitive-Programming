
import java.io.*;
import java.util.*;

public class Main {



    public static void main(String[]args) throws Exception {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();

        while (sc.ready())
        {
            int M = sc.nextInt();
            char [][] arr = new char[M][M];
            for(int i = 0 ; i < M ; ++i) arr[i] = sc.next().toCharArray();

            int max = Integer.MIN_VALUE;
            for(int i = 0 ; i < M ; ++i)
                for(int j = 0 ; j < M ; ++j)
                {
                    if (arr[i][j] != '1') continue;
                    int min = Integer.MAX_VALUE;
                    for(int a = 0 ; a < M ; ++a)
                        for(int b = 0 ; b < M ; ++b)
                            if(arr[a][b] == '3') min = Math.min(min , Math.abs(a - i) + Math.abs(b - j));
                    max = Math.max(max , min);
                }
            out.printf("%d\n" , max);
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
