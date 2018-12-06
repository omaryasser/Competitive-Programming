import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.FileReader;
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
        TaskE solver = new TaskE();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskE {
        public void solve(int testNumber, Scanner sc, PrintWriter out) {
            int n = sc.nextInt();
            TaskE.Pair[] pairs = new TaskE.Pair[n];
            for (int i = 0; i < n; i++) {
                long a = sc.nextLong();
                pairs[i] = new TaskE.Pair(make(a), dont(a));
            }
            Arrays.sort(pairs);
            int make = 0, dont = 0;
            long res = 0;
            for (int i = 0; i < n; i++) {
                if (make == n / 2) {
                    res += pairs[i].d;
                    dont++;
                } else if (dont == n / 2) {
                    res += pairs[i].m;
                    make++;
                } else {
                    if (pairs[i].m <= pairs[i].d) {
                        make++;
                        res += pairs[i].m;
                    } else {
                        dont++;
                        res += pairs[i].d;
                    }
                }
            }
            out.println(res);
        }

        static long make(long num) {
            long sqrt = (long) Math.sqrt(num);
            if (sqrt == 0) return 0;
            return Math.min(Math.abs(num - sqrt * sqrt), Math.min(Math.abs(num - (sqrt + 1) * (sqrt + 1)), Math.abs(num - (sqrt - 1) * (sqrt - 1))));
        }

        static long dont(long num) {
            long min = Long.MAX_VALUE;
            for (long num2 = Math.max(0, num - 2); num2 <= num + 2; num2++) {
                long sqrt = (long) Math.sqrt(num2);
                if (sqrt * sqrt != num2)
                    min = Math.min(min, Math.abs(num - num2));
            }
            return min;
        }

        static class Pair implements Comparable<TaskE.Pair> {
            long m;
            long d;

            Pair(long mm, long dd) {
                m = mm;
                d = dd;
            }

            public int compareTo(TaskE.Pair pair) {
                return new Long(Math.abs(pair.m - pair.d)).compareTo(new Long(Math.abs(m - d)));
            }

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

        public long nextLong() {
            return Long.parseLong(next());
        }

    }
}

