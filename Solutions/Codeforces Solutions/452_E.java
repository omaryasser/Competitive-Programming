import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.io.InputStreamReader;
import java.util.TreeMap;
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
        int MOD = (int) 1e9 + 7;
        int[] res;

        public void solve(int testNumber, Scanner sc, PrintWriter out) {
            String s1 = sc.next(), s2 = sc.next(), s3 = sc.next();
            res = new int[s1.length() + s2.length() + s3.length() + 5];
            SuffixAutomaton SA = new SuffixAutomaton((s1 + "#" + s2 + "$" + s3 + "*").toCharArray());
            int min = Math.min(s1.length(), Math.min(s2.length(), s3.length()));
            int cur = 0;
            for (int i = 0; i <= min; i++) {
                cur += res[i];
                if (cur >= MOD)
                    cur -= MOD;
                if (cur < 0)
                    cur += MOD;
                if (i > 0) {
                    if (i != 1)
                        out.print(" ");
                    out.print(cur);
                }
            }
            out.println();
        }

        class SuffixAutomaton {
            int[] link;
            int[] len;
            int[] nodes;
            int[][] cnt;
            TreeMap<Character, Integer>[] next;
            int lst;
            int idx;

            SuffixAutomaton(char[] s) {
                int n = s.length;
                cnt = new int[3][n << 1];
                link = new int[n << 1];
                len = new int[n << 1];
                nodes = new int[3];
                next = new TreeMap[n << 1];
                next[0] = new TreeMap<>();
                for (char c : s)
                    addLetter(c);
                for (int[] c : cnt)
                    Arrays.fill(c, -1);
                for (int i = 0; i <= idx; i++) {
                    res[len[link[i]] + 1] += ((1l * dp(0, i) * dp(1, i) % MOD) * dp(2, i) % MOD);
                    res[len[i] + 1] -= ((1l * dp(0, i) * dp(1, i) % MOD) * dp(2, i) % MOD);
                    if (res[len[link[i]] + 1] >= MOD)
                        res[len[link[i]] + 1] -= MOD;
                    if (res[len[i] + 1] < 0)
                        res[len[i] + 1] += MOD;
                }
            }

            int dp(int wanted, int cur) {
                if (cnt[wanted][cur] != -1)
                    return cnt[wanted][cur];
                if (cur == nodes[wanted])
                    return 1;
                if (cur == nodes[0] || cur == nodes[1] || cur == nodes[2])
                    return 0;
                int res = 0;
                for (int nxt : next[cur].values()) {
                    res += dp(wanted, nxt);
                    if (res >= MOD) res -= MOD;
                }
                return cnt[wanted][cur] = res;
            }

            void addLetter(char c) {
                int cur = ++idx, p = lst;
                if (c == '#') nodes[0] = cur;
                else if (c == '$') nodes[1] = cur;
                else if (c == '*') nodes[2] = cur;
                while (!next[p].containsKey(c)) {
                    next[p].put(c, cur);
                    p = link[p];
                }

                int q = next[p].get(c);
                if (q != cur)
                    if (len[q] == len[p] + 1)
                        link[cur] = q;
                    else {
                        int clone = ++idx;
                        len[clone] = len[p] + 1;
                        link[clone] = link[q];
                        next[clone] = new TreeMap<>(next[q]);
                        link[cur] = link[q] = clone;
                        while (next[p].get(c) == q) {
                            next[p].put(c, clone);
                            p = link[p];
                        }
                    }
                len[cur] = len[lst] + 1;
                next[cur] = new TreeMap<>();
                lst = cur;
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

    }
}

