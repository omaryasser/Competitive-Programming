import java.io.*;
import java.util.*;

/**
 * Created by omar on 22/06/17.
 */
public class Main {

    static class Trie {
        Node root = new Node ();
        char [] arr;
        static class Node {
            Node nxt [] = new Node[4];
        }
        Trie (char [] s) {arr = s;}
        void insert (int idx) {
            Node cur = root;
            while (idx < arr.length) {
                int c = map(arr[idx]);
                Node nxt = cur.nxt[c];
                if (nxt == null) {
                    cur.nxt[c] = nxt = new Node();
                }
                cur = nxt;
                idx++;
            }
        }
        int search (char [] s, int idx) {
            Node cur = root;
            int cnt = 0;
            while (idx < s.length) {
                int c = map(s[idx]);
                Node nxt = cur.nxt[c];
                if (nxt == null) {
                    return cnt;
                }
                cnt++;
                cur = nxt;
                idx++;
            }
            return cnt;
        }
        int map (char c) {
            switch (c) {
                case 'a' : return 0;
                case 't' : return 1;
                case 'g' : return 2;
                default  : return 3;
            }
        }
    }
    public static void main (String [] args) {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        while (true) {
            char [] s1 = sc.nextLine().toCharArray(), s2 = sc.nextLine().toCharArray();
            Trie trie = new Trie(s1);
            for (int i = 0; i < s1.length; i++)
                trie.insert(i);
            int max = 0;
            ArrayList<Integer> indices = new ArrayList<>();
            for (int i = 0; i < s2.length; i++) {
                int res = trie.search(s2, i);
                if (res > max) {
                    indices = new ArrayList<>();
                    indices.add(i);
                    max = res;
                } else if (res == max) {
                    indices.add(i);
                }
            }
            TreeSet<String> arrayList = new TreeSet<>();
            for (int num : indices) {
                StringBuilder sb = new StringBuilder();
                for (int j = num; j < num + max; j++)
                    sb.append(s2[j]);
                arrayList.add(sb.toString());
            }
            if (max == 0) out.println("No common sequence.");
            else for (String s : arrayList) out.println(s);
            if (sc.ready()) {
                out.println();
                sc.nextLine();
            }
            else break;
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

        public long nextLong() {
            return Long.parseLong(next());
        }

        public String nextLine() {
            try {
                return br.readLine();
            } catch (Exception e) {
                return null;
            }
        }

        public double nextDouble() {
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

        public boolean ready() {
            try {
                return br.ready();
            } catch (Exception e) {
                return false;
            }
        }

    }
}
