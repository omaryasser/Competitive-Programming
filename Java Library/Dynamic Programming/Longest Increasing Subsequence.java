// The smallest number of nonincreasing subsequences = the length of the longest increasing subsequence
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

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


	// mine
	static Stack<Integer> s;
	static int LIS (ArrayList<Integer> arr) {
		int n = arr.size();
		int []P = new int[n], in = new int[n];
		ArrayList<Integer> L = new ArrayList<>();
		int lis = 0, last = -1;

		for (int i = 0; i < n; i++) {
			int num = arr.get(i);
			int pos = Collections.binarySearch(L, num);
			if (pos < 0) pos = -pos - 1;
			if (pos + 1 > lis) {
				last = i;
				lis++;
				L.add(num);
			}
			else {
				L.set(pos, num);
			}
			P[i] = pos > 0 ? in[pos - 1] : -1;
			in[pos] = i;
		}

		s = new Stack<>();
		while(last != -1) {
			s.push(arr.get(last));
			last = P[last];
		}
		return lis;
	}

	// Longest Increasing Subsequence Starting at i
	static ArrayList<Integer> Suffix_LIS (ArrayList<Integer> arr) {
        int n = arr.size();
        int []P = new int[n], in = new int[n];
        ArrayList<Integer> L = new ArrayList<>();
        ArrayList<Integer> res = new ArrayList<>();
        int lis = 0;

        for (int i = n - 1; i >= 0; i--) {
            int num = -arr.get(i);
            int pos = Collections.binarySearch(L, num);
            if (pos < 0) pos = -pos - 1;
            res.add(pos + 1);
            if (pos + 1 > lis) {
                lis++;
                L.add(num);
            }
            else {
                L.set(pos, num);
            }
            P[i] = pos > 0 ? in[pos - 1] : -1;
            in[pos] = i;
        }

        return res;
    }
	// Longest Non Decreasing Subsequence (<=) ending at i and starting at i
	static int binarySearch (ArrayList<Integer> arr, int val) {
        if (arr.size() == 0) return -1;
        if (val >= arr.get(arr.size() - 1)) return -(arr.size() + 1);

        int lo = 0, hi = arr.size() - 1, best = 0;
        for (int cnt = 0; cnt <= 21; cnt++) {
            int mid = lo + ((hi - lo) >> 1);
            if (arr.get(mid) > val) {
                best = mid;
                hi = Math.max(lo, mid - 1);
            } else {
                lo = Math.min(hi, mid + 1);
            }
        }
        return best;
    }
    static ArrayList<Integer> Prefix_LNS (ArrayList<Integer> arr) {
        int n = arr.size();
        int []P = new int[n], in = new int[n];
        ArrayList<Integer> L = new ArrayList<>();
        ArrayList<Integer> res = new ArrayList<>();
        int lis = 0;

        for (int i = 0; i < n; i++) {
            int num = arr.get(i);
            int pos = binarySearch(L, num);
            if (pos < 0) pos = -pos - 1;
            res.add(pos + 1);
            if (pos + 1 > lis) {
                lis++;
                L.add(num);
            }
            else {
                L.set(pos, num);
            }
            P[i] = pos > 0 ? in[pos - 1] : -1;
            in[pos] = i;
        }

        return res;
    }

    static ArrayList<Integer> Suffix_LNS (ArrayList<Integer> arr) {
        int n = arr.size();
        int []P = new int[n], in = new int[n];
        ArrayList<Integer> L = new ArrayList<>();
        ArrayList<Integer> res = new ArrayList<>();
        int lis = 0;

        for (int i = n - 1; i >= 0; i--) {
            int num = -arr.get(i);
            int pos = binarySearch(L, num);
            if (pos < 0) pos = -pos - 1;
            res.add(pos + 1);
            if (pos + 1 > lis) {
                lis++;
                L.add(num);
            }
            else {
                L.set(pos, num);
            }
            P[i] = pos > 0 ? in[pos - 1] : -1;
            in[pos] = i;
        }

        return res;
    }
