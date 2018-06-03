/*
Let dp(i,j) = Max length of a palindrome starting at node i, ending at node j. 
Now you can simulate.
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
#define mp(x,y) make_pair(x,y)

typedef long long ll;

using namespace std;

int n;
const int N=51;
int INF=1e5;
string s[N];
int dp[N][N];
bool vis[N][N];


int solve(int i,int j){
    if(i==j)return 0;
    if(i>j)return -1;
    int &r=dp[i][j];
    if(r!=-1)return r;
    r=-INF;
    REP1(to,i+1,n){
        for(int from=j-1;from>=0;from--){
            if(s[i][to]==s[from][j]){
                if(to<=from||to==j&&from==i)
                    r=max(r,2+solve(to,from));
            }
        }
    }
    return r;
}


int main() {
    #ifndef ONLINE_JUDGE
    freopen("input.txt","r",stdin);
    #endif
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    int tc;
    cin>>tc;


    while(tc--){
        memset(vis,0,sizeof vis);
        cin>>n;
        REP(i,n)cin>>s[i];
        memset(dp,-1,sizeof dp);
        if(solve(0,n-1)<0){
            cout<<"NO PALINDROMIC PATH\n";
        }else{
            vector<pair<int,int> >v;
            v.pb(mp(0,n-1));
            string r="";
            while((int)v.size()){
                int bst=27;
                REP(c,v.size()){
                    int i=v[c].fi,j=v[c].se;
                    int can=solve(i,j);
                    REP1(to,i+1,n){
                        for(int from=j-1;from>=0;from--){
                            if(to<=from||to==j&&from==i)
                                if(s[i][to]==s[from][j]&&solve(to,from)==can-2){
                                bst=min(bst,(int)(s[i][to]-'A'));
                            }
                        }
                    }
                }
                vector<pair<int,int> >v2;
                REP(c,v.size()){
                    int i=v[c].fi,j=v[c].se;
                    int can=solve(i,j);
                    REP1(to,i+1,n){
                        for(int from=j-1;from>=0;from--){
                            if(to<=from||to==j&&from==i)
                                if(s[i][to]==s[from][j]&&solve(to,from)==can-2&&s[i][to]==(char)(bst+'A')){
                                if(to!=from&&!(to==j&&from==i)&&!vis[to][from])
                                    v2.pb(mp(to,from)),vis[to][from]=1;
                            }
                        }
                    }
                }
                v=v2;
                r+=(char)(bst+'A');
            }

            string ad;
            if(solve(0,n-1)&1){
                ad=r.substr(0,r.length()-1);
            }else{
                ad=r.substr(0,r.length());
            }
            reverse(all(ad));
            cout<<r+ad<<"\n";
        }
    }

    return 0;
}
