import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class A {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        int tc = sc.nextInt();
        for (int tt = 1; tt <= tc; tt++){
            int n = sc.nextInt();
            int [] x = new int[n], y = new int[n];
            for (int i = 0; i < n; i++) {
                x[i] = sc.nextInt();
                y[i] = sc.nextInt();
            }
            double adjMat [][] = new double[n][n];
            int INF = 100000;
            for (int i = 0; i < n; i++) Arrays.fill(adjMat[i], INF);
            for (int i = 0; i < n; i++) adjMat[i][i] = 0;

            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                        if ((x[i] - x[j]) * (x[i] - x[j]) + (y[i] - y[j]) * (y[i] - y[j]) <= 100) {
                            adjMat[i][j] = adjMat[j][i] = Math.sqrt((x[i] - x[j]) * (x[i] - x[j]) + (y[i] - y[j]) * (y[i] - y[j]));
                        }
                }
            }

            for (int k = 0; k < n; k ++) for (int i = 0; i < n; i++) for (int j = 0; j < n; j++) {
                adjMat[i][j] = Math.min(adjMat[i][j], adjMat[i][k] + adjMat[k][j]);
            }

            boolean can = true;
            double max = 0;
            for (int i = 0; i < n; i++) for (int j = i + 1; j < n; j++) {
                if (adjMat[i][j] == INF) can = false;
                max = Math.max(max, adjMat[i][j]);
            }
            out.printf("Case #%d:\n", tt);
            if (!can) out.println("Send Kurdy");
            else out.printf("%.4f\n", max);
            out.println();
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
