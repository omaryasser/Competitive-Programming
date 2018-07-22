import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {

	static double [] arr;
	static int n;
	static TreeMap<Pair, Double> memo;
	static double wanted;
	static double dp (int idx, double soFar) {
		if (idx == n) {
			if (soFar > 50) {
				return (wanted / soFar) * 100;
			}
			else 
				return 0;
		}
		
		Pair cur = new Pair (idx, soFar);
		if (memo.containsKey(cur)) {
			return memo.get(cur);
		}
		double take = dp (idx + 1, soFar + arr[idx]);
		double leave = dp (idx + 1, soFar);
		
		memo.put(cur, Math.max(take, leave));
		return Math.max(take, leave);
	}
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		while (true) {
			n = sc.nextInt() - 1;
			int x = sc.nextInt();
			if (n == -1) break;
			
			memo = new TreeMap<>();
			arr = new double[n];
			int idx = 0;
			for (int i = 0; i < n + 1; i++) {
				double num = sc.nextDouble();
				if (i == x - 1) {
					wanted = num;
				}
				else  {
					arr[idx++] = num;
				}
			}
			
			
			out.printf("%.2f\n", dp(0,wanted));
		}
		
		out.flush();
		out.close();
	}
	
	static class Pair implements Comparable<Pair>{
		int idx;
		double soFar;
		
		Pair (int i, double s) {
			idx = i;
			soFar = s;
		}
		
		@Override
		public int compareTo(Pair o) {
			return idx == o.idx ? (soFar - o.soFar) < 0 ? -1 : soFar - o.soFar > 0 ? 1 : 0 : idx - o.idx;
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
