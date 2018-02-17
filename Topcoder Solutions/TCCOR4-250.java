/**
 * Created by omar on 6/5/17.
 */
public class BadNeighbors {
    public int maxDonations(int[] arr) {
        int n = arr.length;
        int dp [][] = new int[n][2];
        dp[0][1] = arr[0];
        for (int i = 1; i < n; i++) {
            if (i == n - 1) {
                dp[i][1] = dp[i - 1][1];
                dp[i][0] = Math.max(dp[i - 1][0], arr[i] + ((i - 2 < 0) ? 0 : dp[i - 2][0]));
            } else {
                dp[i][0] = Math.max(dp[i - 1][0], arr[i] + ((i - 2 < 0) ? 0 : dp[i - 2][0]));
                dp[i][1] = Math.max(dp[i - 1][1], arr[i] + ((i - 2 < 0) ? 0 : dp[i - 2][1]));
            }
        }
        return Math.max(dp[n - 1][0], dp[n - 1][1]);
    }
}
