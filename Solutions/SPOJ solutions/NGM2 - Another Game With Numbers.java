import java.io.*;
import java.util.StringTokenizer;

/**
 * Created by omar on 17/10/17.
 */
public class NGM2 {
    static long gcd (long a, long b) {return b == 0 ? a : gcd(b, a % b);}
    static long LCM (long a, long b) {return a * (b / gcd(a, b));}
    static int numDiv (int max, int div) {return max / div;}
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        int n = sc.nextInt(), k = sc.nextInt();
        int[] arr = new int[k];
        for (int i = 0; i < k; i++) arr[i] = sc.nextInt();
        long removed = 0;
        for (int mask = 1; mask < 1 << k; mask++) {
            int sign = (Integer.bitCount(mask) & 1) == 1 ? 1 : -1;
            long mult = 1;
            boolean bad = false;
            for (int i = 0; i < k; i++)
                if (((mask >> i) & 1) == 1) {
                    mult = LCM (mult, arr[i]);
                    if (mult > 1000_000_000) bad = true;
                }
            if (bad) continue;
            removed += sign * numDiv(n, (int)mult);
        }
        out.println(n - removed);
        out.close();
    }
    static class Scanner
    {
        BufferedReader br;
        StringTokenizer st;

        Scanner(InputStream s) { br = new BufferedReader(new InputStreamReader(s)); }

        Scanner(FileReader s) { br = new BufferedReader(s); }

        String next() throws IOException {
            while (st == null || !st.hasMoreTokens())
                st = new StringTokenizer(br.readLine());
            return st.nextToken();
        }

        int nextInt() throws IOException { return Integer.parseInt(next()); }
    }
}
