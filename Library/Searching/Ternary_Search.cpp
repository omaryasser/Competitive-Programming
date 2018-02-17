int left = 0 , right = n - 1;
	while (right - left > 3){
		int g = left + (right - left) / 3;
		int h = left + 2 * (right - left) / 3;
		if (f (g) <= f (h)){
			right = h;
		}
		else  left = g;
	}

	int ans = left;
	FOR1(i , left + 1 , left + 1 + 3){
		if (f(i) < f (ans)){
			ans = i ;
		}
	}
