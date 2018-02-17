import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        int T = sc.nextInt();
        for (int t = 1 ; t <= T ; ++ t){
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();
            int m = sc.nextInt();
            int n = sc.nextInt();

            int arr [] = new int[n];
            arr[0] = a;
            for (int i = 1 ; i < n ; ++ i)
                arr[i] = ((arr[i - 1] * b + c) % m) + 1;

            int wanted [] = new int[m];
            int ans = 0;
            int modSoFar = 0;
            for (int i = 0 ; i < n ; ++ i) {
                modSoFar = (modSoFar + arr[i]) % m;
                ans += wanted[modSoFar] + (arr[i] % m == 0 ? 1 : 0);
                arr[i] %= m;
                wanted[((((m - arr[i]) + modSoFar) % m) + m) % m] ++;
            }

            out.printf("Case %d: %d\n" , t , ans);
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
