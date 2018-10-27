import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class P1 {
	
	static int [] dx = {0, 0, 1, -1}, dy = {1, -1, 0, 0};
	static int n, m;
	static boolean [] row, col;
	
	
	static boolean valid (int x, int y)
	{
		return x >= 0 && y >= 0 && x < n && y < m;
	}
	
	static boolean dfs (int x, int y)
	{
		if (!row[x] && !col[y]) return true;
		
		for (int k = 0; k < 4; k++)
		{
			int nx = x + dx[k], ny = y + dy[k];
			if (valid(nx, ny) && !row[nx] && !col[ny]) return true;
		}
		
		return false;
	}
	public static void main(String[] args) throws Exception 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		for (int T = 1;; T++)
		{
			n = sc.nextInt();
			m = sc.nextInt();
			row = new boolean[n];
			col = new boolean[m];
			
			int N = sc.nextInt();
			if (n == 0 && m == 0 && N == 0) break;
			
			while(N-->0)
			{
				row[sc.nextInt()] = true;
				col[sc.nextInt()] = true;
			}
			
			int x = sc.nextInt(), y = sc.nextInt();
			if (dfs(x, y)) out.printf("Case %d: Escaped again! More 2D grid problems!\n", T);
			else out.printf("Case %d: Party time! Let's find a restaurant!\n", T);
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
		
		public boolean nextEmpty() throws IOException
		{
			String s = nextLine();
			st = new StringTokenizer(s);
			return s.isEmpty();
		}
	}
}
