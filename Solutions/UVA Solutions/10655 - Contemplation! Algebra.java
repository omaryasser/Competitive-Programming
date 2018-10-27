import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

public class Main {

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		StringBuilder sb = new StringBuilder();

		while (true) {
			StringTokenizer st = new StringTokenizer(sc.nextLine());
			if (st.countTokens() < 3) break;
			long p = Integer.parseInt(st.nextToken()) , q = Integer.parseInt(st.nextToken()) , n = Integer.parseInt(st.nextToken());
			if (n == 0) sb.append(2 + "\n");
			else if (n == 1) sb.append(p + "\n");
			else {
				long mat [][] = {{2 , p} , {1 , 1}};
				long transform [][] = {{0 , - q} , {1 , p}};
				sb.append(multiply(mat, matPow(transform, n - 1))[0][1] + "\n");
			}
		}

		out.print(sb);
		out.flush();
		out.close();
	}

	static long[][] zero(int n, int m) {
		return new long[n][m];
	}

	static long[][] identity(int n) { // Always square matrix
		long[][] x = zero(n, n);
		for (int i = 0; i < x.length; i++)
			x[i][i]++;
		return x;
	}

	static long matrixTrace(long[][] a) { // sumOfDigonal Values
		long ret = 0;
		for (int i = 0; i < a.length; i++)
			ret += a[i][i];
		return ret;
	}

	static long[][] rotate(long[][] v) { // rotate clockwise
		long[][] r = zero(v[0].length, v.length);
		for (int i = 0; i < r.length; i++) {
			for (int j = 0; j < r.length; j++) {
				r[j][v.length - 1 - i] = v[i][j];
			}
		}
		return r;
	}

	static long[][] transpose(long[][] v) { // rotate clockwise
		long[][] r = zero(v[0].length, v.length);
		for (int i = 0; i < r.length; i++) {
			for (int j = 0; j < r.length; j++) {
				r[j][i] = v[i][j];
			}
		}
		return r;
	}

	static long[][] reflect(long[][] v) { // Reflect Horizontally
		long[][] r = zero(v.length, v[0].length);
		for (int i = 0; i < r.length; i++) {
			for (int k = 0; k < r[i].length; k++) {
				r[i][v[0].length - 1 - k] = v[i][k];
			}
		}
		return r;
	}

	static long[][] add(long[][] a, long[][] b) {
		long[][] ret = zero(a.length, a[0].length);
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[i].length; j++) {
				ret[i][j] = a[i][j] + b[i][j];
			}
		}
		return ret;
	}

	static long[][] addIdentity(long[][] a) {
		for (int i = 0; i < a.length; i++) {
			a[i][i]++;
		}
		return a;
	}

	static long[][] multiply(long[][] a, long[][] b) {
		long[][] ret = zero(a.length, b[0].length);
		// Optimizations here not to enter the last loop of a[i][k] = 0
		// Also we can swtich last two loops if b[k][j] has more zeros

		// for MOD optimization we use the following
		for (int i = 0; i < a.length; ++i) {
			for (int k = 0; k < a[0].length; ++k) {

				for (int j = 0; j < b[0].length; ++j) {
					ret[i][j] += a[i][k] * b[k][j];
				}
			}

		}
		return ret;
	}

	static long[][] matPow(long[][] a, long k) { // N^3 LOG
		if (k == 0)
			return identity(a.length);
		if (k % 2 == 1) {
			return multiply(a, matPow(a, k - 1));
		}
		return matPow(multiply(a, a), k >> 1);
	}

	static long[][] sumPows(long[][] a, long k) { // Log
		if (k == 0) {
			return zero(a.length, a.length);
		}
		if (k % 2 == 1) {
			return multiply(a, addIdentity(sumPows(a, k - 1)));
		}
		return multiply(sumPows(a, k >> 1L), addIdentity(matPow(a, k >> 1L)));
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
