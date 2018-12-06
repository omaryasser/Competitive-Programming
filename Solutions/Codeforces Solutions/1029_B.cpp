#include <bits/stdc++.h>

using namespace std;

int main() {
    ios_base::sync_with_stdio(0); cin.tie(0);
    int n;
    cin >> n;
    int mx = 1;
    int lst;
    cin >> lst;
    int cur = 1;
    for(int i = 0; i < n - 1; i++)
    {
        int x;
        cin >> x;
        if(x <= 2 * lst)
        {
            cur++;
            mx = max(mx, cur);
        } else
        {
            cur = 1;
        }
        lst = x;
    }
    cout << mx << "\n";
}