import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.FileReader;
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
        static HashMap<Integer, TaskD.Result> map = new HashMap<Integer, TaskD.Result>();

        static void solve(int[] A, int[] B, int steps, boolean add) {
            if (steps == 151) return;
            int[] nxtA1, nxtA2, nxtB;
            nxtB = A;
            nxtA1 = mult(A);
            nxtA2 = mult(A);
            nxtA1 = add(nxtA1, B, 1);
            nxtA2 = add(nxtA2, B, -1);
            for (int i = 0; i < 151; i++)
                if (Math.abs(nxtA1[i]) >= 2) {
                    solve(nxtA2, nxtB, steps + 1, !add);
                    map.put(steps, new TaskD.Result(nxtA2, nxtB));
                    return;
                }
            solve(nxtA1, nxtB, steps + 1, !add);
            map.put(steps, new TaskD.Result(nxtA1, nxtB));
        }

        static int[] add(int[] A, int[] B, int sign) {
            int[] res = new int[151];
            for (int i = 0; i < 151; i++)
                res[i] = A[i] + sign * B[i];
            return res;
        }

        static int[] mult(int[] A) {
            int[] res = new int[151];
            for (int i = 0; i < 150; i++)
                res[i + 1] = A[i];
            return res;
        }

        public void solve(int testNumber, Scanner sc, PrintWriter out) {
            int n = sc.nextInt();
            if (n == 1) out.println("1\n" +
                    "0 1\n" +
                    "0\n" +
                    "1");
            else {
                int[] f = new int[151];
                int[] s = new int[151];
                f[0] = 0;
                f[1] = 1;
                s[0] = 1;
                solve(f, s, 2, false);
                int[] A = map.get(n).A, B = map.get(n).B;
                int lastA = 0, lastB = 0;
                for (int i = 0; i < 151; i++) {
                    if (A[i] == 1) lastA = i;
                    if (B[i] == 1) lastB = i;
                }
                out.println(lastA);
                for (int i = 0; i <= lastA; i++)
                    out.print(A[i] + " ");
                out.println();
                out.println(lastB);
                for (int i = 0; i <= lastB; i++)
                    out.print(B[i] + " ");
                out.println();
            }
        }

        static class Result {
            int[] A;
            int[] B;

            Result(int[] AA, int[] BB) {
                A = AA;
                B = BB;
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

    }
}

