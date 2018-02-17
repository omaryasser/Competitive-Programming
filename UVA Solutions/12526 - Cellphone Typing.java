import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class Main {
    static class Trie {
        Node root = new Node();
        static class Node {
            int c;
            boolean end;
            int cnt;
            Node [] nxt = new Node[26];
        }
        void insert (String s) {insert(root, s, 0);}
        void insert (Node root, String s, int idx) {
            int num = s.charAt(idx) - 'a';
            Node nxt = root.nxt[num];
            if (nxt == null) {
                root.nxt[num] = nxt = new Node();
                nxt.c = num;
            }
            nxt.cnt++;
            if (idx != s.length() - 1) insert(nxt, s, idx + 1);
            else nxt.end = true;
        }
        int solve () {
            int res = 0;
            for (int i = 0; i < 26; i++)
                if (root.nxt[i] != null) {
                    res += root.nxt[i].cnt + dfs(root.nxt[i]);
                }
            return res;
        }
        int dfs (Node n) {
            int res = 0;
            int cnt = n.end ? 1 : 0;
            for (int i = 0; i < 26; i++)
                if (n.nxt[i] != null)
                    cnt++;
            for (int i = 0; i < 26; i++)
                if (n.nxt[i] != null)
                    if (cnt == 1) res += dfs(n.nxt[i]);
                    else res += n.nxt[i].cnt + dfs(n.nxt[i]);
            return res;
        }
    }
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

//        int tc = 3;
        while (sc.ready()) {
            Trie t = new Trie();
            int n = sc.nextInt();
            for (int i = 0; i < n; i++)
                t.insert(sc.next());
//            System.err.println(t.solve());
            out.printf("%.2f\n", 1.0 * t.solve() / n);
        }

        out.flush();
        out.close();
    }

























    static class Scanner {
        StringTokenizer st;
        BufferedReader br;

        public Scanner(InputStream s) {
            br = new BufferedReader(new InputStreamReader(s));
        }

        public String next() throws IOException {
            while (st == null || !st.hasMoreTokens())
                st = new StringTokenizer(br.readLine());
            return st.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }

        public long nextLong() throws IOException {
            return Long.parseLong(next());
        }

        public String nextLine() throws IOException {
            return br.readLine();
        }

        public double nextDouble() throws IOException {
            String x = next();
            StringBuilder sb = new StringBuilder("0");
            double res = 0, f = 1;
            boolean dec = false, neg = false;
            int start = 0;
            if (x.charAt(0) == '-') {
                neg = true;
                start++;
            }
            for (int i = start; i < x.length(); i++)
                if (x.charAt(i) == '.') {
                    res = Long.parseLong(sb.toString());
                    sb = new StringBuilder("0");
                    dec = true;
                } else {
                    sb.append(x.charAt(i));
                    if (dec)
                        f *= 10;
                }
            res += Long.parseLong(sb.toString()) / f;
            return res * (neg ? -1 : 1);
        }

        public boolean ready() throws IOException {
            return br.ready();
        }

    }
}
