
import java.io.*;
import java.util.*;

public class Main {

    public static boolean valid (int x , int y)
    {
        return  x >= 0 && y>=0 && x<=1025 && y<=1025;
    }
    public static void main(String[]args) throws Exception {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();
        int T = sc.nextInt();
        while (T -- > 0)
        {
            int d = sc.nextInt();
            int done [][] = new int[1025][1025];

            int N = sc.nextInt();
            while (N --  >0)
            {
                int x = sc.nextInt() , y = sc.nextInt() , num = sc.nextInt();
                for(int i = Math.max(x - d , 0) ; i <= Math.min(x + d , 1024) ; ++i)
                    for(int j = Math.max(y - d , 0) ; j <= Math.min(y + d , 1024) ; ++j)
                        done[i][j]+=num;
            }

            int x = 0 , y = 0 , max = 0;
            for(int i = 0 ; i < 1025 ; ++i)
                for(int j = 0 ; j < 1025 ; ++j)
                    if (done[i][j] > max)
                    {
                        x = i ;
                        y = j ;
                        max = done[i][j];
                    }

            out.printf("%d %d %d\n" , x , y , max);
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
