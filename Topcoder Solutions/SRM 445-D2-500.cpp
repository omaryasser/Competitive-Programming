#include<bits/stdc++.h>

#define FAST ios_base::sync_with_stdio(0); cin.tie(0);
#define FOR(i, s, n) for (int i = s; i < n; i++)


using namespace std;
typedef long long ll;

class TheNewHouseDivTwo {
public:
    int count(vector<int> x, vector<int> y) {
        int cnt = 0;
        FOR(i, -100, 100) {
            FOR(j, -100, 100) {
                bool up = 0, down = 0, left = 0, right = 0;
                FOR(k, 0, x.size()) {
                    up |= x[k] == i && y[k] < j;
                    down |= x[k] == i && y[k] > j;
                    left |= y[k] == j && x[k] < i;
                    right |= y[k] == j && x[k] > i;
                }
                if (up && down && left && right)
                    cerr << i << " " << j << "\n";
                cnt += (up && down && left && right);
            }
        }
        return cnt;
    }
};

int main() {
    FAST;

    int f[] = {4, 5, 0, 8, -3, 5, 4, 7};
    int s[] = {9, -4, 2, 1, 1, 11, 0, 2};
    cout << TheNewHouseDivTwo().count(vector<int>(f, f + sizeof(f) / sizeof(f[0])), vector<int>(s, s + sizeof(s) /
                                                                                                       sizeof(s[0])))
         << "\n";
}
