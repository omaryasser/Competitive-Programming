#include <bits/stdc++.h>
#include <bitset>

#define REP(i,n) for(int i=0;i<(int)n;i++)
#define REP1(i,j,n) for(int i=j;i<(int)n;i++)
#define pb(x) push_back(x)
#define fi first
#define se second
#define all(x) x.begin(),x.end()
#define mp(x,y) make_pair(x,y)

typedef long long ll;

using namespace std;

const int N=200001;
int a[N],b[N];
int n;
int BIT[N+1];
void update(int i,int a){
    i++;
    while(i<=N){
        BIT[i]+=a;
        i+=i&(-i);
    }
}
int sum(int i){
    i++;
    int s=0;
    while(i>=1){
        s+=BIT[i];
        i-=i&(-i);
    }
    return s;
}

int main() {
    #ifndef ONLINE_JUDGE
    freopen("in.txt","r",stdin);
    #endif     

    scanf("%d",&n);
    REP(i,n)scanf("%d",a+i);
    REP(i,n)scanf("%d",b+i);

    REP(i,n)update(i,1);
    REP(i,n){
        int t=a[i];
        a[i]=sum(a[i])-1;
        update(t,-1);
    }

    REP(i,n)update(i,1);

    REP(i,n){
        int t=b[i];
        b[i]=sum(b[i])-1;
        update(t,-1);
        a[i]+=b[i];
    }

    for(int i=n-2;i>=0;i--){
        if(i-1>=0)a[i-1]+=a[i]/(n-i);
        a[i]%=(n-i);
    }



    REP(i,n)update(i,1);
    REP(i,n){
        int s=a[i]+1;
        int lo=0,hi=n-1;
        while(lo<=hi){
            int m=lo+hi>>1;
            if(sum(m)<s)
                lo=m+1;
            else hi=m-1;
        }
        update(lo,-1);
        printf("%d\n", lo);
    }
    

    return 0;
}