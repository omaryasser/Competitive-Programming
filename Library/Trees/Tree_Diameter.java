static ArrayList<Integer> adjList [];
static int f[]; // f[i] is The maximum height of the tree rooted at i + 1 (current node);
  	static int g[]; // is the sum of the maximum two heights of the tree rooted at i + 1 (current node);
  	static int diameter;
 	// O(N)
 	static void dfs (int V , int pV) { // ans is max(diameter, f[V], g[V]) i.e. diameter after method call in Main.
 	//	ArrayList<Integer> fValues = new ArrayList<>();
 		int max1=0,max2=0;
  		for (int v : adjList[V]) {
  			if (v == pV) continue;
  			dfs (v , V);
 	//		fValues.add(f[v]);
 			if(f[v]>=max1){
 				max2=max1;
 				max1=f[v];
 			}else
 				if(f[v]>max2)
 					max2=f[v];
  		}
  		
		//		Collections.sort(fValues);
  		
  		f[V] = 1;
  	//	if (!fValues.isEmpty()) f[V] += fValues.get(fValues.size() - 1);
 		f[V]+=max1;
  		
 	//	if (fValues.size() >= 2)
 	//		g[V] = 1 + fValues.get(fValues.size() - 1) + fValues.get(fValues.size() - 2);
 		if(max1>0 && max2>0)
 			g[V] = 1 + max1 + max2;
  		diameter = Math.max(diameter, Math.max(f[V], g[V]));
  	}
