#include <bits/stdc++.h>

#define REP(i,n) for(int i=0;i<(int)n;i++)
#define pb(x) push_back(x)
#define mp(x,y) make_pair(x,y)
#define fi first
#define se second

typedef long long ll;

using namespace std;

int n;
char in[1001];
set<string>st;

int main() {
    #ifndef ONLINE_JUDGE
    freopen("in.txt","r",stdin);
    #endif

    scanf("%d",&n);

    REP(i,n){
    	scanf("%s",in);
    	string s=string(in);
    	sort(s.begin(),s.end());
    	s.erase(unique(s.begin(),s.end()),s.end());
    	st.insert(s);
    }

    printf("%d\n", st.size());
    
    return 0;
}