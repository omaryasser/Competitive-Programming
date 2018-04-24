
import java.io.*;
import java.util.*;

public class Main {


    public static void main(String[]args) throws Exception {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();
        while (sc.ready())
        {
            int n = sc.nextInt() , m = sc.nextInt();
            int arr [][] = new int[m][n];
            for(int i = 0 ; i < n ; ++i)
            {
                int num = sc.nextInt();
                int tmp [] = new int[num];
                for(int j = 0 ; j < num ; ++j) tmp[j] = sc.nextInt();
                for(int j = 0 ; j < num ; ++j) arr[tmp[j] - 1][i] = sc.nextInt();
            }

            out.printf("%d %d\n" , m , n);
            for (int i = 0 ; i < m ; ++i)
            {
                int cnt = 0;
                for(int j = 0 ; j < n ; ++j) if (arr[i][j]!=0) ++cnt;
                out.printf("%d",cnt);
                for(int j = 0 ; j < n ; ++j) if (arr[i][j]!=0)out.printf(" %d",j + 1);
                out.printf("\n");
                boolean first = true;
                for(int j = 0 ; j < n ; ++j) 
                    if (arr[i][j]!=0)
                        if (first) {out.printf("%d" , arr[i][j]); first = false ;}
                        else out.printf(" %d" , arr[i][j]);
                
                out.printf("\n");
            }
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
