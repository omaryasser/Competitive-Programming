#include <bits/stdc++.h>
#include <bitset>

#define REP(i,n) for(int i=0;i<(int)n;i++)
#define REP1(i,j,n) for(int i=j;i<(int)n;i++)
#define all(x) x.begin(),x.end()
#define double long double
#define BUG cerr<<"BUG\n";

typedef long long ll;

using namespace std;


const int N=2e3+100;
ll mem[N];
ll arr[N];
int n;
ll MOD=998244353;
ll comb[N][N];
ll nCr(int n,int k){
    if(n<k)return 0;
    if(k==0||k==n)return 1;
    if(comb[n][k]!=-1)return comb[n][k];
    if(n-k<k)return comb[n][k]=nCr(n,n-k);
    return comb[n][k]=(nCr(n-1,k-1)+nCr(n-1,k))%MOD;
}
ll dp(int idx){
    if(idx==n)return 1;
    if(arr[idx]<=0)return 0;
    ll &ret=mem[idx];
    if(ret!=-1)return ret;

    if(arr[idx]<=0)return 0;
    if(idx+arr[idx]>=n)return 0;
    ret=0;
    REP1(i,idx+arr[idx]+1,n+1){
        ll go=nCr(i-idx-1,arr[idx])*(dp(i))%MOD;
        ret+=go;
        while(ret>=MOD)ret-=MOD;
    }
    return ret;
}

int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    memset(comb,-1,sizeof comb);
    cin>>n;
    REP(i,n)cin>>arr[i];
    ll res=0;
    memset(mem,-1,sizeof mem);
    for(int i=n-1;i>=0;i--){
        res+=dp(i);
        while(res>=MOD)res-=MOD;
    }
    cout<<res<<"\n";
}