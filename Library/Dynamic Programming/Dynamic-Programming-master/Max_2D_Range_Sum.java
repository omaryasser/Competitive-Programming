// O (N ^ 3)

int A [][] = new int[n][n];
        for (int i = 0 ; i < n ; ++i)
            for (int j = 0 ; j < n ; ++j)
                if (j > 0) A[i][j] = sc.nextInt() + A[i][j - 1];
                else A[i][j] = sc.nextInt();

        int maxSubRec = Integer.MIN_VALUE;
        for (int l = 0 ; l < n ; ++l)
            for (int r = l ; r < n ; ++r)
            {
                int maxSub = 0;
                for (int row = 0 ; row < n ; ++row)
                {
                    if (l > 0) maxSub += A[row][r] - A[row][l - 1];
                    else maxSub += A[row][r];
                }
                if (maxSub < 0) maxSub = 0;
                maxSubRec = Math.max(maxSubRec , maxSub);
            }


// O (N ^ 4)
int N =sc.nextInt();
		int A [][] = new int [N][N];
		for(int i =0 ; i < N ; i ++)
			for(int j =0 ; j < N ; j++)
			{
				A[i][j]=sc.nextInt();
				if(i>0)A[i][j]+=A[i-1][j];
				if(j>0)A[i][j]+=A[i][j-1];
				if(i>0 && j >0)A[i][j]-=A[i-1][j-1];
			}
			
		int maxSubRect = Integer.MIN_VALUE;
		for(int i =0 ; i < N ;i ++)
			for(int j =0 ; j < N ;j ++)
				for(int k =i ; k < N ; k++)
					for(int l =j ;l <N ; l++)
					{
						int sub = A[k][l];
						if(i>0)sub-=A[i-1][l];
						if(j>0)sub-=A[k][j-1];
						if(i>0 && j>0)sub+=A[i-1][j-1];
						maxSubRect= Math.max(sub, maxSubRect);
					}
