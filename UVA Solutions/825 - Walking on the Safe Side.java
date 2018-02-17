import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {

	
	public static void main(String[] args) throws Exception {
		
		StringBuilder sb = new StringBuilder();
		PrintWriter out = new PrintWriter(System.out);
		Scanner sc = new Scanner(System.in);
		StringTokenizer st = new StringTokenizer(sc.nextLine());
		int T = Integer.parseInt(st.nextToken());
		boolean first = true;
		while (T -- > 0) {
			
			String line ;
			if ((line = sc.nextLine()) == null) break;
			st = new StringTokenizer(sc.nextLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			boolean bad [][] = new boolean[N][M];
			for (int i = 0 ; i < N ; ++i)
			{
				st = new StringTokenizer(sc.nextLine());
				int row = Integer.parseInt(st.nextToken());
				while (st.hasMoreTokens())
					bad[row - 1][Integer.parseInt(st.nextToken()) - 1] = true;
			}
			
			int dp [][] = new int[N][M];
			dp[0][0] = 1;
			for (int i = 0 ; i < N ; ++i) {
				for (int j = 0 ; j < M ; ++j) {
					if (i == 0 && j == 0) continue;
					if (bad[i][j]) continue;
					if (i > 0) dp[i][j] += dp[i - 1][j];
					if (j > 0) dp[i][j] += dp[i][j - 1];
				}
			}
			if (first) first = false;
			else sb.append("\n");
			sb.append(dp[N - 1][M - 1] + "\n");
		}
		out.print(sb);
		out.flush();
		out.close();
	}
	
	
}
