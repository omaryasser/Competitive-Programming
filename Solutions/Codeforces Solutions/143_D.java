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
            int r = sc.nextInt(), c = sc.nextInt();
            int inRow = (r / 4) * 2 + ((r % 4 == 0) ? 0 : (r % 4 == 1) ? 1 : 2);
            int inCol = (c / 4) * 2 + ((c % 4 == 0) ? 0 : (c % 4 == 1) ? 1 : 2);
            int first = inRow * inCol;
            out.println(r == 0 ? 0 : c == 0 ? 0 : (c == 1 || r == 1) ? Math.max(r, c) : Math.max(first, (r * c + 1) >> 1));
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

