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
        TaskD solver = new TaskD();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskD {
        public void solve(int testNumber, Scanner sc, PrintWriter out) {
            int n = sc.nextInt();
            char[][] a = new char[n][];
            for (int i = 0; i < n; i++) a[i] = sc.next().toCharArray();
            StringBuilder[] res = new StringBuilder[n];
            for (int i = 0; i < n; i++) res[i] = new StringBuilder();
            res[n - 1].append(new String(a[n - 1]));
            int lstIdx = a[n - 1].length - 1;
            for (int i = n - 2; i >= 0; i--) {
                boolean less = false;
                int bigger = -1;
                for (int j = 0; j < Math.min(a[i].length, lstIdx + 1); j++) {
                    if (a[i][j] != a[i + 1][j]) {
                        if (a[i][j] < a[i + 1][j]) {
                            less = true;
                            break;
                        } else {
                            bigger = j;
                            break;
                        }
                    }
                }
                if (less) {
                    lstIdx = a[i].length - 1;
                    res[i].append(new String(a[i]));
                } else if (bigger != -1) {
                    lstIdx = bigger - 1;
                    for (int j = 0; j < bigger; j++) res[i].append(a[i][j]);
                } else {
                    lstIdx = Math.min(a[i].length - 1, lstIdx);
                    for (int j = 0; j <= lstIdx; j++) res[i].append(a[i][j]);
                }
            }
            for (int i = 0; i < n; i++)
                out.println(res[i]);
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

