import java.io.*;
import java.util.*;

public class A {


    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        for (int tc = 1;; tc++){
            int V = sc.nextInt(), E = sc.nextInt();
            if (V == 0 && E == 0) break;
            int adjMat [][] = new int[V][V];
            while (E-- > 0) {
                int u = sc.nextInt() - 1, v = sc.nextInt() - 1, w = sc.nextInt() - 1;
                adjMat[u][v] = w;
                adjMat[v][u] = w;
            }

            for (int k = 0; k < V; k++) {
                for (int i = 0; i < V; i++) {
                    for (int j = 0; j < V; j++) {
                        adjMat[i][j] = Math.max(adjMat[i][j], Math.min(adjMat[i][k], adjMat[k][j]));
                    }
                }
            }

            int s = sc.nextInt() - 1, d = sc.nextInt() - 1, t = sc.nextInt();
            out.printf("Scenario #%d\n", tc);
            out.printf("Minimum Number of Trips = %d\n\n", (t + adjMat[s][d] - 1) / adjMat[s][d]);
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

        public Scanner (FileReader f) {br = new BufferedReader(f);}

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
