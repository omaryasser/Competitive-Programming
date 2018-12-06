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

const int N=1e5+10,P=101;
int n,m,p;
int dist[N];
ll dp[P][N];

class convexhull {
public:
    vector<ll>A,B,starts,ends;
    int ptr,len;
    ll INF=(ll)1e10;
    convexhull (int n) {
        A.assign(n,0);
        B.assign(n,0);
        starts.assign(n,0);
        ends.assign(n,0);
        ptr=0,len=0;
    }

    void addLine(ll a,ll b){// lines are sorted with their slopes decreasing
        while(len>=2&&(A[len-1]-A[len-2])*(B[len-1]-b)<=(A[len-1]-a)*(B[len-1]-B[len-2]))--len;
        A[len]=a;
        B[len]=b;
        if(len==0)starts[len]=-INF;
        else starts[len]=(B[len]-B[len-1]+A[len-1]-A[len]-1)/(A[len-1]-A[len]);
        ++len;
    }

    ll queryPointer(ll x){// O(n), queries must be performed in increasing order
        ptr=min(ptr,len-1);
        while(ptr<len-1&&A[ptr+1]*x+B[ptr+1]<=A[ptr]*x+B[ptr])
            ++ptr;
        return A[ptr]*x+B[ptr];
    }

    void addLine2(ll a,ll b){ // lines are sorted with their slopes increasing
        while(len>=2&&(A[len-1]-A[len-2])*(B[len-1]-b)>=(A[len-1]-a)*(B[len-1]-B[len-2]))--len;
        A[len]=a;
        B[len]=b;
        if(len==0)ends[0]=INF;
        else ends[len]=(B[len]-B[len-1])/(A[len-1]-A[len]);
        ++len;
    }


    ll queryBS(ll x){ // use it if you filled starts[]
        int ans=0;
        int lo=1,hi=len-1;
        while(lo<=hi){
            int mid=lo+hi>>1;
            if(starts[mid]<=x){
                ans=mid;
                lo=mid+1;
            } else hi=mid-1;
        }
        return x*A[ans]+B[ans];
    }

    ll queryBS2(ll x){ // use it if you filled ends[]
        int ans=0;
        int lo=1,hi=len-1;
        while(lo<=hi){
            int mid=lo+hi>>1;
            if(ends[mid]>=x){
                ans=mid;
                lo=mid+1;
            } else hi=mid-1;
        }
        return x*A[ans]+B[ans];
    }
};

int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    cin>>n>>m>>p;
    REP1(i,1,n){
        cin>>dist[i];
        dist[i]+=dist[i-1];
    }
    vector<ll>cats(m),sum(m);
    REP(i,m){
        int h,t;
        cin>>h>>t;
        cats[i]=(t-dist[h-1]);
    }
    sort(all(cats));
    REP(i,m){
        sum[i]=cats[i];
        if(i)sum[i]+=sum[i-1];
    }
    REP(i,m)dp[0][i]=cats[i]*(i+1)-sum[i];
    REP1(f,1,p){
        convexhull hull(m);
        dp[f][0]=0;
        hull.addLine(0,sum[0]);
        REP1(i,1,m){
            dp[f][i]=cats[i]*i-sum[i]+hull.queryPointer(cats[i]);
            hull.addLine(-i,dp[f-1][i]+sum[i]);
        }
    }
    cout<<dp[p-1][m-1]<<"\n";
}