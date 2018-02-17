import java.util.Arrays;

/**
 * Created by omar on 21/07/17.
 */
public class ChipRace {
    double[][] memo;
    int[] arr;
    int n;
    int gifts, total;
    int cur;
    public double[] chances(int[] chips) {
        for (int x : chips) gifts += x;
        total = gifts;
        gifts = (gifts + 2) / 5;
        arr = chips;
        n = arr.length;

        memo = new double[53][1 << 10];
        double[] res = new double[n];
        for (int i = 0; i < n; i++) {
            for (double[] d : memo) Arrays.fill(d, -2);
            cur = i;
            res[i] = dp(52, 0);
        }
        return res;
    }

    double dp (int curNum, int mask) {
        if (memo[curNum][mask] > -1) return memo[curNum][mask];
        int rem = 0;
        for (int i = 0; i < n; i++) if ((mask & (1 << i)) == 0) rem += arr[i];
        if (curNum < rem) return 0;
        if (Integer.bitCount(mask) == gifts) {
            return ((1 << cur) & mask) == 0 ? 0 : 1;
        }
        if (curNum == 0) return 0;
        double res = dp(curNum - 1, mask) * (1 - 1.0 * rem / curNum);
        for (int i = 0; i < n; i++)
            if (((1 << i) & mask) == 0 && arr[i] > 0)
                res += dp(curNum - 1, mask | (1 << i)) * arr[i] / curNum;
        return memo[curNum][mask] = res;
    }
}
