#include <bits/stdc++.h>
#include <bitset>

#define REP(i,n) for(int i=0;i<(int)n;i++)
#define REP1(i,j,n) for(int i=j;i<(int)n;i++)
#define all(x) x.begin(),x.end()
#define double long double
#define BUG cerr<<"BUG\n";

typedef long long ll;

using namespace std;


int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    int n,m;
    cin>>n>>m;
    n++;
    vector<int>vv(n);
    REP(i,n-1)cin>>vv[i];
    vector<ll>v(n),good(n),bad(n);
    v[0]=vv[0];
    REP1(i,1,n-1)v[i]=vv[i]-vv[i-1];
    v[n-1]=m-vv[n-2];
    ll res=0;
    REP(i,n)if(i%2==0)res+=v[i];
    good[0]=v[0];
    REP1(i,1,n){
        if(i&1)good[i]=good[i-1];
        else good[i]=v[i]+good[i-1];
    }
    if((n-1)&1)bad[n-1]=v[n-1];
    for(int i=n-2;i>=0;i--){
        if(i&1)bad[i]=v[i]+bad[i+1];
        else bad[i]=bad[i+1];
    }
    REP(i,n){
        if(i%2==0&&(i+1>=n-1||vv[i]!=vv[i+1])){
            res=max(res,good[i]+bad[i]-1);
        }
    }
    if(vv[0]!=1){
        res=max(res,vv[0]-1+bad[0]);
    }
    cout<<res<<"\n";
}