import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static ArrayList<Pair> [][] adjList;
	static int [] x, y, z;
	
	static int [][] memo;
	
	static int dp (int i, int j) {
		if (memo[i][j] != -1)
			return memo[i][j];
		
		int res = j == 0 ? z[i] : j == 1 ? y[i] : x[i];
		int alone = res;
		for (Pair node : adjList[i][j])
			res = Math.max(res, alone + dp(node.x, node.y));
		
		return memo[i][j] = res;
	}
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		for (int tc = 1;; tc++) {
			int nn = sc.nextInt();
			int n = nn * 3;
			if (n == 0) break;
			
			adjList = new ArrayList[n][3];
			for (int i = 0; i < n; i++)
				for (int j = 0; j < 3; j++)
					adjList[i][j] = new ArrayList<>();
			
			x = new int[n]; y = new int[n]; z = new int[n];
			memo = new int[n][3];
			for (int i = 0; i < n; i++)
				Arrays.fill(memo[i], -1);
			
			for (int i = 0; i < nn; i++) {
				x[i] = sc.nextInt();
				y[i] = sc.nextInt();
				z[i] = sc.nextInt();
				
				x[nn + i] = x[i];
				y[nn + i] = z[i];
				z[nn + i] = y[i];

				x[nn * 2 + i] = y[i];
				y[nn * 2 + i] = z[i];
				z[nn * 2 + i] = x[i];
			}
			
			// 0 -> x, y 
			// 1 -> x, z
			// 2 -> y, z
			
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (i == j) continue;
					// 0
					if ((x[i] < x[j] && y[i] < y[j]) || (x[i] < y[j] && y[i] < x[j])) {
						adjList[i][0].add(new Pair(j, 0));
					}
					if ((x[i] < x[j] && y[i] < z[j]) || (x[i] < z[j] && y[i] < x[j])) {
						adjList[i][0].add(new Pair(j, 1));
					}
					if ((x[i] < y[j] && y[i] < z[j]) || (x[i] < z[j] && y[i] < y[j])) {
						adjList[i][0].add(new Pair(j, 2));
					}
					
					// 1
					if ((x[i] < x[j] && z[i] < y[j]) || (x[i] < y[j] && z[i] < x[j])) {
						adjList[i][1].add(new Pair(j, 0));
					}
					if ((x[i] < x[j] && z[i] < z[j]) || (x[i] < z[j] && z[i] < x[j])) {
						adjList[i][1].add(new Pair(j, 1));
					}
					if ((x[i] < y[j] && z[i] < z[j]) || (x[i] < z[j] && z[i] < y[j])) {
						adjList[i][1].add(new Pair(j, 2));
					}
					
					// 2
					if ((z[i] < x[j] && y[i] < y[j]) || (z[i] < y[j] && y[i] < x[j])) {
						adjList[i][2].add(new Pair(j, 0));
					}
					if ((z[i] < x[j] && y[i] < z[j]) || (z[i] < z[j] && y[i] < x[j])) {
						adjList[i][2].add(new Pair(j, 1));
					}
					if ((z[i] < y[j] && y[i] < z[j]) || (z[i] < z[j] && y[i] < y[j])) {
						adjList[i][2].add(new Pair(j, 2));
					}
				}
			}
			int res = 0;
			for (int i = 0; i < n; i++)
				for (int j = 0; j < 3; j++)
					res = Math.max(res, dp(i, j));
			out.printf("Case %d: maximum height = %d\n", tc, res);
		}
		
		out.flush();
		out.close();
	}
	
	
	static class Pair {
		int x, y;
		Pair (int xx, int yy) {x = xx; y = yy;}
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
