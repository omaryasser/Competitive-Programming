/**
 * Created by omar on 28/07/17.
 */
public class RedBlack {
    public int numTwists(int[] keys) {
        RB_Tree tree = new RB_Tree();
        int res = 0;
        for (int num : keys) res += tree.insert(num);
        return res;
    }
    class RB_Tree {
        Node root;
        int ans;
        int insert (int k) {
            root = insert (root, k);
            ans = 0;
            root = twist (root);
            root.isRed = false;
            return ans;
        }
        Node insert (Node cur, int k) {
            if (cur == null) return new Node(k, true);
            if (k < cur.key) {
                cur.left = insert(cur.left, k);
                return cur;
            } else {
                cur.right = insert(cur.right, k);
                return cur;
            }
        }

        Node twist (Node cur) {
            if (cur == null) return null;
            cur.left = twist(cur.left);
            cur.right = twist(cur.right);
            // case 1
            if (cur.left != null && cur.left.left != null && cur.left.isRed && cur.left.left.isRed) {
                ans++;
                Node curRoot = cur.left;
                cur.left = curRoot.right;
                curRoot.right = cur;
                curRoot.left.isRed = false;
                return twist(curRoot);
            }
            // case 2
            else if (cur.left != null && cur.left.right != null && cur.left.isRed && cur.left.right.isRed) {
                ans++;
                Node curRoot = cur.left.right;
                cur.left.right = cur.left.right.left;
                curRoot.left = cur.left;
                cur.left = curRoot.right;
                curRoot.right = cur;
                curRoot.left.isRed = false;
                return twist(curRoot);
            }
            // case 3
            else if (cur.right != null && cur.right.left != null && cur.right.isRed && cur.right.left.isRed) {
                ans++;
                Node curRoot = cur.right.left;
                cur.right.left = curRoot.right;
                curRoot.right = cur.right;
                cur.right = curRoot.left;
                curRoot.left = cur;
                curRoot.right.isRed = false;
                return twist(curRoot);
            }
            // case 4
            else if (cur.right != null && cur.right.right != null && cur.right.isRed && cur.right.right.isRed) {
                ans++;
                Node curRoot = cur.right;
                cur.right = cur.right.left;
                curRoot.left = cur;
                curRoot.right.isRed = false;
                return twist(curRoot);
            }
            return cur;
        }
    }
    class Node {
        int key;
        boolean isRed;
        Node left, right;
        Node (int k, boolean isRed) {
            key = k;
            this.isRed = isRed;
        }
    }
}
