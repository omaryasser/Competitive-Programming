import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class Main {
    static char P[];
    static int b[], m, n, memo [][];
    static int arr [][];
    public static void kmpPreProcess()
    {
        m = P.length;
        b = new int[m + 1];
        int i = 0, j = -1; b[0] = -1;
        while(i < m)
        {
            while(j >= 0 && P[i] != P[j]) j = b[j];
            i++; j++;
            b[i] = j;
        }
    }

    static int solve (int len) {
        kmpPreProcess();
        return len % (len - b[len]) == 0 ? len - b[len] : len;
    }

    static int dp (int s, int e) {
        if (s == e) return 1;
        if (memo[s][e] != -1) return memo[s][e];
        if (arr[s][e] != e - s + 1) return memo[s][e] = dp(s, s + arr[s][e] - 1);
        else {
            int mn = Integer.MAX_VALUE;
            for (int i = s; i < e; i++)
                mn = Math.min(mn, dp(s, i) + dp(i + 1, e));
            return memo[s][e] = mn;
        }
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        while (true) {
            String s = sc.next();
            n = s.length();
            arr = new int[n][n];
            memo = new int[n][n];
            for (int i = 0; i < n; i++) Arrays.fill(memo[i], - 1);
            if (s.equals("*")) break;
            for (int i = 0; i < s.length(); i++) {
                for (int j = i; j < s.length(); j++) {
                    P = s.substring(i, j + 1).toCharArray();
                    arr [i][j] = solve(j - i + 1);
                }
            }
            out.println(dp(0, n - 1));
        }

        out.flush();
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
