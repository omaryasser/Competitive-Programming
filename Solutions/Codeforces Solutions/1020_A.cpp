#include <bits/stdc++.h>
#define REP(i,n) for(int i=0;i<(int)n;i++)
#define REP1(i,j,n) for(int i=j;i<(int)n;i++)
#define all(x) x.begin(),x.end()
#define BUG cerr<<"BUG\n";

typedef long long ll;

using namespace std;


int main() {

    ll n,h,a,b;
    scanf("%lld%lld%lld%lld",&n,&h,&a,&b);
    int q;
    scanf("%d",&q);
    while(q--){
        ll ta,fa,tb,fb;
        scanf("%lld%lld%lld%lld",&ta,&fa,&tb,&fb);
        if(ta==tb){
            printf("%lld\n",abs(fa-fb));
            continue;
        }
        ll res=fa>=a&&fa<=b?0:min(abs(fa-a),abs(fa-b));
        fa=fa>=a&&fa<=b?fa:abs(fa-a)<abs(fa-b)?a:b;
        res+=abs(ta-tb)+abs(fa-fb);
        printf("%lld\n",res);
    }

}