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

int n;
const int N=1000001;
int a[N];
bool v[N];

int main() {
    #ifndef ONLINE_JUDGE
    freopen("in.txt","r",stdin);
    #endif
        
    scanf("%d",&n);
    REP(i,n)scanf("%d",a+i);
    int s=0;
    REP(i,n){
    	if(!v[i]){
    		int x=i;
    		int c=0;
    		while(!v[x]){
    			v[x]=1;
    			c++;
    			x=a[x]-1;
    		}
    		s+=c-1;
    	}
    }
    int p=3*n;
    if(p<s){
    	printf("Um_nik\n");
    }else{
    	if((p-s)%2==0){
    		printf("Petr\n");
    	}else{
    		printf("Um_nik\n");
    	}
    }
    
    return 0;
}