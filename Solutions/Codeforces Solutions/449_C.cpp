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
const int N=100001;
bool done[N];
bool prime[N];

int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    cin>>n;
    vector<int>primes;
    REP(i,N)prime[i]=1;
    for(int i=2;i*i<N;i++){
        if(prime[i]){
            for(int j=i*i;j<N;j+=i)
                prime[j]=0;
        }
    }
    REP1(i,2,N)if(prime[i])primes.pb(i);
    vector<int>res;
    for(int i=primes.size()-1;i>=0;i--){
        vector<int>all;
        int p=primes[i];
        REP1(mul,1,N){
            if(mul*p>n)break;
            if(!done[mul*p]){
                all.pb(mul*p);
            }
        }
        if(all.size()%2==0){
            for(int j=0;j<all.size();j+=2){
                res.pb(all[j]);
                res.pb(all[j+1]);
                done[all[j]]=1;
                done[all[j+1]]=1;
            }
        }else{
            if(all.size()>=3){
                res.pb(all[0]);
                res.pb(all[2]);
                done[all[0]]=1;
                done[all[2]]=1;
                for(int j=3;j<all.size();j+=2){
                    res.pb(all[j]);
                    res.pb(all[j+1]);
                    done[all[j]]=1;
                    done[all[j+1]]=1;       
                }
            }
        }
    }
    cout<<res.size()/2<<"\n";
    for(int i=0;i<res.size();i+=2)
        cout<<res[i]<<" "<<res[i+1]<<"\n";
}