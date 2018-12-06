#include <bits/stdc++.h>

typedef long long ll;

using namespace std;

ll sq (ll x) {return x * x;}
int main()
{
	ios_base::sync_with_stdio(0); cin.tie(0);

	int tc;
	cin >> tc;
	while(tc--)
    {
        int n;
        cin >> n;
        map<int,int> mp;
        for(int i = 0; i < n; i++)
        {
            int x;
            cin >> x;
            mp[x]++;
        }
        vector<int> v;
        for(auto p : mp)
        {
            if(p.second == 1)
            {
//                mp.erase(p.first);
            }
            else
            {
                int cnt = p.second;
                while(cnt--) v.push_back(p.first);
            }
        }
        ll up = 1e9, down = -1e9;
        ll a = -1, b = -1;
        for(int i = 0; i < (int)v.size() - 3; i++)
        {
            if(v[i] == v[i + 1] && v[i + 2] == v[i + 3])
            {
//                cerr << v[i] << " " << v[i + 2] << "\n";
                ll up1 = (sq(2ll * v[i] + 2 * v[i + 2]));
                ll down1 = 1ll * v[i] * v[i + 2];
                if(up * down1 >= up1 * down)
                {
                    up = up1;
                    down = down1;
                    a = v[i];
                    b = v[i + 2];
                }
            }
        }
        cout << a << " " << a << " " << b << " " << b << "\n";
    }
}