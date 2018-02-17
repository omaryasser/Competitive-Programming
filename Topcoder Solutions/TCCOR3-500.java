import java.util.Arrays;

/**
 * Created by omar on 20/07/17.
 */
public class Volleyball {
    double[][] memo;
    public double win(int S, int R, double P) {
        memo = new double[2001][2001];
        for (double[] d : memo) Arrays.fill(d, -2);
        return solve(S, R, P);
    }

    double solve (int S, int R, double P) {
        if (S > 2000 || R > 2000) return 0;
        if (won(S, R)) return 1;
        if (won(R, S)) return 0;
        if (memo[S][R] > -1) return memo[S][R];
        return memo[S][R] = P * solve(S + 1, R, P) + (1 - P) * (1 - solve(R + 1, S, P));
    }
    boolean won (int x, int y) {
        return x >= 15 && x - y >= 2;
    }
}
