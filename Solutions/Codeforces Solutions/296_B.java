import java.io.*;
import java.util.*;

public class Main {
    static int MOD = (int)1e9 + 7;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        int n = sc.nextInt();
        char[] a = sc.next().toCharArray(), b = sc.next().toCharArray();
        int questionMarks = 0;

        for (int i = 0; i < n; i++) {
            if (a[i] == '?') questionMarks++;
            if (b[i] == '?') questionMarks++;
        }

        long waysLess = 1;
        long waysMore = 1;

        long allEqual = 1;
        for (int i = 0; i < n; i++) {
            if (a[i] == '?' && b[i] == '?') {
                waysLess *= 10 * 11 / 2;
                waysMore *= 10 * 11 / 2;
                allEqual *= 10;
            }
            else if (a[i] == '?') {
                waysLess *= b[i] - '0' + 1;
                waysMore *= 10 - (b[i] - '0');
            } else if (b[i] == '?') {
                waysLess *= 10 - (a[i] - '0');
                waysMore *= a[i] - '0' + 1;
            } else {
                if (a[i] > b[i])
                    waysLess = 0;
                if (a[i] < b[i])
                    waysMore = 0;
                if (a[i] != b[i])
                    allEqual = 0;
            }
            waysLess %= MOD;
            waysMore %= MOD;
        }

        waysLess -= allEqual;
        long pow = 1;
        while (questionMarks-- > 0)
            pow = pow * 10 % MOD;

        out.println((((pow - waysLess - waysMore) % MOD) + MOD) % MOD);
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
