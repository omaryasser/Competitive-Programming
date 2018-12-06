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

int n,m,k;
int MOD=1000000007;
const int N=1001;
int res[N][N];

int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    cin>>n>>m>>k;    
    if(k>500)cout<<"0\n";
    else{
        res[0][n]=1;
        REP(i,k){
            ll add=0,all=0;
            for(int j=n;j>=0;j--){
                if(j+2<=n)add+=res[i][j+2];
                if(add>=MOD)add-=MOD;

                all+=add;
                if(all>=MOD)all%=MOD;

                res[i+1][j]+=all;
                if(res[i+1][j]>=MOD)res[i+1][j]-=MOD;
            }
        }
        ll f=0;
        REP1(i,1,n+1){
            f+=res[k][i];
            if(f>=MOD)f-=MOD;
        }

        memset(res,0,sizeof res);
        res[0][m]=1;
        REP(i,k){
            ll add=0,all=0;
            for(int j=m;j>=0;j--){
                if(j+2<=m)add+=res[i][j+2];
                if(add>=MOD)add-=MOD;

                all+=add;
                if(all>=MOD)all%=MOD;

                res[i+1][j]+=all;
                if(res[i+1][j]>=MOD)res[i+1][j]-=MOD;
            }
        }
        ll s=0;
        REP1(i,1,m+1){
            s+=res[k][i];
            if(s>=MOD)s-=MOD;
        }
        // cerr<<f<<" "<<s<<"\n";
        cout<<f*s%MOD<<"\n";
    }
}