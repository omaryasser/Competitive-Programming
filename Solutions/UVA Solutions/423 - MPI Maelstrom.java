import java.io.*;
import java.util.*;

public class D {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        int n = sc.nextInt();
        long[][] adjMat = new long[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                String nxt = sc.next();
                if (nxt.charAt(0) != 'x') {
                    adjMat[i][j] = adjMat[j][i] = Integer.parseInt(nxt);
                }
                else adjMat[i][j] = adjMat[j][i] = (long)1e17;
            }
        }

        long[] dist = new long[n];
        Arrays.fill(dist, (long)1e17);
        dist[0] = 0;
        for (int k = 0; k < n - 1; k++)
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++)
                    dist[j] = Math.min(dist[j], dist[i] + adjMat[i][j]);
        long min = -1;
        for (int i = 0; i < n; i++)
            min = Math.max(min, dist[i]);
        out.println(min);
        out.close();
    }






































    static class Scanner {


        StringTokenizer st;
        BufferedReader br;

        public Scanner (InputStream s) {br = new BufferedReader(new InputStreamReader(s));}

        public Scanner(FileReader f) {
            br = new BufferedReader(f);
        }

        public String next() {
            while (st == null || !st.hasMoreTokens())
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (Exception e) {
                }
            return st.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }

        public String nextLine() {
            try {
                return br.readLine();
            } catch (Exception e) {
                return null;
            }
        }

        public double nextDouble() {
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

        public boolean ready() {
            try {
                return br.ready();
            } catch (Exception e) {
                return false;
            }
        }
    }

}
