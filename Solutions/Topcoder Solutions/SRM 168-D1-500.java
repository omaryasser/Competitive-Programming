import java.util.*;

/**
 * Created by omar on 28/07/17.
 */
public class DirectoryTree {
    String[][] a;
    Node root;
    void fill (Node cur, int i, int j) {
        if (a[i].length <= j || a[i][j].length() == 0) return;
        String wanted = a[i][j];
        for (Node n : cur.in) {
            if (n.name.equals(wanted)) {
                fill(n, i, j + 1);
                return;
            }
        }
        cur.in.add(new Node(wanted));
        fill(cur.in.get(cur.in.size() - 1), i, j + 1);
    }
    public String[] display(String[] files) {
        for (int i = 0; i < files.length; i++) files[i] = files[i].substring(1);
        a = new String[files.length][];
        for (int i = 0; i < files.length; i++) a[i] = files[i].split("/");
        root = new Node("ROOT");
        for (int i = 0; i < files.length; i++)
            fill (root, i, 0);
        solve (root, 0, new ArrayList<>());
        String[] ret = new String[res.size()];
        for (int i = 0; i < res.size(); i++)
            ret[i] = res.get(i);
        return ret;
    }
    ArrayList<String> res = new ArrayList<>();
    void solve (Node cur, int depth, ArrayList<Integer> dashes) {
        if (depth == 0) res.add("ROOT");
        else {
            String here = "";
            int curIdx = 0;
            for (int idx : dashes) {
                if (idx == depth - 1) continue;
                while (curIdx < idx * 2) {
                    here += " ";
                    curIdx++;
                }
                here += "|";
                curIdx++;
            }
            while (curIdx < (depth - 1) * 2) {curIdx++; here += " ";}
            here += "+-";
            here += cur.name;
            res.add(here);
        }
        Collections.sort(cur.in);
        for (int i = 0; i < cur.in.size(); i++) {
            ArrayList<Integer> now = new ArrayList<>();
            for (int c : dashes) now.add(c);
            now.add(depth);
            if (i != cur.in.size() - 1) {
                solve(cur.in.get(i), depth + 1, now);
            }
            else solve(cur.in.get(i), depth + 1, dashes);
        }
    }
    class Pair implements Comparable<Pair>{
        String a, b;
        Pair (String aa, String bb) {
            a = aa;
            b = bb;
        }

        @Override
        public int compareTo(Pair pair) {
            return a.equals(pair.a) ? b.compareTo(pair.b) : a.compareTo(pair.a);
        }
    }
    class Node implements Comparable<Node>{
        String name;
        ArrayList<Node> in = new ArrayList<>();
        Node (String n) {
            name = n;
        }

        @Override
        public int compareTo(Node node) {
            return name.compareTo(node.name);
        }
    }
}
