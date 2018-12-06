import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collection;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Queue;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedList;
import java.io.InputStream;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 *
 * @author OmarYasser
 */
public class Main {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        Scanner in = new Scanner(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        TaskB solver = new TaskB();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskB {
        public void solve(int testNumber, Scanner sc, PrintWriter out) {
            int n = sc.nextInt(), x = sc.nextInt(), k = sc.nextInt();
            Integer[] tmp = new Integer[n];
            for (int i = 0; i < n; i++) tmp[i] = sc.nextInt();
            Arrays.sort(tmp);
            int[] a = new int[n];
            for (int i = 0; i < n; i++) a[i] = tmp[i];

            long res = 0;
            int e = 0;
            Queue<Integer> q = new LinkedList<>();
            for (int i = 0; i < n; i++) {
                while (!q.isEmpty() && ((q.peek() < i && a[q.peek()] != a[i]) || a[q.peek()] / x - (a[i] - 1) / x != k))
                    q.poll();
                while (e < n) {
                    if (e < i || a[e] / x - ((a[i] - 1) / x) < k) e++;
                    else if (a[e] / x - ((a[i] - 1) / x) == k) q.add(e++);
                    else break;
                }

                res += q.size();
            }
            out.println(res);
        }

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

    }
}

