import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

	static int N;
	static int windS [][];
	static int memo[][];
	static int dp (int layer , int pos) {
		if (pos == N)
			if (layer == 0) return 0;
			else return (int)1e9;
		
		if (memo[layer][pos] != -1) return memo[layer][pos];
		int best = (int)1e9;
		if (layer != 0)
			best = 20 + -1 * windS[layer][pos] + dp(layer - 1 , pos + 1);
		if (layer != 9)
			best = Math.min(best, 60 + -1 * windS[layer][pos] + dp(layer + 1 , pos + 1));
		best = Math.min(best, 30 + -1 * windS[layer][pos] + dp(layer , pos + 1));
		return memo[layer][pos] = best;
	}
	public static void main(String[] args) throws Exception {
		
		StringBuilder sb = new StringBuilder();
		PrintWriter out = new PrintWriter(System.out);
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		while (T -- > 0) {
			int f = sc.nextInt();
			N = f / 100;
			windS = new int[10][N];
			memo = new int[10][N];
			for (int i = 0 ; i <= 9 ; ++ i)
				Arrays.fill(memo[i], -1);
			
			for (int i = 9 ; i >= 0 ; --i)
				for (int j = 0 ; j < N ; ++j)
					windS[i][j] = sc.nextInt();
			
			sb.append(dp(0,0) + "\n\n");
		}
		out.print(sb);
		out.flush();
		out.close();
	}

	
}
