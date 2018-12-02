	public static int matchScore (char x , char y)
	{
		// If we want LCS misMatch = -INF , space = 0 , matchScore = 1

		int spaceScore = -1 , misMatchScore = -1 , matchScore = 2;
		if(x==' ' || y==' ')
			return spaceScore;
		else if(x==y)
			return matchScore;
		else return misMatchScore;
	}


    public static int maxScoreAlignment(char[] x, char[] y) {
        int dp[][] = new int[x.length + 1][y.length + 1];


        dp[0][0] = 0;
        for (int i = 1; i <= x.length; i++)
            dp[i][0] = i * matchScore(' ' , ' ');
        for (int j = 1; j <= y.length; j++)
            dp[0][j] = j * matchScore(' ' , ' ');

        for (int i = 1; i < x.length + 1; ++i)
            for (int j = 1; j < y.length + 1; ++j) {
                int diagonal = dp[i - 1][j - 1] + matchScore(x[i - 1], y[j - 1]);
                int up = dp[i - 1][j] + matchScore(x[i - 1], ' ');
                int left = dp[i][j - 1] + matchScore(' ', y[j - 1]);

                dp[i][j] = Math.max(Math.max(up, left), diagonal);
            }

        for (int i = 0; i < x.length + 1; i++) {
            for (int j = 0; j < y.length + 1; j++)
                System.out.print(dp[i][j] + "  ");
            System.out.println();
        }

        return dp[x.length][y.length];
    }
