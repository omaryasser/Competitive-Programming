import java.util.Arrays;

/**
 * Created by omar on 6/5/17.
 */
public class StarAdventure {
    int n, m;
    int arr [][];
    int memo [][][][];
    int dx [] = {1, 0};
    int dy [] = {0, 1};
    boolean valid (int row, int col) {
        return row >= 0 && col >= 0 && row < n && col < m;
    }
    int dp (int row1, int col1, int row2, int col2, int row3, int col3) {
        if (memo[row1][col1][col2][col3] != -1) return memo[row1][col1][col2][col3];
        if (row1 == n - 1 && col1 == m - 1 && col2 == m - 1 && col3 == m - 1) return arr[row1][col1];
        int base = arr[row1][col1];
        int res = base;
        if (row2 != row1 || col2 != col1) base += arr[row2][col2];
        if ((row3 != row1 || col3 != col1) && (row2 != row3 || col2 != col3)) base += arr[row3][col3];
        for (int k1 = 0; k1 < 2; k1++) for (int k2 = 0; k2 < 2; k2++) for (int k3 = 0; k3 < 2; k3++) {
            int row11 = row1 + dx[k1];
            int row22 = row2 + dx[k2];
            int row33 = row3 + dx[k3];
            int col11 = col1 + dy[k1];
            int col22 = col2 + dy[k2];
            int col33 = col3 + dy[k3];
            if (valid(row11, col11) && valid(row22, col22) && valid(row33, col33))
                res = Math.max(res, base + dp(row11, col11, row22, col22, row33, col33));
        }
        return memo[row1][col1][col2][col3] = res;
    }
    public 	int mostStars(String[] level) {
        n = level.length;
        m = level[0].length();
        arr = new int[n][m];
        memo = new int[n][m][m][m];
        for (int i = 0; i < n; i++) for (int j = 0; j < m; j++) for (int k = 0; k < m; k++) Arrays.fill(memo[i][j][k], -1);
        for (int i = 0; i < n; i++) for (int j = 0; j < m; j++) arr[i][j] = level[i].charAt(j) - '0';
        return dp(0, 0, 0, 0, 0, 0);
    }
    
}
