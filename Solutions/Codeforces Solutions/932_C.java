import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
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
            int n = sc.nextInt(), a = sc.nextInt(), b = sc.nextInt();
            ArrayList<Integer> res = new ArrayList<>();
            for (int first = 0; first * a <= n; first++) {
                int rem = n - first * a;
                if (rem % b == 0) {
                    int second = rem / b;
                    int cur = 1;
                    for (int i = 0; i < first; i++) {
                        int start = cur;
                        for (int j = 0; j < a; j++) {
                            if (j == a - 1) {
                                res.add(start);
                                cur++;
                            } else res.add(++cur);
                        }
                    }
                    for (int i = 0; i < second; i++) {
                        int start = cur;
                        for (int j = 0; j < b; j++) {
                            if (j == b - 1) {
                                res.add(start);
                                cur++;
                            } else res.add(++cur);
                        }
                    }
                    for (int i = 0; i < res.size(); i++) {
                        if (i > 0) out.print(" ");
                        out.print(res.get(i));
                    }
                    out.println();
                    return;
                }
            }
            out.println(-1);
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

