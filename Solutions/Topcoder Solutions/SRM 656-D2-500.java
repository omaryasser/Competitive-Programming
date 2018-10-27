import java.util.Arrays;

/**
 * Created by omar on 4/15/17.
 */
public class RandomPancakeStackDiv2 {
    int [] arr;
    int n, all;
    Double memo[][];
    int get(int mask) {
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            if ((mask & (1 << i)) != 0)
                cnt += arr[i];
        }
        return cnt;
    }
    double dp (int mask, int top) {
        if (Integer.bitCount(mask) == n) {
            return all;
        }
        if (memo[mask][top] != null)
            return memo[mask][top];

        int rem = n - Integer.bitCount(mask);
        double ret = 0;
        for (int i = 0; i < n; i++) {
            if ((mask & (1 << i)) == 0) {
                if (i > top) {
                    ret += (1.0 / rem) * get(mask);
                } else {
                    ret += (1.0 / rem) * dp(mask | (1 << i), i);
                }
            }
        }
        return memo[mask][top] = ret;
    }
    public double expectedDeliciousness(int[] d) {
        arr = d;
        n = arr.length;
        all = 0;
        for (int i = 0; i < n; i++)
            all += d[i];
        memo = new Double[1 << n][12];
        for (int i = 0; i < 1 << n; i++)
            Arrays.fill(memo[i], null);
        return dp(0, 11);
    }

    public static void main (String [] args) {
        System.out.println(new RandomPancakeStackDiv2().expectedDeliciousness(new int [] {1,1,1}));
    }
}
