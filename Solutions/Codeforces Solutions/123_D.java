import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.TreeMap;
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
        TaskD solver = new TaskD();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskD {
        public void solve(int testNumber, Scanner sc, PrintWriter out) {
            out.println(new SuffixAutomaton((sc.next() + "#").toCharArray()).solve());
        }

        class SuffixAutomaton {
            int[] link;
            int[] len;
            TreeMap<Character, Integer>[] next;
            int lst;
            int idx;
            long[] cnt;

            SuffixAutomaton(char[] s) {
                int n = s.length;
                link = new int[n << 1];
                len = new int[n << 1];
                next = new TreeMap[n << 1];
                cnt = new long[n << 1 << 1];
                Arrays.fill(cnt, -1);
                next[0] = new TreeMap<>();
                for (char c : s)
                    addLetter(c);
            }

            long solve() {
                long res = 0;
                for (int i = 1; i < idx; i++) {
                    res += 1l * (len[i] - len[link[i]]) * dp(i) * (dp(i) + 1) / 2;
                }
                return res;
            }

            long dp(int i) {
                if (i == idx)
                    return 1;
                if (cnt[i] != -1)
                    return cnt[i];
                long res = 0;
                for (int nxt : next[i].values())
                    res += dp(nxt);
                return cnt[i] = res;
            }

            void addLetter(char c) {
                int cur = ++idx, p = lst;
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

