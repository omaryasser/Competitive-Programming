import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class A {
	static long gauss(BitSet[] a, int n, int m) {
		long res = 1;
//		for (BitSet b : a) System.err.println(b);
		for (int col = 0, row = 0; col < m - 1 && row < n; ++col) {
			for (int i = row; i < n; ++i)
				if (a[i].get(col)) {
					BitSet tmp = a[i];
					a[i] = a[row];
					a[row] = tmp;
					break;
				}
			if (!a[row].get(col)) {
				res <<= 1L;
				continue;
			}
			
			for (int i = 0; i < n; ++i)
				if (i != row && a[i].get(col))
					a[i].xor(a[row]);
			++row;
		}
		return res - 1;
	}

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		int tc = sc.nextInt();
		while(tc-- > 0) {
			int n = sc.nextInt();
			HashMap<Integer, Integer> primeMap = new HashMap<>();
			int MAX = 500;
			boolean isPrime[] = new boolean[MAX];
			Arrays.fill(isPrime, true);
			isPrime[0] = isPrime[1] = false;
			for (int i = 2; i * i < MAX; i++)
				if (isPrime[i])
					for (int j = i * i; j < MAX; j += i)
						isPrime[j] = false;
			for (int i = 2; i < MAX; i++)
				if (isPrime[i]) 
					primeMap.put(i, primeMap.size());
			
			BitSet[] arr = new BitSet[primeMap.size()];
			for (int i = 0; i < primeMap.size(); i++) arr[i] = new BitSet();
			for (int i = 0; i < n; i++) {
				long num = sc.nextLong();
				for (Map.Entry<Integer, Integer> map : primeMap.entrySet()) {
					int prime = map.getKey();
					int cnt = 0;
					while (num % prime == 0) {
						num /= prime;
						cnt++;
					}
					cnt %= 2;
					if (cnt == 1) 
						arr[map.getValue()].set(i);
				}
			}
			out.println(gauss(arr, primeMap.size(), n + 1));
		}
		
		out.flush();
	}

	static class Scanner {
		StringTokenizer st;
		BufferedReader br;

		public Scanner(InputStream s) {
			br = new BufferedReader(new InputStreamReader(s));
		}

		public Scanner(FileReader f) {
			br = new BufferedReader(f);
		}

		public String next() {
			while (st == null || !st.hasMoreTokens())
				try {
					st = new StringTokenizer(br.readLine());
				} catch (Exception e) {
				}
			return st.nextToken();
		}

		public double nextDouble() throws IOException {
			return Double.parseDouble(next());
		}

		public int nextInt() {
			return Integer.parseInt(next());
		}
		public long nextLong() {
			return Long.parseLong(next());
		}

	}
}
