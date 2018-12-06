#include <bits/stdc++.h>

using namespace std;

typedef long long ll;

int n;
char a[(int)1e6 + 2], b[(int)1e6 + 2];
int main () {
    scanf("%d %s %s", &n, a, b);
    int res = 0;
    for (int i = 0; i < n; i++) {
        if(a[i] != b[i]) {
            if (i + 1 < n && a[i + 1] != b[i + 1] && a[i + 1] == b[i]) {
                swap(a[i], a[i + 1]);
            }
            res++;
        }
    }
    cout << res << "\n";
}