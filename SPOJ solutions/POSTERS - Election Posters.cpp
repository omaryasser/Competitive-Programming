#include <bits/stdc++.h>
#include <bitset>

#define REP(i,n) for(int i=0;i<(int)n;i++)
#define REP1(i,j,n) for(int i=j;i<(int)n;i++)
#define all(x) x.begin(),x.end()
#define double long double
#define BUG cerr<<"BUG\n";

typedef long long ll;

using namespace std;

struct ST{
    vector<int>tree,lazy;
    int n;
    unordered_set<int>ret;
    void init(int n){
        this->n=n;
        tree.assign(n<<1<<1,0);
        lazy.assign(n<<1<<1,0);
    }

    void propagate(int u,int s,int m,int e){
        if(lazy[u]){
            tree[u<<1]=lazy[u];
            tree[u<<1|1]=lazy[u];
            lazy[u<<1]=lazy[u];
            lazy[u<<1|1]=lazy[u];
            lazy[u]=0;
        }
    }
    void update(int u,int s,int e,int l,int r,int ad){
        if(s>=l&&e<=r){
            tree[u]=ad;
            lazy[u]=ad;
        }else if(s>r||e<l);
        else{
            int m=s+e>>1;
            propagate(u,s,m,e);
            update(u<<1,s,m,l,r,ad);
            update(u<<1|1,m+1,e,l,r,ad);
        }
    }

    void update(int l,int r,int ad){
        update(1,0,n-1,l,r,ad);
    }

    void query(int u=1,int s=0,int e=2){
        if(s==e){if(tree[u])ret.insert(tree[u]);}
        else{
            int mid=s+e>>1;
            propagate(u,s,mid,e);
            query(u<<1,s,mid);
            query(u<<1|1,mid+1,e);
        }
    }

};

int main() {
    ios_base::sync_with_stdio(0);cin.tie(0);

    int tc;
    cin>>tc;
    while(tc--){
        int n;
        cin>>n;
        vector<int>l(n),r(n);
        set<int>v;
        REP(i,n){
            cin>>l[i]>>r[i];
            v.insert(l[i]);
            v.insert(r[i]);
            if(l[i]+1<r[i])v.insert(l[i]+1);
        }
        unordered_map<int,int>mp;
        int mpIdx=0;
        for(auto x:v)mp[x]=++mpIdx;
        ST st; st.init(mpIdx+1);
        REP(i,n){
            st.update(mp[l[i]],mp[r[i]],i+1);
        }
        st.query(1,0,st.n-1);
        cout<<st.ret.size()<<"\n";
    }

    return 0;
}
