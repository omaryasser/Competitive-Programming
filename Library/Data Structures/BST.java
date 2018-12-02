K	static class Node {
		int data;
		Node left;
		Node right;

		public Node(int data) {
			this.data = data;
			left = null;
			right = null;
		}
	}

	static class BinaryTree {
		public static Node root;

		public BinaryTree() {
			this.root = null;
		}

		public boolean find(int id) {
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

		public boolean delete(int id) {
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

		public Node getSuccessor(Node deleleNode) {
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

		public void insert(int id) {
			Node newNode = new Node(id);
			if (root == null) {
				root = newNode;
				return;
			}
			Node current = root;
			Node parent = null;
			while (true) {
				parent = current;
				if (id < current.data) {
					current = current.left;
					if (current == null) {
						parent.left = newNode;
						return;
					}
				} else {
					current = current.right;
					if (current == null) {
						parent.right = newNode;
						return;
					}
				}
			}
		}

		public boolean isBST ()
		{
			return isBST(root , Integer.MIN_VALUE , Integer.MAX_VALUE);
		}

		public boolean isBST (Node current , int min , int max)
		{
			if(current == null)
				return true;
			if(current.data >= min && current.data <=max)
				return isBST(current.left , min , current.data) &&
						isBST(current.right , current.data , max);
			else return false;
		}

		public Node LCA_BST (Node n1 , Node n2)
		{
			return LCA_BST_helper(root , n1 , n2);
		}

		public Node LCA_BST_helper(Node current , Node n1 , Node n2)
		{
			if(current.data  > Math.max(n1.data, n2.data))
				return LCA_BST_helper(current.left , n1, n2);
			else if(current.data < Math.min(n1.data, n2.data))
				return LCA_BST_helper(current.right , n1, n2);
			else return current;
		}

		public Node LCA (Node n1 , Node n2)
		{
			return LCA (root , n1 , n2);
		}

		public Node LCA (Node current , Node n1 , Node n2)
		{
			if(current == null) return null;
			if(current.data == n1.data || current.data == n2.data) return current;

			Node left = LCA (current.left , n1 , n2);
			Node right = LCA (current.right , n1 , n2);

			if(left == null && right == null) return null;
			if(left != null && right !=null) return current;

			return left!=null?left:right;
		}


		public int height() {
			return height(root);
		}

		public int height(Node current) {
			if (current == null)
				return 0;
			return Math.max(height(current.left), height(current.right)) + 1;
		}

		public void preOrderTraversal() {
			preOrderHelper(root);
		}

		private void preOrderHelper(Node r) {
			if (r != null) {
				System.out.print(r + " ");
				preOrderHelper(r.left);
				preOrderHelper(r.right);
			}
		}

		public void inOrderTraversal() {
			inOrderHelper(root);
		}

		private void inOrderHelper(Node r) {
			if (r != null) {
				inOrderHelper(r.left);
				System.out.print(r + " ");
				inOrderHelper(r.right);
			}
		}

		public void postOrderTraversal() {
			postOrderHelper(root);
		}

		public void postOrderHelper(Node r) {
			if (r != null) {
				preOrderHelper(r.left);
				preOrderHelper(r.right);
				System.out.print(r + " ");
			}
		}

		public void levelOrderTraversal()
		{
			levelOrderHelper(root);
		}

		public void levelOrderHelper(Node root)
		{
			if(root == null) return;
			Queue<Node> q = new LinkedList<Node>();
			q.add(root);
			while(!q.isEmpty())
			{
				root = q.poll();
				System.out.println(root.data);
				if(root.left != null)
					q.add(root.left);
				if(root.right !=null)
					q.add(root.right);
			}
		}
		public void display(Node root) {
			if (root != null) {
				display(root.left);
				System.out.print(" " + root.data);
				display(root.right);
			}
		}

		public  boolean root_to_leaf_sum  (int sum)
		{
			return root_to_leaf_sum (root , sum , new LinkedList<Integer>());
		}

		public  boolean root_to_leaf_sum  (Node current , int sum , LinkedList<Integer> result)
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
	}
