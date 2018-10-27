import java.io.*;
import java.util.*;

/**
 * Created by omar on 22/06/17.
 */
public class Main {

    static class SuffixArray {

        int[] SA;           // SA[i] = index of the ith smallest suffix
        char [] s;
        int n;

        SuffixArray(char[] arr)                   //has a terminating character (e.g. '$')
        {
            s = arr;
            n = s.length;
            buildSA();
            buildLCP();
        }

        void buildSA () {
            int RA[] = new int[n];
            SA = new int[n];

            for(int i = 0; i < n; ++i) { RA[i] = s[i]; SA[i] = i; }

            for(int k = 1; k < n; k <<= 1)
            {
                sort(SA, RA, n, k);
                sort(SA, RA, n, 0);
                int[] tmp = new int[n];

                for(int i = 1, r = 0, s1 = SA[0], s2; i < n; ++i)
                {
                    s2 = SA[i];
                    tmp[s2] = RA[s1] == RA[s2] && RA[s1 + k] == RA[s2 + k] ? r : ++r;
                    s1 = s2;
                }
                for(int i = 0; i < n; ++i)
                    RA[i] = tmp[i];

                if(RA[SA[n-1]] == n - 1)
                    break;
            }
        }

        void sort(int[] SA, int[] RA, int n, int k)
        {
            int maxi = Math.max(256, n), c[] = new int[maxi];
            for(int i = 0; i < n; ++i)
                c[i + k < n ? RA[i + k] : 0]++;
            for(int i = 0, sum = 0; i < maxi; ++i)
            {
                int t = c[i];
                c[i] = sum;
                sum += t;
            }
            int[] tmp = new int[n];
            for(int i = 0; i < n; ++i)
            {
                int j = SA[i] + k;
                tmp[c[j < n ? RA[j] : 0]++] = SA[i];
            }

            for(int i = 0; i < n; ++i)
                SA[i] = tmp[i];
        }

        int [] LCP;
        void buildLCP () {
            LCP = new int[n];
            int rank [] = new int[n];
            for (int i = 0; i < n; i++)
                rank[SA[i]] = i;

            int commonLength = 0;
            for (int i = 0; i < n; i++) {
                if (rank[i] != 0) {
                    int j = SA[rank[i] - 1];
                    while (i + commonLength < n && s[i + commonLength] == s[j + commonLength])
                        commonLength++;
                }
                LCP[rank[i]] = commonLength;
                if (commonLength > 0) commonLength--;
            }
        }
    }


    public static void main (String [] args) {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        int tc = sc.nextInt();
        while (tc-->0) {
            char[] a = (sc.next() + (char)(0)).toCharArray();
            SuffixArray suffixArray = new SuffixArray(a);
            long res = 0;
            for (int i = 1; i < a.length; i++) {
                res += (a.length - 1 - suffixArray.SA[i] - suffixArray.LCP[i]);
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

        public Scanner(FileReader f) {
            br = new BufferedReader(f);
        }

        public String next() {
            while (st == null || !st.hasMoreTokens())
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (Exception e) {
                }
            return st.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }

        public String nextLine() {
            try {
                return br.readLine();
            } catch (Exception e) {
                return null;
            }
        }

        public double nextDouble() {
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

        public boolean ready() {
            try {
                return br.ready();
            } catch (Exception e) {
                return false;
            }
        }

    }
}
