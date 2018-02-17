import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class A {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		while(true) {
			int n = 301;
			int m = sc.nextInt();
			if (m == 0) break;
			ArrayList<Pair> []adjList = new ArrayList[n];
			for (int i = 0; i < n; i++)
				adjList[i] = new ArrayList<>();
			int MAX = (int)1e3 + 10;
			int sum[] = new int[n];

			for (int i = 0; i < m; i++) {
				int u = sc.nextInt(), v = sc.nextInt(), p = 1;
				if ((u == 300 || u <= 290) && (v == 300 || v <= 290)) {
					adjList[u].add(new Pair(v, p));
					adjList[v].add(new Pair(u, p));
				}
				sum[u]++;
				sum[v]++;
			}
			int s = 0, E = 300;
			double[][] dp = new double[n][MAX];
			dp[s][0] = 1;
			for (int steps = 1; steps < MAX; steps++) {
				for (int node = 0; node < n; node++) {
					dp[node][steps] = 0;
					for (Pair nxt : adjList[node]) {
						if (nxt.to != 300)
							dp[node][steps] += ((1.0 * nxt.pref) / sum[nxt.to]) * dp[nxt.to][steps - 1];
					}
				}
			}
			
			double res = 0;
			for (int steps = 1; steps < MAX; steps++)
				res += (dp[E][steps]);
			out.printf("%.3f\n", res);
		}
		
		out.flush();
	}
	
	static class Pair {
		int to, pref;
		Pair (int t, int p) {to = t; pref = p;}
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

        public int nextInt() {
            return Integer.parseInt(next());
        }

    }
}
