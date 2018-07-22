import java.io.*;
import java.util.*;


public class C {

    static Scanner sc;
    static TreeMap<Integer , Long> tm;
    static boolean last;
    static class Node
    {
        Node left;
        Node right;
        int idx ; long val;
        Node (int i , long v)
        {
            idx = i;
            val = v;
        }
    }

    static class Tree
    {
        Node root;
        Tree () throws  Exception
        {
            long x = sc.nextLong();
            if (x == -1) {
                last = true;
                return;
            }
            root = new Node(0 , x);

            go (root);
        }
        void go (Node parent) throws Exception
        {
            long x = sc.nextLong();
            if (x != -1) {
                parent.left = new Node(parent.idx - 1, x);
                go(parent.left);
            }
            x = sc.nextLong();
            if (x != -1) {
                parent.right = new Node(parent.idx + 1, x);
                go(parent.right);
            }
        }
        void fillMap (Node node)
        {
            if (node == null) return;
            int idx = node.idx ; long  val = node.val;
            if (!tm.containsKey(idx)) tm.put(idx , val);
            else tm.put(idx , tm.get(idx) + val);
            fillMap(node.left);
            fillMap(node.right);
        }
    }
    public static void main(String[] args) throws Exception {
        sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();
        last = false;
        for (int T = 1 ;;T ++)
        {

            tm = new TreeMap<>();

            Tree tree = new Tree();
            if (last) break;
            tree.fillMap(tree.root);
            out.printf("Case %d:\n" , T);
            boolean first = true;
            for (Map.Entry<Integer,Long> map : tm.entrySet())
            {
                if (first) {
                    out.print(map.getValue());
                    first = false;
                }
                else out.printf(" %d" , map.getValue());
            }
            out.printf("\n\n");
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
