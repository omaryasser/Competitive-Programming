
import java.io.*;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.*;

public class Main {


    static HashMap<Character , Long> hm;
    public static long compute (char [] arr)
    {
        long ans = 0;
        for(int i = 0 ; i < arr.length ; ++i)
            if(hm.containsKey(arr[i])) ans += hm.get(arr[i]);
        return ans ;
    }
    public static void main(String[]args) throws Exception {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(sc.nextLine());
        while(T -- > 0)
        {
            hm = new HashMap<>();
            int K = Integer.parseInt(sc.nextLine());
            while(K --  >0)
            {
                StringTokenizer st = new StringTokenizer(sc.nextLine());
                hm.put(st.nextToken().charAt(0) , Long.parseLong(st.nextToken()));
            }
            int M = Integer.parseInt(sc.nextLine());
            long ans = 0;
            while(M -- >0) ans += (compute(sc.nextLine().toCharArray()));
            sb.append(new DecimalFormat("0.00").format( ans / 100.0) + "$\n");
        }
        out.print(sb.toString());
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
