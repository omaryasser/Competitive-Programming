
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
        while (T -- > 0)
        {
            int n = sc.nextInt();
            int arr [] = new int[n];
            for (int i = 0 ; i < n ; ++i) arr[i] = sc.nextInt();

            int LS = 0;
            int [] LI = new int[n] , LD = new int[n];
            Arrays.fill(LI , 1); Arrays.fill(LD , 1);
            for (int i= n - 1 ; i >= 0 ; --i)
                for (int j = i + 1; j < n; ++j)
                    if (arr[j] > arr[i])
                        LI[i] = Math.max(LI[i], LI[j] + 1);
            for (int i = n - 1 ; i >= 0 ; --i)
                for (int j = i + 1 ; j < n ; ++j)
                    if (arr[i] > arr[j])
                        LD[i] = Math.max(LD[i] , LD[j] + 1);

            for (int i = 0 ; i < n ; ++i)
                LS = Math.max(LS , LI[i] + LD[i] - 1);

            out.printf("%d\n" , LS);
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
