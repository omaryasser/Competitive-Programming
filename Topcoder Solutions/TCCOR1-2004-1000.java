import java.util.Arrays;

/**
 * Created by omar on 20/07/17.
 */
public class RockSkipping {
    char[] arr;
    double[][] memo;
    double dp (int idx, int max) {
        if (idx >= arr.length) return 0;
        if (arr[idx] != '.') return 1;
        if (max == 0) return 0;
        if (memo[idx][max] > -1) return memo[idx][max];
        double res = 0;
        for (int i = idx + 1; i <= idx + max; i++) {
            res += (1.0 / max) * dp(i, max - 1);
        }
        return memo[idx][max] = res;
    }
    public double probability(String pads, int maxDist) {
        String now = pads;
        while (now.length() <= maxDist * maxDist) now += pads;
        arr = now.toCharArray();
        memo = new double[arr.length][maxDist + 1];
        for (double[] d : memo) Arrays.fill(d, -2);
        return (1 - dp(0, maxDist)) * 100.0;
    }
}
