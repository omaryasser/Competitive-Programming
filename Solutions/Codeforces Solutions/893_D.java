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
        TaskD solver = new TaskD();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskD {
        public void solve(int testNumber, Scanner sc, PrintWriter out) {
            int n = sc.nextInt(), d = sc.nextInt();
            long[] a = new long[n];
            for (int i = 0; i < n; i++)
                a[i] = sc.nextLong();
            long suffSum[] = new long[n];
            long suffMax[] = new long[n];
            suffSum[n - 1] = a[n - 1];
            suffMax[n - 1] = a[n - 1];
            for (int i = n - 2; i >= 0; i--) {
                suffSum[i] = suffSum[i + 1] + a[i];
                suffMax[i] = Math.max(suffMax[i + 1] + a[i], a[i]);
            }
            boolean ok = true;
            int cnt = 0;
            long sumSoFar = 0;
            for (int i = 0; i < n; i++) {
                sumSoFar += a[i];
                if (sumSoFar > d) {
                    out.println(-1);
                    return;
                }
                if (a[i] == 0) {
                    if (sumSoFar < 0) {
                        sumSoFar = Math.max(0, d - suffMax[i]);
                        cnt++;
                    }
                }
            }
            out.println(cnt);
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

        public long nextLong() {
            return Long.parseLong(next());
        }

    }
}

