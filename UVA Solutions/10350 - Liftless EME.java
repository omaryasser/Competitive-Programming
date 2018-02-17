import java.io.*;
import java.util.*;


public class D {



    static int V , E;
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();
        int t = 0;
        while (sc.ready())
        {
            String testCaseName = sc.next();
            V = sc.nextInt();
            E = sc.nextInt();
            int adjMat [][][] = new int[V - 1][E][E];
            for (int i = 0 ; i < V - 1 ; ++i)
                for (int j = 0 ; j < E ; ++j)
                    for (int k = 0 ; k < E ; ++k)
                        adjMat[i][j][k] = sc.nextInt();

            long dp [][] = new long[V][E];
            for (int i = 1 ; i < V ; ++i)
                Arrays.fill(dp[i] , (long)1e16);

            long res = (long)1e16;
            for (int k = 1 ; k < V ; ++k)
                for (int i = 0 ; i < E ; ++i)
                    for (int  j = 0 ; j < E ; ++j)
                    {
                        dp[k][i] = Math.min(dp[k][i] , dp[k - 1][j] + 2 + adjMat[k - 1][j][i]);
                        if (k == V - 1) res = Math.min(res , dp[k][i]);
                    }

            out.printf("%s\n%d\n" , testCaseName , res);

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
