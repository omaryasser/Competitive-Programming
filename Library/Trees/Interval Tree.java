static class Interval {
		int lo , hi ;
		Interval (int lo , int hi) {
			this.lo = lo ;
			this.hi = hi;
		}
	}

	static class Node {
		Interval interval;
		int max;
		Node left ;
		Node right ;

		Node (Interval interval) {
			this.interval = interval;
			max = interval.hi;
		}
	}

	static Node insert (Node root , Interval interval) {
		if (root == null)
			return new Node (interval);

		int l = root.interval.lo;

		if (interval.lo < l) root.left = insert(root.left , interval);
		else 				 root.right= insert(root.right, interval);

		if (root.max < interval.hi) root.max = interval.hi;

		return root;
	}

	static boolean overLap (Interval i1 , Interval i2) {
		return i1.lo <= i2.hi && i1.hi >= i2.lo;
	}

	static boolean intervalOverLap (Node root , Interval i) {
		if (root == null) return false;
		if (overLap(root.interval, i)) return true;

		if (root.left != null && root.left.max >= i.lo)
			return intervalOverLap(root.left, i);
		return intervalOverLap(root.right, i);
	}
