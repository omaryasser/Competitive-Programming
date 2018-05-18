/*
 count number of ones of numbers less than x
*/
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
#define debug printf("DEBUG\n");

typedef long long ll;

using namespace std;


ll a[2];
string s[2];
const int N=64;
ll mem[N][N][N];
string toB(ll num){
  string r="";
  while(num){
    r=to_string(num%2)+r;
    num/=2;
  }
  return r;
}

ll dp(int r,bool l,int o,int id){
  if(r==0){
    return l?o:0;
  }
  ll &ret=mem[r][l][o];
  if(ret!=-1)return ret;
  ret=0;
  if(l)ret=dp(r-1,l,o+1,id)+dp(r-1,l,o,id);
  else{
    int cid=s[id].length()-r;
    // put one
    if(l||s[id][cid]=='1')ret+=dp(r-1,l,o+1,id);
    // put zero
    ret+=dp(r-1,l||s[id][cid]=='1',o,id);
  }
  return ret;
}

ll solve(int id){
  ll r=0;
  REP1(l,1,s[id].length()+1){
    r+=dp(l-1,l<s[id].length()?1:0,1,id);
  }
  return r;
}

int main() {
    #ifndef ONLINE_JUDGE
    freopen("in.txt","r",stdin);
    #endif
    
    while(scanf("%lld%lld",a,a+1)==2){
      if(!a[0]&&!a[1])return 0;
      a[1]++;
      REP(i,2)s[i]=toB(a[i]);
      memset(mem,-1,sizeof mem);
      ll b=solve(1);
      memset(mem,-1,sizeof mem);
      printf("%lld\n",b-(!a[0]?0:solve(0)));
      tc++;
    }

    return 0;
}
