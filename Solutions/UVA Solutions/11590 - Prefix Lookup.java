import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.*;

public class Main {

    static class Trie {
        static class Node {
            int numFromParent;
            boolean leaf;
            BigInteger res;
            Node l, r;
            Node(int num) {
                numFromParent = num;
            }
        }

        Node root = new Node(-1);
        {
            root.leaf = true;
        }
        void addString(char[] s) {
            Node cur = root;
            for (char c : s) {
                int num = c - '0';
                Node nxt = num == 0 ? cur.l : cur.r;
                if (nxt == null) {
                    nxt = new Node(num);
                }
                if (num == 0) {
                    cur.l = nxt;
                }
                else {
                    cur.r = nxt;
                }
                cur = nxt;
            }
            cur.leaf = true;
        }

        BigInteger dfs(Node node, int bitsLeft) {
            if (bitsLeft == 0) {
                node.res = BigInteger.ONE; return BigInteger.ZERO;
            }
            BigInteger res = BigInteger.ZERO;
            if (node.l == null) {
                res = res.add(BigInteger.ONE.shiftLeft(bitsLeft - 1));
            } else {
                res = res.add(dfs(node.l, bitsLeft - 1));
            }
            if (node.r == null) {
                res = res.add(BigInteger.ONE.shiftLeft(bitsLeft - 1));
            } else {
                res = res.add(dfs(node.r, bitsLeft - 1));
            }
            if (node.leaf) {node.res = res; return BigInteger.ZERO;}
            return res;
        }

        BigInteger getRes(char[] s) {
            Node cur = root;
            for (char c : s) {
                cur = c == '0' ? cur.l : cur.r;
            }
            return cur.res;
        }
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        while (true) {
            int n = sc.nextInt(), k = sc.nextInt();
            if (n == 0) break;
            Trie trie = new Trie();
            while (n-- > 0) {
                String pattern = sc.next();
                pattern = pattern.substring(0, pattern.length() - 1);
                trie.addString(pattern.substring(0, Math.min(pattern.length(), k)).toCharArray());
            }
            trie.dfs(trie.root, k);
            int q = sc.nextInt();
            while (q-- > 0) {
                String pattern = sc.next();
                pattern = pattern.substring(0, pattern.length() - 1);
                out.println(trie.getRes(pattern.substring(0, Math.min(pattern.length(), k)).toCharArray()));
            }
            out.println();
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
