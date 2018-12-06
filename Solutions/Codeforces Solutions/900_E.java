import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
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
        TaskE solver = new TaskE();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskE {
        static int n;
        static int m;
        static char[] s;
        static boolean[] canStartHere;
        static int[][] memoMaxAfter;
        static TaskE.Pair[] memo;
        static int[] cntQuestionMarks;

        static int maxAfter(int idx, int parity) {
            if (idx == n) return 0;
            if (memoMaxAfter[idx][parity] != -1)
                return memoMaxAfter[idx][parity];

            if (s[idx] == '?') return memoMaxAfter[idx][parity] = 1 + maxAfter(idx + 1, 1 - parity);
            if (s[idx] - 'a' != parity)
                return memoMaxAfter[idx][parity] = 0;
            return memoMaxAfter[idx][parity] = 1 + maxAfter(idx + 1, 1 - parity);
        }

        static TaskE.Pair dp(int idx) {
            if (idx == n) return new TaskE.Pair(0, 0);
            if (memo[idx] != null) return memo[idx];
            TaskE.Pair best = new TaskE.Pair(0, 0);
            if (s[idx] == 'a' || s[idx] == '?') {
                if (canStartHere[idx]) {
                    TaskE.Pair go = dp(idx + m);
                    int cnt = go.occ + 1, rep = go.replacements + cntQuestionMarks[idx];
                    if (best.occ < cnt) {
                        best = new TaskE.Pair(cnt, rep);
                    } else if (best.occ == cnt) {
                        best.replacements = Math.min(best.replacements, rep);
                    }
                }
                TaskE.Pair go = dp(idx + 1);
                int cnt = go.occ, rep = go.replacements;
                if (best.occ < cnt) {
                    best = new TaskE.Pair(cnt, rep);
                } else if (best.occ == cnt) {
                    best.replacements = Math.min(best.replacements, rep);
                }
            } else {
                best = dp(idx + 1);
            }
            return memo[idx] = best;
        }

        public void solve(int testNumber, Scanner sc, PrintWriter out) {
            n = sc.nextInt();
            s = sc.next().toCharArray();
            m = sc.nextInt();
            canStartHere = new boolean[n];
            memoMaxAfter = new int[n][2];
            memo = new TaskE.Pair[n];
            cntQuestionMarks = new int[n];
            int soFar = 0;
            int ii, jj;
            for (ii = n - 1, jj = 0; ii >= 0 && jj < m; ii--, jj++) {
                soFar += s[ii] == '?' ? 1 : 0;
                cntQuestionMarks[ii] = soFar;
            }
            for (; ii >= 0; ii--) {
                soFar += s[ii] == '?' ? 1 : 0;
                soFar -= s[ii + m] == '?' ? 1 : 0;
                cntQuestionMarks[ii] = soFar;
            }
            for (int i = 0; i < n; i++) Arrays.fill(memoMaxAfter[i], -1);
            for (int i = 0; i < n; i++) {
                if (maxAfter(i, 0) >= m)
                    canStartHere[i] = true;
            }
            TaskE.Pair x = dp(0);
            out.println(x.replacements);
        }

        static class Pair {
            int occ;
            int replacements;

            Pair(int o, int r) {
                occ = o;
                replacements = r;
            }

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

