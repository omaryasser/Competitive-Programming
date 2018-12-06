#include <bits/stdc++.h>
#include <bitset>

#define REP(i,n) for(int i=0;i<(int)n;i++)
#define REP1(i,j,n) for(int i=j;i<(int)n;i++)
#define pb(x) push_back(x)
#define fi first
#define se second
#define all(x) x.begin(),x.end()
#define double long double
#define mk(x,y) make_pair(x,y)

typedef long long ll;

using namespace std;

int n;
const int N=200001;
int w[N];
char s[2*N];
int main() {
    #ifndef ONLINE_JUDGE
    freopen("in.txt","r",stdin);
    #endif
    
    scanf("%d",&n);
    REP(i,n)scanf("%d",w+i);
    scanf("%s",s);
    set<pair<int,int> >o,tt;
    REP(i,n)o.insert(mk(w[i],i));
    REP(i,2*n){
    	if(s[i]=='0'){
    		pair<int,int>p=*o.begin();
    		o.erase(o.begin());
    		printf("%d\n", p.se+1);
    		tt.insert(p);
    	}else{
    		pair<int,int>p=*tt.rbegin();
    		tt.erase(--tt.end());
    		printf("%d\n", p.se+1);
    	}
    }

    return 0;
}