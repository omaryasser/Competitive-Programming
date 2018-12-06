import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.util.Collections;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.io.InputStream;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 */
public class Main {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        Scanner in = new Scanner(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        TaskC solver = new TaskC();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskC {
        static int binarySearch(ArrayList<Integer> arr, int val) {
            if (arr.size() == 0) return -1;
            if (val >= arr.get(arr.size() - 1)) return -(arr.size() + 1);

            int lo = 0, hi = arr.size() - 1, best = 0;
            for (int cnt = 0; cnt <= 21; cnt++) {
                int mid = lo + ((hi - lo) >> 1);
                if (arr.get(mid) > val) {
                    best = mid;
                    hi = Math.max(lo, mid - 1);
                } else {
                    lo = Math.min(hi, mid + 1);
                }
            }
            return best;
        }

        static int[] Prefix_LNS(ArrayList<Integer> arr) {
            int n = arr.size();
            ArrayList<Integer> L = new ArrayList<>();
            int lis = 0;
            int[] res = new int[n];
            for (int i = 0; i < n; i++) {
                int num = arr.get(i);
                int pos = binarySearch(L, num);
                if (pos < 0) pos = -pos - 1;
                res[i] = pos + 1;
                if (pos + 1 > lis) {
                    lis++;
                    L.add(num);
                } else {
                    L.set(pos, num);
                }
            }

            return res;
        }

        public void solve(int testNumber, Scanner sc, PrintWriter out) {
            int n = sc.nextInt(), m = sc.nextInt();
            int[] cnt = new int[m + 2];
            for (int i = 0; i < n; i++) {
                cnt[sc.nextInt()]++;
                cnt[sc.nextInt() + 1]--;
            }

            ArrayList<Integer> a = new ArrayList<>();
            int cum = 0;
            for (int i = 1; i <= m; i++) {
                a.add(cum += cnt[i]);
            }
            int[] first = Prefix_LNS(a);
            Collections.reverse(a);
            int[] second = Prefix_LNS(a);
            int max = 0;
            for (int i = 0; i < m; i++) {
                max = Math.max(max, first[i] + second[m - i - 1] - 1);
            }
            out.println(max);
        }

    }

    static class Scanner {
        StringTokenizer st;
        BufferedReader br;

        public Scanner(InputStream s) {
            br = new BufferedReader(new InputStreamReader(s));
        }

        public String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (Exception e) {
                }
                ;
            }
            return st.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

    }
}

