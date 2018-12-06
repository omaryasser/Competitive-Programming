#include<bits/stdc++.h>
#include <cstdio>
#include <iostream>

#define FAST ios_base::sync_with_stdio(0); cin.tie(0);
#define FOR(i, n) for (int i = 0; i < n; i++)

using namespace std;
typedef long long ll;

int map_char (char c) {
    return c <= '9' ? c - '0' : c <= 'Z' ? 10 + c - 'A' : 10 + 26 + c - 'a';
}
char map_char2 (int n) {
    if (n < 10) return (char)(n + '0');
    if (n < 10 + 26) return (char)(n - 10 + 'A');
    return (char)(n - 10 - 26 + 'a');
}

int n, m;
string s;
const int CHARS = 10 + 2 * 26, MAX = 2 * 100002;
vector<set<int> > positions(CHARS, set<int>());

int FT[CHARS][MAX];
int ALL[MAX];
void update (int c, int idx, int val) {
    while (idx < MAX) {
        FT[c][idx] += val;
        ALL[idx] += val;
        idx += idx & (-idx);
    }
}
int query (int c, int idx) {
    int sum = 0;
    while(idx > 0) {
        sum += FT[c][idx];
        idx -= idx & (-idx);
    }
    return sum;
}

int queryALL (int idx) {
    int sum = 0;
    while(idx > 0) {
        sum += ALL[idx];
        idx -= idx & -idx;
    }
    return sum;
}
int original (int wanted) {
    int lo = 1, hi = MAX - 1, best = MAX;
    for (int cnt = 0; cnt <= 18; cnt++) {
        int mid = lo + ((hi - lo) >> 1);
        int sumHere = queryALL(mid);
        if (sumHere < wanted) {
            lo = min(hi, mid + 1);
        } else {
            best = min(best, mid);
            hi = max(lo, mid - 1);
        }
    }
    return best;
}

int main() {
    FAST;
    cin >> n >> m >> s;
    FOR(i, n) positions[map_char(s[i])].insert(1 + i), update(map_char(s[i]), i + 1, 1);
    while(m--) {
        int l, r; char c;
        cin >> l >> r >> c;
        l = original(l), r = original(r);
        vector<int> removeNow;
        for (set<int> :: iterator it = positions[map_char(c)].lower_bound(l); it != positions[map_char(c)].end() && *it <= r; it++) {
            removeNow.push_back(*it);
        }
        for (auto remIdx : removeNow) {
            positions[map_char(c)].erase(remIdx), update(map_char(c), remIdx, -1);
        }
    }

    vector<int> was(CHARS, 0);
    string s = "";
    FOR(i, n) {
        FOR(j, CHARS)
            if (query(j, i + 1) != was[j]) {
                s += string(1, map_char2(j));
                was[j]++;
                break;
            }
    }
    cout << s << "\n";
}