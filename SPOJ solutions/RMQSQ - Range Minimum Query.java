import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        int N = sc.nextInt();
        int A [] = new int[N];
        for (int i = 0 ; i < N ; ++ i) A[i] = sc.nextInt();
        int M [][] = new int[N][(int)(Math.log(N) / Math.log(2)) + 1];
        for (int i = 0 ; i < N ; ++ i) M[i][0] = i;
        for (int j = 1 ; (1 << j) <= N ; ++ j) {
            for (int i = 0 ; i + (1 << j) - 1 < N ; ++ i) {
                if (A[M[i][j - 1]] <= A[M[i + (1 << (j - 1))][j - 1]])
                    M[i][j] = M[i][j - 1];
                else M[i][j] = M[i + (1 << (j - 1))][j - 1];
            }
        }
        int Q = sc.nextInt();
        while (Q -- > 0) {
            int i = sc.nextInt() , j = sc.nextInt();
            int len = j - i + 1;
            int log = (int)(Math.log(len) / Math.log(2));
            if (A[M[i][log]] <= A[M[j - (1 << log) + 1][log]])
                out.println(A[M[i][log]]);
            else out.println(A[M[j - (1 << log) + 1][log]]);
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
