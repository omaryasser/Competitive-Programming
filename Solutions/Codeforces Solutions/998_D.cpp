#include <bits/stdc++.h>
#include <bitset>

#define REP(i,n) for(int i=0;i<(int)n;i++)
#define REP1(i,j,n) for(int i=j;i<(int)n;i++)
#define all(x) x.begin(),x.end()
#define double long double
#define BUG cerr<<"BUG\n";

typedef long long ll;

using namespace std;
int main() {
    ios_base::sync_with_stdio(0); cin.tie(0);

    ll res=0;
    ll n;
    cin>>n;
    vector<int> mn(49, 1e8);
    REP (i, 49) {
        REP (j, 49) {
            mn[(4 * i + 9 * j) % 49]=min(mn[(4 * i + 9 * j) % 49], i + j);
        }
    }
    REP (i, 49) {
        res += max(0ll, n - mn[i] + 1);
    }
    cout<<res<<endl;
    return 0;
}