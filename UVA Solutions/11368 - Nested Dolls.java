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

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		int t = sc.nextInt();
		while(t-- > 0) {
			int n = sc.nextInt();
			Pair [] dolls = new Pair[n];
			for (int i = 0; i < n; i++)
				dolls[i] = new Pair (sc.nextInt(), sc.nextInt());
			Arrays.sort(dolls, Collections.reverseOrder());
			
			ArrayList<Integer> L = new ArrayList<>();
			for (int i = 0; i < n; i++) {
				int pos = binary_search(L, dolls[i].h);
				if (pos == L.size())
					L.add(dolls[i].h);
				else 
					L.set(pos, dolls[i].h);
			}
			
			out.println(L.size());
		}
		
		out.flush();
		out.close();
	}
	
	static int binary_search (ArrayList<Integer> L, int num) {
		if (L.size() == 0) return 0;
		int res = L.size(), lo = 0, hi = L.size() - 1;
		for (int i = 0; i < 17; i++) {
			int mid = lo + ((hi - lo) >> 1);
			if (L.get(mid) > num) {
				res = mid;
				hi = Math.max(lo, mid - 1);
			}
			else 
				lo = Math.min(hi, mid + 1);
		}
		return res;
	}
	
	static class Pair implements Comparable<Pair> {
		int w, h;
		Pair(int ww, int hh) {
			w = ww;
			h = hh;
		}
		
		@Override
		public int compareTo(Pair o) {
			return w == o.w ? o.h - h : w - o.w;
		}
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
