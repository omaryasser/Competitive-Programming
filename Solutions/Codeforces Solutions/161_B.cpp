#include <bits/stdc++.h>
#include <bitset>

#define REP(i,n) for(int i=0;i<(int)n;i++)
#define REP1(i,j,n) for(int i=j;i<(int)n;i++)
#define pb(x) push_back(x)
#define fi first
#define se second
#define all(x) x.begin(),x.end()
#define mp(x,y) make_pair(x,y)

typedef long long ll;

using namespace std;

vector<pair<int,int> >s,p;
int n,k;
bool ok[1001];

int main() {
    #ifndef ONLINE_JUDGE
    freopen("in.txt","r",stdin);
    #endif     

    scanf("%d%d",&n,&k);
    REP(i,n){
        int x,t;scanf("%d%d",&x,&t);
        if(--t)p.pb(mp(x,i));
        else s.pb(mp(x,i));
    }
    
    sort(all(s));
    vector<vector<pair<int,int> > >v(k);
    int i=k-1;
    for(int j=s.size()-1;i>=0;i--){
        if(j<0)break;
        if(j>=0){
            v[i].pb(s[j]);
            ok[i]=1;
            j--;
        }
        if(!i){
            while(j-->=0)v[0].pb(s[j+1]),ok[0]=1;
        }
    }


    int j=p.size()-1;
    while(i>=0){
        v[i].pb(p[j--]);
        i--;
    }
    while(j-->=0)v[0].pb(p[j+1]);
    double r=0;
    for(int i=0;i<k;i++){
        vector<pair<int,int> >vv=v[i];
        sort(all(vv));
        if(ok[i])r+=vv[0].fi/2.0;
        else r+=vv[0].fi;
        REP1(j,1,vv.size())
            r+=vv[j].fi;
    }
    printf("%.1lf\n", r);
    for(auto vv:v){
        printf("%d\n", vv.size());
        for(auto x:vv)printf("%d\n", x.se+1);
    }
    return 0;
}