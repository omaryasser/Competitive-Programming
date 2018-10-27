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

		int n = Integer.parseInt(sc.nextLine());
		while (sc.ready()) {
			StringTokenizer st = new StringTokenizer(sc.nextLine());
			int[] map = new int[n + 1];
			for (int i = 0; i < n; i++)
				map[i + 1] = Integer.parseInt(st.nextToken());

			while (sc.ready()) {
				st = new StringTokenizer(sc.nextLine());
				
				if (st.countTokens() == 1) {
					n = Integer.parseInt(st.nextToken());
					break;
				}

				int[] arr = new int[n + 1];
				int score = 0;
				for (int i = 0; i < n; i++) {
					arr[Integer.parseInt(st.nextToken())] = i + 1;
					if (i == map[arr[i]])
						score++;
				}
				score = 0;
				int res = 0;
				int[] lis = new int[n];
				for (int i = 0; i < n; i++) {
					lis[i] = 1;
					for (int j = 0; j < i; j++) {
						if (map[arr[j + 1]] < map[arr[i + 1]])
							lis[i] = Math.max(lis[i], 1 + lis[j]);
					}
					res = Math.max(res, lis[i]);
				}

				score += res;
				out.println(score);
			}
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
