import java.util.Arrays;

/**
 * Created by omar on 23/07/17.
 */
public class BagOfDevouring {
    int[] v, w;
    int n;
    double[] memo;
    double dp (int inBag) {
        if (inBag == 0) return 0;
        if (memo[inBag] > -1) return memo[inBag];
        double max = 0;
        for (int remove = 0; remove < n; remove++) {
            if (((1 << remove) & inBag) != 0) {
                double here = v[remove];
                int remItems = Integer.bitCount(inBag) - 1;
                if (remItems == 0) here = v[remove];
                else {
                    int totalWeight = 0;
                    for (int i = 0; i < n; i++) {
                        if (i != remove && ((1 << i) & inBag) != 0)
                            totalWeight += w[i];
                    }
                    double probNot = 0;
                    for (int i = 0; i < n; i++) {
                        if (i != remove && ((1 << i) & inBag) != 0) {
                            probNot += (1.0 * w[i] / (totalWeight + 100) );
                            here += (1.0 * w[i] / (totalWeight + 100) ) * dp(inBag ^ (1 << remove) ^ (1 << i));
                        }
                    }
                    here += (1 - probNot) * dp(inBag ^ (1 << remove));
                }
                max = Math.max(max, here);
            }
        }
        return memo[inBag] = max;
    }
    public double expectedYield(int[] values, int[] weights) {
        v = values;
        w = weights;
        n = values.length;
        memo = new double[1 << n];
        Arrays.fill(memo, -2);
        return dp((1 << n) - 1);
    }
}
