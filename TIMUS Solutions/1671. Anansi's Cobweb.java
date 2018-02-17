import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class P1 {

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		int n = sc.nextInt(), m = sc.nextInt();
		Pair [] edges = new Pair[m];
		
		for (int i = 0; i < m; i++)
			edges[i] = new Pair(sc.nextInt() - 1, sc.nextInt() - 1);
		
		
		DS uf = new DS(n);
		
		int Q = sc.nextInt();
		TreeSet<Pair> queries1 = new TreeSet<>();
		Pair [] queries2 = new Pair[m];
		for (int i = 0; i < Q; i++)
		{
			int idx = sc.nextInt() - 1;
			queries1.add(new Pair(edges[idx].x, edges[idx].y));
			queries2[i] = new Pair(edges[idx].x, edges[idx].y);
		}
		
		
		for (int i = 0; i < m; i++)
			if (!queries1.contains(edges[i]))
				uf.union(edges[i].x, edges[i].y);
		
		int [] res = new int[Q];
		for (int i = Q - 1; i >= 0; i--)
		{
			res[i] = uf.numSets;
			uf.union(queries2[i].x, queries2[i].y);
		}
		
		for (int i = 0; i < Q; i++)
			if (i == 0) out.print(res[i]);
			else out.print(" " + res[i]);
		out.println();
		out.flush();
		out.close();
	}
	
	static class Pair implements Comparable<Pair>{
		int x, y;
		Pair (int xx, int yy) {x = xx; y = yy;}
		@Override
		public int compareTo(Pair o) {
			return x == o.x ? y - o.y : x - o.x;
		}
	}
	
	static class DS {
		int sz, numSets;
		int [] p, rank;
		DS (int size) {numSets = size; sz = size; rank = new int[size]; p = new int[size]; for (int i = 0; i < sz; i++) p[i] = i;}
		int find (int idx) {return p[idx] == idx ? idx : (p[idx] = find(p[idx]));}
		void union (int i, int j) {
			int x = find(i), y = find(j);
			if (x == y) return;
			
			numSets--;
			if (rank[x] > rank[y])
			{
				p[y] = x;
			}
			else 
			{
				p[x] = y;
				if (rank[x] == rank[y]) rank[y]++;
			}
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
