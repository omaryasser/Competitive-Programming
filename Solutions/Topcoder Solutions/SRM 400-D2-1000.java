import com.sun.org.apache.regexp.internal.RE;

import java.util.*;

/**
 * Created by omar on 12/4/16.
 */
public class LightedPanels {
    static int n , m ;
    static int g [][];
    static boolean valid (int i , int j) {
        return i >= 0 && j >= 0 && i < n && j < m;
    }
    static int memo[][][];
    static int dy[] = {0 , - 1 , 1};
    static int dp (int row , int mask , int myMask) {
        if (row == n){
            if (Integer.bitCount(mask) == m) {
                return memo[row][mask][myMask] = 0;
            }
            else return memo[row][mask][myMask] = 500;
        }

        if (memo[row][mask][myMask] != - 1) {
            return memo[row][mask][myMask];
        }

        int best = 500;
        for (int i = 0 ; i < (1 << m) ; ++ i) {
            int up = mask ;
            for (int j = 0 ; j < m ; ++ j) {
                if (((1 << j) & i) != 0) {
                    for (int k = 0 ; k < 3 ; ++ k) {
                        if (valid(0 , j + dy[k])) {
                            up ^= (1 << (j + dy[k]));
                        }
                    }
                }
            }
            if (Integer.bitCount(up) == m) {
                for (int j = 0 ; j < m ; ++ j) {
                    if (((1 << j) & i) != 0) {
                        g[row][j] ^= 1;
                        if (valid(row , j + 1)) g[row][j + 1] ^= 1;
                        if (valid(row , j - 1)) g[row][j - 1] ^= 1;
                    }
                }
                if (row + 1 < n) {
                    for (int j = 0; j < m; ++j) {
                        if (((1 << j) & i) != 0) {
                            for (int k = 0; k < 3; ++k) {
                                if (valid(1, j + dy[k])) {
                                    g[row + 1][j + dy[k]] ^= 1;
                                }
                            }
                        }
                    }
                }
                int newMask = 0;
                for (int j = 0 ; j < m ; ++ j) {
                    if (g[row][j] == 1) {
                        newMask |= (1 << j);
                    }
                }
                int nxt = 0;
                if (row + 1 < n) {
                    for (int j = 0; j < m; ++j) {
                        if (g[row + 1][j] == 1) {
                            nxt |= (1 << j);
                        }
                    }
                }
                best = Math.min(best , Integer.bitCount(i) + dp(row + 1 , newMask , nxt));
                if (row == 1 && mask == 0 && i == 2 && myMask == 0) System.out.println(Integer.bitCount(nxt) + " " + Integer.bitCount(i) + " " + Integer.toBinaryString(newMask));
                if (row + 1 < n) {
                        for (int j = 0; j < m; ++j) {
                            if (((1 << j) & i) != 0) {
                                for (int k = 0; k < 3; ++k) {
                                    if (valid(1, j + dy[k])) {
                                        g[row + 1][j + dy[k]] ^= 1;
                                    }
                                }
                            }
                        }
                }
                for (int j = 0 ; j < m ; ++ j) {
                    if (((1 << j) & i) != 0) {
                        g[row][j] ^= 1;
                        if (valid(row , j + 1)) g[row][j + 1] ^= 1;
                        if (valid(row , j - 1)) g[row][j - 1] ^= 1;
                    }
                }
            }
        }
        return memo[row][mask][myMask] = best;
    }
    public static int minTouch(String[] board) {
        memo = new int[10][1<< 9][1 << 9];
        for (int i = 0 ; i < 8 ; ++ i) {
            for (int j = 0 ; j < (1 << 8) ; ++ j) {
                Arrays.fill(memo[i][j], -1);
            }
        }
        n = board.length ; m = board[0].length();
        g = new int[n][m];
        for (int i = 0 ; i < n ; ++ i){
            for (int j = 0 ; j < m ; ++ j) {
                g[i][j] = board[i].charAt(j) == '*' ? 1 : 0;
            }
        }
        int best = 1000;
        for (int i = 0 ; i < (1 << m) ; ++ i) {
            for (int j = 0 ; j < m ; ++ j) {
                if (((1 << j) & i) != 0) {
                    g[0][j] ^= 1;
                    if (valid(0 , j + 1)) g[0][j + 1] ^= 1;
                    if (valid(0 , j - 1)) g[0][j - 1] ^= 1;
                    for (int k = 0 ; k < 3 ; ++ k) {
                        if (valid(1 , j + dy[k])) {
                            g[1][j + dy[k]] ^= 1;
                        }
                    }
                }
            }
            int mask = 0 ;
            for (int j = 0 ; j < m ; ++ j) {
                if (g[0][j] == 1) {
                    mask |= (1 << j);
                }
            }
                int nxt = 0;
            if (1 < n) {
                for (int j = 0; j < m; ++j) {
                    if (g[1][j] == 1) {
                        nxt |= (1 << j);
                    }
                }
            }

            best = Math.min(best , Integer.bitCount(i) + dp(1 , mask , nxt));

            for (int j = 0 ; j < m ; ++ j) {
                if (((1 << j) & i) != 0) {
                    g[0][j] ^= 1;
                    if (valid(0 , j + 1)) g[0][j + 1] ^= 1;
                    if (valid(0 , j - 1)) g[0][j - 1] ^= 1;
                    for (int k = 0 ; k < 3 ; ++ k) {
                        if (valid(1 , j + dy[k])) {
                            g[1][j + dy[k]] ^= 1;
                        }
                    }
                }
            }
        }
        return best > 65 ? - 1 : best;
    }

    public static void main (String [] args) {
        System.out.println(LightedPanels.minTouch(new String []{"*...",
                "**..",
                "..**",
                "...*"}));
    }
}
