import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
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
        TaskC solver = new TaskC();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskC {
        public void solve(int testNumber, Scanner sc, PrintWriter out) {
            int h = sc.nextInt();
            int[] a = new int[h + 1];
            int found = 0;
            boolean bad = false;
            for (int i = 0; i < h + 1; i++) {
                a[i] = sc.nextInt();
                if (a[i] != 1) found++;
                else found = 0;
                bad |= found >= 2;
            }

            if (!bad) out.println("perfect");
            else {
                out.println("ambiguous");
                int lvl = 0;
                for (int i = 0; i < h + 1; i++)
                    if (a[i] != 1 && a[i + 1] != 1) {
                        lvl = i;
                        break;
                    }
                ArrayList<Integer> res1 = new ArrayList<>();
                ArrayList<Integer> res2 = new ArrayList<>();
                int last = 1;
                res1.add(0);
                res2.add(0);
                for (int i = 1; i <= lvl; i++) {
                    for (int j = 0; j < a[i]; j++) {
                        res1.add(last);
                        res2.add(last);
                    }
                    last += a[i];
                }
                for (int k = 0; k < a[lvl + 1]; k++) {
                    res1.add(last);
                }
                for (int k = 0; k < a[lvl + 1] - 1; k++)
                    res2.add(last - 1);
                res2.add(last);
                last += a[lvl + 1];
                for (int i = lvl + 2; i < h + 1; i++) {
                    for (int j = 0; j < a[i]; j++) {
                        res1.add(last);
                        res2.add(last);
                    }
                    last += a[i];
                }

                for (int i = 0; i < res1.size(); i++) {
                    if (i > 0) out.print(" ");
                    out.print(res1.get(i));
                }
                out.println();
                for (int i = 0; i < res2.size(); i++) {
                    if (i > 0) out.print(" ");
                    out.print(res2.get(i));
                }
                out.println();
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