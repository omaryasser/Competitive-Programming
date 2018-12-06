import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
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
        TaskB solver = new TaskB();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskB {
        int[] memo;
        int MAX = (int) 1e6 + 10;

        int product(int x) {
            int ret = 1;
            while (x > 0) {
                if (x % 10 != 0)
                    ret *= x % 10;
                x /= 10;
            }
            return ret;
        }

        int g(int x) {
            if (x < 10) return x;
            if (memo[x] != -1)
                return memo[x];
            return memo[x] = g(product(x));
        }

        public void solve(int testNumber, Scanner sc, PrintWriter out) {
            memo = new int[MAX];
            Arrays.fill(memo, -1);
            int[][] till = new int[MAX][10];
            for (int i = 1; i < MAX; i++) {
                for (int j = 1; j <= 9; j++)
                    till[i][j] = till[i - 1][j];
                till[i][g(i)]++;
            }
            int q = sc.nextInt();
            while (q-- > 0) {
                int l = sc.nextInt(), r = sc.nextInt(), k = sc.nextInt();
                out.println(till[r][k] - till[l - 1][k]);
            }
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

