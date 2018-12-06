#include <bits/stdc++.h>
#define f(i, j, n) for(int i = j; i < n; i++)
#define all(v) v.begin(), v.end()
#define ll long long
using namespace std;


int main(){
    ios_base::sync_with_stdio(0); cin.tie(0);
    int n; cin >> n;
    int x1, y1, x2, y2, x3, y3; cin >> x1 >> y1 >> x2 >> y2 >> x3 >> y3;
    if(((x3 > x1) == (x2 > x1)) && ((y3 > y1) == (y2 > y1))){
        cout << "YES\n";
    }else cout << "NO\n";
}