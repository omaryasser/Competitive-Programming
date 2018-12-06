import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Deque;
import java.util.LinkedList;
import java.io.InputStreamReader;
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
        TaskD solver = new TaskD();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskD {
        public void solve(int testNumber, Scanner sc, PrintWriter out) {
            int n = sc.nextInt(), m = sc.nextInt(), k = sc.nextInt();
            Integer[] tmp = new Integer[n];
            for (int i = 0; i < n; i++)
                tmp[i] = sc.nextInt();
            Arrays.sort(tmp);
            int[] a = new int[n];
            for (int i = 0; i < n; i++)
                a[i] = tmp[i];

            Deque<Integer> q = new LinkedList<>();
            int cnt = 0;
            int idx = 0;
            while (idx < n) {
                if (q.size() < k) {
                    q.addLast(a[idx]);
                }
                while (q.getLast() - q.getFirst() + 1 > m) q.removeFirst();
                if (q.size() == k) {
                    int last = q.getLast(), first = q.getFirst();
                    if (last - first + 1 <= m) {
                        cnt++;
                        q.removeLast();
                    } else q.removeFirst();
                }
                idx++;
            }
            out.println(cnt);
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

