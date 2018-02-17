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
		
		int T = sc.nextInt();
		while (T-->0)
		{
			int trees_num = sc.nextInt(), doctors_num = sc.nextInt(), dist_trees = sc.nextInt(), dist_doc = sc.nextInt();
			Doctor []doctors = new Doctor[doctors_num];
			Tree [] trees = new Tree[trees_num];
			
			int ALL = trees_num;
			for (int i = 0; i < doctors_num; i++)
				doctors[i] = new Doctor(ALL++, sc.nextInt(), sc.nextInt());
			
			for (int i = 0; i < trees_num; i++)
			{
				int cnt = sc.nextInt();
				int [] x = new int[cnt], y = new int[cnt];
				for (int j = 0; j < cnt; j++)
				{
					x[j] = sc.nextInt();
					y[j] = sc.nextInt();
				}
				
				trees[i] = new Tree(i, x, y);
			}
			
			DS uf = new DS(ALL);
			
			for (int i = 0; i < trees_num; i++)
			{
				for (int j = i + 1; j < trees_num; j++)
				{
					if (can(trees[i], trees[j], dist_trees))
						uf.union(trees[i].idx, trees[j].idx);
				}
			}

			
			for (int i = 0; i < trees_num; i++)
				for (int j = 0; j < doctors_num; j++)
					if (can(trees[i], new Tree(doctors[j].idx, new int [] {doctors[j].x}, new int [] {doctors[j].y}), dist_doc))
						uf.union(trees[i].idx, doctors[j].idx);

			boolean ok = false;
			for (int j = 0; j < doctors_num; j++)
				ok |= (uf.find(0) == uf.find(doctors[j].idx));
			
			if (ok) out.println("Tree can be saved :)");
			else out.println("Tree can't be saved :(");
		}
		
		out.flush();
		out.close();
	}
	
	static int dist_sq (int x1, int y1, int x2, int y2) {
		return (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);
	}
	static boolean can (Tree t1, Tree t2, int d)
	{
		d *= d;
		for (int i = 0; i < t1.x.length; i++)
			for (int j = 0; j < t2.x.length; j++)
				if (dist_sq(t1.x[i], t1.y[i], t2.x[j], t2.y[j]) <= d)
					return true;
		
		return false;
	}

	static class DS
	{
		int sz;
		int [] rank, p;
		
		DS(int size) {sz = size; rank = new int[sz]; p = new int[sz]; for (int i = 0; i < sz; i++) p[i] = i;}
		
		int find (int idx) {return p[idx] == idx ? idx : (p[idx] = find(p[idx]));}
		
		void union (int i, int j)
		{
			int x = find(i), y = find(j);
			if (x == y) return;
			
			if (rank[x] > rank[y])
				p[y] = x;
			else
			{
				p[x] = y;
				if (rank[x] == rank[y]) rank[y]++;
			}
		}
	}
	static class Tree
	{
		int []x, y;
		int idx;
		Tree (int ii, int []xx, int []yy) {x = xx; y = yy; idx = ii;}
	}
	
	static class Doctor
	{
		int idx, x, y;
		Doctor(int ii, int xx, int yy) {idx = ii; x = xx; y = yy;}
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
