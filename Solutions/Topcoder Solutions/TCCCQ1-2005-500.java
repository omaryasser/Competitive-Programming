/**
 * Created by omar on 20/07/17.
 */
public class ChessKnight {
    Double[][][] memo;
    int[] dx = {2, 2, -2, -2, 1, -1, 1, -1};
    int[] dy = {1, -1, 1, -1, 2, 2, -2, -2};
    boolean valid (int x, int y) {
        return x >= 0 && y >= 0 && x < 8 && y < 8;
    }
    double dp (int x, int y, int n) {
        if (n == 0) return valid(x, y) ? 1 : 0;
        if (memo[x][y][n] != null) return memo[x][y][n];
        double res = 0;
        for (int i = 0; i < 8; i++) {
            int nx = x + dx[i], ny = y + dy[i];
            if (valid(nx, ny))
                res += 1.0 / 8 * dp(nx, ny, n - 1);
        }
        return memo[x][y][n] = res;
    }
    public double probability(int x, int y, int n) {
        memo = new Double[8][8][n + 1];
        return dp(x - 1, y - 1, n);
    }
}
