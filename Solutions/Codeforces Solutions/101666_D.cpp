#include <bits/stdc++.h>
#include <bitset>

#define REP(i,n) for(int i=0;i<(int)n;i++)
#define REP1(i,j,n) for(int i=j;i<(int)n;i++)
#define all(x) x.begin(),x.end()
#define double long double
#define BUG cerr<<"BUG\n";

typedef long long ll;

using namespace std;

int MOD=1e9+7;

void ADD(int &a,int b){
    a+=b;
    if(a>=MOD)a-=MOD;
}
int MULT(int a,int b){
    return (int)(1ll*a*b%MOD);
}

struct ST{
    vector<int>tree,lazy;
    int n;
    void init(int n){
        this->n=n;
        tree.assign(n<<1<<1,0);
        lazy.assign(n<<1<<1,0);
    }

    void propagate(int u,int s,int m,int e){
        if(lazy[u]){
            ADD(tree[u<<1],lazy[u]);
            ADD(tree[u<<1|1],lazy[u]);
            ADD(lazy[u<<1],lazy[u]);
            ADD(lazy[u<<1|1],lazy[u]);
            lazy[u]=0;
        }
    }
    void update(int u,int s,int e,int l,int r,int ad){
        if(s>=l&&e<=r){
            ADD(tree[u],ad);
            ADD(lazy[u],ad);
        }else if(s>r||e<l);
        else{
            int m=s+e>>1;
            propagate(u,s,m,e);
            update(u<<1,s,m,l,r,ad);
            update(u<<1|1,m+1,e,l,r,ad);
            tree[u]=tree[u<<1]+tree[u<<1|1];
            if(tree[u]>=MOD)tree[u]-=MOD;
        }
    }

    void update(int l,int r,int ad){
        update(1,0,n-1,l,r,ad);
    }

    int query(int u,int s,int e,int l,int r){
        if(s>=l&&e<=r)return tree[u];
        if(s>r||e<l)return 0;
        int m=s+e>>1;
        propagate(u,s,m,e);
        int ret=query(u<<1,s,m,l,r)+query(u<<1|1,m+1,e,l,r);
        if(ret>=MOD)ret-=MOD;
        return ret;
    }

    int query(int l,int r){
        return query(1,0,n-1,l,r);
    }
};

const int N=3e5+2;
int n;
vector<int>T[N];
int tin[N],tout[N],lvl[N],ord[N];
int tim=0;

void dfs(int u=0,int p=-1){
    ord[tim]=u;
    tin[u]=tim++;
    for(int v:T[u])
        if(v!=p)
            lvl[v]=lvl[u]+1,dfs(v,u);
    tout[u]=tim-1;
}

int main() {
    ios_base::sync_with_stdio(0);cin.tie(0);

    cin>>n;
    REP1(i,1,n){
        int v;cin>>v;
        T[i].push_back(--v);
        T[v].push_back(i);
    }
    dfs();

    int q;cin>>q;
    ST st1,st2;
    vector<int>tmp(n);
    st1.init(n);
    st2.init(n);
    while(q--){
        int op,v;cin>>op>>v;
        v--;
        if(--op){
            int ret=st1.query(tin[v],tin[v])-MULT(lvl[v],st2.query(tin[v],tin[v]));
            if(ret<0)ret+=MOD;
            if(ret>=MOD)ret-=MOD;
            cout<<ret<<"\n";
        }else{
            int x,k;cin>>x>>k;
            ADD(x,MULT(k,lvl[v]));
            st1.update(tin[v],tout[v],x);
            st2.update(tin[v],tout[v],k);
        }
    }
    return 0;
}