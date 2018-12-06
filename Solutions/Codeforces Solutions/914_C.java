import java.io.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static int arr[];
    static int n;
    static int k;
    static int MOD = (int)1e9 + 7;
    static int memo[][][];
    static int dp (int idx, int rem, int restricted) {
        if (idx == n) {
            return rem == 0 ? 1 : 0;
        }
        if (memo[idx][rem][restricted] != -1) return memo[idx][rem][restricted];
        int res = 0;
        if (arr[idx] == 0) {
            if (restricted == 1) res = dp(idx + 1, rem, restricted);
            else {
                res = dp(idx + 1, rem, restricted);
                if (rem > 0) res += dp(idx + 1, rem - 1, restricted);
                if (res >= MOD) res -= MOD;
            }
        } else {
            res = dp(idx + 1, rem, 0);
            if (rem > 0) res += dp(idx + 1, rem - 1, restricted);
            if (res >= MOD) res -= MOD;
        }
        return memo[idx][rem][restricted] = res;
    }
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        String s = sc.next();
        ArrayList<Integer> a = new ArrayList<>();
        for (int i = 0; i < s.length(); i++)
            a.add(s.charAt(i) - '0');
        k = sc.nextInt();
        memo = new int[1001][1001][2];
        for (int[][] mm : memo) for (int[] m : mm) Arrays.fill(m, -1);
        n = a.size();
        arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = a.get(i);

        int ways[] = new int[1001];
        ways[1] = n - 1;
        for (int i = 2; i <= 1000; i++)
            ways[i] = dp(0, i, 1);

        memo2 = new int [1001];
        Arrays.fill(memo2, -1);
        int res = 0;
        for (int i = 1; i <= 1000; i++)
            if(f(i) == k) {
                res += ways[i];
                if (res >= MOD) res -= MOD;
            }

        out.println(k == 0 ? 1 : res);
        out.flush();
        out.close();
    }

    static int memo2[];
    static int f(int bits) {
        if (memo2[bits] != -1) return memo2[bits];
        if (bits == 1) return 1;
        else return memo2[bits] = 1 + f(Integer.bitCount(bits));
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
