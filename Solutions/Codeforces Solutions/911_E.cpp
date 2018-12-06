#include<bits/stdc++.h>
#define FOR(i,s,n) for (int i = s; i < n; i++)
#define FAST ios_base::sync_with_stdio(0); cin.tie(0);
using namespace std;

typedef long long ll;

int n, k;
const int MAX = 200001;
int arr[MAX];
bool found[MAX];
int main() {
    FAST

    cin >> n >> k;
    stack<pair<int,int> > s;
    bool ok = true;
    int waiting = 1;
    FOR(i, 0, k) {
        cin >> arr[i];
        found[arr[i]] = true;
        if (s.size()) s.push({arr[i], min(arr[i], s.top().second)});
        else s.push({arr[i], arr[i]});
        ok &= s.top().first <= s.top().second;
        while(s.size() && s.top().first == waiting) s.pop(), waiting++;
    }
    if (!ok) return puts("-1");
    int idx = k;
    while (s.size()) {
        int mx = s.top().second;
        found[mx - 1] = true;
        s.push({mx - 1, mx - 1});
        arr[idx++] = mx - 1;
        while(s.size() && s.top().first == waiting) s.pop(), waiting++;
    }
    for (int i = n; i >= 1; i--)
        if (!found[i])
            arr[idx++] = i;

    FOR (i, 0, n) {
        if (i > 0) cout << " ";
        cout << arr[i];
    }
    cout << "\n";
    return 0;
}