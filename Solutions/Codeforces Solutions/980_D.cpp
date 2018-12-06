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
const int N=5001;
int a[N];
int d[N][N];
unordered_map<int,int>c;
int r[N];
bool e[N];
int leftt[N];
int rightt[N];

int main() {
    #ifndef ONLINE_JUDGE
    freopen("in.txt","r",stdin);
    #endif
  
    scanf("%d",&n);
    REP(i,n){
      int x;
      scanf("%d",&x);
      a[i]=x<0?-1:1;
      if(x!=0){
        x=abs(x);
        for(int ii=2;ii*ii<=x;ii++){
          int c=0;
          while(x%ii==0){
            x/=ii;
            c++;
          }
          if(c&1)a[i]*=ii;
        }
        if(x!=1)a[i]*=x;
      }else a[i]=0;
      e[i]=a[i]==0||a[i]==-1||a[i]==1;
    }
    memset(leftt,-1,sizeof leftt);
    memset(rightt,-1,sizeof rightt);
    REP(i,n){
      for(int j=i-1;j>=0;j--){
        if(a[j]==a[i]){
          leftt[i]=j;
          break;
        }
      }
      for(int j=i+1;j<n;j++){
        if(a[j]==a[i]){
          rightt[i]=j;
          break;
        }
      }
    }
    REP1(l,1,n+1){
      int sz=0;
      REP(j,l)if(!e[j]){
        if(leftt[j]==-1||j-leftt[j]+1>l)sz++;
      }
      d[0][l-1]=sz;
      REP1(j,l,n){
        if(!e[j]){
          if(leftt[j]==-1||j-leftt[j]+1>l)sz++;
        }
        if(!e[j-l]){
          if(rightt[j-l]==-1||rightt[j-l]-(j-l)+1>l)sz--;
        }
        d[j-l+1][j]=sz;

      }
    }
    

    REP(i,n){
      int z=0,pe=0,ne=0;
      REP1(j,i,n){
        if(a[j]==0)z++;
        else if(a[j]==1)pe++;
        else if(a[j]==-1)ne++;
        r[d[i][j]+(ne>=1)+(pe>=1)+(z==j-i+1)]++;
      }
    }
    REP1(i,1,n+1){
      if(i>1)printf(" ");
      printf("%d", r[i]);
    }
    printf("\n");
    return 0;
}