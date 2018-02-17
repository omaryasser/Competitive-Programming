import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();

        boolean isPrime [] = new boolean[2002];
        Arrays.fill(isPrime , true);
        isPrime[0] = isPrime[1] = false;
        for (int i = 2 ; i * i <= 2001 ; ++ i)
            if (isPrime[i])
                for (int j = i * i ; j <= 2001 ; j += i)
                    isPrime[j] = false;

        int T = sc.nextInt();
        for (int t = 1 ; t <= T ; ++ t) {
            char [] arr = sc.next().toCharArray();
            int cnt [] = new int[200];
            for (int i = 0 ; i < arr.length ; ++ i)
                cnt[(int)arr[i]] ++ ;

            TreeSet<Character> treeSet = new TreeSet<>();
            for (int i = 0 ; i < 200 ; ++ i)
                if (isPrime[cnt[i]])
                    treeSet.add((char)i);
            out.printf("Case %d: " , t);
            if (treeSet.size() == 0) out.printf("empty");
            else {
                sb = new StringBuilder();
                for (Character c : treeSet)
                    sb.append(c);
                out.print(sb);
            }
            out.println();

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
