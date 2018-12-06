import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.TreeSet;
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
            int n = sc.nextInt(), m = sc.nextInt();
            TreeSet<Integer> res = new TreeSet<>();
            int[] arr = new int[n];
            for (int i = 0; i < n; i++)
                arr[i] = sc.nextInt();

            int half = n / 2;
            for (int mask = 0; mask < (1 << half); mask++) {
                int sum = 0;
                for (int j = 0; j < half; j++) {
                    if ((mask & (1 << j)) != 0) {
                        sum += (arr[j] % m);
                        if (sum >= m) sum -= m;
                    }
                }
                res.add(-sum);
            }
            int rem = n - half;
            int max = 0;
            for (int mask = 0; mask < (1 << rem); mask++) {
                int sum = 0;
                for (int j = 0; j < rem; j++) {
                    if ((mask & (1 << j)) != 0) {
                        sum += (arr[j + half] % m);
                        if (sum >= m) sum -= m;
                    }
                }
                max = Math.max(max, sum + (res.higher(-(m - sum)) == null ? 0 : -res.higher(-(m - sum))));
            }
            out.println(max);
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

