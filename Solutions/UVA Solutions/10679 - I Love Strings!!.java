import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {

    // O(n + m + z) n = |T| m = sum(|P|) z = # of occ.
    static class AhoCorasik {
        static final int ALPHABET_SIZE = 52, MAX_NODES = (int)1e6;
        Node[] nodes;
        int nodeCount;

        static class Node {
            int parent;
            char charFromParent;
            int suffLink = -1;
            int[] children = new int[ALPHABET_SIZE];
            int[] transitions = new int[ALPHABET_SIZE];
            boolean leaf, good;
            Node () {
                Arrays.fill(children, -1);
                Arrays.fill(transitions, -1);
            }
        }
        AhoCorasik() {
            nodes = new Node[MAX_NODES];
            nodes[0] = new Node();
            nodes[0].suffLink = 0;
            nodes[0].parent = -1;
            nodeCount = 1;
        }
        public void addString(char [] s) {
            int cur = 0;
            for (char ch : s) {
                int c = map(ch);
                if (nodes[cur].children[c] == -1) {
                    nodes[nodeCount] = new Node();
                    nodes[nodeCount].parent = cur;
                    nodes[nodeCount].charFromParent = ch;
                    nodes[cur].children[c] = nodeCount++;
                }
                cur = nodes[cur].children[c];
            }
            nodes[cur].leaf = true;
        }
        public int suffLink(int nodeIndex) {
            Node node = nodes[nodeIndex];
            if (node.suffLink == -1)
                node.suffLink = node.parent == 0 ? 0 : transition(suffLink(node.parent), node.charFromParent);
            return node.suffLink;
        }
        public int transition(int nodeIndex, char ch) {
            int c = map(ch);
            Node node = nodes[nodeIndex];
            if (node.transitions[c] == -1)
                node.transitions[c] = node.children[c] != -1 ? node.children[c] : (nodeIndex == 0 ? 0 : transition(suffLink(nodeIndex), ch));
            return node.transitions[c];
        }
        public void dfs (char [] s) {
            int curNode = 0;
            for (char c : s) {
                curNode = transition(curNode, c);
                nodes[curNode].good = true;
            }
            for (int i = 0; i < nodeCount; i++) {
                if (nodes[i].good) {
                    curNode = suffLink(i);
                    while (!nodes[curNode].good) {
                        nodes[curNode].good = true;
                        curNode = suffLink(curNode);
                    }
                }
            }
        }
        public boolean isGood (char [] p) {
            int cur = 0;
            for (char c : p) cur = transition(cur, c);
            return nodes[cur].good;
        }
        public int map (char c) {
            return c <= 'Z' ? c - 'A' : c - 'a' + 26;
        }
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        int tc = sc.nextInt();
        while (tc-- > 0) {
            AhoCorasik ahoCorasik = new AhoCorasik();
            char [] T = sc.next().toCharArray();
            int Q = sc.nextInt();
            char [][] arr = new char[Q][];
            for (int i = 0; i < Q; i++){
                ahoCorasik.addString(arr[i] = sc.next().toCharArray());
            }
            ahoCorasik.dfs(T);
            for (int i = 0; i < Q; i++)
                out.println(ahoCorasik.isGood(arr[i]) ? "y" : "n");
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
