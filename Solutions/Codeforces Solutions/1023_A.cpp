#include <bits/stdc++.h>

#define REP(i, n) for(int i=0;i<(int)n;i++)
#define REP1(i, j, n) for(int i=j;i<(int)n;i++)
#define all(x) x.begin(),x.end()
#define BUG cerr<<"BUG\n";

typedef long long ll;

using namespace std;

int main()
{
    ios_base::sync_with_stdio(0); cin.tie(0);

    int n, m;
    cin >> n >> m;
    string a, b;
    cin >> a >> b;
    if(a.find("*") == -1)
    {
    	cout << (a == b ? "YES\n" : "NO\n");
    }
    else
	{
		int idx = a.find("*");
		REP(i, idx)
		{
			if(a[i] != b[i] || i == m)
			{
				cout << "NO\n";
				return 0;
			}
		}
		for(int i = n - 1, j = m - 1; i > idx; i--, j--){
			if(j == idx - 1 || a[i] != b[j])
			{
				cout << "NO\n";
				return 0;
			}
		}
		cout << "YES\n";
	}
}