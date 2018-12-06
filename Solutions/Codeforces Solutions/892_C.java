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
        TaskC solver = new TaskC();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskC {
        public static int gcd(int a, int b) {
            return b == 0 ? a : gcd(b, a % b);
        }

        public void solve(int testNumber, Scanner sc, PrintWriter out) {
            int n = sc.nextInt();
            int[] arr = new int[n];
            int ones = 0, gcd = 0;
            for (int i = 0; i < n; i++) {
                arr[i] = sc.nextInt();
                gcd = gcd(gcd, arr[i]);
                if (arr[i] == 1) ones++;
            }

            if (gcd != 1) out.println(-1);
            else {
                if (ones != 0) out.println(n - ones);
                else {
                    int min = n;
                    for (int i = 0; i < n; i++) {
                        int g = arr[i];
                        for (int j = i + 1; j < n; j++) {
                            g = gcd(g, arr[j]);
                            if (g == 1) min = Math.min(min, j - i);
                        }
                    }
                    out.println(min + n - 1);
                }
            }
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

