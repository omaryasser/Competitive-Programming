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


int n,k;
const int N=5001;
int a[N];
int h[N];
int cnt[(int)1e6];
int cntCards[(int)1e6];
ll mem[501][11*501];

ll dp(int players,int cards){
    cards=min(cards,players*k);
    if(!players||!cards)return 0;
    ll &ret=mem[players][cards];
    if(ret!=-1)return ret;

    ret=0;
    REP(give,min(cards+1,k+1)){
        ret=max(ret,h[give]+dp(players-1,cards-give));
    }
    return ret;
}
int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    cin>>n>>k;
    REP(i,n*k){
        cin>>a[i];
        cntCards[a[i]]++;
    }
    REP(i,n){
        int x;
        cin>>x;
        cnt[x]++;
    }
    REP1(i,1,k+1)cin>>h[i];
    ll res=0;
    memset(mem,-1,sizeof mem);
    REP(i,1e6){
        res+=dp(cnt[i],cntCards[i]);
    }
    cout<<res<<"\n";
}