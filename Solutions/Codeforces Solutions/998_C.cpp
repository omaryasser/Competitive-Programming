#include <bits/stdc++.h>
#include <bitset>

#define REP(i,n) for(int i=0;i<(int)n;i++)
#define REP1(i,j,n) for(int i=j;i<(int)n;i++)
#define all(x) x.begin(),x.end()
#define double long double
#define BUG cerr<<"BUG\n";

typedef long long ll;

using namespace std;
int main() {
    ios_base::sync_with_stdio(0); cin.tie(0);

    int n,x,y;
    cin>>n>>x>>y;
    string s;cin>>s;
    int subs=0;
    REP(i,n){
        if(s[i]=='0'){
            subs++;
            int f=-1;
            REP1(j,i,n+1)if(j==n||s[j]!='0'){f=j;break;}
            i=f-1;
        }
    }
    if(x<y)cout<<1ll*x*max(0,(subs-1))+y*min(1,subs)<<"\n";
    else cout<<1ll*subs*y<<"\n";
    return 0;
}