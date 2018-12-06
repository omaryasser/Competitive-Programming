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
    int n,lg;
    vector<vector<int> >tree;
    vector<int>a,plog;
    ST(int n,int *a){
        this->n=n;
        this->a=vector<int>(a,a+n);
        lg=0;
        while((1<<lg)<n)lg++;
        tree.assign(n,vector<int>(lg));
        plog.assign(n+1,0);
        int pw=2,curL=0;
        REP(i,n+1){
            if(i==pw)curL++,pw<<=1;
            plog[i]=curL;
        }
        REP(i,n)tree[i][0]=a[i];
        REP1(log,1,lg)
            REP(i,n){
                if(i+(1<<log)-1<n)
                    tree[i][log]=__gcd(tree[i][log-1],tree[i+(1<<(log-1))][log-1]);
            }
    }
    int q(int s,int e){
        int d=plog[e-s+1];
        if(s==e)return a[s];
        return __gcd(tree[s][d],tree[e-(1<<d)+1][d]);
    }
};
const int N=100001;
int n;
int a[N];
unordered_map<int,ll>mp;

int main() {
    #ifndef ONLINE_JUDGE
    freopen("in.txt","r",stdin);
    #endif

    scanf("%d",&n);
    REP(i,n)scanf("%d",a+i);
    ST st(n,a);
    REP(i,n){
        int e=i;
        while(e<n){
            int g=st.q(i,e);
            int lo=e,hi=n-1,b=e;
            while(lo<=hi){
                int m=lo+hi>>1;
                if(st.q(i,m)==g){
                    b=m;
                    lo=m+1;
                }else hi=m-1;
            }
            mp[g]+=b-e+1;
            e=b+1;
        }
    }
    int q;
    scanf("%d",&q);
    while(q--){
        int x;
        scanf("%d",&x);
        printf("%lld\n", mp[x]);
    }

    return 0;
}