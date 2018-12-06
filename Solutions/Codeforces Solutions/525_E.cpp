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
ll S;
const int N=26;
ll a[N];
ll f[19];
unordered_map<ll,int>mp[15];
int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    f[0]=1;
    REP1(i,1,19)
        f[i]=f[i-1]*i;
    
    cin>>n>>k>>S;
    REP(i,n)cin>>a[i];

    int fbts=min(n,13),sbts=n-fbts;
    int pw=1;
    REP(i,sbts)pw*=3;

    REP(xx,pw){
        int msk=xx;
        int cntO=0;
        ll sum=0;
        REP(i,sbts){
            int bit=msk%3;
            msk/=3;
            if(bit==1)sum+=a[i+fbts];
            else if(bit==2){
                if(a[i+fbts]>18)sum=S+1;
                else sum+=f[a[i+fbts]];
                cntO++;
            }
        }
        if(cntO>k||sum>S)continue;
        mp[cntO][sum]++;
    }

    pw=1;
    REP(i,fbts)pw*=3;
    ll res=0;
    REP(xx,pw){

        int msk=xx;
        int cntO=0;
        ll sum=0;
        REP(i,fbts){
            int bit=msk%3;
            msk/=3;
            if(bit==1)sum+=a[i];
            else if(bit==2){
                if(a[i]>18)sum=S+1;
                else sum+=f[a[i]];
                cntO++;
            }
        }

        if(cntO>k||sum>S)continue;
        
        REP(i,min(15,k-cntO+1)){
            res+=mp[i][S-sum];
        }

    }
    cout<<res<<"\n";
}