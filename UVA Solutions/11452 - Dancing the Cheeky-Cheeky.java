import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class Main {

    static char P[];
    static int b[], m;

    public static void kmpPreProcess()
    {
        m = P.length;
        b = new int[m + 1];
        int i = 0, j = -1; b[0] = -1;
        while(i < m)
        {
            while(j >= 0 && P[i] != P[j]) j = b[j];
            i++; j++;
            b[i] = j;
        }
    }


    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        int tc = sc.nextInt();
        while (tc-- > 0) {
            String s = sc.next();
            int L = s.length();
            int len, start;
            for (int i = L - 1;; i--) {
                P = s.substring(0, i + 1).toCharArray();
                kmpPreProcess();
                int LL = i + 1;
                if (LL % (LL - b[i + 1]) == 0 && LL / (LL - b[i + 1]) == 2) {
                    start = L - (i + 1);
                    len = LL - b[i + 1];
                    break;
                }
            }
            for (int i = 0; i < 8; i++) out.print(s.charAt((start + i) % len));
            out.println("...");
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
