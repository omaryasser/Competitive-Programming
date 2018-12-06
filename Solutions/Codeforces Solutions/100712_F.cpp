#include <bits/stdc++.h>
#define f(i, j, n) for(int i = j; i < n; i++)
#define all(v) v.begin(), v.end()
#define ll long long
using namespace std;

int main(){
    ios_base::sync_with_stdio(0); cin.tie(0);
    int n; cin >> n;
    if((n - 2) % 3 == 0){
        cout << 1 << " " << 2 << " " << n - 3 << "\n";
    }else{
        cout << 1 << " " << 1 << " " << n - 2 << "\n";
    }
}