/**
 * Created by omar on 6/5/17.
 */
public class ZigZag {
    public int longestZigZag(int[] arr) {
        int n = arr.length;
        if (n == 0) return 0;
        int dp [][] = new int[n][2];
        dp[0][0] = dp[0][1] = 1;
        int max = 1;
        for (int i = 1; i < n; i++) {
            dp[i][0] = dp[i][1] = 1;
            for (int j = 0; j < i; j++) {
                if (arr[i] - arr[j] > 0) {
                    dp[i][0] = Math.max(dp[i][0], 1 + dp[j][1]);
                } else if (arr[i] - arr[j] < 0) {
                    dp[i][1] = Math.max(dp[i][1], 1 + dp[j][0]);
                }
            }
            max = Math.max(max, Math.max(dp[i][0], dp[i][1]));
        }
        return max;
    }

}
