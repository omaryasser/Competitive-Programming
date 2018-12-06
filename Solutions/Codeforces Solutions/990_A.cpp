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

    //freopen("in.txt","r",stdin);

    ll n,m,a,b;
    cin>>n>>m>>a>>b;
    cout<<min((n%m)*b,(m-(n%m))*a)<<"\n";

}