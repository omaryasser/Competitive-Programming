
import java.io.*;
import java.util.*;

public class Main {



    public static void main(String[]args) throws Exception {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();

        for(int T = 1 ;; ++T)
        {
            int N = sc.nextInt();
            if(N == 0) break;

            int arr [] = new int[N];
            for(int i = 0 ; i < N ; ++i) arr[i] = sc.nextInt();

            int Q = sc.nextInt();
            out.printf("Case %d:\n" , T);
            while (Q-- > 0)
            {
                int num = sc.nextInt();
                int diff = arr[0] + arr[1];
                for(int i = 0 ; i < N - 1 ; ++i)
                    for(int j = i + 1 ; j < N ; ++j)
                        diff = Math.abs(num - (arr[i] + arr[j])) < Math.abs(num - diff) ? arr[i] + arr[j] : diff;
                out.printf("Closest sum to %d is %d.\n" , num , diff);
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
