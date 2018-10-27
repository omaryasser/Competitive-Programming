import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class DP {

	static int[] p, w;
	static int N, maxWeight;
	static int[][] memo;
	
	static int dp(int item, int remW)		//Top-down Approach
	{
		if(item == N)
			return 0;
		if(memo[item][remW] != -1)
			return memo[item][remW];
		
		int take = 0, leave = dp(item + 1, remW);
		if(w[item] <= remW)
			take = p[item] + dp(item + 1, remW - w[item]);
		return memo[item][remW] = Math.max(take, leave);
	}
	
	static int dp2()						//Bottom-up Approach
	{
		int[][] dp = new int[N+1][maxWeight + 1];
		
		//base cases
		for(int i = 0; i <= maxWeight; ++i)
			dp[N][i] = 0;
		
		for(int remW = 1; remW <= maxWeight; ++remW)
			for(int item = N - 1; item >= 0; --item)
			{
				int take = 0;
				int leave = dp[item+1][remW];
				if(w[item] <= remW)
					take = dp[item+1][remW-w[item]] + p[item];
				dp[item][remW] = Math.max(take, leave);
			}
		return dp[0][maxWeight];
	}
	
}