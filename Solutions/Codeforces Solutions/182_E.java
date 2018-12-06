import java.io.*;
import java.util.*;

public class Main {
    static int MOD = (int) 1e9 + 7;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        int n = sc.nextInt(), l = sc.nextInt();
        int[][] sides = new int[2][n];
        boolean[] same = new boolean[n];
        for (int i = 0; i < n; i++) {
            sides[0][i] = sc.nextInt();
            sides[1][i] = sc.nextInt();
            same[i] = sides[0][i] == sides[1][i];
        }

        int[][][] dp = new int[2][n][l + 1];
        for (int rotation = 0; rotation < 2; rotation++)
            for (int last = 0; last < n; last++) {
                if (same[last] && rotation == 1) continue;
                if (sides[rotation][last] <= l)
                    dp[rotation][last][sides[rotation][last]] = 1;
            }
        for (int len = 0; len <= l; len++) {
            for (int rotation = 0; rotation < 2; rotation++) {
                for (int last = 0; last < n; last++) {
                    if (rotation == 1 && same[last]) continue;
                    for (int beforeLast = 0; beforeLast < n; beforeLast++) {
                        if (beforeLast == last) continue;
                        for (int beforeLastRotation = 0; beforeLastRotation < 2; beforeLastRotation++) {
                            if (beforeLastRotation == 1 && same[beforeLast]) continue;

                            if (len - sides[rotation][last] - sides[beforeLastRotation][beforeLast] >= 0 &&
                                    sides[rotation][last] == sides[1 ^ beforeLastRotation][beforeLast]) {
                                dp[rotation][last][len] += dp[beforeLastRotation][beforeLast][len - sides[rotation][last]];
                                if (dp[rotation][last][len] >= MOD)
                                    dp[rotation][last][len] -= MOD;
                            }

                        }
                    }
                }
            }
        }

        int res = 0;
        for (int rotation = 0; rotation < 2; rotation++) {
            for (int last = 0; last < n; last++) {
                if (same[last] && rotation == 1) continue;
                res += dp[rotation][last][l];
                if (res >= MOD) res -= MOD;
            }
        }
        out.println(res);
        out.flush();
        out.close();
    }

    static class Scanner {
        StringTokenizer st;
        BufferedReader br;

        public Scanner(InputStream s) {
            br = new BufferedReader(new InputStreamReader(s));
        }

        public Scanner(String file) {
            try {
                br = new BufferedReader(new FileReader(file));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        public String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }

    }
}
