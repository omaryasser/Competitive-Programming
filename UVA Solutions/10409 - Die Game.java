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
        while (true){
            int n = sc.nextInt();
            if (n == 0) break;

            int top = 1;
            int bottom = 6;
            int north = 2;
            int south = 5;
            int west = 3;
            int east = 4;

            for (int i = 0 ; i <n ; ++i){
                String nxt = sc.next();
                char c = nxt.charAt(0);
                if (c == 'n'){
                    int top2 = south;
                    int north2 = top;
                    int bottom2 = north;
                    int south2 = bottom;
                    top = top2;
                    bottom = bottom2;
                    north = north2;
                    south = south2;
                }
                else if (c == 's'){
                    int top2 = north;
                    int north2 = bottom;
                    int bottom2 = south;
                    int south2 = top;
                    top = top2;
                    bottom = bottom2;
                    north = north2;
                    south = south2;
                }
                else if (c == 'e'){
                    int top2 = west;
                    int west2 = bottom;
                    int bottom2 = east;
                    int east2 = top;
                    top = top2;
                    west = west2;
                    bottom = bottom2;
                    east = east2;
                }
                else if (c == 'w'){
                    int top2 = east;
                    int west2 = top;
                    int bottom2 = west;
                    int east2 = bottom;
                    top = top2;
                    west = west2;
                    bottom = bottom2;
                    east = east2;
                }
            }
            out.print(top + "\n");
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
