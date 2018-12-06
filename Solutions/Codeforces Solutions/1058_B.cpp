#include <bits/stdc++.h>
#define f(i, j, n) for(int i = j; i < n; i++)
#define all(v) v.begin(), v.end()
#define ll long long
using namespace std;

int main(){
    ios_base::sync_with_stdio(0); cin.tie(0);
    set<pair<int,int> >inside;
    int n, d;
    cin >> n >> d;
    int x = d, y = 0, x2 = 0, y2 = d;
    while(x <= n){
        int x_ = x, y_ = y, x2_ = x2, y2_ = y2;
        while(1){
            inside.insert({x_, y_});
            if(x_ == x2_ && y_ == y2_)break;
            x_--,y_++;
        }
        if(x != n) {
            x_ = x, y_ = y + 1, x2_ = x2 + 1, y2_ = y2;
            while (1) {
                inside.insert({x_, y_});
                if (x_ == x2_ && y_ == y2_)break;
                x_--, y_++;
            }
        }
        x++, y++;
        x2++,y2++;
    }
    int m; cin >> m;
    while(m--){
        int x, y; cin >> x >> y;
        if(inside.find({x, y}) != inside.end())cout << "YES\n";
        else cout << "NO\n";
    }
}