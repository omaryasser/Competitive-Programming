import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class P1 {

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		int tc = sc.nextInt();
		while (tc-->0)
		{
			int n = sc.nextInt(), k = sc.nextInt();
			DS uf = new DS(n);
			
			int res = 0;
			while(k-->0)
			{
				int t = sc.nextInt(), x = sc.nextInt() - 1, y = sc.nextInt() - 1;
				if (x >= n || y >= n || (t == 2 && x == y)) res++;
				else if (!uf.merge(x, y, t - 1)) res++;
			}
			out.println(res);
		}
		out.flush();
		out.close();
	}
	
	
	static class DS {
		int sz;
		int [] p, R; // R[i] = relationship between i and rep[i]
		
		DS (int size) {sz = size; p = new int [sz]; for (int i = 0; i < sz; i++) p[i] = i; R = new int[sz];}
		
		int find (int idx) {
			if (p[idx] == idx) return idx;
			int old_parent = p[idx];
			p[idx] = find(p[idx]);
			R[idx] = R[idx] + R[old_parent];
			return p[idx];
		}
		
		boolean merge (int i, int j, int t)
		{
			int x = find(i), y = find(j);
			if (x == y)
			{
				if (((((R[i] - R[j]) % 3) + 3) % 3) != t) return false;
			}
			else
			{
				p[x] = y;
				R[x] = (((-R[i] + t + R[j]) % 3) + 3) % 3;
			}
			return true;
		}
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
