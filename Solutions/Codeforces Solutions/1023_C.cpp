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

    int n, k;
    string s;
    cin >> n >> k >> s;
    k /= 2;
    string res = "";
    int needed_neg = 0, needed = k;
    REP(i, n)
	{
		if(s[i] == '(' && needed)
		{
			res += "(";
			needed--;
			needed_neg++;
		}
		if(s[i] == ')' && needed_neg)
		{
			res += ")";
			needed_neg--;
		}
    }
    cout << res << "\n";
}