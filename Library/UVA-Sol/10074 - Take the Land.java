
import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class Main {

    public static void main(String[]args) throws Exception {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();
        while (true)
        {
            int N = sc.nextInt() ,  M = sc.nextInt();
            if (N == 0 && M ==0)break;
            long A[][] = new long[N][M];
            for(int i =0 ; i < N ; i ++)
                for(int j =0 ; j < M ; j++)
                {
                    A[i][j]=sc.nextInt() == 0 ? 1 : Integer.MIN_VALUE;
                    if(i>0)A[i][j]+=A[i-1][j];
                    if(j>0)A[i][j]+=A[i][j-1];
                    if(i>0 && j >0)A[i][j]-=A[i-1][j-1];
                }

            long maxSubRect = Long.MIN_VALUE;
            for(int i =0 ; i < N ;i ++)
                for(int j =0 ; j < M;j ++)
                    for(int k =i ; k < N ; k++)
                        for(int l =j ;l < M ; l++)
                        {
                            long sub = A[k][l];
                            if(i>0)sub-=A[i-1][l];
                            if(j>0)sub-=A[k][j-1];
                            if(i>0 && j>0)sub+=A[i-1][j-1];
                            maxSubRect= Math.max(sub, maxSubRect);
                        }


            out.printf("%d\n" , Math.max(maxSubRect , 0 ));

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
