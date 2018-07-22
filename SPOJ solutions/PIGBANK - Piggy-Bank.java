import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        int T = sc.nextInt();
        while (T -- > 0) {
            int maxW = - sc.nextInt();
            maxW += sc.nextInt();
            int n = sc.nextInt();
            int price [] = new int[n] , weight [] = new int[n];
            for (int i = 0 ; i < n ; ++ i) {
                price [i] = sc.nextInt();
                weight [i] = sc.nextInt();
            }

            int dp [][] = new int [n][maxW + 1];
            for (int i = 0 ; i < n ; ++ i)
                Arrays.fill(dp[i] , - 1);
            for (int i = 0 ; i < n ; ++ i)
                dp[i][0] = 0;
            for (int i = 0 ; i <= maxW ; ++ i)
                dp[0][i] = i % weight[0] == 0 ? price[0] * (i / weight[0]) : - 1;

            for (int w = 1 ; w <= maxW ; ++ w) {
                for (int i = 1 ; i < n ; ++ i) {
                    for (int j = 0 ; ; ++ j) {
                        if (j * weight[i] > w) break;
                        int here = - 1;
                        if (dp[i - 1][w - j * weight[i]] != - 1)
                            here = j * price[i] + dp[i - 1][w - j * weight[i]];
                        if (here == - 1) continue;
                        if (dp[i][w] == - 1 || here < dp[i][w])
                            dp[i][w] = here;
                    }
                }
            }
            if (dp[n - 1][maxW] == - 1)
                out.println("This is impossible.\n");
            else out.printf("The minimum amount of money in the piggy-bank is %d.\n" , dp[n - 1][maxW]);
        }
        out.close();
    }




























    static class Scanner {
        StringTokenizer st;
        BufferedReader br;

        public Scanner(InputStream s) {
            br = new BufferedReader(new InputStreamReader(s));
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
            String x = next();
            StringBuilder sb = new StringBuilder("0");
            double res = 0, f = 1;
            boolean dec = false, neg = false;
            int start = 0;
            if (x.charAt(0) == '-') {
                neg = true;
                start++;
            }
            for (int i = start; i < x.length(); i++)
                if (x.charAt(i) == '.') {
                    res = Long.parseLong(sb.toString());
                    sb = new StringBuilder("0");
                    dec = true;
                } else {
                    sb.append(x.charAt(i));
                    if (dec)
                        f *= 10;
                }
            res += Long.parseLong(sb.toString()) / f;
            return res * (neg ? -1 : 1);
        }

        public boolean ready() throws IOException {
            return br.ready();
        }

    }
}
