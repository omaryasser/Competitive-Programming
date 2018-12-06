#include <bits/stdc++.h>
#include <bitset>

#define REP(i,n) for(int i=0;i<(int)n;i++)
#define REP1(i,j,n) for(int i=j;i<(int)n;i++)
#define all(x) x.begin(),x.end()
#define double long double
#define BUG cerr<<"BUG\n";

typedef long long ll;

using namespace std;

const int N=1e5+10;
int strt[N],endd[N];

int main() {
    ios_base::sync_with_stdio(0); cin.tie(0);

    int n;
    vector<int>v(n);
    cin>>n;
    REP(i,n)cin>>v[i];
    memset(strt,-1,sizeof strt);
    memset(endd,-1,sizeof endd);
    vector<int>ends;

    for(int i=n-1;i>=0;i--){
        if(endd[v[i]]==-1){
            ends.push_back(i);
            endd[v[i]]=i;
        }
    }

    sort(all(ends));
    ll res=0;
    REP(i,n){
        if(strt[v[i]]==-1){
            strt[v[i]]=i;
            res+=ends.end()-upper_bound(all(ends),i);
        }
    }
    cout<<res<<"\n";
    return 0;
}