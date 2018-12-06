#include <bits/stdc++.h>
#define f(i, j, n) for(int i = j; i < n; i++)
#define all(v) v.begin(), v.end()
#define ll long long
using namespace std;

int main(){
    ios_base::sync_with_stdio(0); cin.tie(0);
    int n;
    string s;
    cin >> n >> s;
    int sum = 0;
    f(i, 0, n)sum += s[i] - '0';
    f(i, 2, n + 1){
        if(sum % i != 0)continue;
        int wanted = sum / i;
        int cur = 0;
        f(j, 0, n){
            cur += s[j] - '0';
            if(cur == wanted)cur = 0;
        }
        if(cur == 0){
            cout << "YES\n";
            return 0;
        }
    }
    cout << "NO\n";
}