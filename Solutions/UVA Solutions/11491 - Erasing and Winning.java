import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class P1 {
	
	public static void main(String[] args) throws Exception 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		StringBuilder sb = new StringBuilder();
		
		while (true)
		{
			int n = sc.nextInt(), d = sc.nextInt();
			int tmpD = d;
			if (n == 0 && d == 0) break;
			
			char [] arr = sc.next().toCharArray();
			TreeSet<Integer> [] treeSets = new TreeSet[10];
			for (int i = 0; i < 10; i++) treeSets[i] = new TreeSet<>();
			
			for (int i = 0; i < n; i++)
				treeSets[arr[i] - '0'].add(i);
			
			StringBuilder res = new StringBuilder();
			int idx = 0;
			while (idx < n)
			{
				boolean f = false;
				for (int i = 9; i > arr[idx] - '0'; i--)
				{
					Integer nxt = treeSets[i].higher(idx);
					if (nxt != null && nxt.intValue() - idx <= d)
					{
						d -= (nxt.intValue() - idx);
						res.append((char)('0' + i));
						idx = nxt.intValue() + 1;
						f = true;
						break;
					}
				}
				if (!f)
				{
					res.append(arr[idx]);
					idx++;
				}
			}
			
			for (int i = 0; i < n - tmpD; i++)
				sb.append(res.charAt(i));
			sb.append("\n");
		}
		
		out.print(sb);
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
