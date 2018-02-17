import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.StringTokenizer;

public class A {
	static BitSet gauss(BitSet[] a, int n, int m) {
		int[] where = new int[m + 1];
		Arrays.fill(where, -1);
		for (int col = 0, row = 0; col < m && row < n; ++col) {
			for (int i = row; i < n; ++i)
				if (a[i].get(col)) {
					BitSet tmp = a[i];
					a[i] = a[row];
					a[row] = tmp;
					break;
				}
			if (!a[row].get(col))
				continue;
			where[col] = row;
			for (int i = 0; i < n; ++i)
				if (i != row && a[i].get(col))
					a[i].xor(a[row]);
			++row;
		}
		BitSet ans = new BitSet();
		for (int i=0; i<m; ++i) {
			if (where[i] != -1) {
				if (a[where[i]].get(m - 1)) ans.set(i);
			}
		}
		return ans;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		int n = sc.nextInt();
		BitSet[] arr = new BitSet[n];
		ArrayList<Integer>[] valves = new ArrayList[n];
		for (int i = 0; i < n; i++) 			
			arr[i] = new BitSet();
		for (int i = 0; i < n; i++) {
			valves[i] = new ArrayList<>();
			while (true) {
				int idx = sc.nextInt() - 1;
				if (idx == -2)
					break;
				valves[i].add(idx);
				arr[idx].set(i);
			}
			arr[i].set(n);
		}
		BitSet res = gauss(arr, n, n + 1);
		boolean[] taken = new boolean[n];
		for (int i = 0; i < n; i++)
			if (res.get(i))
				taken[i] = true;

		int[] cnt = new int[n];
		for (int i = 0; i < n; i++)
			if (taken[i]) {
				for (int valve : valves[i])
					cnt[valve]++;
			}

		boolean ok = true;
		for (int i = 0; i < n; i++)
			ok &= cnt[i] % 2 == 1;

		if (!ok)
			out.println("No solution");
		else {
			boolean first = true;
			for (int i = 0; i < n; i++)
				if (taken[i]) {
					if (first)
						first = false;
					else
						out.print(" ");
					out.print(i + 1);
				}
			out.println();
		}
		out.flush();
	}

	static class Pair {
		int to, pref;

		Pair(int t, int p) {
			to = t;
			pref = p;
		}
	}

	static class Scanner {
		StringTokenizer st;
		BufferedReader br;

		public Scanner(InputStream s) {
			br = new BufferedReader(new InputStreamReader(s));
		}

		public Scanner(FileReader f) {
			br = new BufferedReader(f);
		}

		public String next() {
			while (st == null || !st.hasMoreTokens())
				try {
					st = new StringTokenizer(br.readLine());
				} catch (Exception e) {
				}
			return st.nextToken();
		}

		public int nextInt() {
			return Integer.parseInt(next());
		}

	}
}
