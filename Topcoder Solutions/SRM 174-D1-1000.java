/**
 * Created by omar on 20/07/17.
 */
public class PointSystem {
    int c, d;
    public double oddsOfWinning(int pointsToWin, int pointsToWinBy, int skill) {
        int MAX = 2000; // MAXIMUM TO FIT IN MEMORY LIMIT
        double[][] dp = new double[MAX][MAX];
        c = pointsToWin;
        d = pointsToWinBy;
        dp[0][0] = 1;
        double S = skill / 100.0;
        double res = 0;
        for (int i = 1; i < MAX; i++) {
            dp[i][0] = S * dp[i - 1][0];
            dp[0][i] = (1 - S) * dp[0][i - 1];
            if (ok(i, 0)) {
                res += dp[i][0];
                dp[i][0] = dp[0][i] = 0;
            }
        }

        for (int i = 1; i < MAX; i++) {
            for (int j = 1; j < MAX; j++) {
                dp[i][j] = S * dp[i - 1][j] + (1 - S) * dp[i][j - 1];
                if (ok(i, j)) {
                    res += dp[i][j];
                    dp[i][j] = 0;
                }
                if (ok(j, i))
                    dp[i][j] = 0;
            }
        }
        return res;
    }
    boolean ok (int a, int b) {
        return  a >= c && a - b >= d;
    }
}
