import java.io.*;
import java.util.*;

public class Main {
    static int manacher(char[] ss)
    {
        char [] s = new char[2 * ss.length + 1];
        for (int i = 0; i < 2 * ss.length + 1; i++) {
            if ((i & 1) != 0) s[i] = ss[i / 2];
            else s[i] = '#';
        }
        int n = s.length, d[] = new int[n];
        for(int i = 0, l = 0, r = -1; i < n; ++i)
        {
            int k = i > r ? 1 : Math.min(r - i + 1, d[l + r - i]);
            while(i + k < n && i - k >= 0 && s[i + k] == s[i - k])
                ++k;
            d[i] = k--;
            if(i + k > r) { l = i - k; r = i + k; }
        }

        int max = d[0];
        for (int i = 1; i < n; i++)
            max = Math.max(max, d[i]);
        return max - 1;
    }
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        sc.nextInt();
        out.println(manacher(sc.next().toCharArray()));

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
