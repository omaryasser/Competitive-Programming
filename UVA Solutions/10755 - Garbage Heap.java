import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		int t = sc.nextInt();
		while (t-->0) {
			int x = sc.nextInt(), y = sc.nextInt(), z = sc.nextInt();
			long [][][] arr = new long [x][y][z];
			long [][][] sum = new long [x][y][z];
			for (int i = 0; i < x; i++) {
				for (int j = 0; j < y; j++) {
					for (int k = 0; k < z; k++) {
						arr[i][j][k] = sc.nextLong();
					}
				}
			}
			
			for (int i = 0; i < x; i++) {
				for (int j = 0; j < y; j++) {
					for (int k = 0; k < z; k++) {
						sum[i][j][k] = arr[i][j][k];
						if (i > 0) {
							sum[i][j][k] += sum[i - 1][j][k];
						}
						if (j > 0) {
							sum[i][j][k] += sum[i][j - 1][k];
						}
						if (i > 0 && j > 0) {
							sum[i][j][k] -= sum[i - 1][j - 1][k];
						}
					}
				}
			}
			
			long min = Long.MIN_VALUE;
			for (int x1 = 0; x1 < x; x1++) {
				for (int y1 = 0; y1 < y; y1++) {
					for (int x2 = x1; x2 < x; x2++) {
						for (int y2 = y1; y2 < y; y2++) {
							long cur = 0;
							for (int k = 0; k < z; k++) {
								long here = sum[x2][y2][k];
								if (x1 > 0) {
									here -= sum[x1 - 1][y2][k];
								}
								if (y1 > 0) {
									here -= sum[x2][y1 - 1][k];
								}
								if (x1 > 0 && y1 > 0) {
									here += sum[x1 - 1][y1 - 1][k];
								}
								cur += here;
								min = Math.max(min, cur);
								if (cur < 0) {
									cur = 0;
								}
							}
						}
					}
				}
			}
			
			out.println(min);
			if (t != 0) out.println();
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
