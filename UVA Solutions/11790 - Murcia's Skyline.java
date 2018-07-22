
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class Main {

    public static void main(String[]args) throws Exception {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();
        int T = sc.nextInt();
        for (int tt = 1 ; tt <= T ; ++tt)
        {
            int N = sc.nextInt();
            int A [] = new int[N];
            int W [] = new int[N];
            int L [] = new int[N];
            int D [] = new int[N];
            for (int i = 0 ; i < N ; ++i) A[i] = sc.nextInt();
            for (int i = 0 ; i < N ; ++i) D[i] = L[i] = W[i] = sc.nextInt();

            int LIS = 0;
            for (int i = 0 ; i < N ; ++i) {
                for (int j = 0; j < i; ++j)
                    if (A[i] > A[j])
                        L[i] = Math.max(L[i], L[j] + W[i]);
                LIS = Math.max(LIS , L[i]);
            }

            int LDS = 0;
            for (int i = 0 ; i < N ; ++i) {
                for (int j = 0; j < i; ++j)
                    if (A[i] < A[j])
                        D[i] = Math.max(D[i], D[j] + W[i]);
                LDS = Math.max(LDS , D[i]);
            }

            if (LIS >= LDS) out.printf("Case %d. Increasing (%d). Decreasing (%d).\n" , tt , LIS , LDS);
            else out.printf("Case %d. Decreasing (%d). Increasing (%d).\n" , tt , LDS , LIS);

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
