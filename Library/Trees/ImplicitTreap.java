public class ImplicitTreap {
    class Node {
        int val, priority, cnt, min;
        Node left, right;

        Node(int v) {
            val = v;
            priority = (int) (Math.random() * 1e9);
        }
    }

    Node root;
    int INF = (int)1e9;

    Node[] split (Node T, int val) { return split(T, val, 0); }
    Node[] split(Node T, int val, int add) {
        if (T == null) return new Node[] {null, null};
        int curKey = add + cnt(T.left);
        if (val < curKey) {
            Node[] under = split(T.left, val, add);
            T.left = under[1];
            updateCnt(T);
            updateMin(T);
            return new Node[] {under[0], T};
        } else {
            Node[] under = split(T.right, val, add + 1 + cnt(T.left));
            T.right = under[0];
            updateCnt(T);
            updateMin(T);
            return new Node[] {T, under[1]};
        }
    }

    Node merge(Node l, Node r) {
        Node res;
        if (l == null || r == null) res = l == null ? r : l;
        else if (l.priority > r.priority) {
            l.right = merge(l.right, r);
            res = l;
        } else {
            r.left = merge(l, r.left);
            res = r;
        }
        updateCnt(res);
        updateMin(res);
        return res;
    }

    void insert(int val, int pos) {
        Node cur = new Node(val);
        if (pos == 0) root = merge(cur, root);
        else {
            Node[] res = split(root, pos - 1);
            res[0] = merge(res[0], cur);
            root = merge(res[0], res[1]);
        }
    }

    void erase(int idx) {
        root = erase(root, idx);
    }
    Node erase(Node T, int idx) {
        if (cnt(T.left) == idx) {
            T = merge(T.left, T.right);
            updateCnt(T);
            updateMin(T);
            return T;
        }
        if (cnt(T.left) > idx) T.left = erase(T.left, idx);
        else T.right = erase(T.right, idx - (cnt(T.left) + 1));
        updateCnt(T);
        updateMin(T);
        return T;
    }

    int kth (int idx) {return kth(root, idx);}
    int kth (Node T, int idx) {
        if (cnt(T.left) == idx) return T.val;
        if (cnt(T.left) > idx) return kth(T.left, idx);
        return kth(T.right, idx - (cnt(T.left) + 1));
    }

    int minQuery (int l, int r) {
        Node left, middle, right;
        if (l == 0) {
            left = null;
            Node[] res = split(root, r);
            middle = res[0];
            right = res[1];
        } else {
            Node[] res = split(root, l - 1);
            left = res[0];
            Node[] res2 = split(res[1], r - cnt(left));
            middle = res2[0];
            right = res2[1];
        }

        int ans = middle.min;
        root = merge(merge(left, middle), right);
        return ans;
    }
    int cnt(Node T) {
        return T == null ? 0 : T.cnt;
    }

    void updateCnt(Node T) {
        if (T != null) T.cnt = 1 + cnt(T.left) + cnt(T.right);
    }
    int min (Node T) {
        return T == null ? INF : T.min;
    }
    void updateMin(Node T) {
        if (T != null) T.min = Math.min(T.val, Math.min(min(T.left), min(T.right)));
    }

    void print() {
        print(root);
    }

    void print(Node T) {
        if (T == null) return;
        System.err.print("[v = " + T.val + ", p = " + T.priority + "](");
        print(T.left);
        System.err.print(")(");
        print(T.right);
        System.err.print(")");
    }

    public static void main(String[] args) {

    }
}
