#include <bits/stdc++.h>

using namespace std;

typedef long long ll;

ll n, k, s;
int main() {
    scanf("%lld %lld %lld", &n, &k, &s);
    n--;
    int cur = 1;
    vector<int> res;
    while(k--) {
        if(s >= n) {
            res.push_back(cur = (cur == 1 ? n + 1 : 1));
            s -= n;
        } else {
            res.push_back(cur == 1 ? 1 + s : n + 1 - s);
            s = 0;
        }
        if(!s) {
            vector<int> res2;
            int need = k;
            res.insert(res.begin(), 1);
            for(int sz = (int)res.size(), i = sz - 1; i >= 0; i--) {
                res2.push_back(res[i]);
                int start = res[i];
                int end = i ? res[i - 1] : -1;
                while(need > 0 && end != -1 && abs(start - end) >= 2) {
                    need--;
                    res2.push_back(start = (start < end ? start + 1 : start - 1));
                }
            }
            if(need) {
                printf("NO\n");
                return 0;
            }
            printf("YES\n");
            reverse(res2.begin(), res2.end());
            for(int j = 1, sz = (int)res2.size(); j < sz; j++) {
                if(j > 1) printf(" ");
                printf("%d", res2[j]);
            }
            printf("\n");
            return 0;
        }
    }
    printf("NO\n");
}
