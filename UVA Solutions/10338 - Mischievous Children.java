import java.io.*;
import java.math.BigInteger;
import java.util.*;


public class Main {

    public static long fac (int n )
    {
        long res = 1;
        for (int i = 2 ; i <= n ; ++i)
            res *= i;
        return res;
    }


    public  static long solve (char arr [])
    {
        TreeMap<Character , Integer> freq = new TreeMap<>();
        for (int i = 0 ; i < arr.length ; ++i)
            if (!freq.containsKey(arr[i]))
                freq.put(arr[i] , 1);
            else freq.put(arr[i] , freq.get(arr[i]) + 1);

        long donm = 1;
        for (Map.Entry<Character,Integer> map : freq.entrySet())
            donm *= fac(map.getValue());

        return fac(arr.length) / donm;
    }
    public static void main(String [] args) throws  Exception
    {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();

        int T = sc.nextInt();
        for (int t = 1 ; t <= T ; ++t)
            out.printf("Data set %d: %d\n" , t , solve (sc.next().toCharArray()));
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
