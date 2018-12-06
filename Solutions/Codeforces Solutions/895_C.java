import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.io.InputStream;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 *
 * @author OmarYasser
 */
public class Main {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        Scanner in = new Scanner(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        TaskC solver = new TaskC();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskC {
        static boolean[] isPrime;
        static int BIG_PRIME = 32;
        static int n;
        static int MOD = 1000_000_007;
        static int[] a;
        static int[] fac;

        static void seive(int MAX) {
            isPrime = new boolean[MAX];
            Arrays.fill(isPrime, true);
            isPrime[0] = isPrime[1] = false;
            for (int i = 2; i * i < MAX; i++)
                if (isPrime[i])
                    for (int j = i * i; j < MAX; j += i)
                        isPrime[j] = false;
        }

        public void solve(int testNumber, Scanner sc, PrintWriter out) {
            int MAX = 1000_01;
            fac = new int[MAX];
            fac[0] = 1;
            for (int i = 1; i < MAX; i++)
                fac[i] = (int) (1l * fac[i - 1] * i % MOD);
            seive(71);
            int tmpN = sc.nextInt();
            ArrayList<Integer> tmpA = new ArrayList<>();
            HashMap<Integer, Integer> cnt = new HashMap<>();
            for (int i = 0; i < tmpN; i++) {
                int num = sc.nextInt();
                if (num < BIG_PRIME || !isPrime[num]) tmpA.add(num);
                else cnt.put(num, cnt.getOrDefault(num, 0) + 1);
            }
            int[] order = new int[71];
            int idx = 0;
            for (int i = 2; i < 71; i++)
                if (isPrime[i])
                    order[i] = idx++;
            n = tmpA.size();
            a = new int[n];
            for (int i = 0; i < n; i++) {
                int num = tmpA.get(i);
                for (int j = 2; j < 71; j++)
                    if (isPrime[j]) {
                        int c = 0;
                        while (num % j == 0) {
                            c++;
                            num /= j;
                        }
                        if (c % 2 == 1) a[i] |= (1 << order[j]);
                    }
            }

            long res = 1;
            for (int c : cnt.values()) {
                long here = 0;
                for (int take = 0; take <= c; take += 2) {
                    here += nCr(c, take);
                    if (here >= MOD) here -= MOD;
                }
                res = res * here % MOD;
            }

            if (n > 0) {
                int[] last = new int[1 << 11];
                last[0] = 1;
                last[a[0]] += 1;
                int[] cur = new int[1 << 11];
                for (int i = 1; i < n; i++) {
                    for (int mask = 0; mask < 1 << 11; mask++) {
                        cur[mask] = last[mask] + last[mask ^ a[i]];
                        if (cur[mask] >= MOD) cur[mask] -= MOD;
                    }
                    for (int mask = 0; mask < 1 << 11; mask++) {
                        last[mask] = cur[mask];
                    }
                }
                res = res * last[0] % MOD;
            }
            out.println((((res - 1) % MOD) + MOD) % MOD);
        }

        static int nCr(int n, int r) {
            return (int) ((1l * fac[n] * inv(fac[r]) % MOD) * inv(fac[n - r]) % MOD);
        }

        static int inv(int num) {
            return pow(num, MOD - 2);
        }

        static int pow(int a, int e) {
            long res = 1;
            while (e > 0) {
                if ((e & 1) == 1) res = res * a % MOD;
                e >>= 1;
                a = (int) (1l * a * a % MOD);
            }
            return (int) res;
        }

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

    }
}

