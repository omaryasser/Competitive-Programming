import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
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
        TaskD solver = new TaskD();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskD {
        static int N;
        static int K;
        static int MOD;
        static int[][][] memo;
        static int[] pow10;

        static int dp(int reached, int modSoFar, int idx) {
            if (idx == N) return reached;
            if (memo[reached][modSoFar][idx] != -1)
                return memo[reached][modSoFar][idx];

            int res = 0;
            for (int put = idx == N - 1 ? 1 : 0; put <= 9; put++) {
                int nxtMod = (pow10[idx] * put + modSoFar) % K;
                int nxtReached = reached == 1 || (put != 0 && nxtMod == 0) ? 1 : 0;
                res += dp(nxtReached, nxtMod, idx + 1);
                if (res >= MOD) res -= MOD;
            }
            return memo[reached][modSoFar][idx] = res;
        }

        public void solve(int testNumber, Scanner sc, PrintWriter out) {
            N = sc.nextInt();
            K = sc.nextInt();
            MOD = sc.nextInt();
            pow10 = new int[N];
            pow10[0] = 1;
            for (int i = 1; i < N; i++)
                pow10[i] = (int) (10L * pow10[i - 1] % K);
            memo = new int[2][K + 1][N + 1];
            for (int[][] mm : memo) for (int[] m : mm) Arrays.fill(m, -1);
            out.println(dp(0, 0, 0));
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

