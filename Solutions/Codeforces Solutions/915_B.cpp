#include<bits/stdc++.h>
#define FOR(i,s,n) for (int i = s; i < n; i++)
#define FAST ios_base::sync_with_stdio(0); cin.tie(0);
using namespace std;

typedef long long ll;

int main() {
    FAST

    int n, pos, l, r;
    cin >> n >> pos >> l >> r;

    int pos1 = pos;
    int leftRight = 0;
    if (l != 1) {
        leftRight += abs(pos1 - l) + 1;
        pos1 = l;
    }
    if (r != n) {
        leftRight += abs(pos1 - r) + 1;
    }



    int pos2 = pos;
    int rightLeft = 0;
    if (r != n) {
        rightLeft += abs(pos2 - r) + 1;
        pos2 = r;
    }
    if (l != 1) {
        rightLeft += abs(pos2 - l) + 1;
    }

    cout << min(leftRight, rightLeft) << "\n";



    return 0;
}