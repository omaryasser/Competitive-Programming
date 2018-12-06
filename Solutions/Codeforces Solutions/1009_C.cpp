#include <bits/stdc++.h>
#include <bitset>

#define REP(i,n) for(int i=0;i<(int)n;i++)
#define REP1(i,j,n) for(int i=j;i<(int)n;i++)
#define all(x) x.begin(),x.end()
#define BUG cerr<<"BUG\n";

typedef long long ll;

using namespace std;


int main(){
    ios_base::sync_with_stdio(0);cin.tie(0);
    int n,m;
    cin>>n>>m;

    vector<int>lft(n),rght(n);
    int j=n/2;
    REP1(i,1,n){
        rght[n-i-1]=rght[n-i]+1;
    }
    REP(i,n)lft[i]=abs(i-j);
    ll res=0;
    ll adL=0,adR=0;
    REP(i,m){
        int x,d;
        cin>>x>>d;
        res+=1ll*x*n;
        if(d>0)adL+=d;
        else adR+=d;
    }
    REP(i,n){
        res+=1ll*rght[i]*adL+adR*lft[i];
    }
    cout<<fixed<<setprecision(10)<<1.0*res/n<<"\n";
}