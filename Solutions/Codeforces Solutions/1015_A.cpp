#include <bits/stdc++.h>

using namespace std;

typedef long long ll;

int main() {
    int n, m;
    scanf("%d %d", &n, &m);
    vector<int> pnts(m + 1);
    while(n--) {
        int l, r;
        scanf("%d %d", &l, &r);
        for(int i = l; i <= r; i++)
            pnts[i]++;
    }
    vector<int> res;
    for(int i = 1; i <= m; i++) {
        if(!pnts[i]) {
            res.push_back(i);
        }
    }
    printf("%d\n", (int)res.size());
    for(int i = 0; i < (int)res.size(); i++) {
        if(i) printf(" ");
        printf("%d", res[i]);
    }
    printf("\n");
}
