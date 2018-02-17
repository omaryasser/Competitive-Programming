static int INF = (int) 1e9;
    static class Node {
        int val , priority , max_in_sub , min_in_sub , min_diff , size_sub;
        Node left , right;
        Node (int val , int priority) {
            this.val = val;
            this.priority = priority;
            max_in_sub = min_in_sub = val;
            min_diff = INF;
            size_sub = 1;
        }
    }

    static class Res {
        int max , min , min_diff;
        Res (int a , int i , int d) {
            max = a;
            min = i;
            min_diff = d;
        }
    }
    static class Treap {
        Node root;
        void insert (int val) {
            if (!exist (val))
                insert (root , val , generate_priority());
        }

        int max_diff (int i , int j) {
            if (i == j) return - 1;
            return kth (j) - kth (i);
        }

        int min_diff (int i , int j) {
            if (i == j) return - 1;
            return min_diff (root , 0 , size(root) - 1 , i , j).min_diff;
        }

        Res min_diff (Node node , int start , int end , int i , int j) {
            if (start > j || end < i) return new Res(- INF , INF , INF);
            if (start >= i && end <= j) return new Res(node.max_in_sub , node.min_in_sub , node.min_diff);

            Res left = new Res(- INF , INF , INF) , right = new Res(- INF , INF , INF);
            if (node.left != null) left = min_diff(node.left , start , end - size(node.right) + 1 , i , j);
            if (node.right != null) right = min_diff(node.right , start + size(node.left) + 1 , end , i , j);

            int min = INF , max = - INF , min2 = INF;
            if (size(node.left) + start >= i && size(node.left) + start <= j) {
                min = Math.min(node.val - left.max , right.min - node.val);
                min2 = max = node.val;
            }
            return new Res(Math.max(left.max , Math.max(right.max , max)) , Math.min(left.min , Math.min(min2 , right.min)) ,
                        Math.min(left.min_diff , Math.min(right.min_diff , Math.min(min ,
                                right.min - left.max))));
        }

        void delete (int val) {
            if (exist(val)) {
                Node [] splitted = split(root , val);
                Node [] no_val = split(splitted[0] , val - 1);
                root = merge(no_val[0] , splitted[1]);
            }
        }
        void insert (Node node , int val , int priority) {
            Node [] splitted = split(root , val);
            root = merge (splitted[0] , merge(new Node(val , priority) , splitted[1]));
            update(root);
        }

        void print (Node node) {
            if (node != null) {
                System.out.print("( " + node.val + " , " + node.min_in_sub + " , " + node.max_in_sub + " ) " + " left = ");
                print(node.left);
                System.out.print("( " + node.val + " , " + node.min_in_sub + " , " + node.max_in_sub + " ) " + " right = ");
                print(node.right);
            }
            else System.out.print(" null ");
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

        int kth (int pos) {
            return kth(root , pos);
        }

        int kth (Node node , int pos) {
            int left_size = size(node.left);
            if (left_size > pos) return kth(node.left , pos);
            else if (left_size < pos) return kth(node.right , pos - left_size - 1);
            return node.val;
        }
        boolean exist (int val) {
            return exist(root , val);
        }
        boolean exist (Node node , int val){
            if (node == null) return false;
            if (node.val == val) return true;
            return val > node.val ? exist(node.right , val) : exist(node.left , val);
        }
        void update (Node node) {
            if (node != null) {
                node.size_sub = size (node.left) + 1 + size (node.right);
                node.max_in_sub = Math.max(node.val , Math.max(max(node.left) , max(node.right)));
                node.min_in_sub = Math.min(node.val , Math.min(min(node.left) , min(node.right)));
                node.min_diff = Math.min(node.val - max(node.left) , Math.min(min(node.right) - node.val ,
                        Math.min(min_diff(node.left) , min_diff(node.right))));
            }
        }

        int min_diff (Node node) {return node == null ? INF : node.min_diff;}
        int max (Node node) {return node == null ? - INF : node.max_in_sub;}
        int min (Node node) {return node == null ? INF : node.min_in_sub;}
        int size (Node node) {return node == null ? 0 : node.size_sub;}
        int generate_priority () {return (int)(Math.random() * 1e9);}
