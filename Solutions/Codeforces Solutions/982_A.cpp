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
char a[1001];

bool chk(){
  bool o=1;
  REP(i,n-1)if(a[i]=='1'&&a[i+1]=='1')o=0;
  return o;
}
int main() {
    #ifndef ONLINE_JUDGE
    freopen("in.txt","r",stdin);
    #endif
    
    scanf("%d",&n);
    scanf("%s",a);

    bool o=chk();
    REP(i,n)if(a[i]=='0'){
      a[i]='1';
      o&=!chk();
      a[i]='0';
    }
    printf("%s\n",o?"YES":"NO");


    return 0;
}