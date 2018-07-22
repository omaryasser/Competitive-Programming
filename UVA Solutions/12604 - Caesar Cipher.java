import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class Main {
    static char T[], P[];
    static int b[], n, m;

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

    public static int kmpSearch()
    {
        int cnt = 0;
        int i =0, j =0;
        n = T.length;
        while(i < n)
        {

            while(j >= 0 && T[i] != P[j]) j = b[j];
            i++; j++;
            if(j == m) {
                cnt++;
                j = b[j];
            }

        }
        return cnt;
    }


    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        int tc = sc.nextInt();
        while (tc-- > 0) {
            ArrayList<Integer> res = new ArrayList<>();
            char [] A = sc.next().toCharArray();
            HashMap<Character,Integer> pos = new HashMap<>();
            for (int i = 0; i < A.length; i++) pos.put(A[i], i);
            P = sc.next().toCharArray();
            char [] S = sc.next().toCharArray();
            kmpPreProcess();
            for (int i = 0; i < A.length; i++) {
                T = new char[S.length];
                for (int j = 0; j < S.length; j++) {
                    T[j] = A[(pos.get(S[j]) + i) % A.length];
                }
                if (kmpSearch() == 1) res.add((A.length - i) % A.length);
            }
            if (res.size() == 0) out.println("no solution");
            else if (res.size() == 1) out.println("unique: " + res.get(0));
            else {
                Collections.sort(res);
                out.print("ambiguous:");
                for (int num : res) out.print(" " + num);
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
