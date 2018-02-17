import java.io.*;
import java.math.BigInteger;
import java.util.*;

/**
 * Created by aa on 1/06/2016.
 */
public class A {

    public static boolean ok (int arr [] , int one , int all)
    {
        int cnt = 1 , soFar = 0;
        for (int i = 0 ; i < arr.length ; ++i)
        {
            if (soFar + arr[i] > one){
                soFar = 0;
                cnt ++;
            }
            soFar+= arr[i];
        }
        return cnt <= all;
    }
    public static void main(String [] args) throws  Exception
    {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();
        for (int T = 1 ; sc.ready() ; ++T)
        {
            int N = sc.nextInt() , M = sc.nextInt();
            int arr [] = new int[N];
            int max = 0 , sum = 0;
            for (int i = 0 ; i < N ; ++i){
                arr[i] = sc.nextInt();
                max = Math.max(max , arr[i]);
                sum += arr[i];
            }
            int lo = max , hi = sum ;
            int least = Integer.MAX_VALUE;
            for(int i = 0 ; i < 100 ; ++i)
            {
                int mid = lo + ((hi - lo) >> 1) ;
                if (ok (arr , mid , M)) {
                    least = Math.min(least , mid);
                    hi = Math.max(lo , mid - 1);
                }
                else lo  = Math.min(mid + 1, hi);
            }
            out.printf("%d\n" , least);
        }
        out.flush();
        out.close();
    }









    static class Scanner
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
