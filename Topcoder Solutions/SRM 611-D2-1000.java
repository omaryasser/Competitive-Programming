/**
 * Created by omar on 12/3/16.
 */
public class ElephantDrinkingEasy {
    public static boolean ok (int mask , String map []) {
        int n = map.length;
        boolean taken [][] = new boolean[n][n];
        boolean ok = true;
        for (int i = 0 ; i < n ; ++ i) {
            if (((1 << i) & mask) != 0) {
                boolean f = false;
                for (int j = 0; j < n; ++ j) {
                    if (taken[i][j]) return false;
                    taken[i][j] = true;
                    if (map[i].charAt(j) == 'Y') {
                        f = true;
                        break;
                    }
                }
                if (!f) return f;
            }

            if (((1 << (i + n)) & mask) != 0) {
                boolean f = false;
                for (int j = n - 1; j >= 0; -- j) {
                    if (taken[i][j]) return false;
                    taken[i][j] = true;
                    if (map[i].charAt(j) == 'Y') {
                        f = true;
                        break;
                    }
                }
                if (!f) return f;
            }

            if (((1 << (i + n + n)) & mask) != 0) {
                boolean f = false;
                for (int j = 0; j < n; ++ j) {
                    if (taken[j][i]) return false;
                    taken[j][i] = true;
                    if (map[j].charAt(i) == 'Y') {
                        f = true;
                        break;
                    }
                }
                if (!f) return f;
            }

            if (((1 << (i + n + n + n)) & mask) != 0) {
                boolean f = false;
                for (int j = n - 1; j >= 0; j--) {
                    if (taken[j][i]) return false;
                    taken[j][i] = true;
                    if (map[j].charAt(i) == 'Y') {
                        f = true;
                        break;
                    }
                }
                if (!f) return f;
            }
        }

        return ok;
    }
    public static int maxElephants(String[] map) {
        int n = map.length;
        int mask = (1 << (4 * n)) - 1;
        int res = 0 ;
        while (mask -- > 0) {
            if (ok(mask , map)) {
                int cnt = 0 ;
                for (int i = 0; i < (4 * n); ++ i) {
                    if ((mask & (1 << i)) != 0) cnt ++ ;
                }
                res = Math.max(res , cnt);
            }
        }
        return res;
    }


    public static void main (String [] args) {
        String x [] =  {"YNYN",
                "NNYY",
                "YYNN",
                "YYYY"};
        System.out.println(ElephantDrinkingEasy.maxElephants(x));
    }
}
