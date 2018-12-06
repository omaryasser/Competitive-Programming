import java.io.*;
import java.util.StringTokenizer;

/**
 * Created by omar on 15/03/18.
 */
public class B {

    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        int n = sc.nextInt();
        int u = sc.nextInt();
        long[] a = new long[n];
        for (int i = 0; i < n; i++)
            a[i] = sc.nextLong();

        double best = -2;
        for (int i = 0; i < n; i++) {
            if (i + 2 >= n) continue;
            int lo = i + 2, hi = n - 1, res = lo;
            if (a[lo] - a[i] > u) continue;
            while (lo <= hi) {
                int mid = lo + hi >> 1;
                if (a[mid] - a[i] <= u) {
                    res = mid;
                    lo = mid + 1;
                }
                else
                    hi = mid - 1;
            }
            best = Math.max(best, (1.0 * a[res] - a[i + 1]) / (a[res] - a[i]));
        }
        if (best < -1) out.println(-1);
        else out.printf("%.11f\n", best);


        out.flush();
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
    }
}
