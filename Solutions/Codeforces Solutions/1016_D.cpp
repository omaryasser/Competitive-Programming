#include <bits/stdc++.h>
#define REP(i,n) for(int i=0;i<(int)n;i++)
#define REP1(i,j,n) for(int i=j;i<(int)n;i++)
#define all(x) x.begin(),x.end()
#define BUG cerr<<"BUG\n";

typedef long long ll;

using namespace std;

const int n_=101;
int res[n_][n_];
int main() {
    ios_base::sync_with_stdio(0); cin.tie(0);
    int n, m;
    cin >> n >> m;
    vector<int>r(n),c(m);
    int allr=0,allc=0;
    REP(i,n)cin>>r[i],allr^=r[i];
    REP(i,m)cin>>c[i],allc^=c[i];
    REP(i,n)REP(j,m)res[i][j]=0;
    REP1(i,1,n)res[i][0]=r[i];
    REP1(j,2,m)res[0][j]=c[j];
    res[0][0]=c[0]^(allr^r[0]);
    res[0][1]=r[0]^allc^c[0]^c[1]^res[0][0];
    REP(i,n){
        int rr=0;
        REP(j,m)rr^=res[i][j];
        if(rr^=r[i]){
            cout<<"NO\n";
            return 0;
        }
    }
    REP(j,m){
        int cc=0;
        REP(i,n){
           cc^=res[i][j];
        }
        if(cc!=c[j]){
            cout<<"NO\n";
            return 0;
        }
    }
    cout<<"YES\n";
    REP(i,n){
        REP(j,m){
            if(j)cout<<" ";
            cout<<res[i][j];
        }
        cout<<"\n";
    }
}