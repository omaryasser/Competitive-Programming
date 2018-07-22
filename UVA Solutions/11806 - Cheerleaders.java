import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, MOD = 1000007, memo[][][];
    static ArrayList<Integer> sides[];
    static int FULL = (1 << 4) - 1;
    static int INNER;

    static int dp(int idx, int mask, int rem) {
        if (rem < 0) return 0;
        if (idx == N) {
            return mask == FULL && rem <= INNER ? (int)nCr2(INNER, rem) : 0;
        }
        if (memo[idx][mask][rem] != -1) return memo[idx][mask][rem];
        int res = dp(idx + 1, mask, rem);
        int newMask = mask;
        for (int i = 0; i < sides[idx].size(); i++) {
            newMask |= (1 << sides[idx].get(i));
        }
        res += dp(idx + 1, newMask, rem - 1);
        res %= MOD;
        return memo[idx][mask][rem] = res;
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        int tc = sc.nextInt();
        comb = new long[401][501];
        for (long[] c : comb) Arrays.fill(c, -1);
        for (int tcc = 1; tcc <= tc; tcc++) {
            int n = sc.nextInt(), m = sc.nextInt(), k = sc.nextInt();
            INNER = (n - 2) * (m - 2);
            N = n + m - 1 + n - 1 + m - 2;
            sides = new ArrayList[N];
            for (int i = 0; i < N; i++) sides[i] = new ArrayList<>(2);
            int idx = 0;
            sides[0].add(3);
            while (idx < n) sides[idx++].add(0);
            idx--;
            while (idx < n + m - 1) sides[idx++].add(1);
            idx--;
            while (idx < n + m - 1 + n - 1) sides[idx++].add(2);
            idx--;
            while (idx < N) sides[idx++].add(3);
            memo = new int[N][1 << 4][k + 1];
            for (int[][] ee : memo) for (int[] e : ee) Arrays.fill(e, -1);
            out.printf("Case %d: %d\n", tcc, dp(0, 0, k));
        }

        out.flush();
    }

    static long [][] comb; // Initialize it with -1
    static long nCr2(int n, int k)		// O(n * k)
    {
        if(n < k)
            return 0;
        if(k == 0 || k == n)		//may add k == 1 as a base case for fast calculations
            return 1;
        if(comb[n][k] != -1)
            return comb[n][k];
        if(n - k < k)
            return comb[n][k] = nCr2(n, n - k);		//reduce k to n - k
        return comb[n][k] = (nCr2(n - 1, k - 1) + nCr2(n - 1 , k)) % MOD;
    }


    static class Scanner {
        BufferedReader br;
        StringTokenizer st;

        Scanner(InputStream s) {
            br = new BufferedReader(new InputStreamReader(s));
        }

        Scanner(FileReader s) {
            br = new BufferedReader(s);
        }

        String next() throws IOException {
            while (st == null || !st.hasMoreTokens())
                st = new StringTokenizer(br.readLine());
            return st.nextToken();
        }

        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
}
