import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
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
        TaskE solver = new TaskE();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskE {
        public void solve(int testNumber, Scanner sc, PrintWriter out) {
            int n = sc.nextInt();
            long[] x = new long[n], y = new long[n], sumX = new long[n], sumY = new long[n];
            for (int i = 0; i < n; i++) {
                sumX[i] = x[i] = sc.nextInt();
                sumY[i] = y[i] = sc.nextInt();
                if (i > 0) {
                    sumX[i] += sumX[i - 1];
                    sumY[i] += sumY[i - 1];
                }
            }

            long res = 0;
            for (int i = 1; i <= n - 1; i++)
                res += x[i] * x[i] * i;
            for (int i = 0; i <= n - 1; i++)
                res += x[i] * x[i] * (n - 1 - i);
            for (int i = 1; i <= n - 1; i++)
                res -= x[i] * 2 * sumX[i - 1];

            for (int i = 1; i <= n - 1; i++)
                res += y[i] * y[i] * i;
            for (int i = 0; i <= n - 1; i++)
                res += y[i] * y[i] * (n - 1 - i);
            for (int i = 1; i <= n - 1; i++)
                res -= y[i] * 2 * sumY[i - 1];

            out.println(res);
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

