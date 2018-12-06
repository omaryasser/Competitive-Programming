import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.PrintStream;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 */
public class Main {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        Scanner in = new Scanner(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        TaskD solver = new TaskD();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskD {
        long n;
        long m;
        long[] s1;
        long[] s2;
        long MOD = (int) 1e9 + 7;
        Long[] memo;
        long[] inv;
        long mm;

        long dp(int idx) {
            if (idx == n)
                return 0;
            if (memo[idx] != null)
                return memo[idx];
            if (s1[idx] != 0 && s2[idx] != 0) {
                if (s1[idx] > s2[idx])
                    return 1;
                else if (s2[idx] > s1[idx])
                    return 0;
                else
                    return memo[idx] = dp(idx + 1);
            }
            long res = 0;
            if (s1[idx] == 0 && s2[idx] == 0) {
                // make greater
                res = ((((((1l * m * (m - 1)) % MOD) * inv[2]) % MOD) * mm) % MOD);
                // stay equal
                res += (((1l * m * mm % MOD) * dp(idx + 1)) % MOD);
                if (res >= MOD) res -= MOD;
                if (res < 0) res += MOD;
                return memo[idx] = res;
            } else if (s1[idx] == 0) {
                // make greater
                res = ((1l * (m - s2[idx]) * inv[(int) m]) % MOD);
                // stay equal
                res += (1l * inv[(int) m] * dp(idx + 1)) % MOD;
                if (res >= MOD) res -= MOD;
                if (res < 0) res += MOD;
                return memo[idx] = res;
            } else {
                // make greater
                res = ((1l * (s1[idx] - 1) * inv[(int) m]) % MOD);
                // stay equal
                res += (1l * inv[(int) m] * dp(idx + 1)) % MOD;
                if (res >= MOD) res -= MOD;
                if (res < 0) res += MOD;
                return memo[idx] = res;
            }
        }

        long inv(long x, long mod) {
            x %= mod;
            long r, y;
            for (r = 1, y = mod - 2; y != 0; x = x * x % mod, y >>= 1)
                if ((y & 1) == 1)
                    r = r * x % mod;
            return r;
        }

        public void solve(int testNumber, Scanner sc, PrintWriter out) {
            n = sc.nextInt();
            m = sc.nextInt();
            s1 = new long[(int) n];
            s2 = new long[(int) n];
            mm = inv(1l * m * m, MOD);
            for (int i = 0; i < n; i++)
                s1[i] = sc.nextInt();
            for (int i = 0; i < n; i++)
                s2[i] = sc.nextInt();
            memo = new Long[(int) n];
            inv = new long[(int) 1e5 + 10];
            for (int i = 0; i < (int) 1e5 + 10; i++)
                inv[i] = inv(i, MOD);
            System.err.println(inv(123412344444l, MOD));
            System.err.println(inv(123412344444l % MOD, MOD));
            out.println(((dp(0) % MOD) + MOD) % MOD);
        }

    }

    static class Scanner {
        StringTokenizer st;
        BufferedReader br;

        public Scanner(InputStream s) {
            br = new BufferedReader(new InputStreamReader(s));
        }

        public String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (Exception e) {
                }
                ;
            }
            return st.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

    }
}

