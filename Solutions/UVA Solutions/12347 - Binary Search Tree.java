import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

public class Main {

	static TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>();
	static TreeMap<Integer, Integer> map2 = new TreeMap<Integer, Integer>();
	static int T [][];
	static int n;
	static ArrayList<Integer> res = new ArrayList<>();
	
	static void go (int cur) {
		if (T[cur][0] != -1) go(T[cur][0]);
		if (T[cur][1] != -1) go(T[cur][1]);
		
		res.add(map2.get(cur));
	}
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		ArrayList<Integer> arr = new ArrayList<>();
		T = new int[10001][2];
		for (int i = 0; i < 10001; i++)
			Arrays.fill(T[i], -1);
		TreeSet<Integer> treeSet = new TreeSet<>();
		int idx = 0;
		int root = 0;
		while (sc.ready()) {
			int x = sc.nextInt();
			arr.add(x);
			map.put(x, idx++);
			map2.put(idx - 1, x);
			Integer f ;
			if ((f = treeSet.higher(x)) != null && T[map.get(f)][0] == -1) {
				T[map.get(f)][0] = map.get(x);
			}
			else if ((f = treeSet.lower(x)) != null){
				T[map.get(f)][1] = map.get(x);
			}
			treeSet.add(x);
		}
		
		go(0);
		for (int x : res)
			out.println(x);
		
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
