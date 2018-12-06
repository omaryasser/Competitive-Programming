import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.HashMap;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;
import java.util.Map;
import java.util.Map.Entry;
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
        TaskC solver = new TaskC();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskC {
        public void solve(int testNumber, Scanner sc, PrintWriter out) {
            int n = sc.nextInt();
            HashMap<String, HashSet<String>> map = new HashMap<>();
            while (n-- > 0) {
                String name = sc.next();
                HashSet<String> cur = map.getOrDefault(name, new HashSet<>());
                int x = sc.nextInt();
                while (x-- > 0) {
                    cur.add(sc.next());
                }
                map.put(name, cur);
            }

            out.println(map.size());
            for (Map.Entry<String, HashSet<String>> mp : map.entrySet()) {
                ArrayList<String> good = solve(mp.getValue());
                out.print(mp.getKey() + " " + good.size());
                for (String s : good) out.print(" " + s);
                out.println();
            }
        }

        static ArrayList<String> solve(HashSet<String> aa) {
            ArrayList<String> a = new ArrayList<>();
            for (String s : aa) a.add(s);
            boolean good[] = new boolean[a.size()];
            Arrays.fill(good, true);
            System.err.println(a);
            for (int i = 0; i < a.size(); i++) {
                for (int j = 0; j < a.size(); j++) {
                    if (i != j) {
                        if (isSuffix(a.get(i), a.get(j))) good[i] = false;
                    }
                }
            }
            ArrayList<String> res = new ArrayList<>();
            for (int i = 0; i < a.size(); i++)
                if (good[i])
                    res.add(a.get(i));
            return res;
        }

        static boolean isSuffix(String a, String b) {
            if (a.length() > b.length()) return false;
            for (int i = a.length() - 1, j = b.length() - 1; i >= 0; i--, j--)
                if (a.charAt(i) != b.charAt(j)) return false;
            return true;
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

