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
	
	static Stack<Integer> s;
	static int LIS (ArrayList<Integer> arr) {
		int n = arr.size();
		int []P = new int[n], in = new int[n];
		ArrayList<Integer> L = new ArrayList<>();
		int lis = 0, last = -1;

		for (int i = 0; i < n; i++) {
			int num = arr.get(i);
			int pos = Collections.binarySearch(L, num);
			if (pos < 0) pos = -pos - 1;
			if (pos + 1 > lis) {
				last = i;
				lis++;
				L.add(num);
			}
			else {
				if (pos + 1 == lis)
					last = i;
				L.set(pos, num);
			}
			P[i] = pos > 0 ? in[pos - 1] : -1;
			in[pos] = i;
		}

		s = new Stack<>();
		while(last != -1) {
			s.push(arr.get(last));
			last = P[last];
		}
		return lis;
	}
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		ArrayList<Integer> arr = new ArrayList<>();
		while(sc.ready())
			arr.add(sc.nextInt());
		
		out.println(LIS(arr));
		out.println("-");
		while(!s.isEmpty())
			out.println(s.pop());
			
		out.flush();
		out.close();
	}
	
	
	static class Pair {
		int x, y;
		Pair (int xx, int yy) {x = xx; y = yy;}
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
