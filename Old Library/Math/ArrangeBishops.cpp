int cells (int i) {
	if (i & 1)
		return i / 4 * 2 + 1;
	else
		return (i - 1) / 4 * 2 + 2;
}
int n, k; // input data
if (k> 2 * n-1) {
        cout << 0;
        return 0;
}

vector <vector <int> > d (n * 2, vector <int> (k + 2));
for (int i = 0; i <n * 2; ++i)
        d [i] [0] = 1;
d [1] [1] = 1;
for (int i = 2; i <n * 2; ++i)
        for (int j = 1; j <= k; ++j)
                d [i] [j] = d [i-2] [j] + d [i-2] [j-1] * (cells (i) - j + 1);

int ans = 0;
for (int i = 0; i <= k; ++i)
        ans + = d [n * 2-1] [i] * d [n * 2-2] [ki];
cout << ans;
