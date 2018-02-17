package codes;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class TheGridDivTwo {
    public int find(int[] x, int[] y, int k) {
        int MAX = 1001;
        boolean[][] blocked = new boolean[2 * MAX][2 * MAX];
        for (int i = 0; i < x.length; i++)
            blocked[x[i] + MAX][y[i] + MAX] = true;
        int max = MAX;
        Queue<Integer> q = new LinkedList<>();
        q.add(MAX); q.add(MAX);
        int[][] dist = new int[2 * MAX][2 * MAX];
        int[] dx = {0, 0, 1, -1}, dy = {1, -1, 0, 0};
        for (int[] d : dist) Arrays.fill(d, k + 10000);
        dist[MAX][MAX] = 0;
        while (!q.isEmpty()) {
            int curX = q.poll(), curY = q.poll();
            if (dist[curX][curY] < k) {
                for (int d = 0; d < 4; d++) {
                    int nx = curX + dx[d], ny = curY + dy[d];
                    if (nx >= 0 && ny >= 0 && !blocked[nx][ny] && dist[nx][ny] == k + 10000) {
                        dist[nx][ny] = dist[curX][curY] + 1;
                        max = Math.max(max, nx);
                        q.add(nx); q.add(ny);
                    }
                }
            }
        }
        return max - MAX;
    }
}
