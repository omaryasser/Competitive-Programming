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

const int N=3e5+10;
int open[N],close[N];

int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    int n;
    cin>>n;
    vector<string>s(n);
    int good=0;
    REP(i,n){
        cin>>s[i];
        int o=0,c=0,d=0;
        REP(j,s[i].size()){
            if(s[i][j]=='(')o++,d++;
            else {
                o--;
                if(o<0)c=max(c,-o),o=0;
                d--;
            }
        }
        if(o>0&&c)continue;
        if(!o&&!c)good++;
        else if(o>0)open[o]++;
        else {

            close[-d]++;
        }
    }
    ll res=1ll*good*(good-1)+good;
    REP(i,N){
        res+=1ll*open[i]*close[i];
    }
    cout<<res<<"\n";
}