#include <bits/stdc++.h>
#define REP(i,n) for(int i=0;i<(int)n;i++)
#define REP1(i,j,n) for(int i=j;i<(int)n;i++)
#define all(x) x.begin(),x.end()
#define BUG cerr<<"BUG\n";

typedef long long ll;

using namespace std;

const int n_=12;
int cnt[(1<<n_)+3];
int res[(1<<n_)+3];
char s[n_+3];
vector<pair<int,int> > ress[(1<<n_)+3];
vector<int>cum[(1<<n_)+3];
int main() {
//    ios_base::sync_with_stdio(0); cin.tie(0);

    int n,m,q;
    scanf("%d%d%d",&n,&m,&q);
    vector<int>w(n);
    int all=0;
    REP(i,n)cin>>w[i],all+=w[i];
    for(int msk=0;msk<(1<<n);msk++){
        int sum=0;
        REP(i,n){
            if(msk&(1<<i))
                sum+=w[i];
        }
        res[msk]=all-sum;
    }
    while(m--){
        scanf("%s",s);
        int msk=0;
        REP(i,n)if(s[i]=='1')msk|=(1<<i);
        cnt[msk]++;
    }
    for(int msk=0;msk<(1<<n);msk++){
        for(int msk2=0;msk2<(1<<n);msk2++){
            ress[msk].push_back(make_pair(res[msk2^msk],cnt[msk2]));
        }
    }
    for(int msk=0;msk<(1<<n);msk++)sort(all(ress[msk]));
    for(int msk=0;msk<(1<<n);msk++){
        cum[msk]=vector<int>(ress[msk].size());
        REP(i,ress[msk].size()){
            cum[msk][i]=ress[msk][i].second+(i?cum[msk][i-1]:0);
        }
    }

    while(q--){
        scanf("%s",s);
        int msk=0;
        REP(i,n)if(s[i]=='1')msk|=(1<<i);
        int k;
        scanf("%d",&k);
        int lo=0,hi=(int)ress[msk].size()-1,bst=-1;
        while(lo<=hi){
            int m=lo+hi>>1;
            if(ress[msk][m].first<=k){
                bst=m;
                lo=m+1;
            }else hi=m-1;
        }
        printf("%d\n",bst==-1?0:cum[msk][bst]);
    }
}