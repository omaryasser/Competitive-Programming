import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
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
        TaskC solver = new TaskC();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskC {
        public void solve(int testNumber, Scanner sc, PrintWriter out) {
            long k = sc.nextLong(), d = sc.nextLong(), t = sc.nextLong();
            long cycleLength = d * (k / d + 1);
            if (cycleLength - d >= k) cycleLength -= d;
            double timeForCycle = k + (cycleLength - k) / 2.0;
            long completeCycles = (long) (t / timeForCycle);
            double rem = t - completeCycles * timeForCycle;
            double res = (double) completeCycles * cycleLength;
            if (rem <= k) {
                res += rem;
            } else {
                res += k + (rem - k) * 2;
            }
            out.printf("%.10f\n", res);
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

        public long nextLong() {
            return Long.parseLong(next());
        }

    }
}

