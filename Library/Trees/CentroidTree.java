static int[][] adjList;
	static int[] subSize, label;
	
	static int size(int u, int p)
	{
		int sz = 1;
		for(int i = 0, size = adjList[u].length; i < size; ++i)
		{
			int v = adjList[u][i];
			if(v != p && label[v] == -1)
				sz += size(v, u);
		}
		return subSize[u] = sz;
	}
	
	static int N;
	static void decompose(int u, int p, int lb)
	{
		if(p == -1)
			N = size(u, -1);
		for(int i = 0, size = adjList[u].length; i < size; ++i)
		{
			int v = adjList[u][i];
			if(v != p && label[v] == -1 && subSize[v] > N / 2)
			{ 	
				decompose(v, u, lb);
				return;
			}
		}
		label[u] = lb;
		for(int i = 0, size = adjList[u].length; i < size; ++i)
		{
			int v = adjList[u][i];
			if(label[v] == -1)
				decompose(v, -1, lb + 1);
		}
	}
