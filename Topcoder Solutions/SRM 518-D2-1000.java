import java.util.Arrays;

/**
 * Created by omar on 4/15/17.
 */
public class CoinReversing {
    Double [][] comb; // Initialize it with -1
    double nCr2(int n, int k) {
        if(n < k)
            return 0;
        if(k == 0 || k == n)		//may add k == 1 as a base case for fast calculations
            return 1;
        if(comb[n][k] != null)
            return comb[n][k];
        if(n - k < k)
            return comb[n][k] = nCr2(n, n - k);		//reduce k to n - k
        return comb[n][k] = nCr2(n - 1, k - 1) + nCr2(n - 1 , k);
    }

    int n, all;
    int arr [];
    Double memo[][];
    double dp (int idx, int heads) {
        if (idx == n) return heads;
        if (memo[idx][heads] != null) return memo[idx][heads];

        int tails = all - heads;
        double res = 0;
        for (int headsTaken = 0; headsTaken <= Math.min(arr[idx], heads); headsTaken++) {
            int tailsTaken = arr[idx] - headsTaken;
            if (tailsTaken <= tails) {
                res += ((nCr2(heads, headsTaken) * nCr2(tails, tailsTaken)) / nCr2(all, arr[idx])) *
                        dp(idx + 1, heads - headsTaken + tailsTaken);
            }
        }
        return memo[idx][heads] = res;
    }
    public double expectedHeads(int N, int[] a) {
        comb = new Double[1001][1001];
        for (int i = 0; i < 1001; i++)
            Arrays.fill(comb[i], null);

        arr = a;
        n = a.length;
        all = N;
        memo = new Double[n][N + 1];
        for (int i = 0; i < n; i++)
            Arrays.fill(memo[i], null);

        return dp(0, N);
    }
}
