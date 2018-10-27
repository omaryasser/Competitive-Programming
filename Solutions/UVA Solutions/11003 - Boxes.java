// BOTTOM UP
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

		while(true) {
			int n = sc.nextInt();
			if (n == 0) break;
			int [] w = new int [n], l = new int[n];
			
			for (int i = 0; i < n; i++) {
				w[i] = sc.nextInt();
				l[i] = sc.nextInt();
			}
			
			int MAX_L = 3002;
			int [][] dp = new int[n][MAX_L];
			for (int i = 0; i < MAX_L; i++)
				dp[n - 1][i] = w[n - 1] <= i ? 1 : 0;
			
			for (int i = n - 2; i >= 0; i--) {
				for (int j = 0; j < MAX_L; j++) {
					dp[i][j] = dp[i + 1][j];
					if (w[i] <= j) {
						dp[i][j] = Math.max(dp[i][j], 1 + dp[i + 1][Math.min(l[i], j == 3001 ? 3001 : j - w[i])]);
					}
				}
			}
			
			out.println(dp[0][MAX_L - 1]);
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

// TOP DOWN
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int n;
	static int[] arr, max;
	static int[][] memo;

	static int dp(int idx, int can) {
		if (idx == n)
			return 0;
		if (memo[idx][can] != -1)
			return memo[idx][can];

		int take = 0, leave = dp(idx + 1, can);
		if (arr[idx] <= can) {
			int newOne = can == 3001 ? can : can - arr[idx];
			take = 1 + dp(idx + 1, Math.min(newOne, max[idx]));
		}
		return memo[idx][can] = Math.max(take, leave);
	}

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		while (true) {
			n = sc.nextInt();
			if (n == 0)
				break;
			arr = new int[n];
			max = new int[n];
			for (int i = 0; i < n; i++) {
				arr[i] = sc.nextInt();
				max[i] = sc.nextInt();
			}

			memo = new int[n][3002];
			for (int i = 0; i < n; i++)
				Arrays.fill(memo[i], -1);
			out.println(dp(0, 3001));
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
