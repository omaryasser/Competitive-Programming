import javax.jws.soap.SOAPBinding;
import java.io.*;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.DoubleStream;

/**
 * Created by aa on 1/06/2016.
 */
public class Main {
    static int N , K , M;
    static long memo [][][][];
    public static long search (int idx , int last , int lastCnt , int bars)
    {
        if (idx == N) {
            if (bars == K) return 1;
            return 0;
        }

        if (bars > K ) return 0;
        if (memo[idx][last][lastCnt][bars] != -1) return memo[idx][last][lastCnt][bars];
        long cnt = 0 ;
        if (lastCnt + 1 <= M) cnt += search(idx + 1 , last , lastCnt + 1 , bars);
        cnt +=search(idx + 1 , 1 - last , 1 , bars + 1);
        return memo[idx][last][lastCnt][bars] = cnt;
    }
    public static void main(String [] args) throws  Exception
    {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();
        while (sc.ready())
        {
            N = sc.nextInt(); K = sc.nextInt() ; M = sc.nextInt();
            memo = new long[N][2][M + 1][K * 2];
            for (int i = 0 ; i < N ; ++i) for (int j = 0 ; j < 2 ; ++j) for (int k = 0 ; k < M + 1 ; ++k) Arrays.fill(memo[i][j][k] , -1);
            out.printf("%d\n", search(1,1,1,1));
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
