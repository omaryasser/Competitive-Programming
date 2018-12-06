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
            char[] s = sc.next().toCharArray();
            int n = s.length;
            int cnt = 0;
            boolean ok[][] = new boolean[s.length][s.length];
            for (int i = 0; i < n; i++) {
                int open = 0, closed = 0, mark = 0;
                for (int j = i; j < n; j++) {
                    if (s[j] == '(') open++;
                    else if (s[j] == ')') closed++;
                    else mark++;
                    if (open + mark < closed) break;
                    ok[i][j] = true;
                }
            }
            for (int i = n - 1; i >= 0; i--) {
                int open = 0, closed = 0, mark = 0;
                for (int j = i; j >= 0; j--) {
                    if (s[j] == '(') open++;
                    else if (s[j] == '(') closed++;
                    else mark++;
                    if (closed + mark < open) break;
                    if (ok[j][i] && ((j - i + 1) & 1) == 0) {
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

    }
}

