// LCS for only 2 sequences
static char [] first , second;
    static int memo[][];
    public static int LCS (int i , int j )
    {
        if (i == first.length || j == second.length) return 0;
        if (memo[i][j] != -1) return memo[i][j];

        if (first[i] == second[j]) return memo[i][j] = 1 + LCS(i + 1 , j + 1);

        return memo[i][j] = Math.max(LCS(i + 1 , j) , LCS(i , j + 1));
    }

// LCS for more than one sequence

    int n =sc.nextInt() , rows = sc.nextInt();
			int arr [][] = new int [rows+1][n+1];
			for(int i = 1 ; i <= rows ; i ++)
				for (int j = 1 ; j <= n ; j++)
					arr[i][j]=sc.nextInt();
			
			// position[x][y]=z means the position of the number y in row x is z
			
			int pos [][] = new int[rows+1][n+1];
			for(int i =1 ; i <= rows ; i ++)
			{
				int k = 1 ;
				for (int j = 1 ; j <= n ; j++)
					pos[i][arr[i][j]]=k++;
			}
			
			int max = 1;
			int dp [] = new int [n+1];
			Arrays.fill(dp, 1);
			for (int i =1 ; i <= n ; i ++)
			{
				int tempMax = 1;
				for (int j = 1 ; j < i ; j++)
				{
					int  tempRow;
					for( tempRow =2 ; tempRow <=rows ; tempRow++){
							
						if(pos[tempRow][arr[1][j]]>pos[tempRow][arr[1][i]])
							break;
					}
				if(tempRow == rows+1 )
					tempMax = Math.max(tempMax, dp[j]+1);
				}
				dp[i]= tempMax;
				max= Math.max(max, dp[i]);
			}
			
			System.out.println(max);
