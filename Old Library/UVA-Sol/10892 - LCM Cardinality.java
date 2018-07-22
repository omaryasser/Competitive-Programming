import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class A {

    public static ArrayList<Integer> getDivisors (int n)
    {
        ArrayList<Integer> divisors = new ArrayList<>();
        for (int i = 1 ; i * i <= n ; ++i)
            if (n % i == 0)
            {
                divisors.add(i);
                if (i != n / i) divisors.add(n / i);
            }
        return divisors;
    }

    public static long count (ArrayList<Integer> divisors , int LCM)
    {
        long cnt = 0;
        for (int i = 0 ; i < divisors.size() ; ++i)
            for (int j = i ; j < divisors.size() ; ++j)
                if (lcm (divisors.get(i) , divisors.get(j)) == LCM)
                    ++cnt;
        return cnt;
    }

    public static long gcd (long a , long b)
    {
        while (b > 0)
        {
            long t = a % b;
            a = b;
            b = t;
        }
        return a;
    }

    public static long lcm (long a , long b)
    {
        return a * (b / gcd(a , b));
    }
    public static void main(String [] args) throws  Exception
    {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();
        while (true)
        {
            int n = sc.nextInt();
            if (n == 0)
                break;
            out.printf("%d %d\n" , n , count(getDivisors(n) , n));
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
