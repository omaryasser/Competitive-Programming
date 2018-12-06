#include <bits/stdc++.h>
#define f(i, j, n) for(int i = j; i < n; i++)
#define all(v) v.begin(), v.end()
#define ll long long
using namespace std;

int dig(ll n){
    int res = 0;
    while(n)res += n % 10, n /= 10;
    return res;
}
int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    ll n; cin >> n;
    string s = "";
    while(stoll(s + "9") <= n)s += "9";
    if(s == "")s = "0";
    cout << dig(stoll(s)) + dig(n - (stoll(s))) << "\n";
}