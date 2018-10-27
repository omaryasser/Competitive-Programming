import java.util.Arrays;

/**
 * Created by omar on 4/15/17.
 */
public class BoardEscapeDiv2 {
    char [][] arr;
    int n, m;
    int dx [] = {0, 0, 1, -1};
    int dy [] = {1, -1, 0, 0};
    boolean valid (int x, int y) {
        return x >= 0 && y >= 0 && x < n && y < m && arr[x][y] != '#';
    }

    int memo[][][][];
    int dp (int x, int y, int rem, int turn) {
        if (rem == 0) return 1 - turn;
        if (memo[x][y][rem][turn] != -1) {
            return memo[x][y][rem][turn];
        }

        int res = 1 - turn;
        for (int move = 0; move < 4; move++) {
            int nx = x + dx[move], ny = y + dy[move];
            if (valid(nx, ny)) {
                if (arr[nx][ny] == 'E' || dp(nx, ny, rem - 1, 1 - turn) == turn)
                    res = turn;
            }
        }
        return memo[x][y][rem][turn] = res;
    }
    public String findWinner(String[] s, int k) {
        n = s.length;
        m = s[0].length();
        arr = new char[n][m];
        for (int i = 0; i < n; i++)
            arr[i] = s[i].toCharArray();

        memo = new int[n][m][k + 1][2];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                for (int l = 0; l < k + 1; l++)
                    Arrays.fill(memo[i][j][l], -1);

        int x = 0, y = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (arr[i][j] == 'T') {
                    x = i;
                    y = j;
                }
            }
        }
        return dp(x, y, k, 0) == 0 ? "Alice" : "Bob";
    }
}
