import java.io.*;
import java.util.*;

    static int dx [] = {0, 0, 1, -1, 1, -1, 1, -1};
    static int dy [] = {1, -1, 0, 0, 1, 1, -1, -1};

    static char [][] arr;
    static int n, m;
    static boolean valid (int row, int col) {
        return row >= 0 && col >= 0 && row < n && col < m;
    }
    static boolean visited [][];
    static void flood_fill (int row, int col) {
        visited[row][col] = true;
        for (int k = 0; k < 8; k++) {
            int nxtRow = row + dx[k], nxtCol = col + dy[k];
            if (valid(nxtRow, nxtCol) && arr[nxtRow][nxtCol] != '*' && !visited[nxtRow][nxtCol]) {
                flood_fill(nxtRow, nxtCol);
            }
        }
    }
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        while (true) {
            n = sc.nextInt();
            if (n == 0) break;
            m = sc.nextInt();
            arr = new char[n][m];
            visited = new boolean[n][m];
            for (int i = 0; i < n; i++) arr[i] = sc.next().toCharArray();

            int res = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (arr[i][j] != '*' && !visited[i][j]) {
                        res++;
                        flood_fill(i, j);
                    }
                }
            }
            out.println(res);
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
