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
            tree[u<<1]+=lazy[u];
            tree[u<<1|1]+=lazy[u];
            lazy[u<<1]+=lazy[u];
            lazy[u<<1|1]+=lazy[u];
            lazy[u]=0;
        }
    }
    void update(int u,int s,int e,int l,int r,int ad){
        if(s>=l&&e<=r){
            tree[u]+=ad;
            lazy[u]+=ad;
        }else if(s>r||e<l);
        else{
            int m=s+e>>1;
            propagate(u,s,m,e);
            update(u<<1,s,m,l,r,ad);
            update(u<<1|1,m+1,e,l,r,ad);
            tree[u]=max(tree[u<<1],tree[u<<1|1]);
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
        return max(query(u<<1,s,m,l,r),query(u<<1|1,m+1,e,l,r));
    }

    int query(int l,int r){
        return query(1,0,n-1,l,r);
    }
};

const int N=35001;
int n,K;
int a[N];
int lst[N];
int dp[51][N];

int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    cin>>n>>K;
    REP(i,n)cin>>a[i];
    memset(lst,-1,sizeof lst);
    int ccc=0;
    REP(i,n){
        if(lst[a[i]]==-1)lst[a[i]]=1,ccc++;
        dp[0][i]=ccc;
    }
    REP1(k,1,K){
        ST st;
        st.init(n);
        memset(lst,0,sizeof lst);
        REP(i,k){
            st.update(i,i,dp[k-1][i]);
            lst[a[i]]=i;
        }
        REP1(i,k,n){
            st.update(lst[a[i]],i-1,1);
            lst[a[i]]=i;
            dp[k][i]=st.query(0,i-1);
            st.update(i,i,dp[k-1][i]);
        }
    }
    int bst=dp[K-1][K-1];
    REP1(i,K,n)bst=max(bst,dp[K-1][i]);
    cout<<bst<<"\n";

}