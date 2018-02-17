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
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		int t = sc.nextInt();
		while (t-- > 0) {
			int n = sc.nextInt();
			int [][] arr = new int[n][n];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					arr[i][j] = sc.nextInt();
					if (j > 0) arr[i][j] += arr[i][j - 1];
 				}
			}
			
			int res = 0, cur = 0;
			for (int l = 0; l < n; l++) for (int r = l; r < n; r++) {
				for (int s = 0; s < n; s++) {
					cur = 0;
					for (int row = s; row < s + n; row++) {
						int curRow = row % n;
						cur += arr[curRow][r] - (l == 0 ? 0 : arr[curRow][l - 1]);
						if (cur < 0) {
							cur = 0;
						}
						
						res = Math.max(res, cur);
					}
				}
			}
			
			for (int l = 0; l < n; l++) for (int r = l; r < n; r++) {
				for (int s = 0; s < n; s++) {
					cur = 0;
					for (int row = s; row < s + n; row++) {
						int curRow = row % n;
						cur += (l == 0 ? 0 : arr[curRow][l - 1]) + (arr[curRow][n - 1] - arr[curRow][r]);
						if (cur < 0) {
							cur = 0;
						}
						
						res = Math.max(res, cur);
					}
				}
			}
			out.println(res);
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
