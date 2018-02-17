static class Node {
		int data ;
		Node left ;
		Node right ;
		int height ;
		Node (int n){
			data = n;
			height = 1;
		}
	}

	static class AVL_Tree{
		Node root ;
		public AVL_Tree(Node root) {
			this.root = root;
		}
		boolean find(int id) {
			Node current = root;
			while (current != null) {
				if (current.data == id) {
					return true;
				} else if (current.data > id) {
					current = current.left;
				} else {
					current = current.right;
				}
			}
			return false;
		}
		boolean delete(int id) {
			Node parent = root;
			Node current = root;
			boolean isLeftChild = false;
			while (current.data != id) {
				parent = current;
				if (current.data > id) {
					isLeftChild = true;
					current = current.left;
				} else {
					isLeftChild = false;
					current = current.right;
				}
				if (current == null) {
					return false;
				}
			}
			// if i am here that means we have found the node
			// Case 1: if node to be deleted has no children
			if (current.left == null && current.right == null) {
				if (current == root) {
					root = null;
				}
				if (isLeftChild == true) {
					parent.left = null;
				} else {
					parent.right = null;
				}
			}
			// Case 2 : if node to be deleted has only one child
			else if (current.right == null) {
				if (current == root) {
					root = current.left;
				} else if (isLeftChild) {
					parent.left = current.left;
				} else {
					parent.right = current.left;
				}
			} else if (current.left == null) {
				if (current == root) {
					root = current.right;
				} else if (isLeftChild) {
					parent.left = current.right;
				} else {
					parent.right = current.right;
				}
			} else if (current.left != null && current.right != null) {

				// now we have found the minimum element in the right sub tree
				Node successor = getSuccessor(current);
				if (current == root) {
					root = successor;
				} else if (isLeftChild) {
					parent.left = successor;
				} else {
					parent.right = successor;
				}
				successor.left = current.left;
			}
			return true;
		}

		Node getSuccessor(Node deleleNode) {
			Node successsor = null;
			Node successsorParent = null;
			Node current = deleleNode.right;
			while (current != null) {
				successsorParent = successsor;
				successsor = current;
				current = current.left;
			}
			// check if successor has the right child, it cannot have left child
			// for sure
			// if it does have the right child, add it to the left of
			// successorParent.
			// successsorParent
			if (successsor != deleleNode.right) {
				successsorParent.left = successsor.right;
				successsor.right = deleleNode.right;
			}
			return successsor;
		}
		void insert (int data){
			root = insert (root , data);
		}
		Node insert (Node current , int data){
			if (current == null) return new Node (data);
			if (data <= current.data) current.left = insert (current.left , data);
			else current.right = insert(current.right, data);

			int balance = height(current.left)  - height(current.right);
			if (balance > 1){
				if (height(current.left.left) >= height(current.left.right)){
					return rotateRight (current); // LL
				}
				else {
					current.left = rotateLeft (current.left);
					return rotateRight (current); // LR
				}
			}
			if (balance < - 1) {
				if (height(current.right.right) >= height(current.right.left)){
					return rotateLeft (current); // RR
				}
				else {
					current.right = rotateRight (current.right);
					return rotateLeft (current); // RL
				}
			}

			current.height = 1 + Math.max(height(current.left), height(current.right));
			return current;
		}

		int height (Node cur){
			if (cur == null) return 0 ;
			else return cur.height;
		}
		Node rotateRight (Node current){
			Node newRoot = current.left;
			current.left = newRoot.right;
			newRoot.right = current;
			current.height = 1 + Math.max (height(current.left) , height(current.right));
			newRoot.height = 1 + Math.max(height(newRoot.left), height(newRoot.right));
			return newRoot ;
		}

		Node rotateLeft (Node current){
			Node newRoot = current.right;
			current.right = newRoot.left;
			newRoot.left = current;
			current.height = 1 + Math.max(height(current.left), height(current.right));
			newRoot.height = 1 + Math.max(height(newRoot.left), height(newRoot.right));
			return newRoot ;
		}

		Node LCA (Node n1 , Node n2)
		{
			return LCA (root , n1 , n2);
		}

		Node LCA (Node current , Node n1 , Node n2)
		{
			if(current == null) return null;
			if(current.data == n1.data || current.data == n2.data) return current;

			Node left = LCA (current.left , n1 , n2);
			Node right = LCA (current.right , n1 , n2);

			if(left == null && right == null) return null;
			if(left != null && right !=null) return current;

			return left!=null?left:right;
		}

		boolean root_to_leaf_sum  (int sum)
		{
			return root_to_leaf_sum (root , sum , new LinkedList<Integer>());
		}

		boolean root_to_leaf_sum  (Node current , int sum , LinkedList<Integer> result)
		{
			if(root == null)
				return false;

			if(root.left == null && root.right == null)
			{
				if(root.data == sum)
				{
					result.add(root.data);
					return true;
				}
				else return false;
			}

			if(root_to_leaf_sum(root.left , sum - root.data , result))
			{
				result.add(root.data);
				return true;
			}

			if(root_to_leaf_sum(root.right , sum - root.data , result))
			{
				result.add(root.data);
				return true;
			}

			return false;
		}

		void display(Node root) {
			if (root != null) {
				display(root.left);
				System.out.print(" " + root.data);
				display(root.right);
			}
		}
	}
