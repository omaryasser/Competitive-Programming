#include <bits/stdc++.h>

#define REP(i, n) for(int i=0;i<(int)n;i++)
#define REP1(i, j, n) for(int i=j;i<(int)n;i++)
#define all(x) x.begin(),x.end()
#define BUG cerr<<"BUG\n";

typedef long long ll;

using namespace std;

string print(int r1, int c1, int r2, int c2)
{
	cout << "? " << r1 << " " << c1 << " " << r2 << " " << c2 << "\n";
	cout.flush();
	string res;
	cin >> res;
	return res;
}
int main()
{
	ios_base::sync_with_stdio(0); cin.tie(0);
	int n;
	cin >> n;
	int row = 1, col = 1;
	string res = "";
	while(n - (row + 1) + n - col >= n - 1)
	{
		if(col + 1 <= n && print(row, col + 1, n, n) == "YES") {
			res += "R";
			col++;
		}
		else
		{
			res += "D";
			row++;
		}
	}

	string res2 = "";
	row = n, col = n;
	while((int)res.size() + (int)res2.size() != 2 * n - 2)
	{
		if(row -1 && print(1, 1, row - 1, col) == "YES") {
			res2 += "D";
			row--;
		}
		else
		{
			col--;
			res2 += "R";
		}
	}
	reverse(all(res2));
	cout << "! " << res + res2 << "\n";
	cout.flush();
}