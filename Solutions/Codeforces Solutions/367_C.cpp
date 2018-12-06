#include <bits/stdc++.h>
#include <bitset>

#define REP(i,n) for(int i=0;i<(int)n;i++)
#define REP1(i,j,n) for(int i=j;i<(int)n;i++)
#define pb(x) push_back(x)
#define fi first
#define se second
#define all(x) x.begin(),x.end()
#define double long double
#define mp(x,y) make_pair(x,y)

typedef long long ll;

using namespace std;



int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    int n,m;
    cin>>n>>m;
    vector<int>v;
    REP(i,m){
        int dum,c;
        cin>>dum>>c;
        v.pb(c);
    }
    sort(all(v));
    reverse(all(v));

    ll res=v[0];
    if(n>=2&&m>=2)res=v[0]+v[1];
    REP1(i,3,min(m+1,n+1)){
        ll need;
        if(i&1){
            need=(1ll*i*(i-1))/2+1;
        }else{
            need=(1ll*i*(i-1))/2+(i-2)/2+1;
        }
        if(need<=n)res+=v[i-1];
    }
    cout<<res<<"\n";
}