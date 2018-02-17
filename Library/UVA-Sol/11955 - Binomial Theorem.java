
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Main {

    static BigInteger nCr1(int N, int K)		// O(K)
    {
        if(K > N)
            return BigInteger.ZERO;
        if (N - K < K) K = N - K;
        BigInteger res = BigInteger.ONE;
        for(int i = 1; i <= K; ++i)
            res = res.multiply(BigInteger.valueOf(N - i + 1)).divide(BigInteger.valueOf(i));
        return res;
    }

    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();
        int T = sc.nextInt();
        for (int t = 1 ; t <= T ; ++t)
        {
            StringTokenizer inp = new StringTokenizer(sc.next() , "^");
            StringTokenizer inpL = new StringTokenizer(inp.nextToken() , "+");
            String a = inpL.nextToken().substring(1);
            String b = inpL.nextToken(); b = b.substring(0 , b.length() - 1);
            sb.append("Case " +t +": ");
            int pow = Integer.parseInt(inp.nextToken());
            for (int i = 0 ; i < pow+ 1 ; ++i)
            {
                if (i != 0) sb.append("+");
                BigInteger comb = nCr1(pow,i);
                if (comb.compareTo(BigInteger.ZERO) != 0 && comb.compareTo(BigInteger.ONE) != 0)
                    sb.append(comb.toString()+"*");
                if (pow - i == 1)
                    sb.append(a);
                else if (pow - i != 0)
                    sb.append(a +"^" + (pow - i));

                if (i == pow && i!= 0)
                    if (i!= 1)
                        sb.append(b +"^" +i);
                    else sb.append(b);
                else if (i == 1)
                    sb.append("*" + b);
                else if (i!= 0)
                    sb.append("*" + b + "^" + i);
            }
            sb.append("\n");
        }
        out.print(sb.toString());
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
