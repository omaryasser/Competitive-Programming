#include<bits/stdc++.h>

#define FOR(i, s, n) for (int i = s; i < n; i++)
using namespace std;
typedef long long ll;

const int MAX=1000000;
bool isPrime[MAX];
vector<int>primes;
int main() {
    ios_base::sync_with_stdio(0);cin.tie();

//    FOR(i,0,MAX)isPrime[i]=true;
//    for(int i=2;i*i<MAX;i++)
//    if(isPrime[i])
//        for(int j=i*i;j<MAX;j+=i)
//            isPrime[j]=false;
//    FOR(i,2,MAX)if(isPrime[i])primes.push_back(i);
//    cerr<<primes[primes.size()-1]<<"\n";
    int n,m;
    scanf("%d%d",&n,&m);
    if(n==2){cout<<"2 2\n";cout<<"1 2 2\n";
        return 0;}
    m--;
    cout<<"2 "<<999983<<"\n";
    cout<<"1 "<<n<<" 2\n";
    int rem=999981;
    FOR(i,1,n-2) {
        cout << i << " " << i + 1 << " " << 1 << "\n";
        m--;
        rem--;
    }
    m--;
    cout<<n-2<<" "<<n-1<<" "<<rem<<"\n";
    for(int u=1;u<=n&&m;u++)
        for(int v=u+1;v<=n&&m;v++){
            if(u==v||abs(u-v)==1&&!(u==n-1&&v==n)||(u==1&&v==n||u==n&&v==1))continue;
            m--;
            cout<<u<<" "<<v<<" "<<1000000000<<"\n";
        }
}