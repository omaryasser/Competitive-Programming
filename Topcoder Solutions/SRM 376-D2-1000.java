import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.TreeSet;

/**
 * Created by omar on 12/3/16.
 */
public class JollyJumpers {
    static int encode (int grid [][] ) {
        int code = 0;
        for (int i = 0 ; i < 4 ; ++ i) {
            for (int j = 0 ; j < 4 ; ++ j) {
                if (grid[i][j] == 1) code |= (1 << (i * 4 + j));
            }
        }
        return  code;
    }
    static class Pair {
        int g [][];
        int code;
        int score;
        Pair(int [][] gg , int cc , int ss) {
            g = gg;
            code = cc;
            score = ss;
        }
    }

    public static void pp (int a[][]) {
        for (int i = 0 ; i < 4 ; ++ i) {
            for (int j = 0 ; j < 4 ; ++ j) {
                System.out.print(a[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
    }
    static boolean valid (int i , int j){
        return i >= 0 && j >= 0 && i < 4 && j < 4;
    }
    static int [][] clone (int a[][]) {
        int b[][] = new int[4][4];
        for (int i = 0 ; i < 4 ; ++ i) {
            for (int j = 0 ; j < 4 ; ++ j) {
                b[i][j] = a[i][j];
            }
        }
        return b;
    }
    public static int maxScore(String[] layout) {
        int grid [][] = new int[4][4];
        for (int i = 0 ; i < 4;  ++ i) {
            for (int j = 0 ; j < 4 ; ++ j) {
                grid[i][j] = layout[i].charAt(j) == '#' ? 1 : 0;
            }
        }
        int code = encode(grid);
        Queue<Pair> queue = new LinkedList<>();
        TreeSet<Integer> codes = new TreeSet<>();
        codes.add(code);
        queue.add(new Pair(grid , code , 0));
        int maxScore = 0 ;
        while (!queue.isEmpty()) {
            Pair cur = queue.poll();
            int g [][] = cur.g;
            int score = cur.score ;
            maxScore = Math.max(maxScore , score);
            for (int i = 0 ; i < 4 ; ++ i) {
                for (int j = 0 ; j < 4 ; ++ j) {
                    if (g[i][j] == 1) {
                        if (valid(i, j + 1) && g[i][j + 1] == 0) {
                            g[i][j] = 0;
                            g[i][j + 1] = 1;
                            int newCode = encode(g);
                            if (!codes.contains(newCode)) {
                            int f [][] = clone(g);
                                codes.add(newCode);
                                queue.add(new Pair(f, newCode, score - 1));
                            }
                            g[i][j] = 1;
                            g[i][j + 1] = 0;
                        }

                        if (valid(i, j - 1) && g[i][j - 1] == 0) {
                            g[i][j] = 0;
                            g[i][j - 1] = 1;
                            int newCode = encode(g);
                            if (!codes.contains(newCode)) {
                            int f [][] = clone(g);
                                codes.add(newCode);
                                queue.add(new Pair(f, newCode, score - 1));
                            }
                            g[i][j] = 1;
                            g[i][j - 1] = 0;
                        }

                        if (valid(i - 1 , j) && valid(i - 2 , j) && g[i - 1][j] == 1 && g[i - 2][j] == 0) {
                            g[i][j] = 0 ;
                            g[i - 1][j] = 0;
                            g[i - 2][j] = 1;
                            int newCode = encode(g);
                            if (!codes.contains(newCode)) {
                            int f [][] = clone(g);
                                codes.add(newCode);
                                queue.add(new Pair(f, newCode, score + 2));
                            }
                            g[i][j] = 1 ;
                            g[i - 1][j] = 1;
                            g[i - 2][j] = 0;
                        }

                        if (valid(i + 1 , j) && valid(i + 2 , j) && g[i + 1][j] == 1 && g[i + 2][j] == 0) {

                            g[i][j] = 0 ;
                            g[i + 1][j] = 0;
                            g[i + 2][j] = 1;
                            int newCode = encode(g);
                            if (!codes.contains(newCode)) {
                                  int f [][] = clone(g);
                                codes.add(newCode);
                                queue.add(new Pair(f, newCode, score + 2));
                            }
                            g[i][j] = 1 ;
                            g[i + 1][j] = 1;
                            g[i + 2][j] = 0;
                        }
                    }
                }
            }
        }

        return maxScore;
    }

    public static void main (String [] args) {
        String a [] = {
                "....",
                ".#..",
                "..#.",
                "...."};
        System.out.println(JollyJumpers.maxScore(a));
    }
}
