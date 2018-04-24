
const double EPS = (1e-10);
const int NO_SOLUTION = -10, UNIQUE = 1, INFINITE_SOLUTION = 10;

bool isZero(double c){return abs(c) < EPS;}

bool IsInvalidEqu(int c, vector<double> row) {
	// 0x+0y = 10 is invalid equ
	for(;c < (int)row.size()-1;++c) {
		if(!isZero(row[c]))
			return false;
	}
	return isZero(row.back()) ? false : true;
}


/*
// E.g. Solve: x + y = 3	x - y = 7
vector<vector<double>> mat(2);
mat[0] = {1,  1, 3};
mat[1] = {1, -1, 7};

int sol = solveLinerEqu(mat);	=> UNIQUE
=> mat[0][2] = 5, mat[1][2] = -2
*/
int GaussElim(vector<vector<double> > & mat){
	int n = mat.size(), m = mat[0].size()-1;
	int status = UNIQUE;

	for(int r = 0, c = 0; r < n && c < m; ++r, ++c){
		int stable_r = r;	// partial pivoting
		for(int j = r + 1; j < n; ++j)
			if(abs(mat[j][c]) > abs(mat[stable_r][c]))
				stable_r = j;
		if(stable_r != r)
			swap(mat[stable_r], mat[r]);
		else if(isZero(mat[r][c])){
			if(IsInvalidEqu(c, mat[r]))
				return NO_SOLUTION;
			--r, status = INFINITE_SOLUTION;	// c-th variable can be anything
			continue;
		}
		for(int j = m; j >= c; --j) // convert diagonal to 1
			mat[r][j] /= mat[r][c];

		// zero all my column, except current row. Optimize for spare matrix
		for(int k = 0; k < n; ++k) if(k != r && !isZero(mat[k][c])) {
			for(int j = m; j >= c; --j)	// Add to kth-row: -mat[k][c] * mat[r] to zero it
				mat[k][j] -= mat[k][c] * mat[r][j];
		}
	}	// watch out from solutions -0.0000000001
	// To compute rank, see how many non zero equations
	return status;
}


int gauss (vector < vector<double> > a, vector<double> & ans) {
	int n = (int) a.size();
	int m = (int) a[0].size() - 1;

	vector<int> where (m, -1);
	for (int col=0, row=0; col<m && row<n; ++col) {
		int sel = row;
		for (int i=row; i<n; ++i)
			if (abs (a[i][col]) > abs (a[sel][col]))
				sel = i;
		if (abs (a[sel][col]) < EPS)
			continue;
		for (int i=col; i<=m; ++i)
			swap (a[sel][i], a[row][i]);
		where[col] = row;

		for (int i=0; i<n; ++i)
			if (i != row) {
				double c = a[i][col] / a[row][col];
				for (int j=col; j<=m; ++j)
					a[i][j] -= a[row][j] * c;
			}
		++row;
	}

	ans.assign (m, 0);
	for (int i=0; i<m; ++i)
		if (where[i] != -1)
			ans[i] = a[where[i]][m] / a[where[i]][i];
	for (int i=0; i<n; ++i) {
		double sum = 0;
		for (int j=0; j<m; ++j)
			sum += ans[j] * a[i][j];
		if (abs (sum - a[i][m]) > EPS)
			return 0;
	}

	for (int i=0; i<m; ++i)
		if (where[i] == -1)
			return INF;
	return 1;
}

// %2
int gauss (vector < bitset<N> > a, int n, int m, bitset<N> & ans) {
	vector<int> where (m, -1);
	for (int col=0, row=0; col<m && row<n; ++col) {
		for (int i=row; i<n; ++i)
			if (a[i][col]) {
				swap (a[i], a[row]);
				break;
			}
		if (! a[row][col])
			continue;
		where[col] = row;

		for (int i=0; i<n; ++i)
			if (i != row && a[i][col])
				a[i] ^= a[row];
		++row;
	}
