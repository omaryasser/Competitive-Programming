import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	static int [] lis, lds;
	static void LIS (ArrayList<Integer> arr) {
		int n = arr.size();
		ArrayList<Integer> L = new ArrayList<>();
		int liss = 0;
		for (int i = 0; i < n; i++) {
			int num = arr.get(i);
			int pos = Collections.binarySearch(L, num);
			if (pos < 0) pos = -pos - 1;
			if (pos + 1 > liss) {
				liss++;
				L.add(num);
			}
			else {
				L.set(pos, num);
			}
			lis[i] = pos + 1;
		}
	}
	
	static void LDS (ArrayList<Integer> arr) {
		int n = arr.size();
		ArrayList<Integer> L = new ArrayList<>();
		int liss = 0;
		for (int i = n - 1; i >= 0; i--) {
			int num = arr.get(i);
			int pos = Collections.binarySearch(L, num);
			if (pos < 0) pos = -pos - 1;
			if (pos + 1 > liss) {
				liss++;
				L.add(num);
			}
			else {
				L.set(pos, num);
			}
			lds[i] = pos + 1;
		}
	}

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		while (sc.ready()) {
			int n = sc.nextInt();
			ArrayList<Integer> arr = new ArrayList<>();
			for (int i = 0; i < n; i++)
				arr.add(sc.nextInt());
			lis = new int[n];
			lds = new int[n];

			LIS(arr);
			LDS(arr);
			
			int max = 1;
			for (int i = 0; i < n; i++)
				max = Math.max(max, 2 * Math.min(lis[i], lds[i]) - 1);
			
			out.println(max);
		}
		
		
		out.flush();
		out.close();
	}
	
	
	static class Scanner {

		StringTokenizer st;
		BufferedReader br;

		public Scanner(InputStream s) {
			br = new BufferedReader(new InputStreamReader(s));
		}

		public String next() throws IOException {
			while (st == null || !st.hasMoreTokens())
				st = new StringTokenizer(br.readLine());
			return st.nextToken();
		}

		public int nextInt() throws IOException {
			return Integer.parseInt(next());
		}

		public long nextLong() throws IOException {
			return Long.parseLong(next());
		}

		public String nextLine() throws IOException {
			return br.readLine();
		}

		public double nextDouble() throws IOException {
			String x = next();
			StringBuilder sb = new StringBuilder("0");
			double res = 0, f = 1;
			boolean dec = false, neg = false;
			int start = 0;
			if (x.charAt(0) == '-') {
				neg = true;
				start++;
			}
			for (int i = start; i < x.length(); i++)
				if (x.charAt(i) == '.') {
					res = Long.parseLong(sb.toString());
					sb = new StringBuilder("0");
					dec = true;
				} else {
					sb.append(x.charAt(i));
					if (dec)
						f *= 10;
				}
			res += Long.parseLong(sb.toString()) / f;
			return res * (neg ? -1 : 1);
		}

		public boolean ready() throws IOException {
			return br.ready();
		}

		public boolean nextEmpty() throws IOException {
			String s = nextLine();
			st = new StringTokenizer(s);
			return s.isEmpty();
		}
	}
}
