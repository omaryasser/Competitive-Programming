import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;


/* LIS using segments TREE */
/* sort the array according to the values remembering the index , let All tree node be zeros
loop through the sorted array find RMAXQ up to the index of the current element and plus one */



public class LIS {
	
	static int lis(int[] a, int n)
	{
		int lis = 0;
		int[] L = new int[n];
		for(int i = 0; i < n; ++i)
		{
			int cur_L = 1;
			for(int j = 0; j < i; ++j)
				if(a[j] < a[i])
					cur_L = Math.max(cur_L, L[j] + 1);
			L[i] = cur_L;
			lis = Math.max(lis, cur_L);
		}
		return lis;
	}
	
	
	static Stack<Integer> stack;	//contains the last solution in increasing order
	static int lis2(int[] A, int n)
	{
		ArrayList<Integer> L = new ArrayList<Integer>();
		int[] P = new int[n];
		int[] L_id = new int[n];
		
		int lis = 0, lis_end = -1;
		for(int i = 0; i < n; ++i) 
		{
			int pos = Collections.binarySearch(L, A[i]);
			if (pos < 0) pos = -(pos + 1);
			//			 else	pos++;		non-decreasing

			if(pos >= L.size()) L.add(A[i]);
			else                 L.set(pos, A[i]);

			if(pos + 1 > lis)
			{
				lis = pos + 1;
				lis_end = i;
			}
			
			//lis_end and the following part for printing the solution
			L_id[pos] = i;
			P[i] = pos > 0 ? L_id[pos-1] : -1;
		}
		
		stack = new Stack<Integer>();
		while(lis_end != -1)
		{
			stack.push(A[lis_end]);
			lis_end = P[lis_end];
		}
		return lis;
	}
