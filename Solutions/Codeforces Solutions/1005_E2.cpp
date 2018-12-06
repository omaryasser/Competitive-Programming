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
    void init(int n){
        this->n=n;
        tree.assign(n<<1<<1,0);
        lazy.assign(n<<1<<1,0);
    }

    void propagate(int u,int s,int m,int e){
        if(lazy[u]){
            tree[u<<1]+=lazy[u];
            tree[u<<1^1]+=lazy[u];
            lazy[u<<1]+=lazy[u];
            lazy[u<<1^1]+=lazy[u];
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
            update(u<<1^1,m+1,e,l,r,ad);
            tree[u]=max(tree[u<<1],tree[u<<1^1]);
        }
    }

    void update(int l,int r,int ad){
        if(l>r)swap(l,r);
        update(1,0,n-1,l,r,ad);
    }

    int query(int u,int s,int e,int l,int r){
        if(s>=l&&e<=r)return tree[u];
        if(s>r||e<l)return 0;
        int m=s+e>>1;
        propagate(u,s,m,e);
        return max(query(u<<1,s,m,l,r),query(u<<1^1,m+1,e,l,r));
    }

    int query(int l,int r){
        return query(1,0,n-1,l,r);
    }
};

const int N=2e5+10;
int n;
int pref1[N],pref2[N];
int OFFSET=N+1;
int main(){
    int k;
    scanf("%d%d",&n,&k);
    int more=0,less_or_eq=0,less=0,more_or_eq=0;
    ST st1,st2;
    st1.init(N+N+3),st2.init(N+N+3);
    int INF=N+N+2,NINF=0;
    REP(i,n){
        int x;
        scanf("%d",&x);
        if(x==k)less_or_eq++,more_or_eq++;
        if(x>k)more++,more_or_eq++;
        if(x<k)less++,less_or_eq++;
        pref1[i]=more-less_or_eq;
        pref2[i]=less-more_or_eq;
    }
    ll res1=0,res2=0;
    for(int i=n-1;i>=0;i--){
        int diff1=(i)?pref1[i-1]+OFFSET:OFFSET,diff2=(i)?pref2[i-1]+OFFSET:OFFSET;
        int l1=NINF,r1=pref1[i]-1+OFFSET;
        int l2=NINF,r2=pref2[i]+OFFSET;
        st1.update(l1,r1,1);
        st2.update(l2,r2,1);
        res1+=st1.query(diff1,diff1);
        res2+=st2.query(diff2,diff2);
    }
    printf("%lld",1ll*n*(n+1)/2-res1-res2);
}