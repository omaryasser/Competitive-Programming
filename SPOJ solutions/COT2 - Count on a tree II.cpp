/*
Flatten the tree into an array by tin, tout.
use MO on the given queries.
*/
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

const int N=40000*2+1,LOG=log(N)/log(2)+2,SQRT=sqrt(N),M=100001;
vector<int>T[N];
int tin[N],tout[N],order[N],lvl[N],arr[N],res[M];
int P[LOG][N];
int n,m;
int tim=0;
bool in[N];

unordered_map<int,int>cnt;

struct query{
    int l,r,idx,extra;
    query(int ll,int rr,int i,int e=-1):l(ll),r(rr),idx(i),extra(e){}
    query(){extra=-1;}
}queries[M];

bool compQueries (query q1, query q2){
    int blk1=q1.l/SQRT,blk2=q2.l/SQRT;
    return blk1!=blk2?blk1<blk2:q1.r<q2.r;
}

int LCA(int u,int v){
    if(lvl[v]>lvl[u])
        swap(u,v);
    for(int log=LOG-1;log>=0;log--)
        if((lvl[u]-(1<<log))>=lvl[v])
            u=P[log][u];

    if(u==v)return u;

    for(int log=LOG-1;log>=0;log--)
        if(P[log][u]!=P[log][v])
            u=P[log][u],v=P[log][v];
    return P[0][u];
}

void dfs(int u=0,int p=-1){
    P[0][u]=p;
    order[tim]=u;
    tin[u]=tim++;
    for(int v:T[u]){
        if(v!=p){
            lvl[v]=lvl[u]+1;
            dfs(v,u);
        }
    }
    order[tim]=u;
    tout[u]=tim++;
}

int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    cin>>n>>m;
    REP(i,n)cin>>arr[i];
    REP(i,n-1){
        int u,v;
        cin>>u>>v;
        T[--u].pb(--v);
        T[v].pb(u);
    }

    memset(P,-1,sizeof P);
    dfs();
    REP1(log,1,LOG)REP(i,n)
                       if(P[log-1][i]!=-1)
                           P[log][i]=P[log-1][P[log-1][i]];
    REP(i,m){
        int l,r;
        cin>>l>>r;
        l--,r--;
        int lca=LCA(l,r);
        if(lca==l){
            queries[i].l=tin[l],queries[i].r=tin[r];
            //cerr<<tin[l]<<" "<<tin[r]<<"\n";
        }else if(lca==r){
            queries[i].l=tin[r],queries[i].r=tin[l];
            //cerr<<tin[r]<<" "<<tin[l]<<"\n";
        }else{
            if(tin[l]<tin[r]){
                //cerr<<tout[l]<<" "<<tin[r]<<"\n";
                queries[i].l=tout[l],queries[i].r=tin[r];
                queries[i].extra=lca;
            }else{
                queries[i].l=tout[r],queries[i].r=tin[l];
                queries[i].extra=lca;
            }
        }
        queries[i].idx=i;
    }
    sort(queries,queries+m,compQueries);
    int moR=-1,moL=0;
    int distinct=0;
    REP(i,m){
        int l=queries[i].l,r=queries[i].r;
        //cerr<<l<<" "<<r<<"\n";
        while(moR<r){
            moR++;
            if(!in[order[moR]]){
                cnt[arr[order[moR]]]++;
                if((cnt[arr[order[moR]]])==1)distinct++;
            }else{
                cnt[arr[order[moR]]]--;
                if((cnt[arr[order[moR]]])==0)distinct--;
            }
            //if(l==9)cerr<<moR<<" "<<distinct<<" "<<cnt[arr[order[moR]]]<<"MOR\n";
            in[order[moR]]^=1;

        }
        //cerr<<distinct<<"before\n";

        while(moL>l){
            moL--;
            if(!in[order[moL]]){
                cnt[arr[order[moL]]]++;
                if((cnt[arr[order[moL]]])==1)distinct++;
            }else{
                cnt[arr[order[moL]]]--;
                if((cnt[arr[order[moL]]])==0)distinct--;
            }
            in[order[moL]]^=1;
            //if(l==9)cerr<<moL<<" "<<distinct<<"MOL\n";
        }
        //cerr<<distinct<<"before\n";

        while(moR>r){
            if(!in[order[moR]]){
                cnt[arr[order[moR]]]++;
                if((cnt[arr[order[moR]]])==1)distinct++;
            }else{
                cnt[arr[order[moR]]]--;
                if((cnt[arr[order[moR]]])==0)distinct--;
            }
            in[order[moR]]^=1;
            moR--;
        }
        //cerr<<distinct<<"before\n";

        while(moL<l){
            if(!in[order[moL]]){
                cnt[arr[order[moL]]]++;
                if((cnt[arr[order[moL]]])==1)distinct++;
            }else{
                cnt[arr[order[moL]]]--;
                if((cnt[arr[order[moL]]])==0)distinct--;
            }
            in[order[moL]]^=1;
            //if(l==9)cerr<<moL<<" "<<distinct<<" "<<cnt[arr[order[moL]]]<<"MOL\n";

            moL++;
        }
        //cerr<<distinct<<"before\n";
        if(queries[i].extra!=-1){
            //  cerr<<"sdf\n";
            if(cnt[arr[queries[i].extra]]==0)distinct++;
        }
        res[queries[i].idx]=distinct;
        if(queries[i].extra!=-1){
            if(cnt[arr[queries[i].extra]]==0)distinct--;
        }
    }
    REP(i,m)cout<<res[i]<<"\n";
}
