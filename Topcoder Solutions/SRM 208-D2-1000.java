import java.util.Arrays;

/**
 * Created by omar on 21/07/17.
 */
public class QueenInterference {
    boolean[][] isQueen;

    int n;
    public int numSteps(int nn) {
        n = nn;
        isQueen = new boolean[n + 1][n + 1];
        for (int c = 1; c <= n; c++)
            isQueen[(int)rand(n)][c] = true;

        int steps = 0;
        while (true) {
            boolean[] T = T();
            int TT = 0;
            for (boolean x : T)
                TT += (x) ? 1 : 0;
            if (TT == 0) break;
            steps++;
            int K = (int)rand(TT);
            for (int C = 1; C <= n; C++) {
                if (T[C] && K == 1) {
                    int numberCanReach[] = new int[n + 1];
                    int best = 1;
                    int tie = 0;
                    for (int row = 1; row <= n; row++) {
                        numberCanReach[row] = canReachCol(row, C);
                        if (numberCanReach[best] == numberCanReach[row]) {
                            tie++;
                        } else if (numberCanReach[best] > numberCanReach[row]){
                            best = row;
                            tie = 1;
                        }
                    }
                    if (tie == 1) {
                        for (int row = 1; row <= n; row++) {
                            if (isQueen[row][C]) {
                                isQueen[row][C] = false;
                                isQueen[best][C] = true;
                                break;
                            }
                        }
                    } else {
                        int Q = (int)rand(tie);
                        for (int row = 1; row <= n; row++) {
                            if (numberCanReach[row] == numberCanReach[best]) {
                                if (Q == 1) {
                                    for (int row2 = 1; row2 <= n; row2++) {
                                        if (isQueen[row2][C]) {
                                            isQueen[row2][C] = false;
                                            isQueen[row][C] = true;
                                            break;
                                        }
                                    }
                                    break;
                                } else {
                                    Q--;
                                }
                            }
                        }
                    }
                    break;
                } else if (T[C]) K--;
            }

        }
        return steps;
    }

    boolean[] T () {
        boolean[] res = new boolean[n + 1];
        for (int c = 1; c <= n; c++) {
            boolean can = false;
            for (int row = 1; row <= n; row++) {
                can |= (isQueen[row][c] && canReachCol(row, c) != 0);
            }
            res[c] = can;
        }
        return res;
    }

    int canReachCol (int row, int col) {
        int res = 0;
        for (int c = col + 1; c <= n; c++)
            if (isQueen[row][c])
                res++;
        for (int c = col - 1; c >= 1; c--)
            if (isQueen[row][c])
                res++;
        int[] dx = {1, 1, -1, -1};
        int[] dy = {1, -1, 1, -1};
        for (int add = 1; add <= n; add++) {
            for (int k = 0; k < 4; k++) {
                int nRow = row + dx[k] * add;
                int nCol = col + dy[k] * add;
                if (valid(nRow, nCol) && isQueen[nRow][nCol])
                    res++;
            }
        }
        return res;
    }

    boolean valid (int row, int col) {
        return row >= 1 && col >= 1 && row <= n && col <= n;
    }
    long last = -1;
    long rand (int up) {
        if (last == -1)
            last = 1;
        else
            last = ( 1L * 16807 * last ) % 2147483647L;
        return (last % up) + 1;
    }
}
