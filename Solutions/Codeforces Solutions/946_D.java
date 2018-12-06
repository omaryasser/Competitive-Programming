import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
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
        TaskD solver = new TaskD();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskD {
        int n;
        int m;
        int k;
        int[][] best;
        int[] all;
        int[][] memo;

        int dp(int day, int left) {
            if (day == n)
                return 0;
            if (memo[day][left] != -1)
                return memo[day][left];

            int res = (int) 1e9;
            if (left == 0)
                res = best[day][all[day]] + dp(day + 1, left);
            else {
                for (int take = 0; take <= all[day]; take++) {
                    if (left - (all[day] - take) < 0)
                        continue;
                    res = Math.min(res, best[day][take] + dp(day + 1, left - (all[day] - take)));
                }
            }
            return memo[day][left] = res;
        }

        public void solve(int testNumber, Scanner sc, PrintWriter out) {
            n = sc.nextInt();
            m = sc.nextInt();
            k = sc.nextInt();
            best = new int[n][Math.max(m, k + 1) + 1];
            memo = new int[n][Math.max(m, k + 1) + 1];
            all = new int[n];
            for (int[] m : memo) Arrays.fill(m, -1);
            for (int[] b : best) Arrays.fill(b, Integer.MAX_VALUE);
            char[][] a = new char[n][m];
            for (int i = 0; i < n; i++) {
                a[i] = sc.next().toCharArray();
                ArrayList<Integer> pos = new ArrayList<>();
                for (int j = 0; j < a[i].length; j++)
                    if (a[i][j] == '1')
                        pos.add(j);
                all[i] = pos.size();
                best[i][0] = 0;
                for (int take = 1; take <= pos.size(); take++) {
                    for (int s = 0; s < pos.size(); s++) {
                        int e = s + take - 1;
                        if (e >= pos.size()) break;
                        best[i][take] = Math.min(best[i][take], pos.get(e) - pos.get(s) + 1);
                    }
                }
            }

            out.println(dp(0, k));
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

