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
    vector<pair<int,int> >tree;
    int n;
    void init(int n){
        this->n=n;
        tree.assign(n<<1<<1,{0,0});
    }

    void update(int u,int s,int e,int l,int r,int ad){
        if(s>=l&&e<=r){
            tree[u]=make_pair(ad,s);
        }else if(s>r||e<l);
        else{
            int m=s+e>>1;
            update(u<<1,s,m,l,r,ad);
            update(u<<1|1,m+1,e,l,r,ad);
            tree[u]=max(tree[u<<1],tree[u<<1|1]);
        }
    }

    void update(int l,int r,int ad){
        update(1,0,n-1,l,r,ad);
    }

    pair<int,int> query(int u,int s,int e,int l,int r){
        if(s>=l&&e<=r)return tree[u];
        if(s>r||e<l)return make_pair(-1,0);
        int m=s+e>>1;
        return max(query(u<<1,s,m,l,r),query(u<<1|1,m+1,e,l,r));
    }

    pair<int,int> query(int l,int r){
        return query(1,0,n-1,l,r);
    }
};

const int N=5e5+10;
int a[N],nxt[N],lst[N],res[N];
bool vis[N];
int n,q;
vector<tuple<int,int,int> >queries;

int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    cin>>n;
    REP(i,n)cin>>a[i];
    REP(i,N)lst[i]=n,nxt[i]=n;
    for(int i=n-1;i>=0;i--)
        nxt[i]=lst[a[i]],lst[a[i]]=i;
    cin>>q;
    REP(i,q){
        int l,r;
        cin>>l>>r;
        l--,r--;
        queries.push_back(make_tuple(l,r,i));
    }
    sort(all(queries));
    ST st; st.init(n+1);
    REP(i,n)if(!vis[a[i]]){
            vis[a[i]]=1;
            st.update(i,i,nxt[i]);
        }

    int lst=0;
    REP(i,q){
        int l=get<0>(queries[i]);
        int r=get<1>(queries[i]);
        int idx=get<2>(queries[i]);
        while(lst<l){
            st.update(lst,lst,-1);
            if(nxt[lst]==n){
                lst++;continue;
            }
            st.update(nxt[lst],nxt[lst],nxt[nxt[lst]]);
            lst++;
        }
        auto ret=st.query(l,r);
        if(ret.first>r)res[idx]=a[ret.second];
    }
    REP(i,q)cout<<res[i]<<"\n";
}