public static void shuffle(long[] a)
	{
		int n = a.length;
		for(int i = 0; i < n; i++)
		{
			int r = i + (int)(Math.random() * (n - i));
			long tmp = a[i];
			a[i] = a[r];
			a[r] = tmp;
		}
	}


public static void shuffle(int[] a)
	{
		int n = a.length;
		for(int i = 0; i < n; i++)
		{
			int r = i + (int)(Math.random() * (n - i));
			int tmp = a[i];
			a[i] = a[r];
			a[r] = tmp;
		}
	}
