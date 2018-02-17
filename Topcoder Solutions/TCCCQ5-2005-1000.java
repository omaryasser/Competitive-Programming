/**
 * Created by omar on 20/07/17.
 */
public class NestedRandomness {
    Double[][] memo;
    Double dp (int n, int rem, int wanted) {
        if (rem == 0) return n == wanted ? 1.0 : 0.0;
        if (n == 0) return 0.0;
        if (memo[n][rem] != null) return memo[n][rem];
        Double res = 0.0;
        for (int i = 0; i < n; i++)
            res += 1.0 / n * dp(i, rem - 1, wanted);
        return memo[n][rem] = res;
    }
    public double probability(int N, int nestings, int target) {
        memo = new Double[N + 1][nestings + 1];
        return dp(N, nestings, target);
    }
}
