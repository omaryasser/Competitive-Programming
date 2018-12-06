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

int n;
const int N=100001;
vector<int>v;

bool go(int x,int l){
    int found=0;
    int md=0;
    for(int i=v.size()-1;i>=0;i--){
        REP1(j,v[i]+1,10){
            int ws=v[i];
            v[i]=j;
            if(l-found+v[i]-ws<=x&&l-found+v[i]-ws+md*9>=x){
                // fill [i+1:end] by x-(l-found+v[i]-ws)
                int left=x-(l-found+v[i]-ws);
                for(int k=v.size()-1;k>=i+1;k--){
                    v[k]=min(9,left);
                    left-=v[k];
                }
                return 1;
            }
            v[i]=ws;
        }
        md++;
        found+=v[i];
    }
    return 0;
}

int main() {
    #ifndef ONLINE_JUDGE
    freopen("in.txt","r",stdin);
    #endif     

    scanf("%d",&n);
    int l=0;
    REP(j,n){
        int x;
        scanf("%d",&x);
        if(x<=l){
            // try same size
            if(!go(x,l)){
                // try bigger size
                v.insert(v.begin(),1);
                int left=x;
                for(int i=v.size()-1;i>=0;i--){
                    if(!i)v[i]=left;
                    else{
                        v[i]=min(9,left-1);
                        left-=v[i];
                    }
                }
            }
        }else{
            // try same size

            if(!go(x,l)){
                // try bigger size
                v.insert(v.begin(),1);
                while(9*(int)v.size()<x)v.insert(v.begin(),1);
                int left=x;
                for(int i=v.size()-1;i>=0;i--){
                    if(!i)v[i]=left;
                    else{
                        v[i]=min(9,left-1);
                        left-=v[i];
                    }
                }
            }
        }
        REP(i,v.size())printf("%d", v[i]);
        printf("\n");
        l=x;
    }

    
    return 0;
}