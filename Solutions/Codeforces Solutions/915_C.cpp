#include<bits/stdc++.h>
#define FOR(i,s,n) for (int i = s; i < n; i++)
#define FAST ios_base::sync_with_stdio(0); cin.tie(0);
using namespace std;

typedef long long ll;

int cntA[10], cntB[10];
string a, b;

string solve () {
    string res = "";
    bool isRestricted = a.length() == b.length();
    bool ok = true;
    vector<int> bad;
    FOR(i, 0, a.length()) {
        bool found = false;
        if (isRestricted) {
            for (int digit = b[i] - '0'; digit >= 0; digit--) {
                if (cntA[digit]) {
                    found = true;
                    bad.push_back(digit);
                    cntA[digit]--;
                    res += to_string(digit);
                    isRestricted = digit == b[i] - '0';
                    break;
                }
            }
        } else {
            for (int digit = 9; digit >= 0; --digit) {
                if (cntA[digit]) {
                    found = true;
                    cntA[digit]--;
                    bad.push_back(digit);
                    res += to_string(digit);
                    break;
                }
            }
        }
        ok &= found;
    }
    for (auto num : bad) cntA[num]++;
    if (ok && res[0] != '0') return res;
    return "";
}
int main() {
    FAST
    cin >> a >> b;
    int lenA = a.length(), lenB = b.length();
    FOR (i, 0, lenA)
        cntA[a[i] - '0']++;

    FOR (i, 0, lenB)
        cntB[b[i] - '0']++;

    string resAll = solve();

    FOR (last, 0, lenA) {
        bool isRestricted = lenA == lenB;
        bool ok = true;
        string res = "";
        vector<int> bad;
        FOR(i, 0, lenA) {
            bool found = false;
            if (isRestricted) {
                for (int digit = ((last <= i) ? b[i] - '0' - 1 : b[i] - '0'); digit >= 0; digit--) {
                    if (cntA[digit]) {
                        cntA[digit]--;
                        bad.push_back(digit);
                        found = true;
                        res += to_string(digit);
                        isRestricted = digit == b[i] - '0';
                        break;
                    }
                }
            } else {
                for (int digit = 9; digit >= 0; --digit) {
                    if (cntA[digit]) {
                        cntA[digit]--;
                        bad.push_back(digit);
                        found = true;
                        res += to_string(digit);
                        break;
                    }
                }
            }
            ok &= found;
        }
        for (auto num : bad) cntA[num]++;
        if (ok && res[0] != '0') resAll = max(res, resAll);
    }

    cout << resAll << "\n";
    return 0;
}