import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeSet;

/**
 * Created by omar on 6/5/17.
 */
public class AvoidRoads {
    TreeSet<Pair> bad;
    static long memo[][];
    static int n, m;

    boolean valid(int sR, int sC, int eR, int eC) {
        return eR < n && eC < m && !bad.contains(new Pair(sR, sC, eR, eC));
    }

    long dp(int row, int col) {
        if (row == n - 1 && col == m - 1) return 1;
        if (memo[row][col] != -1) return memo[row][col];
        long res = 0;
        if (valid(row, col, row + 1, col)) res += dp(row + 1, col);
        if (valid(row, col, row, col + 1)) res += dp(row, col + 1);
        return memo[row][col] = res;
    }
    public long numWays(int mm, int nn, String[] badd) {
        n = nn + 1;
        m = mm + 1;
        memo = new long[n][m];
        for (int i = 0; i < n; i++) Arrays.fill(memo[i], -1);
        bad = new TreeSet<>();
        for (int i = 0; i < badd.length; i++) {
            StringTokenizer st = new StringTokenizer(badd[i]);
            int b = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            bad.add(new Pair(a, b, c, d));
            bad.add(new Pair(c, d, a, b));
        }
        return dp(0, 0);
    }
    static class Pair implements Comparable<Pair> {
        int a, b, c, d;
        Pair (int aa, int bb, int cc, int dd) {
            a = aa;
            b = bb;
            c = cc;
            d = dd;
        }

        @Override
        public int compareTo(Pair pair) {
            return a == pair.a && b == pair.b && c == pair.c && d == pair.d ? 0 : a != pair.a ? a - pair.a : b != pair.b ? b - pair.b : c != pair.c ? c - pair.c : d - pair.d;
        }
    }
}
