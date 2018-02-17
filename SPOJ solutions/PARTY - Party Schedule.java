import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.*;

public class Main {
    static int n;
    static int cost [];
    static int fun [];
    static int memo [][];

    static int dp (int idx , int moneyLeft) {
        if (idx == n) return 0;
        if (memo[idx][moneyLeft] != - 1) return memo[idx][moneyLeft];

        int f = dp(idx + 1 , moneyLeft);
        int s = 0;
        if (cost[idx] <= moneyLeft) s = fun[idx] + dp(idx + 1 , moneyLeft - cost[idx]);
        return memo[idx][moneyLeft] = Math.max(f , s);
    }
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        while (true) {
            int budget = sc.nextInt();
            n = sc.nextInt();
            if (n == 0 && budget == 0) break;
            fun = new int[n];
            cost = new int[n];
            for (int i = 0 ; i < n ; ++ i) {
                cost[i] = sc.nextInt();
                fun[i]  = sc.nextInt();
            }
            memo = new int[n][budget + 1];
            for (int i = 0 ; i < n ; ++ i)
                Arrays.fill(memo[i] , - 1);
            int maxFun = 0 , lowestBudget = 0;
            for (int i = 0 ; i <= budget ; ++ i) {
                int funHere = dp(0 , i);
                if (funHere > maxFun) {
                    maxFun = funHere;
                    lowestBudget = i;
                }
            }

            System.out.println(lowestBudget + " " + maxFun);
        }
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
