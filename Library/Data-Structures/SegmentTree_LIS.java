static class Pair implements Comparable<Pair>{
		int num;
		int idx;
		Pair (int n , int i){
			num = n ;
			idx = i ;
		}
		
		@Override
		public int compareTo(Pair i) {
			return num - i.num;
		}
	}
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		Pair [] arr = new Pair[n];
		for (int i = 0 ; i < n ; ++ i) arr[i] = new Pair (sc.nextInt() , i);
		Arrays.sort(arr);
		
		S s = new S(n);
		for (int i = 0 ; i < n ; ++ i) {
			int max = s.query (1 , 0 , n - 1 , 0 , arr[i].idx);
			s.update (1 , 0 , n - 1 , arr[i].idx , max + 1);
		}
		System.out.println(s.query(1, 0, n - 1, 0, n - 1));
	}
	
	static class S{
		int tree [];
		int N;
		S (int size){
			N = size;
			tree = new int[(N << 1) << 1];
		}
		
		int query (int node , int s , int e , int wantedS , int wantedE) {
			if (wantedS > e || wantedE < s) return 0;
			else if (s >= wantedS && e <= wantedE) return tree[node];
			else {
				int mid = s + e >> 1;
				int left = query(node << 1, s, mid, wantedS, wantedE);
				int right = query(node << 1 | 1, mid + 1, e, wantedS, wantedE);
				return Math.max(left, right);
			}
		}
		
		void update (int node , int s , int e , int idx , int val) {
			if (s == e)
				tree[node] = val ;
			else {
				int mid = s + e >> 1;
				if (idx <= mid) update(node << 1, s, mid, idx , val);
				else update(node << 1 | 1, mid + 1, e, idx , val);
				tree[node] = Math.max(tree[node << 1], tree[node << 1 | 1]);
			}
		}
	}
