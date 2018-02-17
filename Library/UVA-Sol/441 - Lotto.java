import java.io.*;
import java.lang.reflect.Array;
import java.util.*;


public class Main {



    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();

        boolean first = true;
        while (true){

            int k = sc.nextInt();
            if (k == 0) break;
            int arr [] = new int[k];
            for (int i = 0 ; i < k ; ++i) arr[i] = sc.nextInt();

            if (first) first = false;
            else out.print("\n");
            
            for (int a = 0 ; a < k - 5 ; ++a)
                for (int b = a + 1 ; b < k - 4 ; ++b)
                    for (int c = b + 1 ; c < k - 3 ; ++c)
                        for (int d = c + 1 ; d < k - 2 ; ++d)
                            for (int e = d + 1 ; e < k - 1 ; ++e)
                                for (int f = e + 1 ; f < k ; ++ f)
                                    out.printf("%d %d %d %d %d %d\n" , arr[a] , arr[b] , arr[c] , arr[d] , arr[e] , arr[f]);



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
