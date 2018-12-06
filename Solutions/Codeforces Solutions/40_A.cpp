/*
Compare the distance to the radii.
*/
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


int main() {
    #ifndef ONLINE_JUDGE
    freopen("in.txt","r",stdin);
    #endif     

    int x,y;
    scanf("%d%d",&x,&y);
    if(!x&&!y)printf("black\n");
    else {
        REP1(c,1,4002){
            if(x*x+y*y==c*c){
                printf("black");
                return 0;
            }else if(x*x+y*y<c*c){
                if(x==0||y==0){
                    printf("black");
                    return 0;
                }
                bool right=x>0;
                bool up=y>0;
                bool first=(c%2);

                if(up&&right||!up&&!right)
                    printf("%s\n", first?"black":"white");
                else 
                    printf("%s\n", !first?"black":"white");
                return 0;
            }
        }
    }
    
    return 0;
}