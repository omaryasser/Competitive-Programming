#include <bits/stdc++.h>
#include <bitset>

#define REP(i,n) for(int i=0;i<(int)n;i++)
#define REP1(i,j,n) for(int i=j;i<(int)n;i++)
#define all(x) x.begin(),x.end()
#define double long double
#define BUG cerr<<"BUG\n";

typedef long long ll;

using namespace std;

const int N=5e5+10;
int SQRT;
int n,qq,goodIdx;
int a[N],res[N],cnt[N];
bool isGood[N],inArr[N];
int good[N<<1];

struct query{
    int l,r,idx;
    bool operator<(query q){
        return l/SQRT!=q.l/SQRT?l/SQRT<q.l/SQRT:(l/SQRT&1)?r<q.r:r>q.r;
    }
};
vector<query>queries;

inline void add(int idx){
    int x=a[idx];
    cnt[x]++;
    if(cnt[x]==1&&!inArr[x]){inArr[x]=1;good[goodIdx++]=x;}
}
inline void rem(int idx){
    int x=a[idx];
    cnt[x]--;
    if(cnt[x]==1&&!inArr[x]){inArr[x]=1;good[goodIdx++]=x;}
}

int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    cin>>n;
    SQRT=max(1,(int)sqrt(n));
    REP(i,n)cin>>a[i];
    cin>>qq;
    REP(i,qq){
        query q;
        cin>>q.l>>q.r;
        q.idx=i;
        q.l--,q.r--;
        queries.push_back(q);
    }
    sort(all(queries));
    int mor=-1,mol=0;
    REP(i,qq){
        int l=queries[i].l,r=queries[i].r;
        while(mor<r)mor++,add(mor);
        while(mol>l)mol--,add(mol);
        while(mor>r)rem(mor),mor--;
        while(mol<l)rem(mol),mol++;
        res[queries[i].idx]=0;
        while(goodIdx){
            if(cnt[good[goodIdx-1]]==1){
                res[queries[i].idx]=good[goodIdx-1];
                break;
            }else{
                inArr[good[--goodIdx]]=0;
            }
        }
    }
    REP(i,qq)cout<<res[i]<<"\n";
}