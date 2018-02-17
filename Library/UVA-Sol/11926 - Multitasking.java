import java.io.*;
import java.lang.reflect.Array;
import java.util.*;


public class Main {



    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();

        while (true){
            int n = sc.nextInt();
            int m = sc.nextInt();
            if (n == 0 && m == 0) break;

            int MAX = 1000000;
            boolean time [] = new boolean[MAX + 1];
            boolean conflict = false;
            for (int i = 0 ; i < n ; ++i){
                int start = sc.nextInt();
                int end =   sc.nextInt();
                for (int j = start ; j < end ; ++j)
                    if (time[j]) conflict = true;
                    else time[j] = true;
            }

            for (int i = 0 ; i < m ; ++i){
                int start = sc.nextInt();
                int end   = sc.nextInt();
                int p     = sc.nextInt();
                for (int s = start , e = end ; s <= MAX ; s += p , e += p){
                    for (int j = s ; j < e && j <= MAX; ++j)
                        if (time[j]) conflict = true;
                        else time[j] = true;
                }
            }

            out.print(conflict ? "CONFLICT\n" : "NO CONFLICT\n");
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
