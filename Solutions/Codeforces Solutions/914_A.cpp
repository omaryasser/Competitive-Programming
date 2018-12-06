#include<bits/stdc++.h>

#define FOR(i, s, n) for (int i = s; i < n; i++)

using namespace std;
typedef long long ll;

int n;
const int MAX=100001;
int main() {
    scanf("%d",&n);
    set<int> st;
    st.insert(0);
    FOR(i,0,10000)
    st.insert(i*i);
    int mx=-10000000;
    FOR(i,0,n){
        int x;
        scanf("%d",&x);
        if(!st.count(x))mx=max(mx,x);
    }
    cout<<mx<<"\n";
}