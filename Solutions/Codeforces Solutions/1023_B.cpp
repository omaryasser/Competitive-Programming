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

    ll n, k;
    cin >> n >> k;
    if(n + n - 1 < k)
	{
		cout << "0\n";
	}
	else
	{
    	ll all = (k - 1) / 2ll;
    	all -= max(0ll, k - 1 - n);
    	cout << all << "\n";
    }
}