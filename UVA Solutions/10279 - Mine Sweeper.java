import java.io.*;
import java.lang.reflect.Array;
import java.util.*;


public class Main {
    static int dx[] = {1, -1, 0, 0, 1, -1, 1, -1};
    static int dy[] = {0, 0, 1, -1, 1, -1, -1, 1};

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();
        int T = sc.nextInt();
        while (T-- > 0) {
            int n = sc.nextInt();
            char mines[][] = new char[n][n];
            char touched[][] = new char[n][n];
            for (int i = 0; i < n; ++i) mines[i] = sc.next().toCharArray();
            for (int i = 0; i < n; ++i) touched[i] = sc.next().toCharArray();


            boolean lost = lost(mines, touched);

            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < n; ++j) {
                    if (mines[i][j] == '*')
                        if (lost) sb.append("*");
                        else sb.append(".");
                    else if (touched[i][j] == 'x') {
                        int cnt = 0;
                        for (int k = 0; k < 8; ++k) {
                            if (valid(i + dx[k], j + dy[k], n) && mines[i + dx[k]][j + dy[k]] == '*') {
                                ++cnt;
                            }
                        }
                        sb.append(cnt);
                    }
                    else sb.append(".");
                }
                sb.append("\n");
            }


            if (T != 0) sb.append("\n");
        }

        out.print(sb.toString());
        out.flush();
        out.close();
    }

    static boolean lost(char mines[][], char touched[][]) {
        for (int i = 0; i < mines.length; ++i) {
            for (int j = 0; j < mines[i].length; ++j) {
                if (mines[i][j] == '*' && touched[i][j] == 'x') return true;
            }
        }
        return false;
    }

    static boolean valid(int x, int y, int n) {
        return x >= 0 && y >= 0 && x < n && y < n;
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
