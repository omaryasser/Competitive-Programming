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


const int N=100001;
int n;
int a[N];
pair<int,int> s[N];
int g[N];
int cg[N];

int main() {
    #ifndef ONLINE_JUDGE
    freopen("in.txt","r",stdin);
    #endif

	scanf("%d",&n);
	REP(i,n){
		scanf("%d",a+i);    
		s[i]=mk(a[i]+1,i);
	}
	memset(cg,0,sizeof cg);
	memset(g,-1,sizeof g);
    sort(s,s+n);

    int k=1,locK=0;
    int mxL=0,mxLC=0;
    int grps=0;
    REP(loop,n){
    	int i=s[loop].se;
    	if((i+1>=n||a[i+1]>a[i])&&(i==0||a[i-1]>a[i])){
    		g[i]=grps++;
    		cg[g[i]]=1;
    	} else if(!(i+1>=n||a[i+1]>a[i])&&!(i==0||a[i-1]>a[i])){
    		if(cg[g[i-1]]>cg[g[i+1]]){
    			//right like left
    			g[i]=g[i-1];
    			cg[g[i]]++;
    			int rg=g[i+1];
    			REP1(j,i+1,n)
    				if(g[j]==rg){
    					g[j]=g[i];
    					cg[g[i]]++;
    				}else break;
    		}else if(cg[g[i-1]]<=cg[g[i+1]]){
    			//left like right
    			g[i]=g[i+1];
    			cg[g[i]]++;
    			int lg=g[i-1];
    			for(int j=i-1;j>=0;j--)
    				if(g[j]==lg){
    					g[j]=g[i];
    					cg[g[i]]++;
    				}else break;
    		}

    	}else if(!(i+1>=n||a[i+1]>a[i])){
    		g[i]=g[i+1];
    		cg[g[i]]++;
    	}else{
    		g[i]=g[i-1];
    		cg[g[i]]++;
    	}
    	if(cg[g[i]]>mxL){
    			mxL=cg[g[i]];
    			mxLC=1;
    	}else if(cg[g[i]]==mxL){
    			mxLC++;
    	}
    	if(mxLC==grps){
    		if(grps>locK){
    			locK=grps;
    			k=s[loop].fi;
    		}
    	}
    }

    printf("%d\n",k);
    return 0;
}