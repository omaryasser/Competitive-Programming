import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

import java.util.Random;
public class Main {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        for (int tc = 0; sc.ready(); tc++){
            int E = sc.nextInt();
            int adjMat [][] = new int[30][30];
            int max = 0;
            while (E-- > 0) {
                int u = sc.nextInt(), v = sc.nextInt();
                max = Math.max(max, Math.max(u, v));
                adjMat[u][v] = 1;
            }
            for (int k = 0; k < 30; k++) for (int i = 0; i < 30; i++) for (int j = 0; j < 30; j++) {
                adjMat[i][j] += adjMat[i][k] * adjMat[k][j];
            }
            out.printf("matrix for city %d\n", tc);
            for (int i = 0; i <= max; i++) {
                for (int j = 0; j <= max; j++) {
                    if (j > 0) out.print(" ");
                    boolean cycled = false;
                    for (int k = 0; k <= max; k++) {
                        if (adjMat[k][k] != 0 && adjMat[i][k] != 0 && adjMat[k][j] != 0) {
                            cycled = true;
                        }
                    }
                    if (cycled) out.print(-1);
                    else out.print(adjMat[i][j]);
                }
                out.println();
            }
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
