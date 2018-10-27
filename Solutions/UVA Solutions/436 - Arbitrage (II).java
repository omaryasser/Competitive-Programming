import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

import java.util.Random;
public class Main {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        for (int tc = 1;; tc++){
            int n = sc.nextInt();
            if (n == 0) break;

            HashMap<String, Integer> map = new HashMap<>();
            int mapIdx = 0;
            for (int i = 0; i < n; i++) map.put(sc.next(), mapIdx++);

            double adjMat [][] = new double[n][n];
            for (int i = 0; i < n; i++) adjMat[i][i] = 1;
            int E = sc.nextInt();
            while (E-- > 0) {
                String first = sc.next();
                double times = sc.nextDouble();
                String second = sc.next();
                adjMat[map.get(first)][map.get(second)] = times;
            }
            for (int k = 0; k < n; k++) for (int i = 0; i < n; i++) for (int j = 0; j < n; j++) {
                adjMat[i][j] = Math.max(adjMat[i][j], adjMat[i][k] * adjMat[k][j]);
            }

            boolean found = false;
            for (int i = 0; i < n; i++) {
                found |= adjMat[i][i] > 1;
            }
            out.printf("Case %d: %s\n", tc, found ? "Yes" : "No");
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
        public Scanner(FileReader s) {
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
