
import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class Main {

    public static void main(String[]args) throws Exception {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        while (sc.ready())
        {
            int N = sc.nextInt() ,  M = sc.nextInt();
            int A[][] = new int[N][N];
            for (int i = N -1 ; i >= 0 ; --i) for (int j = 0 ; j < N ; ++j) A[i][j] = sc.nextInt();
            for(int i =0 ; i < N ; i ++)
                for(int j =0 ; j < N ; j++)
                {
                    if(i>0)A[i][j]+=A[i-1][j];
                    if(j>0)A[i][j]+=A[i][j-1];
                    if(i>0 && j >0)A[i][j]-=A[i-1][j-1];
                }

            if (first) first = false;
            else out.printf("\n");
            int sum = 0;
            for (int i = N - M ; i >= 0 ; i--)
                for (int j = 0 ; j <= N - M ; ++j)
                {
                    int k = i + M - 1 , l = j + M - 1;
                    int sub = A[k][l];
                    if(i>0)sub-=A[i-1][l];
                    if(j>0)sub-=A[k][j-1];
                    if(i>0 && j>0)sub+=A[i-1][j-1];
                    sum += sub;
                    out.printf("%d\n" , sub);
                }
            out.printf("%d\n" , sum);

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
