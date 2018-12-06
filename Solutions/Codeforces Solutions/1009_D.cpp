#include <bits/stdc++.h>
#include <bitset>

#define REP(i,n) for(int i=0;i<(int)n;i++)
#define REP1(i,j,n) for(int i=j;i<(int)n;i++)
#define all(x) x.begin(),x.end()
#define BUG cerr<<"BUG\n";

typedef long long ll;

using namespace std;

const int N=1e5+10;
bool isPrime[N];
bool done[N];

int main(){
    ios_base::sync_with_stdio(0);cin.tie(0);

    int n,m;
    cin>>n>>m;
    vector<pair<int,int> >res;
    unordered_set<int>lft;
    REP1(i,3,n+1)lft.insert(i);
    if(m<n-1){
        cout<<"Impossible\n";
        return 0;
    }
    REP1(i,2,n+1){
        if(m){
            m--;
            res.push_back(make_pair(1,i));
        }
    }

    REP(i,N)isPrime[i]=1;
    for(int i=2;i<=n;i++){
        if(m==0)break;
        if(isPrime[i]){
            vector<int>others;
            others.push_back(i);
            lft.erase(i);
            for(int j=i+i;j<=n;j+=i){
                if(done[j])continue;
                done[j]=1;
                isPrime[j]=0;
                lft.erase(j);
                others.push_back(j);
            }
            for(auto p:others){
                if(m==0)break;
                for(int j:lft){
                    if(m==0)break;
                    if(__gcd(p,j)==1)res.push_back(make_pair(p,j)),m--;
                    if(m==0)break;
                }
            }
            if(m==0)break;
        }
    }
    if(m)cout<<"Impossible\n";
    else {
        cout<<"Possible\n";
        for(auto p:res)cout<<p.first<<" "<<p.second<<"\n";
    }
}