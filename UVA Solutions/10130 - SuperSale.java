import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int w [];
	static int p [];
	static int memo[][];
	static int dp (int pos , int rem) {
		if (rem < 0) return (int) - 1e7;
		if (pos == N) return 0;
		if (memo[pos][rem] != -1) return memo[pos][rem];
		int take = p[pos] + dp(pos + 1, rem - w[pos]);
		int leave = dp(pos + 1, rem);
		return memo[pos][rem] = Math.max(take, leave);
	}
	public static void main(String[] args) throws Exception {
		
		StringBuilder sb = new StringBuilder();
		PrintWriter out = new PrintWriter(System.out);
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		while (T -- > 0) {
			N = sc.nextInt();
			w = new int[N];
			p = new int[N];
			memo = new int[10001][31];
			for (int i = 0 ; i < 10001 ; ++ i)
				Arrays.fill(memo[i], -1);
			for (int i = 0 ; i < N ; ++i) {
				p[i] = sc.nextInt();
				w[i] = sc.nextInt();
			}
			int G = sc.nextInt();
			int ans = 0;
			while (G -- > 0)
				ans += dp (0 , sc.nextInt());
			sb.append(ans + "\n");
		}
		out.print(sb);
		out.flush();
		out.close();
	}
	static class Scanner {
		BufferedReader br ;
		StringTokenizer st;
		
		Scanner (InputStream s){
			br = new BufferedReader(new InputStreamReader(s));
		}
		String next () throws Exception{
			while (st == null || !st.hasMoreTokens())
				st = new StringTokenizer(br.readLine());
			return st.nextToken();
		}
		
		int nextInt () throws Exception{
			return Integer.parseInt(next());
		}
	}
}
