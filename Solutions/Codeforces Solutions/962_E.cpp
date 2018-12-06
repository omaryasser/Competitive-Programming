#include<bits/stdc++.h>
#include <cstdio>
#include <iostream>

#define FAST ios_base::sync_with_stdio(0); cin.tie(0);
#define FOR(i, s, n) for (int i = s; i < n; i++)

using namespace std;
typedef long long ll;

int n;

ll solve (int left, int right, vector<int> v) {
    if(v.size() == 0) return 0;
    ll mn = min(v[v.size() - 1] - left, right - v[0]);
    FOR (i, 0, v.size() - 1)
        mn = min (mn, 0ll + v[i] - left + right - v[i + 1]);
    return mn;
}

ll noG (vector<pair<int,char> > v) {
    int fR = -1e9 - 11, lR = -1e9 - 11, fB = -1e9 - 11, lB = -1e9 - 11;
    FOR (i, 0, v.size())
        if (v[i].second == 'R') {
            if (fR == -1e9 - 11) fR = v[i].first;
            lR = v[i].first;
        } else {
            if (fB == -1e9 - 11) fB = v[i].first;
            lB = v[i].first;
        }
    ll sum = 0;
    if (fR != -1e9 - 11) sum += lR - fR;
    if (fB != -1e9 - 11) sum += lB - fB;
    return sum;
}
int main() {
    FAST;


    vector<int> GPositions;
    cin >> n;
    vector<pair<int,char> > arr(n,{0, 0});
    FOR (i, 0, n) {
        cin >> arr[i].first >> arr[i].second;
        if (arr[i].second == 'P')
            GPositions.push_back(i);
    }

    if (!GPositions.size()) {cout << noG(arr) << "\n"; return 0;}
    ll sum = 0;
    FOR(i, 0, GPositions.size() - 1) {
        int cur = GPositions[i], nxt = GPositions[i + 1];
        vector<int> blues, reds;
        FOR (i, cur + 1, nxt)
            if (arr[i].second == 'R')
                reds.push_back(arr[i].first);
            else
                blues.push_back(arr[i].first);
        ll f = (arr[nxt].first - arr[cur].first) + solve(arr[cur].first, arr[nxt].first, reds) + solve(arr[cur].first, arr[nxt].first, blues);
        ll s = blues.size() && reds.size() ? 2ll * (arr[nxt].first - arr[cur].first) : f;
        sum += min(f, s);
    }

    ll mxRAfter = arr[GPositions[GPositions.size() - 1]].first;
    ll mxGAfter = arr[GPositions[GPositions.size() - 1]].first;
    FOR (i, GPositions[GPositions.size() - 1], n)
        if (arr[i].second == 'R')
            mxRAfter = max(mxRAfter, 0ll + arr[i].first);
        else if (arr[i].second == 'B')
            mxGAfter = max(mxGAfter, 0ll + arr[i].first);
    ll mnRBefore = arr[GPositions[0]].first;
    ll mnGBefore = arr[GPositions[0]].first;
    FOR (i, 0, GPositions[0])
        if (arr[i].second == 'R')
            mnRBefore = min(mnRBefore, 0ll + arr[i].first);
        else if (arr[i].second == 'B')
            mnGBefore = min(mnGBefore, 0ll + arr[i].first);
    cout << sum + mxRAfter + mxGAfter - 2 * arr[GPositions[GPositions.size() - 1]].first + 2 * arr[GPositions[0]].first - mnRBefore - mnGBefore << "\n";
}