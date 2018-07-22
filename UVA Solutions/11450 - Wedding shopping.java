// Bottom up with memory optimization
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		int tc = sc.nextInt();
		while(tc-->0) {
			int money = sc.nextInt(), n = sc.nextInt();
			int [][] arr = new int[n][];
			boolean [][] can = new boolean[2][money + 1];
			for(int i = 0; i < n; i++){
				int k = sc.nextInt();
				arr[i] = new int[k];
				for(int j = 0; j < k; j++){
					arr[i][j] = sc.nextInt();
				}
			}
			
			for(int j = 0; j < arr[0].length; j++){
				if(arr[0][j] <= money){
					can[0][arr[0][j]] = true;
				}
			}
			
			int cur_parity = 1;
			for(int idx = 1; idx < n; idx++){
				for (int m = 0; m <= money; m++) {
					for(int j = 0; j < arr[idx].length; j++){
						if(arr[idx][j] <= m){
							can[cur_parity][m] |= can[cur_parity ^ 1][m - arr[idx][j]];
						}
					}
				}
				Arrays.fill(can[1 ^ cur_parity], false);
				cur_parity ^= 1;
			}
			
			int max = money;
			while(max >= 1 && !can[cur_parity ^ 1][max]) max--;
			if (max == 0) out.println("no solution");
			else out.println(max);
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

// Bottom up with no optimization

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		int tc = sc.nextInt();
		while(tc-->0) {
			int money = sc.nextInt(), n = sc.nextInt();
			int [][] arr = new int[n][];
			long [][] dp = new long[n][money + 1];
			for(int i = 0; i < n; i++){
				int k = sc.nextInt();
				arr[i] = new int[k];
				Arrays.fill(dp[i], -1);
				for(int j = 0; j < k; j++)
					arr[i][j] = sc.nextInt();
			}
			
			for(int m = 0; m <= money; m++) {
				for(int j = 0; j < arr[0].length; j++) {
					dp[0][m] = arr[0][j] <= m ? Math.max(dp[0][m], arr[0][j]) : dp[0][m];
				}
			}
					
			for(int idx = 1; idx < n; idx++) {
				for(int m = 0; m <= money; m++) {
					for(int j = 0; j < arr[idx].length; j++) {
						if(arr[idx][j] <= m && dp[idx - 1][m - arr[idx][j]] != -1) {
							dp[idx][m] = Math.max(dp[idx][m], arr[idx][j] + dp[idx - 1][m - arr[idx][j]]);
						}
					}
				}
			}
			
			out.println(dp[n - 1][money] == -1 ? "no solution" : dp[n - 1][money]);
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
