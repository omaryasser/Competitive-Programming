package codes;

public class TimeTravellingGardener {
    public int determineUsage(int[] d, int[] height) {
        int n = height.length;
        int[][] distance = new int[n][n];
        for (int i = 0; i < n; i++) {
            int cur = 0;
            for (int j = i + 1; j < n; j++) {
                cur += d[j - 1];
                distance[i][j] = distance[j][i] = cur;
            }
        }
        int res = n - 1;
        for (int i = 0; i < n; i++)
            for (int j = i + 1; j < n; j++) {
                boolean can = true;
                int cnt = 0;

                int dy = height[j] - height[i], dx = distance[i][j];
                if (dy * distance[i][n - 1] < - height[i] * dx) can = false;
                if (height[i] * dx < dy * distance[0][i]) can = false;

                for (int k = 0; k < i; k++) {
                    if ((height[i] - height[k]) * distance[i][j] > (height[j] - height[i]) * distance[k][i]) {
                        can = false;
                    }
                    if ((height[i] - height[k]) * distance[i][j] != (height[j] - height[i]) * distance[k][i])
                        cnt++;
                }
                for (int k = i + 1; k < n; k++) {
                    if ((height[j] - height[i]) * distance[k][i] > (height[k] - height[i]) * distance[i][j])
                        can = false;
                    if ((height[j] - height[i]) * distance[k][i] != (height[k] - height[i]) * distance[i][j])
                        cnt++;
                }

                if (can) res = Math.min(cnt, res);
            }
        return res;
    }
}
