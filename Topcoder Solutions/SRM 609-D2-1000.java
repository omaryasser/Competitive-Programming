import java.util.Arrays;

/**
 * Created by omar on 4/15/17.
 */
public class VocaloidsAndSongs {
    int memo[][][][];
    int mod = (int)1e9 + 7;

    int dp (int remS, int a, int b, int c) {
        if (remS == 0) return a == 0 && b == 0 && c == 0 ? 1 : 0;

        if (memo[remS][a][b][c] != -1) return memo[remS][a][b][c];

        int res = 0;
        if (a > 0)
            res += dp(remS -1, a - 1, b, c);
        if (b > 0)
            res += dp(remS - 1, a, b - 1, c);
        if (res > mod) res -= mod;
        if (c > 0)
            res += dp(remS - 1, a, b, c - 1);
        if (res > mod) res -= mod;
        if (a > 0 && b > 0)
            res += dp(remS - 1, a - 1, b - 1, c);
        if (res > mod) res -= mod;
        if (a > 0 && c > 0)
            res += dp(remS - 1, a - 1, b, c - 1);
        if (res > mod) res -= mod;
        if (b > 0 && c > 0)
            res += dp(remS - 1, a, b - 1, c - 1);
        if (res > mod) res -= mod;
        if (a > 0 && b > 0 && c > 0)
            res += dp(remS - 1, a - 1, b - 1, c - 1);
        if (res > mod) res -= mod;
        return memo[remS][a][b][c] = res;
    }
    public int count(int S, int gumi, int ia, int mayu) {
        memo = new int[S + 1][gumi + 1][ia + 1][mayu + 1];
        for (int i = 0; i <= S; i++)
            for (int j = 0; j <= gumi; j++)
                for (int k = 0; k <= ia; k++)
                    Arrays.fill(memo[i][j][k], -1);
        return dp(S, gumi, ia, mayu);
    }

    public static void main (String [] args) {
        System.out.println(new VocaloidsAndSongs().count(3, 3, 1 , 1));
    }
}
