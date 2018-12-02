int n;
		int A[][] = new int[2 * n][2 * n];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++) {
				A[i][j] = sc.nextInt();
				A[i + n][j] = A[i][j];
				A[i][j + n] = A[i][j];
				A[i + n][j + n] = A[i][j];
			}

		for (int i = 0; i < 2 * n; i++)
			for (int j = 0; j < 2 * n; j++) {
				if (i > 0)
					A[i][j] += A[i - 1][j];
				if (j > 0)
					A[i][j] += A[i][j - 1];
				if (i > 0 && j > 0)
					A[i][j] -= A[i - 1][j - 1];
			}

		int maxSubRect = -127 * 100 * 100 * 10;
		for (int i = 0; i < 2 * n; i++)
			for (int j = 0; j < 2 * n; j++)
				for (int k = i; k < Math.min(2 * n, i + n); k++)
					for (int l = j; l < Math.min(2 * n, j + n); l++) {

						int sub = A[k][l];
						if (i > 0)
							sub -= A[i - 1][l];
						if (j > 0)
							sub -= A[k][j - 1];
						if (i > 0 && j > 0)
							sub += A[i - 1][j - 1];
						maxSubRect = Math.max(sub, maxSubRect);

					}
