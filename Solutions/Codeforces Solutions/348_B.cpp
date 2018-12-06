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

int n;
const int N=1e5+10;
vector<int>T[N];
int a[N];

ll gcd(ll a,ll b){
    return !b?a:gcd(b,a%b);
}

ll LCM(ll a,ll b){
    double x=(1.0)*a*b/gcd(a,b);
    if(x>1e13+1)return -1;
    return a*(b/gcd(a,b));
}

bool can(ll a,ll b){
    return 1.0*a*b<=1e13+1;
}

pair<ll,ll> dfs(int u,int p){
    if(a[u]!=0){
        return mp(1,a[u]);
    }
    ll lcm=1;
    ll mn=-1;
    int cnt=0;
    for(auto v:T[u])
        if(v!=p){
            cnt++;
            pair<ll,ll>p=dfs(v,u);
            if(p.se==0)return mp(1,0);
            lcm=LCM(lcm,p.fi);
            if(mn==-1)mn=p.se;
            else mn=min(mn,p.se);
            if(lcm==-1)return mp(1,0);
        }
    return mp(lcm*cnt,lcm*(mn/lcm)*cnt);
}

int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    cin>>n;
    ll sm=0;
    REP(i,n){
        cin>>a[i];
        sm+=a[i];
    }
    REP(i,n-1){
        int u,v;
        cin>>u>>v;
        T[--u].pb(--v);
        T[v].pb(u);
    }
    cout<<sm-dfs(0,-1).se<<"\n";
}