import java.io.*;
import java.math.BigInteger;
import java.util.*;


public class Main {

    public static BigInteger fac (int N)
    {
        BigInteger res = BigInteger.valueOf(N);
        for (int i = N - 1 ; i >= 2 ; --i)
            res = res.multiply(BigInteger.valueOf(i));
        return res;
    }
    public static void main(String [] args) throws  Exception
    {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();

        while (true)
        {
            int n = sc.nextInt();
            if (n == 0) break;

            int freq [] = new int[10];
            char [] arr = fac(n).toString().toCharArray();
            for (int i = 0 ; i < arr.length ; ++i)
                ++ freq[arr[i] - '0'];
            out.printf("%d! --\n" , n);
            out.printf("   (0)    %d    (1)    %d    (2)    %d    (3)    %d    (4)    %d\n" , freq[0],freq[1],freq[2],freq[3],freq[4]);
            out.printf("   (5)    %d    (6)    %d    (7)    %d    (8)    %d    (9)    %d\n" , freq[5],freq[6],freq[7],freq[8],freq[9]);

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
