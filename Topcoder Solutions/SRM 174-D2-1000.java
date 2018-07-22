import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 * Created by omar on 21/07/17.
 */
public class ProbabilityTree {
    ArrayList<Node>[] children;
    int l, u;
    ArrayList<Integer> res = new ArrayList<>();
    public int[] getOdds(String[] tree, int lowerBound, int upperBound) {
        l = lowerBound;
        u = upperBound;
        int n = tree.length;
        children = new ArrayList[n];
        for (int i = 0; i < n; i++) children[i] = new ArrayList<>();
        Node root = new Node(0, Integer.parseInt(tree[0]) / 100.0, 1);
        for (int i = 1; i < n; i++) {
            StringTokenizer st = new StringTokenizer(tree[i]);
            children[Integer.parseInt(st.nextToken())].add(new Node(i, Integer.parseInt(st.nextToken()) / 100.0, Integer.parseInt(st.nextToken()) / 100.0));
        }
        root.prob2 = 1 - root.prob1;
        if (root.prob1 * 100 >= l && root.prob1 * 100 <= u) res.add(root.idx);
        for (Node child : children[0])
            dfs(child, root.prob1, root.prob2);
        Collections.sort(res);
        int[] res2 = new int[res.size()];
        for (int i = 0; i < res.size(); i++)
            res2[i] = res.get(i);
        return res2;
    }

    void dfs (Node cur, double prob1, double prob2) {
        cur.prob1 = cur.prob1 * prob1 + cur.prob2 * prob2;
        cur.prob2 = 1 - cur.prob1;
        if (cur.prob1 * 100 >= l && cur.prob1 * 100 <= u) res.add(cur.idx);
        for (Node child : children[cur.idx])
            dfs(child, cur.prob1, cur.prob2);
    }
    class Node {
        double prob1, prob2;
        int idx;
        Node (int i, double p1, double p2) {
            idx = i;
            prob1 = p1;
            prob2 = p2;
        }
    }
}
