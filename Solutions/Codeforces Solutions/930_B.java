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
        TaskB solver = new TaskB();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskB {
        public void solve(int testNumber, Scanner sc, PrintWriter out) {
            char[] a = sc.next().toCharArray();
            ArrayList<Integer> pos[] = new ArrayList[26];
            for (int i = 0; i < 26; i++)
                pos[i] = new ArrayList<>();
            for (int i = 0; i < a.length; i++)
                pos[a[i] - 'a'].add(i);

            int cnt[] = new int[26];
            for (char c : a)
                cnt[c - 'a']++;
            int n = a.length;
            double res = 0;
            for (int i = 0; i < 26; i++) {
                double max = 0;
                for (int l = 0; l < a.length; l++) {
                    int[] found = new int[26];
                    for (int position : pos[i]) {
                        int nxt = position + l;
                        if (nxt >= n) nxt -= n;
                        found[a[nxt] - 'a']++;
                    }
                    double here = 0;
                    for (int j = 0; j < 26; j++) {
                        if (found[j] == 1) {
                            here += 1.0 * (1.0 * cnt[i] / a.length) * 1.0 / cnt[i];
                        }
                    }
                    max = Math.max(max, here);

                }
                res += max;
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

    }
}

