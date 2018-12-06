#include<bits/stdc++.h>

#define FOR(i, s, n) for (int i = s; i < n; i++)

using namespace std;
typedef long long ll;

int n;
const int MAX=100001;
int cnt[MAX];
multiset<int>st;
int main() {
    scanf("%d",&n);
    FOR(i,0,n){
        int x;scanf("%d",&x);cnt[x]++;
    }
    bool can=false;
    FOR(i,0,MAX)if(cnt[i]%2)can=true;
    if(can)printf("Conan\n");
    else printf("Agasa\n");
}