#include<bits/stdc++.h>

#define FAST ios_base::sync_with_stdio(0); cin.tie(0);
#define FOR(i, s, n) for (int i = s; i < n; i++)


using namespace std;
typedef long long ll;

class CirclesCountry {
public:
    bool inside(int xc, int yc, int R, int x, int y) {
        return (xc - x) * (xc - x) + (yc - y) * (yc - y) <= R * R;
    }

    int leastBorders(vector<int> x, vector<int> y, vector<int> R, int x1, int y1, int x2, int y2) {
        int cnt = 0;
        FOR(i, 0, x.size()) {
            bool in1 = inside(x[i], y[i], R[i], x1, y1), in2 = inside(x[i], y[i], R[i], x2, y2);
            if (in1 != in2)
                cnt++;
        }
        return cnt;
    }
};

int main() {
    FAST;

}
