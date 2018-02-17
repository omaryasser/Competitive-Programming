package codes;

import java.util.ArrayList;
import java.util.Collections;

public class KnightsTour {
    char[][] grid = new char[8][8];
    int[] dx = {1, 1, -1, -1, 2, 2, -2, -2};
    int[] dy = {2, -2, 2, -2, 1, -1, 1, -1};
    boolean[][] visited= new boolean[8][8];

    boolean valid (int row, int col) {return row >= 0 && col >= 0 && row < 8 && col < 8 && !visited[row][col] && grid[row][col] != '*';}
    public int visitedPositions(String[] board) {
        for (int i = 0; i < 8; i++) grid[i] = board[i].toCharArray();
        int cur_x = 0, cur_y = 0;
        for (int i = 0; i < 8; i++) for (int j = 0; j < 8; j++)
            if (grid[i][j] == 'K') {cur_x = i; cur_y = j;}

        visited[cur_x][cur_y] = true;
        int cnt = 1;
        while (true) {
            ArrayList<Cell> valid = new ArrayList<Cell>();
            for (int k = 0; k < 8; k++) {
                int nx = cur_x + dx[k], ny = cur_y + dy[k];
                if (valid(nx, ny)) valid.add(new Cell(nx, ny));
            }
            Collections.sort(valid);
            if (valid.size() != 0) {
                cnt++;
                cur_x = valid.get(0).x; cur_y = valid.get(0).y;
                visited[cur_x][cur_y] = true;
            } else break;
        }
        return cnt;
    }

    int access (int row, int col) {
        int cnt = 0;
        for (int k = 0; k < 8; k++) {
            int nr = row + dx[k], nc = col + dy[k];
            if (valid(nr, nc)) cnt++;
        }
        return cnt;
    }
    class Cell implements Comparable<Cell> {
        int x, y;
        Cell (int xx, int yy) {x = xx; y = yy;}

        @Override
        public int compareTo(Cell cell) {
            int a1 = access(x, y), a2 = access (cell.x, cell.y);
            return a1 != a2 ? a1 - a2 : x != cell.x ? x - cell.x : y - cell.y;
        }
    }
}
