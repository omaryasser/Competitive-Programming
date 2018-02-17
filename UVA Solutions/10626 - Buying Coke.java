import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B {
    static int memo[][][] = new int[101 + 150][51][151];
    static int totalMoney, neededColas;

    static int dp(int rem5, int rem10, int boughtCoca) {
        if (boughtCoca == neededColas) return 0;
        if (memo[rem5][rem10][boughtCoca] != -1) return memo[rem5][rem10][boughtCoca];
        int rem1 = totalMoney - 8 * boughtCoca - rem5 * 5 - rem10 * 10;
        int best = (int) 1e9;
        if (rem10 != 0) {
            best = 1 + dp(rem5, rem10 - 1, boughtCoca + 1);
            if (rem1 >= 3)
                best = Math.min(best, 4 + dp(rem5 + 1, rem10 - 1, boughtCoca + 1));
        }
        if (rem5 >= 2)
            best = Math.min(best, 2 + dp(rem5 - 2, rem10, boughtCoca + 1));
        if (rem5 >= 1 && rem1 >= 3)
            best = Math.min(best, 4 + dp(rem5 - 1, rem10, boughtCoca + 1));
        if (rem1 >= 8)
            best = Math.min(best, 8 + dp(rem5, rem10, boughtCoca + 1));
        return memo[rem5][rem10][boughtCoca] = best;
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        int tc = sc.nextInt();
        while (tc-- > 0) {
            for (int[][] mm : memo) for (int[] m : mm) Arrays.fill(m, -1);
            neededColas = sc.nextInt();
            int r1 = sc.nextInt(), r5 = sc.nextInt(), r10 = sc.nextInt();
            totalMoney = r1 + r5 * 5 + r10 * 10;
            out.println(dp(r5, r10, 0));
        }

        out.flush();
        out.close();
    }

    static class Scanner {
        StringTokenizer st;
        BufferedReader br;

        public Scanner(InputStream s) {
            br = new BufferedReader(new InputStreamReader(s));
        }

        public Scanner(FileReader s) throws FileNotFoundException {
            br = new BufferedReader(s);
        }

        public String next() throws IOException {
            while (st == null || !st.hasMoreTokens())
                st = new StringTokenizer(br.readLine());
            return st.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }

        public long nextLong() throws IOException {
            return Long.parseLong(next());
        }

        public String nextLine() throws IOException {
            return br.readLine();
        }

        public double nextDouble() throws IOException {
            return Double.parseDouble(next());
        }

        public boolean ready() throws IOException {
            return br.ready();
        }
    }
}
