import java.io.*;
import java.util.*;

/**
 * Created by omar on 13/08/17.
 */
public class Main {
    static long coprimes [];
    public static void num_of_coprimes (int MAX_N)
    {
        coprimes = new long[MAX_N + 1];
        for (int i = 0; i <= MAX_N; ++i) coprimes[i] = i;

        for (int i = 2; i <= MAX_N; ++i)
            if (coprimes[i] == i)
                for (int j = i; j <= MAX_N; j += i)
                    coprimes[j] -= coprimes[j] / i;
    }
    public static void main (String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        int MAX = 200003;
        num_of_coprimes(MAX);

        while (true) {
            long k = sc.nextLong();
            if (k == 0) break;
            if (k == 1) {out.print("0/1\n"); continue;}
            k--;
            int i;
            for (i = 1;; i++) {
                if (k > coprimes[i]) {
                    k -= coprimes[i];
                } else break;
            }
            for (int j = 1; ; j++) {
                if (gcd(i, j) == 1) {
                    k--;
                    if (k == 0) {out.printf("%d/%d\n", j, i); break;}
                }
            }
        }
        out.close();
    }

    public static long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a%b);
    }








































    static class Scanner {


        StringTokenizer st;
        BufferedReader br;

        public Scanner(InputStream s) {
            br = new BufferedReader(new InputStreamReader(s));
        }

        public Scanner(FileReader f) {
            br = new BufferedReader(f);
        }

        public String next() {
            while (st == null || !st.hasMoreTokens())
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (Exception e) {
                }
            return st.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }

        public String nextLine() {
            try {
                return br.readLine();
            } catch (Exception e) {
                return null;
            }
        }

        public double nextDouble() {
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

        public boolean ready() {
            try {
                return br.ready();
            } catch (Exception e) {
                return false;
            }
        }
    }

}
