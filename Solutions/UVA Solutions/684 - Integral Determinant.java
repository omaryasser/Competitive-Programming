import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.StringTokenizer;

public class Main {
	static long gauss(double[][] a) {
		int n = a.length;
		int m = n;
		long sign = 1;
		for (int col = 0, row = 0; col < m && row < n; ++col) {
			int sel = row;
			for (int i = row; i < n; ++i)
				if (Math.abs(a[i][col]) > Math.abs(a[sel][col]))
					sel = i;
			if (Math.abs(a[sel][col]) < 1e-9)
				return 0;
			for (int i = col; i < m; ++i) {
				double tmp = a[sel][i];
				a[sel][i] = a[row][i];
				a[row][i] = tmp;
			}
			if (sel != row) 	
				sign *= -1;

			for (int i = 0; i < n; ++i)
				if (i != row) {
					double c = a[i][col] / a[row][col];
					for (int j = col; j < m; ++j)
						a[i][j] -= a[row][j] * c;
				}
			++row;
		}

		double res = a[0][0];
		for (int i = 1; i < n; i++)
			res *= a[i][i];
		return (long)Math.round(res) * sign;
	}

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		while(true) {
			int n = sc.nextInt();
			if (n == 0) {
				out.println("*");
				break;
			}
			else {
				double[][] a = new double[n][n];
				for (int i = 0; i < n; i++) for (int j = 0; j < n; j++) a[i][j] = sc.nextDouble();
				out.println(gauss(a));
			}
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
		public double nextDouble() throws IOException { return Double.parseDouble(next()); }

		public int nextInt() {
			return Integer.parseInt(next());
		}

	}
}
