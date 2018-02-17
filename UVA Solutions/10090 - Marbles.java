import java.io.*;
import java.math.BigInteger;
import java.util.*;


public class Main {
    public static void main(String [] args) throws  Exception
    {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();
        while (true)
        {
            int n = sc.nextInt();
            if (n == 0) break;

            int c1 = sc.nextInt() , a = sc.nextInt() , c2 = sc.nextInt() , b = sc.nextInt();
            Result result = extendedEuclid(a , b);
            long xDash = result.x , yDash = result.y;
            int g = gcd(a , b);
            if (n % g != 0)
            {
                sb.append("failed\n");
                continue;
            }
            long x = xDash * (n / g);
            long y = yDash * (n / g);
            long min = (long)1e18;
            long best1 = 0 , best2 = 0 ;
            long i1 = (long) Math.ceil(-1 * (double)xDash * (n / (double)b));
            long i2 = (long) Math.floor((double) yDash * (n / (double)a)) ;
            if (i1 > i2)
            {
                sb.append("failed\n");
                continue;
            }
            long arr [] = {i1 , i2 };
            for (int j = 0 ; j < 2 ; ++j)
            {
                long i = arr[j];
                long ans1 = x + (i * b) / g;
                long ans2 = y - (i * a) / g;
                    long ans =  ans1 * c1 + ans2 * c2 ;
                if (ans < min)
                {
                    min = ans ;
                    best1 = ans1;
                    best2 = ans2;
                }
            }

            if (min == (long)1e18)
                sb.append("failed\n");
            else sb.append(best1 + " " + best2 +"\n");
        }
        out.print(sb.toString());
        out.flush();
        out.close();
    }

    static int gcd (int a , int b)
    {
        while (b > 0)
        {
            int t = a % b ;
            a = b;
            b = t;
        }

        return a;
    }
    static class Result
    {
        int d , x , y;
        Result (int dd , int xx , int yy)
        {
            d = dd;
            x = xx;
            y = yy;
        }
    }
    public static Result extendedEuclid(int A , int B)
    {
        if( B == 0)
            return new Result(A , 1 , 0);

        else
        {
            Result nxt = extendedEuclid(B , A%B);
            return new Result (nxt.d , nxt.y , nxt.x - (A/B)*nxt.y);
        }
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
