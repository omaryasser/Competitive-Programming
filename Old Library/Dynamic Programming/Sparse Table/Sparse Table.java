public class SparseTable {                                          
	  
	int A[], SpT[][];
	
	SparseTable(int[] A) 
	{
		int n = A.length;	this.A = A;
		int k = (int)Math.floor(Math.log(n) / Math.log(2)) + 1;
		SpT = new int[n][k];
		
		for (int i = 0; i < n; i++) 
			SpT[i][0] = i; 					// RMQ of sub array starting at index i and of length 2^0=1
	
		//overall time complexity = O(n log n)
		for (int j = 1; (1<<j) <= n; j++) 				
			for (int i = 0; i + (1<<j) - 1 < n; i++)    
				if (A[SpT[i][j-1]] < A[SpT[i+(1<<(j-1))][j-1]])
					SpT[i][j] = SpT[i][j-1];    		// start at index i of length 2^(j-1)
				else                  					// start at index i+2^(j-1) of length 2^(j-1)
					SpT[i][j] = SpT[i+(1<<(j-1))][j-1];
	 }
}