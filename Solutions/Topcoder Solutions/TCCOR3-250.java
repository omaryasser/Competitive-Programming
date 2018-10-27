import java.util.Arrays;

public class Boxing {
	public static  int maxCredit(int[] a, int[] b, int[] c, int[] d, int[] e) {
		int MAX = 180001;
		int [][] pref = new int[5][MAX];
		
		int [][] arr = new int[5][];
		arr[0] = new int[a.length];
		arr[1] = new int[b.length];
		arr[2] = new int[c.length];
		arr[3] = new int[d.length];
		arr[4] = new int[e.length];
		for (int i = 0; i < a.length; i++)
			arr[0][i] = a[i];
		for (int i = 0; i < b.length; i++)
			arr[1][i] = b[i];
		for (int i = 0; i < c.length; i++)
			arr[2][i] = c[i];
		for (int i = 0; i < d.length; i++)
			arr[3][i] = d[i];
		for (int i = 0; i < e.length; i++)
			arr[4][i] = e[i];
		
		for (int i = 0; i < 5; i++)
			Arrays.sort(arr[i]);
		
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < arr[i].length; j++)
				pref[i][arr[i][j]]++;
			for (int j = 1; j < MAX; j++)
				pref[i][j] += pref[i][j - 1];
		}
		
		int res = 0;
		int start = 0, end = 0;
		
		while (end < MAX) {
			int cnt = 0;
			for (int i = 0; i < 5; i++)
				cnt += (pref[i][end] - (start - 1 < 0 ? 0 : pref[i][start - 1]) >= 1) ? 1 : 0;
			
			if (cnt >= 3) {
				res++;
				start = ++end;
			}
			else {
				end++;
			}
			if(end - start > 1000) start++;
		}
		
		return res;
	}
	
	public static void main(String[] args) {
		System.out.println(Boxing.maxCredit(new int [] {1,2,3,4,5,6}
, new int [] {1,2,3,4,5,6,7}
, new int [] {1,2,3,4,5,6}
, new int [] {0,1,2}
, new int [] {1,2,3,4,5,6,7,8}
));
	}
}
