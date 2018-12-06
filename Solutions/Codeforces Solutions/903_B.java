import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;
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
        TaskB solver = new TaskB();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskB {
        public void solve(int testNumber, Scanner sc, PrintWriter out) {
            int health1 = sc.nextInt(), attack1 = sc.nextInt(), increaseHealth = sc.nextInt();
            int health2 = sc.nextInt(), attack2 = sc.nextInt();

            int neededToKill = 0;
            int tmpH = health2;
            while (tmpH > 0) {
                neededToKill++;
                tmpH -= attack1;
            }
            for (int phases = 1; phases <= 1000000; phases++) {
                if (neededToKill <= phases && (phases - neededToKill) * increaseHealth + health1 > attack2 * (phases - 1)) {
                    out.println(phases);
                    for (int i = 0; i < phases - neededToKill; i++)
                        out.println("HEAL");
                    for (int i = 0; i < neededToKill; i++)
                        out.println("STRIKE");
                    return;
                }
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

