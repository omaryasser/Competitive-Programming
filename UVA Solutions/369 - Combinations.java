import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.*;

public class Main {

    static int pow(int a, int e)
    {
        int res = 1;
        while(e > 0)
        {
            if((e & 1) == 1)
                res *= a;
            a *= a;
            e >>= 1;
        }
        return res;
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        while (true) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            if (n == 0 && m == 0) break;
            long res = 1;
            int cnt [] = new int[100];
            for (int i = n ; i > Math.max(m , n - m) ; -- i) {
                int tmp = i;
                for (int j = 2 ; j <= 20 ; ++ j) {
                    while (tmp % j == 0) {
                        tmp /= j;
                        cnt[j] ++ ;
                    }
                }
                if (tmp != 1) cnt[tmp] ++ ;
            }

            for (int i = Math.min(m , n - m) ; i >= 1 ; -- i) {
                int tmp = i;
                for (int j = 2 ; j <= 20 ; ++ j) {
                    while (tmp % j == 0) {
                        tmp /= j;
                        cnt[j] -- ;
                    }
                }
                if (tmp != 1) cnt[tmp] -- ;
            }
            for (int i = 0 ; i < 100 ; ++ i)
                res *= pow(i , cnt[i]);
            out.printf("%d things taken %d at a time is %d exactly.\n" , n , m , res);
        }
        out.close();
    }

























    static class Scanner {
        StringTokenizer st;
        BufferedReader br;

        public Scanner(InputStream s) {
            br = new BufferedReader(new InputStreamReader(s));
        }

        public String next() throws IOException {
            while (st == null || !st.hasMoreTokens())
                st = new StringTokenizer(br.readLine());
            return st.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }

        public long nextLong() throws IOException {
            return Long.parseLong(next());
        }

        public String nextLine() throws IOException {
            return br.readLine();
        }

        public double nextDouble() throws IOException {
            String x = next();
            StringBuilder sb = new StringBuilder("0");
            double res = 0, f = 1;
            boolean dec = false, neg = false;
            int start = 0;
            if (x.charAt(0) == '-') {
                neg = true;
                start++;
            }
            for (int i = start; i < x.length(); i++)
                if (x.charAt(i) == '.') {
                    res = Long.parseLong(sb.toString());
                    sb = new StringBuilder("0");
                    dec = true;
                } else {
                    sb.append(x.charAt(i));
                    if (dec)
                        f *= 10;
                }
            res += Long.parseLong(sb.toString()) / f;
            return res * (neg ? -1 : 1);
        }

        public boolean ready() throws IOException {
            return br.ready();
        }

    }
}
