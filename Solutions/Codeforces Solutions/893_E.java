import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
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
        TaskE solver = new TaskE();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskE {
        static int[] fac;
        static int MOD = 1000_000_007;

        public void solve(int testNumber, Scanner sc, PrintWriter out) {
            int q = sc.nextInt();
            int MAX = 1000_001;
            fac = new int[2 * MAX];
            fac[0] = 1;
            for (int i = 1; i < 2 * MAX; i++)
                fac[i] = (int) (1l * fac[i - 1] * i % MOD);
            boolean[] isPrime = new boolean[MAX];
            Arrays.fill(isPrime, true);
            isPrime[0] = isPrime[1] = false;
            for (int i = 2; i * i < MAX; i++)
                if (isPrime[i])
                    for (int j = i * i; j < MAX; j += i)
                        isPrime[j] = true;

            ArrayList<Integer> primes = new ArrayList<>();
            for (int i = 2; i < MAX; i++) if (isPrime[i]) primes.add(i);
            while (q-- > 0) {
                int num = sc.nextInt(), n = sc.nextInt();
                ArrayList<Integer> e = new ArrayList<>();
                for (int p : primes) {
                    if (p * p > num) break;
                    if (num % p == 0) {
                        int cntHere = 0;
                        while (num % p == 0) {
                            num /= p;
                            cntHere++;
                        }
                        e.add(cntHere);
                    }
                }
                if (num != 1) e.add(1);

                long res = pow(2, n - 1);
                for (int ee : e)
                    res = res * nCr(n + ee - 1, n - 1) % MOD;
                out.println(res);
            }
        }

        static int nCr(int n, int r) {
            return (int) ((1l * fac[n] * pow(fac[r], MOD - 2) % MOD) * pow(fac[n - r], MOD - 2) % MOD);
        }

        static int pow(int a, int e) {
            long res = 1;
            while (e > 0) {
                if ((e & 1) == 1) res = res * a % MOD;
                a = (int) (1L * a * a % MOD);
                e >>= 1;
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

