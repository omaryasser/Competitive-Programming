#include<bits/stdc++.h>
#include <cstdio>
#include <iostream>
#define FAST ios_base::sync_with_stdio(0); cin.tie(0);

using namespace std;
typedef long long ll;


int main() {
    FAST;

    int v1, v2, v3, vm;
    cin >> v1 >> v2 >> v3 >> vm;

    int a1, a2, a3;
    if (max(v3, vm) <= min(vm * 2, v3 * 2))
        a3 = max(v3, vm);
    else return puts("-1");

    int min2 = max(a3 + 1, max(v3, max(v2, vm * 2 + 1)));
    int max2 = v2 * 2;

    if (min2 <= max2)
        a2 = min2;
    else return puts("-1");

    int min1 = max(a2 + 1, max(v1, max(v2, vm * 2 + 1)));
    int max1 = v1 * 2;
    if (min1 <= max1)
        a1 = min1;
    else return puts("-1");

    cout << a1 << " " << a2 << " " << a3 << "\n";
}