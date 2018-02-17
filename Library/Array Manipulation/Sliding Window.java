static class Pair {
		int val ;
		int idx ;
		
		Pair (int v , int i){
			val = v;
			idx = i;
		}
	}
	
	static void sliding_window (int [] arr , int K) {
		Deque<Pair> window = new LinkedList<>();
		
		for (int i = 0 ; i < arr.length ; ++ i) {
			while (!window.isEmpty() && window.getLast().val >= arr[i]) 
				window.removeLast();
			
			window.addLast(new Pair (arr[i] , i));
			
			while (window.getFirst().idx <= i - K)
				window.removeFirst();
			
			System.out.println(" min = " + window.getFirst().val);
		}
	}
