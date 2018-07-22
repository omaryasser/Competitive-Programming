    static int INF = (int) 1e9;
    static class Node {
        int val , priority, size_sub;
        Node left , right;
        Node (int val , int priority) {
            this.val = val;
            this.priority = priority;
            size_sub = 1;
        }
    }
    static class Treap {
        Node root;
        void insert (int val) {if (!exist (val)) insert (root , val , generate_priority());}
        void insert (Node node , int val , int priority) {
            Node [] splitted = split(root , val);
            root = merge (splitted[0] , merge(new Node(val , priority) , splitted[1]));
            update(root);
        }
        void delete (int val) {
            if (exist(val)) {
                Node [] splitted = split(root , val);
                Node [] no_val = split(splitted[0] , val - 1);
                root = merge(no_val[0] , splitted[1]);
            }
        }
        Node [] split (Node node , int val) {
            Node left , right;
            if (node == null) return new Node [] {null , null};
            if (val < node.val) {
                Node [] splitted = split(node.left , val);
                update(node);
                right = node;
                right.left = splitted[1];
                update(right.left);
                left = splitted[0];
            }
            else {
                Node [] splitted = split(node.right , val);
                update(node);
                left = node;
                left.right = splitted[0];
                update(left.right);
                right = splitted[1];
            }
            update (left);
            update (right);
            return new Node[] {left , right};
        }
        Node merge (Node left , Node right) {
            if (left == null) return right;
            if (right == null) return left;

            if (left.priority >= right.priority) {
                left.right = merge(left.right , right);
                update(left.right);
                update(left);
                return left;
            }
            else {
                right.left = merge(left , right.left);
                update(right.left);
                update(right);
                return right;
            }
        }
        int kth (int pos) {return kth(root , pos);}

        int kth (Node node , int pos) {
            int left_size = size(node.left);
            if (left_size > pos) return kth(node.left , pos);
            else if (left_size < pos) return kth(node.right , pos - left_size - 1);
            return node.val;
        }
        boolean exist (int val) {return exist(root , val);}
        boolean exist (Node node , int val){
            if (node == null) return false;
            if (node.val == val) return true;
            return val > node.val ? exist(node.right , val) : exist(node.left , val);
        }
        void update (Node node) {if (node != null) {node.size_sub = size (node.left) + 1 + size (node.right);}}
        int size (Node node) {return node == null ? 0 : node.size_sub;}
        int generate_priority () {return (int)(Math.random() * 1e9);}
    }
