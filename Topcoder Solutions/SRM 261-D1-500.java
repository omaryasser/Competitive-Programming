import java.util.Arrays;

/**
 * Created by omar on 29/07/17.
 */
public class EncodingTrees {
    long[][] memo;
    long dp (int lo, int hi) {
        if (memo[lo][hi] != -1) return memo[lo][hi];
        long res = 0;
        for (int i = lo + 1; i < hi; i++)
            res += dp(lo, i) * dp(i, hi);
        return memo[lo][hi] = Math.max(res, 1);
    }
    public String getCode(int N, int index) {
        memo = new long[N + 2][N + 2];
        for (long[] m : memo) Arrays.fill(m, -1);
        Node root = solve(0, N + 1, index - 1);
        String res = print (root);
        return res.length() == N ? res : "";
    }

    String print (Node cur) {
        if (cur == null) return "";
        return cur.c + print(cur.l) + print(cur.r);
    }
    Node solve (int lo, int hi, long rem) {
        if (lo == hi - 1) return null;
        long best = 0;
        for (int i = lo + 1; i < hi; i++) {
            long was = best;
            best += dp(lo, i) * dp(i, hi);
            if (best > rem) {
                Node cur = new Node((char)('a' + i - 1));
                rem -= was;
                if ((char)('a' + i - 1) == 'a') System.err.println(rem + " " + best + " " + was + " " + dp(i, hi));
                cur.l = solve(lo, i, rem / dp(i, hi));
                cur.r = solve(i, hi, rem % dp(i, hi));
                return cur;
            }
        }
        return null;
    }
    class Node {
        Node l, r;
        char c;
        Node (char cc) {c = cc;}
    }
}
