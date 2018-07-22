/*
  Playing with summations
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

typedef long long ll;

using namespace std;

int M,MIN,MAX;
const int N=2*200000;
double smem[N],smem2[N];
double sum(int l){
  if(l==0)return 0;
  double& r=smem[l];
  if(r>-1)return r;
  return r=1.0L*l+sum(l-1);
}

double s2(int l){
  if(l==0)return 0;
  double& r=smem2[l];
  if(r>-1)return r;
  r=sum(l)+s2(l-1);
  return r;
}

int main() {
    #ifndef ONLINE_JUDGE
    freopen("in.txt","r",stdin);
    #endif
    memset(smem,-1,sizeof smem);
    memset(smem2,-1,sizeof smem2);
    int tc=0;
    while(++tc){
      scanf("%d%d%d",&M,&MIN,&MAX);
      if(!M)break;
      printf("Case %d: ", tc);
      if(M<MIN){
        printf("0.00\n");
        continue;
      }
      int s=MIN,e=min(M,MAX);   
      double o=sum(MAX-MIN+1);
      double a=s2(e-s)*0.10;
      double b=0.20*M*(sum(1+e-s));
      double c=-0.20*sum(e)*(e-s+1);
      double d=0.20*s2(e-1)-0.20*s2(s-2);
      double r=(a+b+c+d)/o;
      if(MAX>M)r+=1.0/o*0.10*(MAX-M)*(sum(M-s));
      printf("%.2Lf\n", r);
    }
    
    return 0;
}
