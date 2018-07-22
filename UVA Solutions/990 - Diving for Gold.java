import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter (System.out);
		
		boolean first = true;
		while (sc.ready()) {
			int total = sc.nextInt();
			int w = sc.nextInt() * 3;
			
			int n = sc.nextInt();
			int [] time = new int[n], g = new int[n];
			for (int i = 0; i < n; i++) {
				time[i] = sc.nextInt() * w;
				g[i] = sc.nextInt();
			}
			
			int [][] dp = new int[n][total + 1];
			
			for (int i = 0; i <= total; i++)
				dp[0][i] = time[0] <= i ? g[0] : 0;
				
			for (int i = 1; i < n; i++) {
				for (int j = 0; j <= total; j++) {
					dp[i][j] = Math.max(dp[i - 1][j], time[i] <= j ? g[i] + dp[i - 1][j - time[i]] : 0);
				}
			}
			
			int tmp = total;
			Stack<Integer> res = new Stack<>();
			int idx = n - 1;
			while (true) {
				if (idx == 0) {
					if (time[0] <= total)
						res.push(0);
					break;
				}
				if (dp[idx][total] > dp[idx - 1][total]) {
					res.push(idx);
					total -= time[idx];
				}
				idx--;
			}
			
			if (first) first = false;
			else out.println();
			
			out.println(dp[n - 1][tmp]);
			out.println(res.size());
			while(!res.isEmpty())
				out.println(time[res.peek()] / w + " " + g[res.pop()]);
			
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
		
		public Scanner(FileReader s) {
			br = new BufferedReader(s);
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
