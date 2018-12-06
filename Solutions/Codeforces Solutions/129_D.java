import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.io.InputStreamReader;
import java.util.TreeMap;
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
        TaskD solver = new TaskD();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskD {
        public void solve(int testNumber, Scanner sc, PrintWriter out) {
            TaskD.SuffixAutomaton SA = new TaskD.SuffixAutomaton((sc.next() + "$").toCharArray());
            int k = sc.nextInt();
            SA.solve(0, k);
            if (SA.ways[0] - SA.occurence(0) < k)
                out.println("No such line.");
            else {
                out.println(SA.sb);
            }
        }

        static class SuffixAutomaton {
            int[] link;
            int[] len;
            TreeMap<Character, Integer>[] next;
            int lst;
            int idx;
            int[] ways;
            int[] reach;
            StringBuilder sb = new StringBuilder();

            SuffixAutomaton(char[] s) {
                int n = s.length;
                link = new int[n << 1];
                len = new int[n << 1];
                next = new TreeMap[n << 1];
                next[0] = new TreeMap<>();
                ways = new int[n << 1];
                reach = new int[n << 1];
                Arrays.fill(ways, -1);
                Arrays.fill(reach, -1);
                for (char c : s)
                    addLetter(c);
                for (int i = 0; i <= idx; i++)
                    dfs(i);
            }

            int occurence(int node) {
                if (node == idx)
                    return 1;
                if (reach[node] != -1)
                    return reach[node];
                int go = 0;
                for (int nxt : next[node].values())
                    go = Math.min((int) 1e5 + 10, go + occurence(nxt));
                return reach[node] = go;
            }

            int dfs(int node) {
                if (node == idx) return ways[node] = 0;
                if (ways[node] != -1)
                    return ways[node];
                int res = occurence(node);
                for (int nxt : next[node].values()) {
                    res = Math.min((int) 3e5 + 10, res + dfs(nxt));
                }
                return ways[node] = res;
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

            void solve(int node, int left) {
                if (node != 0) left -= occurence(node);
                if (left <= 0) return;
                int all = 0;
                for (Map.Entry<Character, Integer> mp : next[node].entrySet()) {
                    if (all + ways[mp.getValue()] < left) {
                        all += ways[mp.getValue()];
                        all = Math.min(all, (int) 1e5 + 10);
                    } else {
                        sb.append(mp.getKey());
                        solve(mp.getValue(), left - all);
                        return;
                    }
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

