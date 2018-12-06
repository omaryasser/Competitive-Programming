#include<bits/stdc++.h>

using namespace std;


int numbits(long long num){
    int cnt=0;
    while(num) {
        cnt++;
        num/=2;
    }
    return cnt;
}
int main() {
    ios_base::sync_with_stdio(0);
    cin.tie();



    int n;
    cin>>n;
    set<int>st;

    while(n--){
        int x;
        cin>>x;
        st.insert(-x);
    }

    for(int i:st){
        if(*st.upper_bound(2*(i))!=i){
            cout<<"YES\n";
            return 0;
        }
    }
    cout<<"NO\n";
    return 0;
}