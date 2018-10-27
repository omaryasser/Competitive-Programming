import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {


	static int T [][];
	static int n;
	static int pre [];
	static int post [];
	static Integer in [];
	static boolean [][] is_less;
	static boolean visited [];
	static int idx = 0;
	static int search (ArrayList<Integer> arr) {
		int root = pre[idx];
		visited[root] = true;
		ArrayList<Integer> less = new ArrayList<>();
		ArrayList<Integer> greater = new ArrayList<>();
		for (int i = 0; i < arr.size(); i++)
			if (!visited[arr.get(i)]) {
				if (is_less[arr.get(i)][root]) less.add(arr.get(i));
				else greater.add(arr.get(i));
			}

//		System.out.println(root + " " + idx + " " + less + " " + greater);
		if (less.size() > 0) {
			idx++;
			T[root][0] = search(less);
		}
		if (greater.size() > 0) {
			idx++;
			T[root][1] = search(greater);
		}
		return root;
	}
	
	static boolean ok (int node) {
		boolean res = true;
//		System.out.println(node + " " + T[node][0] + " " + T[node][1]);
		if (T[node][0] != -1) {
			res &= ok(T[node][0]);
		}
		if (T[node][1] != -1) {
			res &= ok (T[node][1]);
		}
		return res & (post[idx++] == node);
	}
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		n = sc.nextInt();
		pre = new int[n];
		post = new int[n];
		in = new Integer[n];
		visited = new boolean[n];
		is_less = new boolean[n][n];
		
		T = new int[n][2];
		for (int i = 0; i < n; i++)
			T[i][0] = T[i][1] = -1;
		
		for (int i = 0; i < n; i++)
			pre[i] = sc.nextInt() - 1;
		for (int i = 0; i < n; i++)
			post[i] = sc.nextInt() - 1;
		for (int i = 0; i < n; i++)
			in[i] = sc.nextInt() - 1;
		
		for (int i = 0; i < n; i++)
			for (int j = i + 1;  j < n; j++)
				is_less[in[i]][in[j]] = true;
		
		search(new ArrayList<Integer>(Arrays.asList(in)));
//		for (int i  =0; i < n; i++)
//			out.println((i + 1) + " " + (T[i][0] + 1) + " " + (T[i][1] + 1));
		
		idx = 0;
		out.println(ok(pre[0]) ? "yes" : "no");
		
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
