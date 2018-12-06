#include <bits/stdc++.h>
#define REP(i,n) for(int i=0;i<(int)n;i++)
#define REP1(i,j,n) for(int i=j;i<(int)n;i++)
#define all(x) x.begin(),x.end()
#define BUG cerr<<"BUG\n";

typedef long long ll;

using namespace std;


int main() {
    ios_base::sync_with_stdio(0); cin.tie(0);
    int n, m;
    cin >> n >> m;
    int left = m;
    for(int i = 0; i < n; i++) {
        if(i) cout << " ";
        int x;
        cin >> x;
        if(left >= x) {
            left -= x;
            if(!left)cout << "1";
            else cout << "0";
            if(!left) left = m;
        } else {
            int cnt = 1;
            x -= left;
            cnt += (x + m - 1) / m - 1;
            left = x % m;
            if(!left)cnt++;
            left = m - left;
            cout << cnt;
        }
    }
    cout << "\n";
}